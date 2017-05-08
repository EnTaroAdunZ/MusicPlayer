package com.example.Global;

import java.util.ArrayList;
import java.util.List;

import com.example.controller.Controller;
import com.example.gui.MusicUtils;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/** 
* @author ZTF  
* @date 2017年3月19日 下午12:41:58 
* @version 1.0 
* @Description:   TODO(用一句话描述该文件做什么)
*/
public class GlobalVariable {
	public static StringProperty currentMenu = new SimpleStringProperty("");
	public static StringProperty currentSearch = new SimpleStringProperty("");
	public static MusicUtils currentSong;
	public static List<MusicUtils> currentList = new ArrayList<>();
	public static IntegerProperty currentSum = new SimpleIntegerProperty(0);
	public static int PageType = 0;
	public static String currentLrc;
	public static Controller currentCtrl = null;
	
	
	public static final int PlAYMODE_SINGLELOOP= 0;//单曲循环
	public static final int PlAYMODE_LISTLOOP = 1;//列表循环	
	public static final int PlAYMODE_SEQUENCEPLAY = 2;//循环播放
	public static final int PlAYMODE_RAMDOMPLAY = 3;//随机播放
	
	//与播放线程有关的操作
	public static final int PREMUSIC = -1; // 上一首歌曲操作
	public static final int SERVICE_PLAYMUSIC = -2; // 用户操作：点击播放
	public static final int SERVICE_PAUSEMUSIC = -3; //  用户操作：切换播放、暂停状态
	public static final int PLAYINGMUSIC = -4; // 目前状态：正在播放
	public static final int PAUSINGMUSIC = -5; //  目前状态：正在暂停
	public static final int STOPMUSIC = -6; // 结束歌曲操作
	public static final int NEXTMUSIC = -7; // 下一首歌曲操作
	public static final int EXITMUSIC = -8; // 退出播放器
	
	
	//无关操作
	public static final int HASDONOTHING = 17; // 退出播放器
	public static final int ALTERVOLUME = 18; // 修改音乐播放器
	public static final int SEEKTOMUSIC = 19; // 歌曲快进操作
	public static final int SEEKTOMUSICWHENPAUSE = 20; // 歌曲快进操作
	
	//搜索模式
	public static final int SEARCHMODE_SONGNAME = -17; 
	public static final int SEARCHMODE_ALBUM= -18; 
	public static final int SEARCHMODE_SINGER= -19; 
	public static final int SEARCHMODE_HYBRID = -20;
	
}
 