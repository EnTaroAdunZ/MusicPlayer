package com.example.service;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.example.Global.PlayState;
import com.example.dao.SongMenuDao;
import com.example.dao.impl.SongMenuDaoImpl;
import com.example.entity.Song;
import com.example.entity.SongMenu;
import com.example.gui.MusicUtils;
import com.example.util.SongUtil;

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
	
	//抛异常判断是否出错
	public static void addSongMenu(String songMenuName) throws RuntimeException{
		List<Song> songByMenuName =null;
		if(songMenuName.equals("本地音乐")||songMenuName.equals("我喜欢的音乐")){
			throw new RuntimeException("非法访问！");
		}
		songByMenuName= songMenuDao.getSongByMenuName(songMenuName);
		if(songByMenuName!=null){
			throw new RuntimeException("歌单已存在！");
		}
		
		songMenuDao.addSongMenu(songMenuName);
	}
	
	//得到歌单以及歌单下所有歌曲信息，初始化使用
	public static List<SongMenu> getAllSongMenu(){
		return songMenuDao.getAllSongMenu();
	}
	
	//通过歌单名得到歌单下的歌
	public static List<MusicUtils> getSongsByMenuName(String menuName){
		List<Song> songByMenuName = songMenuDao.getSongByMenuName(menuName);
		List<MusicUtils> songUtilMenu=new ArrayList<MusicUtils>();
		Iterator<Song> iterator = songByMenuName.iterator();
		while(iterator.hasNext()){
			Song next = iterator.next();
			MusicUtils songToMucic = SongUtil.songToMucic(next);
			songUtilMenu.add(songToMucic);
		}
		return songUtilMenu;
	}
	
	//通过歌单名删除歌单
	public static void deleteSongMenuByName(String menuName){
		List<Song> songByMenuName =null;
		if(menuName.equals("我喜欢的音乐")){
			throw new RuntimeException("该歌单无法删除！");
		}
		if(menuName.equals("本地音乐")){
			throw new RuntimeException("非法访问！");
		}
		songByMenuName= songMenuDao.getSongByMenuName(menuName);
		if(songByMenuName==null){
			throw new RuntimeException("歌单不存在！不能删除！");
		}
		songMenuDao.deleteSongMenu(menuName);
	}
	//根据歌单名返回歌单的创建时间
	public static String getCreateDateBySongMenuName(String menuName){
		String createDateBySongMenuName = songMenuDao.getCreateDateBySongMenuName(menuName);
		return createDateBySongMenuName;
	}
	
	//返回本地音乐
	public static List<MusicUtils> getLocalSong(){
		List<Song> songByMenuName = songMenuDao.getSongByMenuName("本地音乐");
		List<MusicUtils> songUtilMenu=new ArrayList<MusicUtils>();
		Iterator<Song> iterator = songByMenuName.iterator();
		while(iterator.hasNext()){
			Song next = iterator.next();
			MusicUtils songToMucic = SongUtil.songToMucic(next);
			songUtilMenu.add(songToMucic);
		}
		return songUtilMenu;
	}
	
//	public static void main(String[] args) {
//		System.out.println(getCreateDateBySongMenuName("本地音乐"));
//	}
	
}
 