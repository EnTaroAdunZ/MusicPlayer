package com.example.Main;

import java.util.ResourceBundle;

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
    	//装载fxml于fx主页面，fxml与当前类同一目录，用src/ini.properties文件设置初始参数，将来可改位置
        Parent root = FXMLLoader.load(getClass().getResource("MusicPlayMain.fxml"),
        		ResourceBundle.getBundle("ini"));  
        
        //初始化舞台，启动fxml，目前还不能操作只有界面而已，驱动方法接口过几天再补充
        Scene scene = new Scene(root, 1300, 850);  
        stage.initStyle(StageStyle.DECORATED);  
        stage.setScene(scene);  
        stage.setTitle("MusicPlay");  
        stage.show();
    }


}
