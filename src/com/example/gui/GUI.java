package com.example.gui;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.example.controller.*;
import com.example.event.*;
import com.example.service.PlayOperate;

import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class GUI extends Application{
	public static Stage staticStage;
	
	private PageQueue pageManager = new PageQueue();
	private IntegerProperty index; 
	private IntegerProperty size;
	
	private BorderPane permanent;
	private AnchorPane leftlist;
	private AnchorPane playpage;
	
	private TopAndBottomPageController tabC = null;
	private LeftMusicListController llC = null;
	private PlayPageController ppC = null;
	
	private double xOffset = 0;  
    private double yOffset = 0;  
	
	/**
	 * 借用fxml启动的fx程序
	 * @author Tony Yao
	 */
    @Override  
    public void start(Stage stage) { 
    	staticStage = stage;
    	Thread.currentThread().setName("MusicPlayer");
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
			
			FXMLLoader pp = new FXMLLoader(getClass().getResource("PlayPage.fxml"),
					ResourceBundle.getBundle("ini"));
			playpage = (AnchorPane) pp.load();
			ppC = pp.getController();
			
			permanent.setLeft(leftlist);
			pageManager.bind(index, size);
			tabC.getButton_back().setDisable(true);
			tabC.getButton_forward().setDisable(true);
			
			MainAction ma = new MainAction(this);
			tabC.initData(ma);
			llC.initData(ma);
			ppC.initData(ma);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
    	
		//实现可拖动标题栏的功能
		permanent.setOnMousePressed(new EventHandler<MouseEvent>() {  
            @Override  
            public void handle(MouseEvent event) {  
                xOffset = event.getSceneX();  
                yOffset = event.getSceneY();  
            }  
        });  
		permanent.setOnMouseDragged(new EventHandler<MouseEvent>() {  
            @Override  
            public void handle(MouseEvent event) {  
            	stage.setX(event.getScreenX() - xOffset);  
            	stage.setY(event.getScreenY() - yOffset);  
            }  
        });  
		
		
    	//创建控制类的对象，便于初始化和操作
        //controller.initData();
        //初始化舞台，启动fxml，目前还不能操作只有界面而已，驱动方法接口过几天再补充
        Scene scene = new Scene(permanent, 1300, 850);  
        stage.initStyle(StageStyle.UNDECORATED);  
        stage.setScene(scene);  
        stage.setTitle("MusicPlay");  
        
        //使css文件载入
        ArrayList<String> cssList = new ArrayList<>();
        cssList.add("com/example/css/topandbottom/TopAndBottomPage.css");
        cssList.add("com/example/css/left/LeftMusicPage.css");
        cssList.add("com/example/css/local/LocalMusicPageCss.css");
        cssList.add("com/example/css/play/PlayPageCss.css");
        cssList.add("com/example/css/search/SearchPageCss.css");
        cssList.add("com/example/css/musiclist/MusicListPageCss.css");
        scene.getStylesheets().addAll(cssList);
        
        stage.show();
        

    }

    
    
    public IntegerProperty getSize() {
		return size;
	}
	public TopAndBottomPageController getTabC() {
		return tabC;
	}
	public LeftMusicListController getLlC() {
		return llC;
	}
	public PlayPageController getPpC() {
		return ppC;
	}
	public PageQueue getPageManager() {
		return pageManager;
	}
	public IntegerProperty getIndex() {
		return index;
	}
	public BorderPane getPermanent() {
		return permanent;
	}
	public AnchorPane getLeftlist() {
		return leftlist;
	}
	public AnchorPane getPlaypage() {
		return playpage;
	}

	/**
	 * 用于开始运行fx程序的主函数
	 * @author Tony Yao
	 */
	public static void main(String[] args) {  

        Application.launch(GUI.class, args);
    } 
}
