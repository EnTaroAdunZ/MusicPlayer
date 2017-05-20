package com.example.dao.impl;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;

import com.example.dao.SongMenuDao;
import com.example.entity.Song;
import com.example.entity.SongMenu;
import com.example.entity.Tag;
import com.example.util.SongUtil;
import com.example.util.XMLUtil;

/** 
* @author ZTF  
* @date 2017年3月18日 上午12:36:02 
* @version 1.0 
* @Description:   SongMenuDao的实现类
*/
public class SongMenuDaoImpl implements SongMenuDao{

	//添加歌单
	@Override
	public void addSongMenu(String songMenuName) {
		Document document=XMLUtil.getDoc();
		Element addElement = document.getRootElement().addElement("song-menu");
		addElement.addAttribute("songMenuName", songMenuName);
		Date date=new Date();
		  DateFormat format=new SimpleDateFormat("yyyy年MM月dd日");
		  String time=format.format(date);
		addElement.addAttribute("createDate", time);
		XMLUtil.writeDoc(document);
	}
	//删除歌单
	@Override
	public void deleteSongMenu(String songMenuName) {
		Document document=XMLUtil.getDoc();
		Element menuList = (Element) document.selectSingleNode("//song-menu[@songMenuName='" + songMenuName + "']");
		Element parent = menuList.getParent();
		parent.remove(menuList);
		XMLUtil.writeDoc(document);
	}

	//得到歌单以及歌单下的所有信息
	@Override
	public List<SongMenu> getAllSongMenu() {
		Document document = XMLUtil.getDoc();
		List<Element> elements=document.getRootElement().elements();
		List<SongMenu> songMenus=new ArrayList<SongMenu>();
        if(elements!=null){
            for(Element e:elements){
            	if(e.attributeValue("songMenuName").equals("本地音乐")){
            		continue;
            	}
            	SongMenu songMenu = new SongMenu();
            	List<Element> songElements = e.elements("song");
            	List<Song> songList = new ArrayList<Song>();
            	songMenu.setSongMenuName(e.attributeValue("songMenuName"));
            	songMenus.add(songMenu);
            	if(songElements!=null){
            		for(Element e2:songElements){
                        Song song=SongUtil.eleToSong(e2);
                        songList.add(song);
            		}
            		songMenu.setSongList(songList);
            	}
            }
        }
		return songMenus;
	}

	//通过歌单名得到歌单
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
		            Song song=SongUtil.eleToSong(e);
		            songList.add(song);
				}
			}
		}
		return songList;
	}
	//得到创建日期
	@Override
	public String getCreateDateBySongMenuName(String menuName) {
		Document document= XMLUtil.getDoc();
		Element menuList = (Element) document.selectSingleNode("//song-menu[@songMenuName='" + menuName + "']");
		return menuList.attributeValue("createDate");
	}
	
	

}
 