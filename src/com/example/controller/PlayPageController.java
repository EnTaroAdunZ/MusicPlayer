package com.example.controller;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * @author Tony Yao
 * 该类是fxml文件PlayPage.fxml 的控制类，用于声明各控件的id和方法接口
 */
public class PlayPageController {
	//以下是各控件的ID
	@FXML
	private Button Button_like;//按钮“喜欢”的id
	
	@FXML
	private Button Button_collect;//按钮“收藏”的id
	
	@FXML
	private Button Button_PlayPage_back;//按钮“返回”的id
	
	@FXML
	private Label Label_musicTitle;//文本音乐标题的id
	
	@FXML
	private Label Label_albumName;//文本专辑名字的id
	
	@FXML
	private Label Label_singer;//文本歌手的id
	
	@FXML
	private Label Label_source;//文本来源的id
	
	@FXML
	private TextArea TextArea_lyricsArea;//歌词滚动板的id
	
	@FXML
	private ImageView ImageView_albumCover;//专辑封面的id
	
	@FXML
	private AnchorPane AnchorPane_playPage;//整个播放页面的paneid
	
	@FXML
	private AnchorPane AnchorPane_leftPlayPage;//左半部分页面的paneid
	
	@FXML
	private HBox HBox_totalHBox;//整个页面的Hbox
	
	@FXML
	private HBox HBox_buttonHBox;//左下按钮排版的Hbox
	
	@FXML
	private HBox HBox_titleHBox;//音乐标题排版的Hbox
	
	@FXML
	private HBox HBox_infoHBox;//音乐简略信息的Hbox
	
	@FXML
	private VBox VBox_leftVBox;//左部分布局的Vbox
	
	@FXML
	private VBox VBox_rightVBox;//右部分布局的Vbox
	
	//以下是响应方法的接口
	
	@FXML
	private void onLike(ActionEvent event){//按钮“喜欢”的响应方法
		
	}
	
	@FXML
	private void onCollect(ActionEvent event){//按钮“收藏”的响应方法
		
	}
	
	@FXML
	private void onPlayPageBack(ActionEvent event){//按钮“返回”的响应方法
		
	}
	
	public void initData(){//播放页面的初始化方法，待实现
		
	}
}
