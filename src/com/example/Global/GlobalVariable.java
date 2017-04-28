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
	
	public static final int PlAYMODE_SINGLELOOP= (i++);//单曲循环
	public static final int PlAYMODE_LISTLOOP = (i++);//列表循环	
	public static final int PlAYMODE_SEQUENCEPLAY = (i++);//循环播放
	public static final int PlAYMODE_RAMDOMPLAY = (i++);//随机播放
	
	public static final int INITMUSIC = (i++); // 初始化歌曲操作
	public static final int REINITMUSIC = (i++); // 重新播放歌曲
	public static final int PREMUSIC = (i++); // 上一首歌曲操作
	public static final int PLAYMUSIC = (i++); // 播放歌曲操作
	public static final int PLAYINFOMUSIC = (i++); // 播放歌曲
	public static final int PAUSEMUSIC = (i++); // 暂停歌曲操作
	public static final int STOPMUSIC = (i++); // 结束歌曲操作
	public static final int NEXTMUSIC = (i++); // 下一首歌曲操作
	public static final int ERRORMUSIC = (i++); // 歌曲错误操作
	public static final int SEEKTOMUSIC = (i++); // 歌曲快进操作
	public static final int RANDOMMUSIC = (i++); // 随机选择歌曲操作

	public static final int SERVICEPLAYMUSIC = (i++); // 服务播放歌曲操作
	public static final int SERVICEPLAYINIT = (i++); // 服务播放歌曲操作
	public static final int SERVICEPLAYINGMUSIC = (i++); // 服务正在播放歌曲操作
	public static final int SERVICEPAUSEMUSIC = (i++); // 服务暂停歌曲操作
	public static final int SERVICEPAUSEEDMUSIC = (i++); // 服务已经暂停歌曲操作
	public static final int SERVICESTOPMUSIC = (i++); // 服务结束歌曲操作
	public static final int SERVICESTOPEDMUSIC = (i++); // 服务已经结束歌曲操作
	public static final int SERVICESEEKTOMUSIC = (i++); // 服务快进歌曲
	public static final int SERVICEERRORMUSIC = (i++); // 服务播放歌曲错误操作
	
	
	
}
 