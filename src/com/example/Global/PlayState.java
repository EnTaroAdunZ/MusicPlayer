package com.example.Global;

import java.util.List;
import java.util.Observable;

import com.example.entity.Song;
import com.example.entity.SongMenu;
import com.example.gui.MusicUtils;
import com.example.service.SongMenuOperate;

/** 
* @author ZTF  
* @date 2017年4月10日 下午10:30:11 
* @version 1.0 
* @Description:   播放状态
*/
public class PlayState{
	//只有播放状态是不可以设置的
	private  static PlayState playState;
	private  List<MusicUtils> current_songMenu;//当前选择的歌单
	private  MusicUtils current_song;//当前的歌曲
	private  int current_state;//当前播放状态
	private  int current_mode;//当前播放模式
	private int current_volume;//当前音量
	private double progress = 0;// 当前播放进度

	
	
	public List<MusicUtils> getCurrent_songMenu() {
		return current_songMenu;
	}


	public void setCurrent_songMenu(List<MusicUtils> current_songMenu) {
		this.current_songMenu = current_songMenu;
	}


	public void setCurrent_song(MusicUtils current_song) {
		this.current_song = current_song;
	}


	public void setCurrent_state(int current_state) {
		this.current_state = current_state;
	}


	public void setCurrent_mode(int current_mode) {
		this.current_mode = current_mode;
	}


	public void setCurrent_volume(int current_volume) {
		this.current_volume = current_volume;
	}


	public double getProgress() {
		return progress;
	}


	public void setProgress(double progress) {
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


	public static PlayState getPlayState(){
		if(playState==null){
			playState=new PlayState();
			//初始化
			playState.current_state=GlobalVariable.PAUSEMUSIC;
			playState.current_mode=GlobalVariable.PlAYMODE_LISTLOOP;
			
			//测试用初始化
//			playState.current_songMenu=SongMenuOperate.getSongsByMenuName("我的最爱");
//			playState.current_song=playState.current_songMenu.get(0);
			
		}
		return playState.playState;
	}
	

	//返回播放状态
	public int getState() {
		return playState.current_state;
	}

	//设置暂停
	public  void setState_PAUSEMUSIC() {
		playState.current_state =GlobalVariable.PAUSEMUSIC;
		System.out.println("設置暫停");
ObserverManage.getObserver().setMessage(playState);
	}
	
	//设置播放
	public  void setState_PLAYMUSIC() {
		playState.current_state =GlobalVariable.PLAYMUSIC;
		System.out.println("設置播放");
		ObserverManage.getObserver().setMessage(playState);
	}
	
	//设置上一首
	public  void setState_PREMUSIC() {
		playState.current_state =GlobalVariable.PREMUSIC;
	}
	
	//设置下一首
	public  void setState_NEXTMUSIC() {
		playState.current_state =GlobalVariable.NEXTMUSIC;
	}
	
	//设置列表循环
	public  void setPlAYMODE_LISTLOOP() {
		playState.current_mode =GlobalVariable.PlAYMODE_LISTLOOP;
	}
	
	//设置单曲循环
	public  void setPlAYMODE_SINGLELOOP() {
		playState.current_mode =GlobalVariable.PlAYMODE_SINGLELOOP;
	}
	
	//设置循环播放
	public  void setPlAYMODE_SEQUENCEPLAY() {
		playState.current_mode =GlobalVariable.PlAYMODE_SEQUENCEPLAY;
	}
	
	//设置循环播放
	public  void setPlAYMODE_RAMDOMPLAY() {
		playState.current_mode =GlobalVariable.PlAYMODE_RAMDOMPLAY;
	}
	//退出播放器
	public  void setEXITMUSIC() {
		System.out.println("播放器已经退出！");
		playState.current_mode =GlobalVariable.EXITMUSIC;
	}
	
}
 