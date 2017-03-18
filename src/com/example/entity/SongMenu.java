package com.example.entity;



import java.util.List;

import com.sun.org.apache.bcel.internal.generic.NEW;

/** 
* @author ZTF  
* @date 2017年3月18日 上午12:29:44 
* @version 1.0 
* @Description:   歌单的实体类
*/
public class SongMenu {
	private String songMenuName;
	private List<Song> songList;
	public String getSongMenuName() {
		return songMenuName;
	}
	public void setSongMenuName(String songMenuName) {
		this.songMenuName = songMenuName;
	}
	public List<Song> getSongList() {
		return songList;
	}
	public void setSongList(List<Song> songList) {
		this.songList = songList;
	}
	
}
 