package com.example.Global;

import com.example.controller.Controller;
import com.example.gui.MusicUtils;

/** 
* @author ZTF  
* @date 2017年3月19日 下午12:41:58 
* @version 1.0 
* @Description:   TODO(用一句话描述该文件做什么)
*/
public class GlobalVariable {
	public static String currentMenu = "";
	public static String currentSearch = "";
	public static MusicUtils currentSong;
	public static int PageType = 0;
	public static Controller.ContentController currentCtrl = null;
	
	public static int i=10000;
	
	public static final int PlAYMODE_SINGLELOOP= 0;//单曲循环
	public static final int PlAYMODE_LISTLOOP = 1;//列表循环	
	public static final int PlAYMODE_SEQUENCEPLAY = 2;//循环播放
	public static final int PlAYMODE_RAMDOMPLAY = 3;//随机播放
	
	//与播放线程有关的操作
	public static final int PREMUSIC = (i++); // 上一首歌曲操作
	public static final int PLAYMUSIC = (i++); // 播放歌曲操作
	public static final int PLAYINFOMUSIC = (i++); // 反馈播放按钮
	public static final int PAUSINGMUSIC = (i++); //  反馈暂停按钮
	public static final int PAUSEMUSIC = (i++); // 暂停歌曲操作
	public static final int STOPMUSIC = (i++); // 结束歌曲操作
	public static final int NEXTMUSIC = (i++); // 下一首歌曲操作
	public static final int EXITMUSIC = (i++); // 退出播放器
	
	
	//无关操作
	public static final int HASDONOTHING = (i++); // 退出播放器
	public static final int ALTERVOLUME = (i++); // 修改音乐播放器
	public static final int SEEKTOMUSIC = (i++); // 歌曲快进操作
	public static final int SEEKTOMUSICWHENPAUSE = (i++); // 歌曲快进操作
}
 