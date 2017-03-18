package com.example.Main;

import java.util.ResourceBundle;

import com.example.controller.MainPageController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application{
	/**
	 * 用于开始运行fx程序的主函数
	 * @author Tony Yao
	 */
	public static void main(String[] args) {  
        Application.launch(Main.class, args);  
    }  
      
	
	/**
	 * 借用fxml启动的fx程序
	 * @author Tony Yao
	 */
    @Override  
    public void start(Stage stage) throws Exception { 
    	//装载fxml与properties来创建主页面
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("MusicPlayMain.fxml"),
    			ResourceBundle.getBundle("ini"));
    	Parent root = (Parent)loader.load();
    	
    	//创建控制类的对象，便于初始化和操作
    	MainPageController controller = loader.<MainPageController>getController();
        controller.initData();
        
        //初始化舞台，启动fxml，目前还不能操作只有界面而已，驱动方法接口过几天再补充
        Scene scene = new Scene(root, 1300, 850);  
        stage.initStyle(StageStyle.DECORATED);  
        stage.setScene(scene);  
        stage.setTitle("MusicPlay");  
        stage.show();
    }
}
