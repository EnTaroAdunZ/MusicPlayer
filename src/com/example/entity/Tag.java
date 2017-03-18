package com.example.entity;

import javafx.scene.control.Button;

/** 
* @date 2017年3月15日 下午2:26:49 
* @version 1.0 
* @Description:   Tag信息实体类
*/
public class Tag {
	private String SongName;
	private String Artist;
	private String Album;
	private String length;
	public String getSongName() {
		return SongName;
	}
	
	public String getArtist() {
		return Artist;
	}

	public void setArtist(String artist) {
		Artist = artist;
	}

	public void setSongName(String songName) {
		SongName = songName;
	}
	public String getAlbum() {
		return Album;
	}
	public void setAlbum(String album) {
		Album = album;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	
	
	
}
