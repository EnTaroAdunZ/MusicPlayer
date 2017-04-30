package com.example.service;

import java.util.Observable;
import java.util.Observer;

import com.example.Global.ObserverManage;

/** 
* @author ZTF  
* @date 2017年4月30日 上午1:24:46 
* @version 1.0 
* @Description:   TODO(用一句话描述该文件做什么)
*/
public class MediaManage implements Observer{
	private static MediaManage _mediaManage;
	public static final int PLAYING = 0;
	/**
	 * 暂停
	 */
	public static final int PAUSE = 1;
	/**
	 * 快进
	 */
	public static final int SEEKTO = 2;
	/**
	 * 播放歌曲状态
	 */
	private int playStatus = PAUSE;
	
	public static MediaManage getMediaManage() {
		if (_mediaManage == null) {
			_mediaManage = new MediaManage();
		}
		return _mediaManage;
	}
	
	public void setPlayStatus(int playStatus) {
		this.playStatus = playStatus;
	}

	private void init() {
	ObserverManage.getObserver().addObserver(this);
	}


	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

}
 