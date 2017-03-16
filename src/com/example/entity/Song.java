package com.example.entity; 

/** 
* @author ZTF  
* @date 2017年3月16日 下午9:10:38 
* @version 1.0 
* @Description:   歌曲的实体类
*/
public class Song {
	private Tag tag;
	private String id;
	private String path;
	public Tag getTag() {
		return tag;
	}
	public void setTag(Tag tag) {
		this.tag = tag;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	
}
 