package com.example.service;
import java.util.List;

import com.example.dao.SongMenuDao;
import com.example.dao.impl.SongMenuDaoImpl;
import com.example.entity.SongMenu;

/** 
* @date 2017年3月18日 上午12:37:33 
* @version 1.0 
* @Description:  有关歌单的操作
*/
public class SongMenuOperate {
	static SongMenuDao songMenuDao;
	static{
		songMenuDao=new SongMenuDaoImpl();
	}
	
	//后面可以添加返回值判断是否添加成功
	public static void addSongMenu(String songMenuName){
		//判断歌单是否重名，暂时不支持
		songMenuDao.addSongMenu(songMenuName);
	}
	
	//得到歌单以及歌单下所有歌曲信息，初始化使用
	public static List<SongMenu> getAllSongMenu(){
		return songMenuDao.getAllSongMenu();
	}
	
	//得到歌单名
	public static List<SongMenu> getSongMenu(){
		return songMenuDao.getSongMenu();
	}
	
	
	public static void main(String[] args) {
//		List<SongMenu> songMenu = getSongMenu();
//		for(SongMenu s:songMenu)
//		System.out.println(s.getSongMenuName());
		
		addSongMenu("我的更爱");
		
	}
}
 