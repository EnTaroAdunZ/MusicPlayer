package com.example.gui;

/**
 * @author Tony Yao
 * 该类是歌曲总表的各项音乐的实体类
 * 用于充当音乐总表ViewTable的泛型
 * 应当在其中实现各项属性以及方法
 * 最好将本地读取的对应信息放入该类的对象，以直接应用到TableView中
 */
public class MusicUtils {
	//以下是将用在显示歌曲总表中每一首音乐的主要属性,待继续完善
	private boolean like;
	private String musicTitle;
	private String musicSinger;
	private String albumName;
	private String musicTimeLength;
	private String musicSize;
	private String path;
	
	public boolean isLike() {
		return like;
	}
	public void setLike(boolean like) {
		this.like = like;
	}
	public String getMusicTitle() {
		return musicTitle;
	}
	public void setMusicTitle(String musicTitle) {
		this.musicTitle = musicTitle;
	}
	public String getMusicSinger() {
		return musicSinger;
	}
	public void setMusicSinger(String musicSinger) {
		this.musicSinger = musicSinger;
	}
	public String getAlbumName() {
		return albumName;
	}
	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}
	public String getMusicTimeLength() {
		return musicTimeLength;
	}
	public void setMusicTimeLength(String musicTimeLength) {
		this.musicTimeLength = musicTimeLength;
	}
	public String getMusicSize() {
		return musicSize;
	}
	public void setMusicSize(String musicSize) {
		this.musicSize = musicSize;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	
}
