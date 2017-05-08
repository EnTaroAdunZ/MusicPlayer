package com.example.util;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.xml.sax.XMLReader;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 63574 on 2017/3/1.
 */
public class XMLUtil {
	// 初始化数据文件，后期放到初始化入口执行
	static {
		File file = new File("E://MusicPlay//");
		if (!file.exists()) {
			file.mkdirs();
			File songMenuXML = new File("E://MusicPlay//songmenu.xml");
			try {
				songMenuXML.createNewFile();
				Document document = DocumentHelper.createDocument();
				Element root = document.addElement("menu-list");
				Date date = new Date();
				DateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
				String time = format.format(date);
				root.addElement("song-menu").addAttribute("songMenuName", "我喜欢的音乐").addAttribute("createDate", time);
				root.addElement("song-menu").addAttribute("songMenuName", "本地音乐").addAttribute("createDate", time);
				XMLUtil.writeDoc(document);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	// 歌单默认保存目录E://MusicPlay//songsheet.xml，后面提供方法修改
	public static  Document getDoc(){
        try {
        	File songMenuXML=new File("E://MusicPlay//songmenu.xml");
			if(!songMenuXML.exists()){
					try {
						songMenuXML.createNewFile();
						Document document=DocumentHelper.createDocument();
						Element root=document.addElement("menu-list");
						Date date=new Date();
						  DateFormat format=new SimpleDateFormat("yyyy年MM月dd日");
						  String time=format.format(date);
						root.addElement("song-menu").addAttribute("songMenuName", "我喜欢的音乐").addAttribute("createDate",time );
						root.addElement("song-menu").addAttribute("songMenuName", "本地音乐").addAttribute("createDate",time );
					    XMLUtil.writeDoc(document);
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
            Document read = new SAXReader().read(new File("E://MusicPlay//songmenu.xml"));
            return read;
        } catch (DocumentException e) {
        	e.printStackTrace();
        }return null;

	}

	public static void writeDoc(Document document){
        XMLWriter xmlWriter=null;
        try {
            FileOutputStream fileOutputStream=new FileOutputStream(new File("E://MusicPlay//songmenu.xml"));
            OutputFormat outputFormat=OutputFormat.createPrettyPrint();
            xmlWriter=new XMLWriter(fileOutputStream,outputFormat);
            xmlWriter.write(document);
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
        finally {
            try {
                xmlWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
