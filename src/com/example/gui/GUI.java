package com.example.gui;

import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;

import com.example.controller.Controller;
import com.example.controller.LeftMusicListController;
import com.example.controller.LocalMusicPageController;
import com.example.controller.MainPageController;
import com.example.controller.Page;
import com.example.controller.PageQueue;
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
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class GUI extends Application{

	public static Page page = new Page(); 
	public static PageQueue pageManager = new PageQueue();
	public  IntegerProperty index; 
	public static BorderPane permanent;
	public static AnchorPane leftlist;
	public static TopAndBottomPageController tabC = null;
	public static LeftMusicListController llC = null;
	 
      
	
	/**
	 * 借用fxml启动的fx程序
	 * @author Tony Yao
	 */
    @Override  
    public void start(Stage stage) { 
    	//装载fxml与properties来创建主页面
    	
    	index = new SimpleIntegerProperty();
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

			pageManager.bind(index);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
    	
    	//创建控制类的对象，便于初始化和操作
        //controller.initData();
        llC.getListView_musicList().getItems().add(new Button("XXXX"));
        //初始化舞台，启动fxml，目前还不能操作只有界面而已，驱动方法接口过几天再补充
        Scene scene = new Scene(permanent, 1300, 850);  
        stage.initStyle(StageStyle.DECORATED);  
        stage.setScene(scene);  
        stage.setTitle("MusicPlay");  
        stage.show();

    }
    
	public Page giveLocal() {
		LocalMusicPageController lmC = null;
		AnchorPane localmusic = null;
		try {
			FXMLLoader lm = new FXMLLoader(getClass().getResource("LocalMusicPage.fxml"),
					ResourceBundle.getBundle("ini"));
			localmusic = (AnchorPane) lm.load();
			lmC = lm.getController();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return page.newPage(Controller.LOCAL, localmusic, lmC);
    }
    
	public Page givePlay() {
		AnchorPane playpage = null;
		PlayPageController ppC = null;
		try {
			FXMLLoader pp = new FXMLLoader(getClass().getResource("PlayPage.fxml"), ResourceBundle.getBundle("ini"));
			playpage = (AnchorPane) pp.load();
			ppC = pp.getController();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return page.newPage(Controller.PLAY, playpage, ppC);
    }
    
	public Page giveSearch() {
		AnchorPane searchpage = null;
		SearchPageController spC = null;
		try {
			FXMLLoader sp = new FXMLLoader(getClass().getResource("SearchPage.fxml"), ResourceBundle.getBundle("ini"));
			searchpage = (AnchorPane) sp.load();
			spC = sp.getController();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return page.newPage(Controller.SEARCH, searchpage, spC);
    }
    
    public void addMusicList(String listname) {
    	
    }
    /**
	 * 用于开始运行fx程序的主函数
	 * @author Tony Yao
	 */
	public static void main(String[] args) {  
        Application.launch(GUI.class, args);  
    } 
}
