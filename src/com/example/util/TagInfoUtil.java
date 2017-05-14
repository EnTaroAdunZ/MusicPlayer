package com.example.util;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import com.example.entity.Tag;
import com.tulskiy.musique.audio.AudioFileReader;
import com.tulskiy.musique.model.Track;
import com.tulskiy.musique.system.TrackIO;
import com.tulskiy.musique.util.AudioMath;

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
				throw new RuntimeException("本地文件不存在！ >_<|||");
				
			} 

//		    System.out.println("歌名"+songName);  
//		    System.out.println("歌手"+singer);  
//		    System.out.println("专辑:"+author);  
	}
	
	public static Image getMp3Picture(String mp3path) throws RuntimeException{
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
//			e.printStackTrace();
			throw new RuntimeException("读取Flac信息时出错！");
		} 
	}
	
	
	public static Image getFlacPicture(String flacpath) throws RuntimeException{
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
//			e.printStackTrace();
			throw new RuntimeException("读取Flac图片时出错！");
		}
	}
	
	public static void writePhoto(Image image,String pathName){
		File file=new File("E://MusicPlay//current_image.jpg");
		
		try {
		    BufferedImage bi = new BufferedImage(120, 120, BufferedImage.TYPE_INT_RGB);
		    bi.getGraphics().drawImage(image,0,0, null);
		    ImageIO.write(bi, "JPG", file);
		} catch ( IOException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
		}
	}
	
	public static Tag getOtherTag(String filePath) throws RuntimeException{
		try {
			File sourceFile = new File(filePath);
			Tag tag=new Tag();
			AudioFileReader audioFileReader = TrackIO
					.getAudioFileReader(sourceFile.getName());
			Track track = audioFileReader.read(sourceFile);
			double totalMS = AudioMath.samplesToMillis(track.getTrackData()
					.getTotalSamples(), track.getTrackData().getSampleRate());
			long duration = Math.round(totalMS);
			String durationStr = formatTime((int) duration);
			String displayName = sourceFile.getName();
			int index = displayName.lastIndexOf(".");
			displayName = displayName.substring(0, index);
			String artist = "";
			String title = "";
			if (displayName.contains("-")) {
				String[] titleArr = displayName.split("-");
				System.out.println(titleArr.length);
				artist = titleArr[0].trim();
				title = titleArr[1].trim();
			} else {
				title = displayName;
			}
			tag.setAlbum("未知专辑");
			tag.setArtist(artist);
			tag.setLength(durationStr);
			tag.setSongName(title);
			return tag;
		} catch (Exception e) {
			throw new RuntimeException("其他格式读取错误");
		}

		
	}
	
	public static String formatTime(int time) {
		time /= 1000;
		int minute = time / 60;
		int second = time % 60;
		minute %= 60;
		return String.format("%02d:%02d", minute, second);
	}
 
}
 