package com.example.dao;

import java.util.List;

import com.example.entity.Song;
import com.example.entity.SongMenu;

/** 
* @date 2017年3月18日 上午12:16:41 
* @version 1.0 
* @Description:  歌单的Dao接口
*/
public interface SongMenuDao {
	void addSongMenu(String songMenuName); 
	void deleteSongMenu(String songMenuName); 
	List<SongMenu> getAllSongMenu();
	List<Song> getSongByMenuName(String menuName);
	String getCreateDateBySongMenuName(String menuName);
}
 