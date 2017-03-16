package com.example.dao;

import com.example.entity.Tag;

/** 
* @date 2017年3月15日 下午2:26:49 
* @version 1.0 
* @Description:   修改Tag，暂时不支持
*/
public interface TagDao {
	void alterCharset(String charset);
	void alterSongName(String SongName);
	void alterArtist(String Artist);
	void alterAlbum(String Album);
	void alterYear(String Year);
	void alterComment(String Comment);
}
 