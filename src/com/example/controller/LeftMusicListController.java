package com.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * 
 * @author Tony Yao
 * 该类是LeftMusicList.fxml 即程序左侧歌单列表里控件的id和常用方法的操作类
 */
public class LeftMusicListController {
	//以下是各控件的id
	@FXML
	private AnchorPane AnchorPane_left;//整个歌单列表的底部paneid
	
	@FXML
	private VBox VBox_items;//控制各控件纵向排序的id
	
	@FXML
	private ScrollPane ScrollPane_rollArea;//歌单列表滚动区域
	
	@FXML
	private VBox VBox_leftMainField;//左侧区域纵向排序的id
	
	@FXML
	private VBox VBox_leftButtons;//按钮们纵向排序的id
	
	@FXML
	private Button Button_localMusic;//“本地音乐”按钮的id
	
	@FXML
	private Button Button_addLocalMusic;//“添加本地音乐”按钮的id
	
	@FXML
	private Button Button_addLocalDirectory;//“添加本地音乐文件夹”按钮的id
	
	@FXML
	private HBox HBox_leftManageField;//左中标题栏横向排序id
	
	@FXML
	private Label Label_musicListTitle;//“我的歌单：”文本id
	
	@FXML
	private Button Button_addMusicList;//按钮“+”按钮id
	
	@FXML
	private ListView<Button> ListView_musicList;//歌单按钮列表的id
	
	@FXML
	private AnchorPane AnchorPane_CurrentSongInfo;//当前播放歌曲小窗口id
	
	@FXML
	private HBox Hbox_leftImageAndTitle;//控制排序id
	
	@FXML
	private ImageView ImageView_albumCover;//专辑封面的id
	
	@FXML
	private VBox Vbox_leftTitleAndSinger;//控制纵向排序的id
	
	@FXML
	private Label Label_musicTitle;//音乐标题的id
	
	@FXML
	private Label Label_singer;//歌手的id
	
	//以下是方法接口
	@FXML
	private void onLocalMusic(ActionEvent event){//按钮“本地音乐”的id
		
	}
	
	@FXML
	private void onAddLocalMusic(ActionEvent event){//按钮“添加本地音乐”的id
		
	}
	
	@FXML
	private void onAddLocalDirectory(ActionEvent event){//按钮“添加本地音乐文件夹”的id
		
	}
	
	@FXML
	private void onButtonAddMusicList(ActionEvent event){//按钮“+”的id
		
	}
	
	public void initData(){//初始化本页面的方法接口，待实现
		
	}
}
