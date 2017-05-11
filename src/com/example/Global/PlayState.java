package com.example.Global;

import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URI;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;

import com.example.entity.Song;
import com.example.entity.SongMenu;
import com.example.gui.MusicUtils;
import com.example.service.PlayOperate;
import com.example.service.SongMenuOperate;
import com.example.util.TagInfoUtil;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;

/**
 * @author ZTF
 * @date 2017年4月10日 下午10:30:11
 * @version 1.0
 * @Description: 播放状态
 */
public class PlayState {
	// 只有播放状态是不可以设置的
	private static PlayState playState;
	private List<MusicUtils> current_songMenu;// 当前选择的歌单
	private MusicUtils current_song;// 当前的歌曲
	private int current_state;// 当前播放状态
	private int current_mode;// 当前播放模式
	private int current_volume;// 当前音量
	private double progress = 0;// 当前播放进度：百分比、用于响应设置
	private long progress_long=0;// 当前播放进度：毫秒
	private int current_op;
	private int current_index;//当前索引号
	private boolean isBeginPlay;
	private Image current_image;

	public Image getCurrent_image() {
		return current_image;
	}

	public boolean isBeginPlay() {
		return isBeginPlay;
	}

	public void setBeginPlay(boolean isBeginPlay) {
		this.isBeginPlay = isBeginPlay;
	}

	public int getCurrent_index() {
		return current_index;
	}

	public void setCurrent_index(int current_index) {
		
		this.current_index = current_index;
	}

	public long getProgress_long() {
		return progress_long;
	}

	public void setProgress_long(long progress_long) {
		this.progress_long = progress_long;
	}


	public int getCurrent_op() {
		return current_op;
	}

	public void setCurrent_op(int current_op) {
		this.current_op = current_op;
	}

	public List<MusicUtils> getCurrent_songMenu() {
		return current_songMenu;
	}

	public void setCurrent_songMenu(List<MusicUtils> current_songMenu) {
		this.current_songMenu = current_songMenu;
	}

	public void setCurrent_song(MusicUtils current_song) {
		PlayOperate.getPlayOperate().setBaseCurrentMillis(0);
		 String path = current_song.getPath();
		if(path.endsWith(".flac")){
			try {
				TagInfoUtil.writePhoto(TagInfoUtil.getFlacPicture(path), path);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		else if(path.endsWith(".mp3")){
			try {
				TagInfoUtil.writePhoto(TagInfoUtil.getMp3Picture(path), path);
			} catch (Exception e) {
				// TODO: handle exception
			}
 		}
		File file=new File("E://MusicPlay//current_image.jpg");
		this.current_image=new Image(file.toURI().toString());
		this.current_song = current_song;
	}

	public void setCurrent_state(int current_state) {
		this.current_state = current_state;
	}

	public void setCurrent_mode(int current_mode) {
		this.current_mode = current_mode;
	}

	public void setCurrent_volume(int current_volume) {
		setCurrent_op(GlobalVariable.ALTERVOLUME);
		this.current_volume = current_volume;
		if(PlayOperate.hasMedia()){
//			System.out.println("通知改音量");
			ObserverManage.getObserver().setMessage(playState);
		}
		
	}

	public double getProgress() {
		return progress;
	}

	public void setProgress(double progress) {
		
		this.progress = progress;
		if(PlayOperate.hasMedia()){
			setCurrent_op(GlobalVariable.SEEKTOMUSIC);
			ObserverManage.getObserver().setMessage(playState);
		}
		else{
			setCurrent_op(GlobalVariable.SEEKTOMUSICWHENPAUSE);
			ObserverManage.getObserver().setMessage(playState);
		}
	}
	
	public void setCurProgress(double progress){
		this.progress = progress;
	}
	
	public MusicUtils getCurrent_song() {
		return current_song;
	}

	public int getCurrent_state() {
		return current_state;
	}

	public int getCurrent_mode() {
		return current_mode;
	}

	public int getCurrent_volume() {
		return current_volume;
	}

	public static PlayState getPlayState() {
		if (playState == null) {
			playState = new PlayState();
			// 初始化
			playState.current_state = GlobalVariable.PAUSINGMUSIC;
			playState.current_mode = GlobalVariable.PlAYMODE_LISTLOOP;
			playState.current_op=GlobalVariable.HASDONOTHING;
			playState.current_volume=0;
			
			// 测试用初始化
			// playState.current_songMenu=SongMenuOperate.getSongsByMenuName("我的最爱");
			// playState.current_song=playState.current_songMenu.get(0);

		}
		return playState.playState;
	}

	// 返回播放状态
	public int getState() {
		return playState.current_state;
	}

	// 在播放与暂停切换
	public void setState_PAUSEMUSIC() {
		playState.current_state = GlobalVariable.SERVICE_PAUSEMUSIC;
		System.out.println("設置暫停");
		ObserverManage.getObserver().setMessage(playState);
	}

	// 从某首歌开始播放
	public void setState_PLAYMUSIC() throws RuntimeException{
		playState.current_state = GlobalVariable.SERVICE_PLAYMUSIC;
		int indexOf = playState.current_songMenu.indexOf(playState.current_song);
		playState.setCurrent_index(indexOf);
		
//		System.out.println(indexOf);
//		Iterator<MusicUtils> iterator = playState.getCurrent_songMenu().iterator();
//		int i=0;
//		while(iterator.hasNext()){
//			System.out.println(iterator.next().getPath()+":"+i++);
//			
//		}
//		System.out.println(current_song.getPath());
//		System.out.println("設置播放");
		ObserverManage.getObserver().setMessage(playState);
	}

	// 设置上一首
	public void setState_PREMUSIC() {
		System.out.println("设置上一首");
		setCurProgress(0);
		setProgress_long(0);
		
		playState.current_state = GlobalVariable.PREMUSIC;
		ObserverManage.getObserver().setMessage(playState);
	}

	// 设置下一首
	public void setState_NEXTMUSIC() {
		System.out.println("设置下一首");
		setCurProgress(0);
		setProgress_long(0);
		playState.current_state = GlobalVariable.NEXTMUSIC;
		
//		int indexOf = playState.current_songMenu.indexOf(playState.current_song);
//		System.out.println(indexOf);
//		Iterator<MusicUtils> iterator = playState.getCurrent_songMenu().iterator();
//		int i=0;
//		while(iterator.hasNext()){
//			System.out.println(iterator.next().getPath()+":"+i++);
//			
//		}
		ObserverManage.getObserver().setMessage(playState);
	}

	// 设置列表循环
	public void setPlAYMODE_LISTLOOP() {
		System.out.println("列表循环");
		setCurProgress(0);
		setProgress_long(0);
		playState.current_mode = GlobalVariable.PlAYMODE_LISTLOOP;
	}

	// 设置单曲循环
	public void setPlAYMODE_SINGLELOOP() {
		System.out.println("单曲循环");
		playState.current_mode = GlobalVariable.PlAYMODE_SINGLELOOP;
	}

	// 设置循环播放
	public void setPlAYMODE_SEQUENCEPLAY() {
		System.out.println("循环循环");
		playState.current_mode = GlobalVariable.PlAYMODE_SEQUENCEPLAY;
	}

	// 设置随机播放
	public void setPlAYMODE_RAMDOMPLAY() {
		System.out.println("随机循环");
		playState.current_mode = GlobalVariable.PlAYMODE_RAMDOMPLAY;
	}

	// 退出播放器
	public void setEXITMUSIC() {
		System.out.println("播放器已经退出！");
		playState.current_mode = GlobalVariable.EXITMUSIC;
	}

}
