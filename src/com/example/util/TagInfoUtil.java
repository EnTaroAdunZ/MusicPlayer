package com.example.util;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Set;

import javax.swing.ImageIcon;

import com.example.entity.Tag;

import myorg.jaudiotagger.audio.AudioFile;
import myorg.jaudiotagger.audio.exceptions.CannotReadException;
import myorg.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import myorg.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import myorg.jaudiotagger.audio.flac.FlacFileReader;
import myorg.jaudiotagger.audio.mp3.MP3File;
import myorg.jaudiotagger.tag.FieldKey;
import myorg.jaudiotagger.tag.TagException;
import myorg.jaudiotagger.tag.id3.AbstractID3v2Frame;
import myorg.jaudiotagger.tag.id3.AbstractID3v2Tag;
import myorg.jaudiotagger.tag.id3.framebody.FrameBodyAPIC;
import myorg.jaudiotagger.tag.images.Artwork;

/** 
* @date 2017年3月16日 下午11:47:16 
* @version 1.0 
* 目前测试支持MP3、flac，其他格式有待测试
*/
public class TagInfoUtil {

	
	public static Tag Mp3InfoRead(String path){
		    MP3File file;
			try {
				file = new MP3File(path);
				Set<String> keySet = file.getID3v2Tag().frameMap.keySet();
				String songName=null,artist=null,album=null;
				
				if(keySet.contains("TIT2"))
				songName=file.getID3v2Tag().frameMap.get("TIT2").toString(); 
				if(keySet.contains("TPE1"))
				artist =file.getID3v2Tag().frameMap.get("TPE1").toString();
				if(keySet.contains("TALB"))
				album =file.getID3v2Tag().frameMap.get("TALB").toString();  
			    String length=file.getMP3AudioHeader().getTrackLengthAsString();
			    
			    Tag tag = new Tag();
			    if(songName!=null){
			    	songName=songName.substring(6, songName.length()-3);
			    	
			    	tag.setSongName(songName.trim());
			    }
			   
			    if(album!=null){
			    	album=album.substring(6, album.length()-3);
			 
			    	 tag.setAlbum(album.trim());
			    }
			   
			    if(artist!=null){
			    	 artist=artist.substring(6, artist.length()-3);
			    	 
			    	 tag.setArtist(artist.trim());
			    }
			   
			    tag.setLength(length);
			    return tag;
			} catch (IOException | TagException | ReadOnlyFileException 
					| InvalidAudioFrameException | CannotReadException e) {
				e.printStackTrace();
				throw new RuntimeException("获取Mp3 tag信息出错！");
				
			} 

//		    System.out.println("歌名"+songName);  
//		    System.out.println("歌手"+singer);  
//		    System.out.println("专辑:"+author);  
	}
	
	public static Image getMp3Picture(String mp3path){
		Image img;
		try {
			String url = mp3path;  
			    File sourceFile = new File(url);  
			    MP3File mp3file = new MP3File(sourceFile);  
			
			    AbstractID3v2Tag tag = mp3file.getID3v2Tag();  
			    AbstractID3v2Frame frame = (AbstractID3v2Frame) tag.getFrame("APIC");  
			    FrameBodyAPIC body = (FrameBodyAPIC) frame.getBody();  
			    byte[] imageData = body.getImageData();  
			    img = Toolkit.getDefaultToolkit().createImage(imageData, 0,imageData.length);  
			    ImageIcon icon = new ImageIcon(img);
			    String storePath=mp3path;
			    storePath = storePath.substring(0, storePath.length()-3);
			    storePath+="jpg";
			    FileOutputStream fos = new FileOutputStream(storePath);  
			    fos.write(imageData);  
			    fos.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("读取Mp3图片出错");
		} 
	    return img;
	}
	
	public static Tag FlacInfoRead(String path){
		try {
			FlacFileReader fileReader=new FlacFileReader();
			AudioFile read = fileReader.read(new File(path));
			myorg.jaudiotagger.tag.Tag tag = read.getTag();
			String songName = tag.getFirst(FieldKey.TITLE);
			String artist = tag.getFirst(FieldKey.ARTIST);
			String album=tag.getFirst(FieldKey.ALBUM);
			int trackLength = read.getAudioHeader().getTrackLength();
			int min=trackLength/60;
			int second=trackLength%60;
			String length=min+":"+second;
			Tag tag2 = new Tag();
			tag2.setSongName(songName.trim());
			tag2.setArtist(artist.trim());
			tag2.setAlbum(album.trim());
			tag2.setLength(length);
			return tag2;
//			System.out.println(songName);
//			System.out.println(artist);
//			System.out.println(album);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("读取Flac信息时出错！");
		} 
	}
	
	public static Image getFlacPicture(String flacpath){
		try {
			FlacFileReader fileReader=new FlacFileReader();
			AudioFile read = fileReader.read(new File(flacpath));
			myorg.jaudiotagger.tag.Tag tag = read.getTag();
			Artwork firstArtwork = tag.getFirstArtwork();
			byte[] imageData = firstArtwork.getBinaryData();
			Image image=Toolkit.getDefaultToolkit().createImage(imageData, 0,imageData.length);
			ImageIcon icon = new ImageIcon(image);
			String storePath=flacpath;
			storePath = storePath.substring(0, storePath.length()-4);
			storePath+="jpg";
			System.out.println(storePath);
			FileOutputStream fos = new FileOutputStream(storePath);  
			fos.write(imageData);  
			fos.close();
			return image;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("读取Flac图片时出错！");
		}
	}
	


}
 