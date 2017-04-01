package com.example.gui;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.example.controller.*;
import com.example.event.*;

import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class GUI extends Application{

	public Page page = new Page(); 
	public PageQueue pageManager = new PageQueue();
	public IntegerProperty index; 
	public IntegerProperty size;
	public BorderPane permanent;
	public AnchorPane leftlist;
	public TopAndBottomPageController tabC = null;
	public LeftMusicListController llC = null;
	
	/**
	 * 借用fxml启动的fx程序
	 * @author Tony Yao
	 */
    @Override  
    public void start(Stage stage) { 
    	//装载fxml与properties来创建主页面
    	index = new SimpleIntegerProperty();
    	size = new SimpleIntegerProperty();
		try {
			FXMLLoader tab = new FXMLLoader(getClass().getResource("TopAndBottomPage.fxml"),
					ResourceBundle.getBundle("ini"));
			permanent = (BorderPane) tab.load();
			tabC = tab.getController();

			FXMLLoader ll = new FXMLLoader(getClass().getResource("LeftMusicList.fxml"),
					ResourceBundle.getBundle("ini"));
			leftlist = (AnchorPane) ll.load();
			llC = ll.getController();
			permanent.setLeft(leftlist);

			pageManager.bind(index, size);
			
			MainAction ma = new MainAction(this);
			tabC.initData(ma);
			llC.initData(ma);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
    	
		
		
		
    	//创建控制类的对象，便于初始化和操作
        //controller.initData();
        //初始化舞台，启动fxml，目前还不能操作只有界面而已，驱动方法接口过几天再补充
        Scene scene = new Scene(permanent, 1300, 850);  
        stage.initStyle(StageStyle.DECORATED);  
        stage.setScene(scene);  
        stage.setTitle("MusicPlay");  
        
        //使css文件载入
        ArrayList<String> cssList = new ArrayList<>();
        cssList.add("com/example/css/topandbottom/TopAndBottomPage.css");
        cssList.add("com/example/css/left/LeftMusicPage.css");
        scene.getStylesheets().addAll(cssList);
        
        stage.show();

    }
    
    public void init() {
    	
    }
    
    
    /**
	 * 用于开始运行fx程序的主函数
	 * @author Tony Yao
	 */
	public static void main(String[] args) {  
        Application.launch(GUI.class, args);  
    } 
}
