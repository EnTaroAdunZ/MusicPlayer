package com.example.util;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.ImageIcon;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.audio.flac.FlacFileReader;
import org.jaudiotagger.audio.mp3.MP3File;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.KeyNotFoundException;
import org.jaudiotagger.tag.TagException;
import org.jaudiotagger.tag.id3.AbstractID3v2Frame;
import org.jaudiotagger.tag.id3.AbstractID3v2Tag;
import org.jaudiotagger.tag.id3.framebody.FrameBodyAPIC;
import org.jaudiotagger.tag.images.Artwork;

import com.example.entity.Tag;

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
			    String songName=file.getID3v2Tag().frameMap.get("TIT2").toString();  
			    String artist=file.getID3v2Tag().frameMap.get("TPE1").toString();  
			    String album=file.getID3v2Tag().frameMap.get("TALB").toString();  
			    String length=file.getMP3AudioHeader().getTrackLengthAsString();
			    songName=songName.substring(6, songName.length()-3);
			    artist=artist.substring(6, artist.length()-3);
			    album=album.substring(6, album.length()-3);
			    
			    Tag tag = new Tag();
			    tag.setSongName(songName);
			    tag.setAlbum(album);
			    tag.setArtist(artist);
			    tag.setLength(length);
			    return tag;
			} catch (IOException | TagException | ReadOnlyFileException | CannotReadException
					| InvalidAudioFrameException e) {
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
			org.jaudiotagger.tag.Tag tag = read.getTag();
			String songName = tag.getFirst(FieldKey.TITLE);
			String artist = tag.getFirst(FieldKey.ARTIST);
			String album=tag.getFirst(FieldKey.ALBUM);
			int trackLength = read.getAudioHeader().getTrackLength();
			int min=trackLength/60;
			int second=trackLength%60;
			String length=min+":"+second;
			System.out.println("长度:"+length);
			Tag tag2 = new Tag();
			tag2.setSongName(songName);
			tag2.setArtist(artist);
			tag2.setAlbum(album);
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
			org.jaudiotagger.tag.Tag tag = read.getTag();
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
//	
//	public static void main(String[] args) {
//		FlacInfoRead("D:\\CloudMusic\\back number - クリスマスソング.flac");
//	}

}
 