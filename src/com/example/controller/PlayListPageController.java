package com.example.controller;

import com.example.gui.MusicUtils;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

public class PlayListPageController {
	@FXML
	private AnchorPane AnchorPane_bottom;//整个页面的基底
	
	@FXML
	private Button Button_close;//右上角关闭按钮id
	
	@FXML
	private TabPane TabPane_list;//两个子页面的tabpane的id
	
	@FXML
	private Tab Tab_playList;//左侧的“播放列表”id
	
	@FXML
	private Tab Tab_historyList;//右侧的“历史记录”id
	
	@FXML
	private Label Label_playListNum;//播放列表里当前歌曲的总数
	
	@FXML
	private Button Button_playListCollect;//播放列表里的“收藏全部”按钮id
	
	@FXML
	private Button Button_playListClear;//播放列表里的“清空”按钮id

	@FXML
	private TableView<MusicUtils> TableView_playList;//播放列表里歌表id

	@FXML
	private TableColumn<MusicUtils, String> Col_playListSong;//播放列表里歌表歌名栏id

	@FXML
	private TableColumn<MusicUtils, String> Col_playListSinger;//播放列表里歌表歌手栏id

	@FXML
	private TableColumn<MusicUtils, String> Col_playListLength;//播放列表里歌表时长栏id

	@FXML
	private Label Label_historyListNum;//历史记录里当前歌曲的总数文本id

	@FXML
	private Button Button_historyListClear;//历史记录里的清空按钮id

	@FXML
	private TableView<MusicUtils> TableView_historyList;//历史记录里歌表id

	@FXML
	private TableColumn<MusicUtils, String> Col_historyListSong;//历史记录里歌表歌名栏id

	@FXML
	private TableColumn<MusicUtils, String> Col_historyListSinger;//历史记录里歌表歌手栏id

	@FXML
	private TableColumn<MusicUtils, String> Col_historyListLength;//历史记录里歌表时长栏id
	
	//
	@FXML
	private void onButtonClose(ActionEvent event){//右上角“关闭”按钮响应方法
		
	}
	
	@FXML
	private void onButtonPlayListCollect(ActionEvent event){//播放列表里“收藏全部”按钮响应方法
		
	}
	
	@FXML
	private void onButtonPlayListClear(ActionEvent event){//播放列表里“清空”按钮响应方法
		
	}
	
	@FXML
	private void onButtonHistoryListClear(ActionEvent event){//历史记录里“清空按钮”响应方法
		
	}
	
	public void initData(){//本页面的初始化方法
		
	}
}
