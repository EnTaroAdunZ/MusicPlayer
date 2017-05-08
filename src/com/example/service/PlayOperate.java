package com.example.service;

import java.io.File;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import java.util.UUID;

import com.example.Global.GlobalVariable;
import com.example.Global.ObserverManage;
import com.example.Global.PlayState;
import com.example.gui.MusicUtils;
import com.tulskiy.musique.audio.AudioFileReader;
import com.tulskiy.musique.audio.player.Player;
import com.tulskiy.musique.audio.player.PlayerEvent;
import com.tulskiy.musique.audio.player.PlayerListener;
import com.tulskiy.musique.audio.player.PlayerEvent.PlayerEventCode;
import com.tulskiy.musique.model.Track;
import com.tulskiy.musique.system.TrackIO;
import com.tulskiy.musique.util.AudioMath;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.concurrent.Task;

/**
 * @author ZTF
 * @date 2017年4月9日 下午6:51:29
 * @version 1.0
 * @Description: 播放服务，单例以及观察者
 */
public class PlayOperate implements Observer {

	private Player mediaPlayer;
	private static PlayOperate playOperate;
	private long baseCurrentMillis = 0;
	private UUID overThread;
	public static DoubleProperty cur_p = new SimpleDoubleProperty(0);
	private boolean isHasSeekTo;
	private double last_p;
	private String length_temp=null;
	private int i=0;
	
	public UUID getOverThread() {
		return overThread;
	}

	public void setOverThread() {
		this.overThread = UUID.randomUUID();
	}

	public Player getMediaPlayer() {
		return mediaPlayer;
	}
	
	

	public static boolean hasMedia() {
		if (PlayOperate.getPlayOperate().getMediaPlayer() == null) {
			return false;
		} else {
			return true;
		}
	}

	public PlayOperate() {
		initPlayer();
		ObserverManage.getObserver().addObserver(this);
	}

	public static PlayOperate getPlayOperate() {
		if (playOperate == null) {
			playOperate = new PlayOperate();
		}
		// System.out.println("初始化成功");
		return playOperate;
	}

	@Override
	public void update(Observable o, Object arg) {
		// System.out.println("收到了订阅");
		if (arg instanceof PlayState) {
			if (PlayState.getPlayState().getCurrent_op() == GlobalVariable.ALTERVOLUME) {
				// System.out.println("调节音量中");
				alterVolume(PlayState.getPlayState().getCurrent_volume());
				PlayState.getPlayState().setCurrent_op(GlobalVariable.HASDONOTHING);
			} else if (PlayState.getPlayState().getCurrent_op() == GlobalVariable.SEEKTOMUSIC) {
				isHasSeekTo=true;
				seekTo();
				PlayState.getPlayState().setCurrent_op(GlobalVariable.HASDONOTHING);
			}
			else if(PlayState.getPlayState().getCurrent_op() == GlobalVariable.SEEKTOMUSICWHENPAUSE){
				isHasSeekTo=true;
				seekToWhenPause();
				PlayState.getPlayState().setCurrent_op(GlobalVariable.HASDONOTHING);
			}
			if (PlayState.getPlayState().getState() == GlobalVariable.SERVICE_PLAYMUSIC) {
				// System.out.println("开始播放");
				
				pauseMusic();
				
				playInfoMusic(PlayState.getPlayState().getCurrent_song());
				PlayState.getPlayState().setCurrent_state(GlobalVariable.PLAYINGMUSIC);
			} else if (PlayState.getPlayState().getState() == GlobalVariable.SERVICE_PAUSEMUSIC) {
				// System.out.println("暂停播放");
				pauseMusic();
				PlayState.getPlayState().setCurrent_state(GlobalVariable.PAUSINGMUSIC);
			} else if (PlayState.getPlayState().getState() == GlobalVariable.NEXTMUSIC) {
				PlayState.getPlayState().setBeginPlay(false);
				stopMusic();
				alterNextMusic();
				System.out.println(PlayState.getPlayState().getCurrent_song().getPath() + ":"
						+ PlayState.getPlayState().getCurrent_index());
				PlayState.getPlayState().setCurrent_state(GlobalVariable.PLAYINGMUSIC);
				playInfoMusic(PlayState.getPlayState().getCurrent_song());
			} else if (PlayState.getPlayState().getState() == GlobalVariable.PREMUSIC) {
				PlayState.getPlayState().setBeginPlay(false);
				stopMusic();
				alterPreMusic();
				// System.out.println(PlayState.getPlayState().getCurrent_song().getPath()+":"+PlayState.getPlayState().getCurrent_index());
				PlayState.getPlayState().setCurrent_state(GlobalVariable.PLAYINGMUSIC);
				playInfoMusic(PlayState.getPlayState().getCurrent_song());
			}

		}
	}

	private void seekToWhenPause() {
		try {
			if (mediaPlayer == null) {;
				double currPro = PlayState.getPlayState().getProgress();
//				System.out.println("暂停快进中:"+currPro);
				String[] split = length_temp.split(":");
				long totalLen = Long.valueOf(split[0]) * 60 * 1000 + Long.valueOf(split[1]) * 1000;
				double currentMS = currPro * totalLen / 100;
				long progress = Math.round(currentMS);
				baseCurrentMillis = progress;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private void alterPreMusic() {
		int playIndex = PlayState.getPlayState().getCurrent_index();
		int size = PlayState.getPlayState().getCurrent_songMenu().size();
		if (PlayState.getPlayState().getCurrent_mode() == GlobalVariable.PlAYMODE_RAMDOMPLAY) {
			int nextIndex = playIndex;
			if (size > 1) {
				while (nextIndex == playIndex) {
					Random random = new Random();
					nextIndex = random.nextInt(size);
				}
			}
			PlayState.getPlayState().setCurrent_song(PlayState.getPlayState().getCurrent_songMenu().get(nextIndex));
			PlayState.getPlayState().setCurrent_index(nextIndex);
			System.out.println("改变后的index" + nextIndex);
		} else {
			int nextIndex = playIndex;
			if (size > 1) {
				--nextIndex;
				if (nextIndex < 0) {
					nextIndex = size - 1;
				}
			}
			PlayState.getPlayState().setCurrent_index(nextIndex);
			PlayState.getPlayState().setCurrent_song(PlayState.getPlayState().getCurrent_songMenu().get(nextIndex));
			System.out.println("改变后的index" + nextIndex);
		}
	}

	private void seekTo() {
		try {
			if (mediaPlayer != null) {
				String length=null;
				if(PlayState.getPlayState().getState() == GlobalVariable.PLAYINGMUSIC){
					mediaPlayer.pause();
					length = mediaPlayer.getTrack().getTrackData().getLength();
				}
				double currPro = PlayState.getPlayState().getProgress();
//				System.out.println("快进中:"+currPro);
				
				String[] split = length.split(":");
				long totalLen = Long.valueOf(split[0]) * 60 * 1000 + Long.valueOf(split[1]) * 1000;
				double currentMS = currPro * totalLen / 100;
				long progress = Math.round(currentMS);
				baseCurrentMillis = progress;
				mediaPlayer = null;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		if(PlayState.getPlayState().getState() == GlobalVariable.PLAYINGMUSIC)
		playInfoMusic(PlayState.getPlayState().getCurrent_song());
	}

	/**
	 * 播放歌曲
	 */
	private void playInfoMusic(MusicUtils song) {
		if (song == null) {
			// 判断是否为空
			return;
		}
		File songFile = new File(song.getPath());

		if (!songFile.exists()) {
			alterNextMusic();
			throw new RuntimeException("文件不存在!");
		}

		try {
			if (mediaPlayer == null) {
				mediaPlayer = new Player();
				PlayState.getPlayState().setBeginPlay(false);
				mediaPlayer.addListener(new PlayerListener() {
					@Override
					public void onEvent(PlayerEvent e) {
						if (PlayState.getPlayState().isBeginPlay()){
							if (e.getEventCode() == PlayerEventCode.STOPPED) {
								int playIndex = PlayState.getPlayState().getCurrent_index();
//								System.out.println("现在的播放序列号" + playIndex);
								stopMusic();
								System.out.println("停止成功,开始播放下一首");
								nextMusic();
//								System.out.println("下一首" + PlayState.getPlayState().getCurrent_index());
							}
						}

					}
				});
				initVolume();
				AudioFileReader audioFileReader = TrackIO.getAudioFileReader(songFile.getName());
				Track track = audioFileReader.read(songFile);
				
				mediaPlayer.open(track);
				long seekBytes = AudioMath.millisToSamples(baseCurrentMillis, track.getTrackData().getSampleRate());
				mediaPlayer.seek(seekBytes);
				PlayState.getPlayState().setBeginPlay(true);
				
			}
			PlayOperate.getPlayOperate().setOverThread();
			i++;
			new Thread(
				new Task<Double>() {
					{
						updateValue(last_p);
						cur_p.unbind();
						cur_p.bind(valueProperty());
					}
					@Override
					protected Double call() throws Exception {
						UUID id=PlayOperate.getPlayOperate().getOverThread();
//						System.out.println(id+",开了一个新线程");
						while (true) {
							if(isCancelled()){
								break;
							}
							// 这里完成进度条的更新
							if (id==PlayOperate.getPlayOperate().getOverThread()&&mediaPlayer != null && mediaPlayer.getTrack() != null&&mediaPlayer.isPlaying()) {
								String length = mediaPlayer.getTrack().getTrackData().getLength();
								double currentMS = mediaPlayer.getCurrentMillis();
								long progress = Math.round(currentMS);
								String[] split = length.split(":");
								long totalLen = Long.valueOf(split[0]) * 60 * 1000 + Long.valueOf(split[1]) * 1000;
								double cur_progress = (double) progress / (double) totalLen;
								if (!isHasSeekTo) {
//									System.out.println(i+"刷新进度条："+(int)(cur_progress*100));
									updateValue(cur_progress);
									last_p=cur_progress;
								}
//								else{
////									System.out.println(i+"快进中");
//								}
							}
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							if(id!=PlayOperate.getPlayOperate().getOverThread()){
								this.cancel();
								isHasSeekTo=false;
							}
						}
						return null;
					}
				}	
			).start();

		} catch (Exception e) {
			PlayState.getPlayState().setState_NEXTMUSIC();
			throw new RuntimeException("无法播放此文件！:" + e.getMessage());
		}

	}

	// 初始化播放器
	public void initPlayer() {

		try {
			if (mediaPlayer != null) {
				mediaPlayer.pause();
				mediaPlayer = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 初始化声音音量
	 */
	private void initVolume() {
		if (mediaPlayer != null) {
			mediaPlayer.getAudioOutput().setVolume((float) (PlayState.getPlayState().getCurrent_volume() * 1.0 / 100));
		}
	}

	private void alterVolume(int volume) {
		if (mediaPlayer != null) {
			mediaPlayer.getAudioOutput().setVolume((float) (volume * 1.0 / 100));
		}
	}

	private void pauseMusic() {

		try {
			if (mediaPlayer != null) {
				length_temp=mediaPlayer.getTrack().getTrackData().getLength();
				mediaPlayer.pause();
				double currentMS = mediaPlayer.getCurrentMillis();
				long progress = Math.round(currentMS);
				baseCurrentMillis = progress;
				PlayState.getPlayState().setCurProgress(currentMS);
				System.out.println(baseCurrentMillis);
				mediaPlayer = null;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private void stopMusic() {
		PlayState.getPlayState().setBeginPlay(false);
		try {
			if (mediaPlayer != null) {
				mediaPlayer.pause();
				mediaPlayer = null;

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		PlayState.getPlayState().setCurProgress(0);
		PlayState.getPlayState().setProgress_long(0);
		baseCurrentMillis = 0;
	}

	private void nextMusic() {
		int playIndex = PlayState.getPlayState().getCurrent_index();
		int size = PlayState.getPlayState().getCurrent_songMenu().size();
//		System.out.println("现在的播放序列号" + playIndex);
//		System.out.println("size" + size);
		switch (PlayState.getPlayState().getCurrent_mode()) {
		case 0:
			// 单曲循环

			if (playIndex == -1 || playIndex >= PlayState.getPlayState().getCurrent_songMenu().size()) {
				return;
			}
			playInfoMusic(PlayState.getPlayState().getCurrent_song());
			System.out.println("单曲循环" + playIndex);
			break;
		case 1:
			// 列表循环
			if (playIndex == -1) {
				return;
			}
			++playIndex;
			if (playIndex >= size) {
				PlayState.getPlayState().setCurrent_index(0);
			} else {
				PlayState.getPlayState().setCurrent_index(playIndex);
			}
			PlayState.getPlayState().setCurrent_song(PlayState.getPlayState().getCurrent_songMenu().get(PlayState.getPlayState().getCurrent_index()));
			playInfoMusic(PlayState.getPlayState().getCurrent_song());
			System.out.println("列表循环" + playIndex);
			break;
		case 2:
			// 循环播放
			if (playIndex == -1 || playIndex >= PlayState.getPlayState().getCurrent_songMenu().size()) {
				return;
			}
			playIndex++;
			if (playIndex < size) {
				PlayState.getPlayState().setCurrent_index(playIndex);
				PlayState.getPlayState().setCurrent_song(PlayState.getPlayState().getCurrent_songMenu().get(playIndex));
				playInfoMusic(PlayState.getPlayState().getCurrent_song());
			}
			System.out.println("循环循环" + playIndex);
			break;
		case 3:
			// 随机播放
			int nextIndex = playIndex;
			if (size > 1)
				while (nextIndex == playIndex) {
					Random random = new Random();
					nextIndex = random.nextInt(size);
				}
			PlayState.getPlayState().setCurrent_index(nextIndex);
			PlayState.getPlayState().setCurrent_song(PlayState.getPlayState().getCurrent_songMenu().get(nextIndex));
			playInfoMusic(PlayState.getPlayState().getCurrent_song());
			System.out.println("随机播放" + nextIndex);
			break;
		default:
			break;
		}
	}

	private void alterNextMusic() {
		int playIndex = PlayState.getPlayState().getCurrent_index();
		System.out.println(playIndex);
		int size = PlayState.getPlayState().getCurrent_songMenu().size();
		if (PlayState.getPlayState().getCurrent_mode() == GlobalVariable.PlAYMODE_RAMDOMPLAY) {
			int nextIndex = playIndex;
			if (size > 1) {
				while (nextIndex == playIndex) {
					Random random = new Random();
					nextIndex = random.nextInt(size);
				}
			}
			PlayState.getPlayState().setCurrent_song(PlayState.getPlayState().getCurrent_songMenu().get(nextIndex));
			PlayState.getPlayState().setCurrent_index(nextIndex);
			// System.out.println("改变后的index"+nextIndex);
		} else {
			int nextIndex = playIndex;
			// System.out.println(playIndex);
			if (size > 1) {
				++nextIndex;
				if (nextIndex >= size) {
					nextIndex = 0;
				}
			}
			PlayState.getPlayState().setCurrent_index(nextIndex);
			PlayState.getPlayState().setCurrent_song(PlayState.getPlayState().getCurrent_songMenu().get(nextIndex));
			// System.out.println("改变后的index"+nextIndex);
		}

	}
	
	public void setBaseCurrentMillis(long baseCurrentMillis) {
		this.baseCurrentMillis = baseCurrentMillis;
	}

}
