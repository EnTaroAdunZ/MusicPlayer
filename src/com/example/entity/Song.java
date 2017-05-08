package com.example.entity; 

/**
* @date 2017年3月16日 下午9:10:38 
* @version 1.0 
* @Description:   歌曲的实体类
*/
public class Song {
	private Tag tag;
	private String path;
	private String length;
	private String isLike;
	
	public String getIsLike() {
		return isLike;
	}
	public void setLike(String isLike) {
		if(isLike.equals("YES")){
			this.isLike = "YES";
		}
		else{
			this.isLike = "NO";
		}
	}
	public void setYesLike() {
		this.isLike = "YES";
	}
	public void setNoLike() {
		this.isLike = "NO";
	}
	public Tag getTag() {
		if(tag==null){
			tag=new Tag();
		}
		return tag;
	}
	public void setTag(Tag tag) {
		this.tag = tag;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getLength() {
		return length;
	}
	public void setLength(String length) {
		this.length = length;
	}

	
	
	
	
}
 