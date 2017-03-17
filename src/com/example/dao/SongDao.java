package com.example.dao;

import java.util.List;

import com.example.entity.Song;

/** 
* @author ZTF  
* @date 2017年3月16日 下午9:13:16 
* @version 1.0 
* @Description:   Song的Dao接口
*/
public interface SongDao {
	void addSong(Song song);
	//根据歌单名称获得歌曲列表
	List<Song> getSongList(String songSheet);
	Song getSongByName();
	Song getSongByAr();
	void deleteSong(String songPath);
	void deleteSongWithFile(String songPath);
	void changeSong(Song song);
}
 