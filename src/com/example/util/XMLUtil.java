package com.example.util;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.xml.sax.XMLReader;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by 63574 on 2017/3/1.
 */
public class XMLUtil {
	//歌单默认保存目录E://MusicPlay//songsheet.xml，后面提供方法修改
    public static  Document getDoc(){
        try {
            Document read = new SAXReader().read(new File("E://MusicPlay//songsheet.xml"));
            return read;
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void writeDoc(Document document){
        XMLWriter xmlWriter=null;
        try {
            FileOutputStream fileOutputStream=new FileOutputStream(new File("E://MusicPlay//songsheet.xml"));
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
