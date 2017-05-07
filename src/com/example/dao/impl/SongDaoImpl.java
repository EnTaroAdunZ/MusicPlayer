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
		Element element = (Element) document.selectSingleNode("//song-menu[@songMenuName='" + menuName + "']/song[contains(path,'"+songPath+"')]");
		element.getParent().remove(element);
		XMLUtil.writeDoc(document);
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
		addMusicToLocal(song);
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


//	public static void main(String[] args) {
//		SongDao songUtil=new SongDaoImpl();
//		boolean checkMusicExist = songUtil.checkMusicExist("D:\\CloudMusic\\amazarashi - 14歳.mp3","我最爱的音乐");
//		System.out.println(checkMusicExist);
//	}


	@Override
	public void addMusicToLocal(Song song) {
		Document document= XMLUtil.getDoc();
		Element element = (Element) document.selectSingleNode("//song-menu[@songMenuName='本地音乐']");
		if(!checkMusicExist(song.getPath(),"本地音乐")){
			SongUtil.songToEle(song, element);
			XMLUtil.writeDoc(document);
		}
	}

	//返回true说明存在，false不存在
	@Override
	public boolean checkMusicExist(String path, String menuName) {
		Document document= XMLUtil.getDoc();
		Element element = (Element) document.selectSingleNode("//song-menu[@songMenuName='"+menuName+"']");
		List<Element> songs = element.elements();
		for(Element s:songs){
			Song song=SongUtil.eleToSong(s);
			if(song.getPath().equals(path))
				return true;
		}
		return false;
	}
	
	
}
 