package com.example.service;

import java.io.File;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import com.example.Global.GlobalVariable;
import com.example.Global.ObserverManage;
import com.example.Global.PlayState;
import com.example.gui.MusicUtils;
import com.tulskiy.musique.audio.AudioFileReader;
import com.tulskiy.musique.audio.player.Player;
import com.tulskiy.musique.audio.player.PlayerEvent;
import com.tulskiy.musique.audio.player.PlayerListener;
import com.tulskiy.musique.model.Track;
import com.tulskiy.musique.system.TrackIO;
import com.tulskiy.tta.Main;



/** 
* @author ZTF  
* @date 2017年4月9日 下午6:51:29 
* @version 1.0 
* @Description:   播放服务，单例以及观察者
*/
public class PlayOperate implements Observer{


	private Player mediaPlayer;
	private static PlayOperate playOperate;
	
	
	

	public PlayOperate() {
		initPlayer();
		
		playInfoMusic(PlayState.getPlayState().getCurrent_song());
		ObserverManage.getObserver().addObserver(this);
	}
	
	public static PlayOperate getPlayOperate(){
		if(playOperate==null){
			playOperate=new PlayOperate();
			
		}
		System.out.println("初始化成功");
		return playOperate;
	}
	
	@Override
	public void update(Observable o, Object arg) {
		System.out.println("收到了订阅");
	      if(arg instanceof PlayState){
	    	  if(PlayState.getPlayState().getState()==GlobalVariable.PLAYINFOMUSIC){
	    		  
	    	  }
	    	  else if(PlayState.getPlayState().getState()==GlobalVariable.PAUSEMUSIC){
	    		  
	    	  }
	      }
	}
	
	/**
	 * 播放歌曲
	 * 
	 * @param songInfo
	 */
	private void playInfoMusic(MusicUtils song){
		if(song==null){
			//判断是否为空
			return;
		}
		System.out.println(song.getPath());
		File songFile = new File(song.getPath());
		
		if (!songFile.exists()) {
			throw new RuntimeException("文件不存在!");
			// 下一首
		}
		
		
		if(mediaPlayer==null){
			mediaPlayer=new Player();
			mediaPlayer.addListener(new PlayerListener() {
				
				@Override
				public void onEvent(PlayerEvent e) {
					//监听下一首或者停止或者快进
					
				}
			});
			System.out.println(songFile.getName());
			AudioFileReader audioFileReader = TrackIO
					.getAudioFileReader(songFile.getName());
			Track track = audioFileReader.read(songFile);
			mediaPlayer.open(track);
		}
	}
	
	//初始化播放器
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
	protected void initVolume() {
		if (mediaPlayer != null) {
			mediaPlayer.getAudioOutput().setVolume(
					(float) (50 * 1.0 / 100));
		}
	}
	
//	public static void main(String[] args) {
//		MusicUtils musicUtils=new MusicUtils();
//		musicUtils.setPath("D:\\的\\南征北战 - 亮晶晶.flac");
//		playOperate=new PlayOperate();
//		playOperate.playInfoMusic(musicUtils);
//	}
//	
}
 