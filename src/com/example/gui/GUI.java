package com.example.gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.example.controller.*;
import com.example.event.*;

import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class GUI extends Application{
	public static Stage staticStage;
	public static GUI gui; 

	private PageQueue pageManager = new PageQueue();
	private IntegerProperty index; 
	private IntegerProperty size;
	
	private BorderPane permanent;
	private AnchorPane leftlist;
	private AnchorPane playlist;
	private StackPane playpage;
	
	private TopAndBottomPageController tabC = null;
	private LeftMusicListController llC = null;
	private PlayListPageController plC = null;
	private PlayPageController ppC = null;
	
	private double xOffset = 0;  
    private double yOffset = 0;  
	
    @Override
    public void init() throws Exception {
    	super.init();
    	if(gui == null)
    		gui = this;    	
    }
    
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
			
			FXMLLoader pl = new FXMLLoader(getClass().getResource("PlayListPage.fxml"),
					ResourceBundle.getBundle("ini"));
			playlist = (AnchorPane) pl.load();
			plC = pl.getController();
						
			FXMLLoader pp = new FXMLLoader(getClass().getResource("PlayPage.fxml"),
					ResourceBundle.getBundle("ini"));
			playpage = (StackPane) pp.load();
			ppC = pp.getController();
			
			permanent.setLeft(leftlist);
			pageManager.bind(index, size);
			tabC.getButton_back().setDisable(true);
			tabC.getButton_forward().setDisable(true);
			playpage.getChildren().add(playlist);
			playlist.setVisible(false);
			
			MainAction ma = new MainAction(this);	
			plC.initData(ma);
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
        cssList.add("com/example/css/playList/PlayListPageCss.css");
        scene.getStylesheets().addAll(cssList);
        
        stage.show();
        

    }

    
    public static GUI getGui() {
    	return gui;
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
	public PlayListPageController getPlC() {
		return plC;
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
	public StackPane getPlaypage() {
		return playpage;
	}
	public AnchorPane getPlaylist() {
		return playlist;
	}

	/**
	 * 用于开始运行fx程序的主函数
	 * @author Tony Yao
	 */
	public static void main(String[] args) {  

        Application.launch(GUI.class, args);
    } 
}
