package com.example.util;

import java.text.DecimalFormat;

import org.dom4j.Element;

import com.example.entity.Song;
import com.example.entity.Tag;
import com.example.gui.MusicUtils;
import com.sun.jndi.url.dns.dnsURLContext;

/** 
* @date 2017年3月19日 上午1:10:26 
* @version 1.0 
* @Description:   Song工具类
*/
public class SongUtil {
	public static String getLengthToMb(long size) {

	    DecimalFormat df = new DecimalFormat("#.00");
	    String fileSizeString = "";
	    if (size < 1024) {
	        fileSizeString = df.format((double) size) + "B";
	    } else if (size < 1048576) {
	        fileSizeString = df.format((double) size / 1024) + "K";
	    } else if (size < 1073741824) {
	        fileSizeString = df.format((double) size / 1048576) + "M";
	    }
	        return fileSizeString;
	    }
	
	public static Song eleToSong(Element element) {
        Song song=new Song();
        song.setPath(element.elementText("path"));
        song.setLength(element.elementText("length"));
        Element tagElement = element.element("tag");
        Tag tag=new Tag();
        tag.setAlbum(tagElement.elementText("album"));
        tag.setSongName(tagElement.elementText("songName"));
        tag.setLength(tagElement.elementText("length"));
        song.setTag(tag);
        return song;
	}
	
	public static void songToEle(Song song,Element parent) {
		Element node=parent.addElement("song");
		node.addElement("path").setText(song.getPath());
		node.addElement("length").setText(song.getLength());
		Element tagElement = node.addElement("tag");
		tagElement.addElement("album").setText(song.getTag().getAlbum().trim());
		tagElement.addElement("artist").setText(song.getTag().getArtist().trim());
		tagElement.addElement("songName").setText(song.getTag().getSongName().trim());
		tagElement.addElement("length").setText(song.getTag().getLength().trim());
	}
	
	public static MusicUtils songToMucic(Song song) {
		MusicUtils musicUtils=new MusicUtils();
		musicUtils.setAlbumName(song.getTag().getAlbum());
		musicUtils.setMusicSinger(song.getTag().getArtist());
		musicUtils.setMusicSize(song.getLength());
		musicUtils.setMusicTimeLength(song.getTag().getLength());
		musicUtils.setMusicTitle(song.getTag().getSongName());
		musicUtils.setPath(song.getPath());
		return musicUtils;
	}
	
}
 