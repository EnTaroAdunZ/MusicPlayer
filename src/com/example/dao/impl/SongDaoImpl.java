package com.example.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;

import com.example.dao.SongDao;
import com.example.entity.Song;
import com.example.entity.Tag;
import com.example.util.XMLUtil;

/** 
* @author ZTF  
* @date 2017年3月16日 下午9:13:38 
* @version 1.0 
* @Description:   
*/
public class SongDaoImpl implements SongDao{

	

	@Override
	public void deleteSong(String songPath) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteSongWithFile(String songPath) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changeSong(Song song) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Song> getSongList(String songSheet) {
        Document document= XMLUtil.getDoc();
        List<Element> elements= document.getRootElement().elements(songSheet);
        List<Song> songList=new ArrayList<Song>();
        if(elements!=null){
            for(Element e:elements){
                Song song=new Song();
                song.setPath(e.elementText("path"));
                Element tagElement = e.element("tag");
                Tag tag=new Tag();
                tag.setAlbum(e.elementText("album"));
                tag.setSongName(e.elementText("songName"));
                tag.setLength(e.elementText("length"));
                song.setTag(tag);
                songList.add(song);
            }
        }
        return songList;
	}

	@Override
	public Song getSongByName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Song getSongByArtist() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addSong(Song song, String menuName) {
		Document document= XMLUtil.getDoc();
		Element element = (Element) document.selectSingleNode("//song-menu[@songMenuName='" + menuName + "']");
		Element node=element.addElement("song");
		node.addElement("path").setText(song.getPath());
		Element tagElement = node.addElement("tag");
		tagElement.addElement("album").setText(song.getTag().getAlbum());
		tagElement.addElement("artist").setText(song.getTag().getArtist());
		tagElement.addElement("songName").setText(song.getTag().getSongName());
		tagElement.addElement("length").setText(song.getTag().getLength());
		XMLUtil.writeDoc(document);
	}

	@Override
	public void addSongWithFile(List<Song> songList, String menuName) {
		// TODO Auto-generated method stub
		
	}


	
}
 