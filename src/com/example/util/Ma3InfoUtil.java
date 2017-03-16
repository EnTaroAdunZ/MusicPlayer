package com.example.util;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;


/** 
* @author ZTF  
* @date 2017年3月16日 下午11:47:16 
* @version 1.0 
*/
public class Ma3InfoUtil {
	private String charset = "utf-8";//解析MP3信息时用的字符编码
	private byte[] buf;//MP3的标签信息的byte数组
	
	public Ma3InfoUtil(File mp3) {
		try {
			buf = new byte[128];//初始化标签信息的byte数组
			
			RandomAccessFile raf = new RandomAccessFile(mp3, "r");//随机读写方式打开MP3文件
			raf.seek(raf.length() - 128);//移动到文件MP3末尾
			raf.read(buf);//读取标签信息
			
			raf.close();//关闭文件
			
			if(buf.length != 128){//数据是否合法
				throw new IOException("MP3标签信息数据长度不合法!");
			}
			
			if(!"TAG".equalsIgnoreCase(new String(buf,0,3))){//信息格式是否正确
				throw new IOException("MP3标签信息数据格式不正确!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("加载MP3工具中出错！");
		} 
	}
	
	/**
	 * 获得目前解析时用的字符编码
	 * @return 目前解析时用的字符编码
	 */
	public String getCharset() {
		return charset;
	}

	/**
	 * 设置解析时用的字符编码
	 * @param charset 解析时用的字符编码
	 */
	public void setCharset(String charset) {
		this.charset = charset;
	}
	
	public String getSongName(){
		try {
			return new String(buf,3,30,charset).trim();
		} catch (UnsupportedEncodingException e) {
			return new String(buf,3,30).trim();
		}
	}
	
	public String getArtist(){
		try {
			return new String(buf,33,30,charset).trim();
		} catch (UnsupportedEncodingException e) {
			return new String(buf,33,30).trim();
		}
	}
	
	public String getAlbum(){
		try {
			return new String(buf,63,30,charset).trim();
		} catch (UnsupportedEncodingException e) {
			return new String(buf,63,30).trim();
		}
	}
	
	public String getYear(){
		try {
			return new String(buf,93,4,charset).trim();
		} catch (UnsupportedEncodingException e) {
			return new String(buf,93,4).trim();
		}
	}
	
	public String getComment(){
		try {
			return new String(buf,97,28,charset).trim();
		} catch (UnsupportedEncodingException e) {
			return new String(buf,97,28).trim();
		}
	}
	
	public static void main(String[] args) {
		//TODO 演示
		File MP3FILE = new File("D:\\CloudMusic\\7!! - オレンジ.mp3");
		Ma3InfoUtil info = new Ma3InfoUtil(MP3FILE);
		info.setCharset("UTF-8");
		System.out.println(info.getSongName());
		System.out.println(info.getArtist());
		System.out.println(info.getAlbum());
		System.out.println(info.getYear());
		System.out.println(info.getComment());
	}
}
 