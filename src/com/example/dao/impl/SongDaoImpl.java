package com.example.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;

import com.example.dao.SongDao;
import com.example.entity.Song;
import com.example.entity.Tag;
import com.example.util.SongUtil;
import com.example.util.XMLUtil;

/** 
* @author ZTF  
* @date 2017年3月16日 下午9:13:38 
* @version 1.0 
* @Description:   
*/
public class SongDaoImpl implements SongDao{

	

	@Override
	public void deleteSong(String menuName,String songPath) {
		Document document= XMLUtil.getDoc();
		Element element = (Element) document.selectSingleNode("//song-menu[@songMenuName='" + menuName + "']/song[path='" + songPath + "']");
		element.getParent().remove(element);
		XMLUtil.writeDoc(document);
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
                Song song=SongUtil.eleToSong(e);
                songList.add(song);
            }
        }
        return songList;
	}

	@Override
	public List<Song> getSongByName(String songName,String menuName) {
		Document document= XMLUtil.getDoc();
		
		Element element = (Element) document.selectSingleNode("//song-menu[@songMenuName='" + menuName + "']");
		List<Node> selectNodes = element.selectNodes("//tag[contains(songName,'"+songName+"')]");
		Iterator<Node> iterator = selectNodes.iterator();
		List<Song> songList=new ArrayList<Song>();
		while(iterator.hasNext()){
			Element parent = iterator.next().getParent();
			Song song=SongUtil.eleToSong(parent);
			songList.add(song);
		}
		return songList;
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
		SongUtil.songToEle(song, element);
		XMLUtil.writeDoc(document);
	}

	@Override
	public void addSongWithFile(List<Song> songList, String menuName) {
		Document document= XMLUtil.getDoc();
		Element element = (Element) document.selectSingleNode("//song-menu[@songMenuName='" + menuName + "']");
		Iterator<Song> iterator = songList.iterator();
		while(iterator.hasNext()){
			Song song = iterator.next();
			SongUtil.songToEle(song, element);
		}
		XMLUtil.writeDoc(document);
	}


	public static void main(String[] args) {
		SongDaoImpl songDaoImpl=new SongDaoImpl();
		songDaoImpl.deleteSong("我的最爱","D:\\CloudMusic\\10cm,大橋トリオ - Fine Thank You And You.mp3");
	}
}
 