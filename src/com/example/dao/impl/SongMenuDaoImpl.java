package com.example.dao.impl;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;

import com.example.dao.SongMenuDao;
import com.example.entity.Song;
import com.example.entity.SongMenu;
import com.example.entity.Tag;
import com.example.util.XMLUtil;

/** 
* @author ZTF  
* @date 2017年3月18日 上午12:36:02 
* @version 1.0 
* @Description:   SongMenuDao的实现类
*/
public class SongMenuDaoImpl implements SongMenuDao{

	@Override
	public void addSongMenu(String songMenuName) {
		Document document=XMLUtil.getDoc();
		Element addElement = document.getRootElement().addElement("song-menu").addAttribute("songMenuName", songMenuName);
		XMLUtil.writeDoc(document);
	}

	@Override
	public void deleteSongMenu(String songMenuName) {
		Document document=XMLUtil.getDoc();
		Element menuList = (Element) document.selectSingleNode("//song-menu[@songMenuName='" + songMenuName + "']");
		Element parent = menuList.getParent();
		parent.remove(menuList);
		XMLUtil.writeDoc(document);
	}

	@Override
	public void alterSongMenu(String new_songMenuName, String old_songMenuName) {
		// TODO Auto-generated method stub
		
	}

	//得到歌单以及歌单下的所有信息
	@Override
	public List<SongMenu> getAllSongMenu() {
		Document document = XMLUtil.getDoc();
		List<Element> elements=document.getRootElement().elements();
		List<SongMenu> songMenus=new ArrayList<SongMenu>();
        if(elements!=null){
            for(Element e:elements){
            	SongMenu songMenu = new SongMenu();
            	List<Element> songElements = e.elements("song");
            	List<Song> songList = new ArrayList<Song>();
            	songMenu.setSongMenuName(e.attributeValue("songMenuName"));
            	songMenus.add(songMenu);
            	if(songElements!=null){
            		for(Element e2:songElements){
                        Song song=new Song();
                        song.setPath(e2.elementText("path"));
                        Tag tag = new Tag();
                        Element elementTag = e2.element("tag");
                        tag.setAlbum(elementTag.elementText("album"));
                        tag.setArtist(elementTag.elementText("artist"));
                        tag.setSongName(elementTag.elementText("songName"));
                        tag.setLength(elementTag.elementText("length"));
                        song.setTag(tag);
                        songList.add(song);
            		}
            		songMenu.setSongList(songList);
            	}
            }
        }
		return songMenus;
	}

	//只得到歌名
	@Override
	public List<SongMenu> getSongMenu() {
		Document document = XMLUtil.getDoc();
		List<Element> elements=document.getRootElement().elements();
		List<SongMenu> songMenuList=new ArrayList<SongMenu>();
        if(elements!=null){
            for(Element e:elements){
            	SongMenu songMenu=new SongMenu();
            	songMenu.setSongMenuName(e.attributeValue("songMenuName"));
            	songMenuList.add(songMenu);
            }
        }
		return songMenuList;
	}

	@Override
	public List<Song> getSongByMenuName(String menuName) {
		List<Song> songList = null;
		Document document= XMLUtil.getDoc();
		Element menuList = (Element) document.selectSingleNode("//song-menu[@songMenuName='" + menuName + "']");
		if(menuList!=null){
			List<Element> elements = menuList.elements("song");
			if(elements!=null){
				songList=new ArrayList<Song>();
				for(Element e:elements){
		            Song song=new Song();
		            song.setPath(e.elementText("path"));
		            song.setLength(e.elementText("length"));
		            Tag tag = new Tag();
		            Element elementTag = e.element("tag");
		            tag.setAlbum(elementTag.elementText("album"));
		            tag.setArtist(elementTag.elementText("artist"));
		            tag.setSongName(elementTag.elementText("songName"));
		            tag.setLength(elementTag.elementText("length"));
		            song.setTag(tag);
		            songList.add(song);
				}
			}
		}

		return songList;
	}
	
	

}
 