package com.example.util;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Properties;

import com.sun.jndi.toolkit.ctx.StringHeadTail;
import com.sun.org.apache.xml.internal.security.Init;



/**  
* @date 2017年3月16日 下午7:18:04 
* @version 1.0 
* @Description:  配置文件工具类
*/
public class PropertiesUitl {
	private static String path;
	private static InputStream in;
	private static URL outUrl;
	private static String storePath;
	public static String getPath() {
		return path;
	}

	public static void setPath(String p) {
		path = p;
		try {
			storePath=outUrl.toString().substring(6);
			Properties properties=new Properties();
			properties.load(new InputStreamReader(in));
			properties.setProperty("path", path);
			
			properties.store(new FileWriter(storePath), "MusicPlayer-ini");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("保存配置文件时出错！");
		}
	}
	
	static{
		try {
			in = ClassLoader.getSystemClassLoader().getResourceAsStream("ini.properties");
			outUrl=ClassLoader.getSystemClassLoader().getResource("ini.properties");
			Properties properties=new Properties();
			properties.load(new InputStreamReader(in));
			path=properties.getProperty("path");
			path.replace("\\", "");
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("加载配置文件时出错！");
		}

	}
	
	
	public static String getProperties(String key) {
		try {
			Properties properties=new Properties();
			properties.load(new InputStreamReader(in));
			String value=properties.getProperty(key);
			if(key=="path"){
				throw new RuntimeException("请不要从这里获取路径！");
			}
			return value;
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("加载配置文件时出错！");
		}
	}

	public static void setProperties(String key,String value) {
		try {
			storePath=outUrl.toString().substring(6);
			Properties properties=new Properties();
			properties.load(new InputStreamReader(in));
			properties.setProperty(key, value);
			properties.store(new FileWriter(storePath), "MusicPlayer-ini");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("保存配置信息时出错！");
		}
	}
	
	
	
}
 