package com.example.entity; 

/** 
* @date 2017年3月15日 下午2:26:49 
* @version 1.0 
* @Description:   Tag信息实体类
*/
public class Tag {
	private String SongName;
	private String getArtist;
	private String Album;
	private String Year;
	private String Comment;
	public String getSongName() {
		return SongName;
	}
	public void setSongName(String songName) {
		SongName = songName;
	}
	public String getGetArtist() {
		return getArtist;
	}
	public void setGetArtist(String getArtist) {
		this.getArtist = getArtist;
	}
	public String getAlbum() {
		return Album;
	}
	public void setAlbum(String album) {
		Album = album;
	}
	public String getYear() {
		return Year;
	}
	public void setYear(String year) {
		Year = year;
	}
	public String getComment() {
		return Comment;
	}
	public void setComment(String comment) {
		Comment = comment;
	}
	
	
	
}
