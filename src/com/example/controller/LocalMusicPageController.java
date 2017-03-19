package com.example.controller;

import com.example.Main.MusicUtils;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * 
 * @author Tony Yao
 * 该类是LocalMusicPage.fxml 即程序本地音乐列表里控件的id和常用方法的操作类
 */
public class LocalMusicPageController {
	//以下是各控件的ID
	@FXML
	private AnchorPane AnchorPane_center;//整个页面的pane的id
	
	@FXML
	private VBox VBox_LocalPage;//控制页面纵向排序的id
	
	@FXML
	private AnchorPane topLocalTitle;//顶部区域的id
	
	@FXML
	private HBox HBox_localButton;//顶部区域横向排列的id
	
	@FXML
	private Label Label_musicNum;//显示本地音乐数量的文本id
	
	@FXML
	private Button Button_selectDirectory;//“选择目录”的按钮id
	
	@FXML
	private Button Button_playAll;//“播放全部”的按钮id
	
	@FXML
	private TextField TextField_localSearchText;//“本地搜索”的搜索框id
	
	@FXML
	private Button Button_localSearch;//“本地搜索”按钮的id
	
	@FXML
	private ScrollPane ScrollPane_localMusicList;//本地音乐列表的滚动区域id
	
	@FXML
	private HBox HBox_centerMusicListAndScroll;//控制横向排序的id
	
	@FXML
	private TableView<MusicUtils> TableView_musicTable;//显示本地音乐列表的表id
	
	@FXML
	private TableColumn<MusicUtils, String> TableColumn_musicID;//（隐藏）本地音乐的id列
	
	@FXML
	private TableColumn<MusicUtils, String> TableColumn_musicTitle;//本地音乐的音乐标题列
	
	@FXML
	private TableColumn<MusicUtils, String> TableColumn_musicSinger;//本地音乐的歌手列
	
	@FXML
	private TableColumn<MusicUtils, String> TableColumn_albumName;//本地音乐的专辑列
	
	@FXML
	private TableColumn<MusicUtils, String> TableColumn_musicTimeLength;//本地音乐的时长列
	
	@FXML
	private TableColumn<MusicUtils, String> TableColumn_musicSize;//本地音乐的大小列
	
	//以下是响应方法的接口
	
	@FXML
	private void onSelectDirectory(ActionEvent event){//“选择目录”按钮的响应方法
		
	}
	
	@FXML
	private void onPlayAll(ActionEvent event){//“播放全部”按钮的响应方法
		
	}
	
	@FXML
	private void onLocalSearch(ActionEvent event){//“本地搜索”按钮的响应方法
		
	}
	
	public void initData(){//播放页面的初始化方法，待实现
		
	}
 }
