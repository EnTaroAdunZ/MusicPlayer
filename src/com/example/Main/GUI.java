package com.example.Main;

import java.io.File;
import java.util.ResourceBundle;

import com.example.controller.LeftMusicListController;
import com.example.controller.LocalMusicPageController;
import com.example.controller.MainPageController;
import com.example.controller.PlayPageController;
import com.example.controller.SearchPageController;
import com.example.controller.TopAndBottomPageController;
import com.example.event.*;

import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class GUI extends Application{

	private static Page page = new Page(); 
	public static PageQueue pageManager = new PageQueue();
	private IntegerProperty index; 
	
	/**
	 * 用于开始运行fx程序的主函数
	 * @author Tony Yao
	 */
	public static void main(String[] args) {  
        Application.launch(GUI.class, args);  
    }  
      
	
	/**
	 * 借用fxml启动的fx程序
	 * @author Tony Yao
	 */
    @Override  
    public void start(Stage stage) throws Exception { 
    	//装载fxml与properties来创建主页面
    	index = new SimpleIntegerProperty();
    	
    	FXMLLoader tAb = new FXMLLoader(getClass().getResource("TopAndBottomPage.fxml"),
    			ResourceBundle.getBundle("ini"));
    	BorderPane permanent = (BorderPane)tAb.load();
    	TopAndBottomPageController tAbC = tAb.getController();
    	
    	FXMLLoader ll = new FXMLLoader(getClass().getResource("LeftMusicList.fxml"),
    			ResourceBundle.getBundle("ini"));
    	AnchorPane leftlist = (AnchorPane)ll.load();
    	LeftMusicListController llC = ll.getController();
    	
    	FXMLLoader lm = new FXMLLoader(getClass().getResource("LocalMusicPage.fxml"),
    			ResourceBundle.getBundle("ini"));
    	AnchorPane localmusic = (AnchorPane)lm.load();
    	LocalMusicPageController lmC = lm.getController();
    	
    	FXMLLoader pp = new FXMLLoader(getClass().getResource("PlayPage.fxml"),
    			ResourceBundle.getBundle("ini"));
    	AnchorPane playpage = (AnchorPane)pp.load();
    	PlayPageController ppC = pp.getController();
    	
    	FXMLLoader sp = new FXMLLoader(getClass().getResource("SearchPage.fxml"),
    			ResourceBundle.getBundle("ini"));
    	AnchorPane searchpage = (AnchorPane)sp.load();
    	SearchPageController spC = sp.getController();
    	
    	permanent.setLeft(leftlist);
    	
    	pageManager.add(page.newPage(1, permanent));
    	pageManager.bind(index);
    	
    	//创建控制类的对象，便于初始化和操作
        //controller.initData();
        
        //初始化舞台，启动fxml，目前还不能操作只有界面而已，驱动方法接口过几天再补充
        Scene scene = new Scene(permanent, 1300, 850);  
        stage.initStyle(StageStyle.DECORATED);  
        stage.setScene(scene);  
        stage.setTitle("MusicPlay");  
        stage.show();

    }
}
