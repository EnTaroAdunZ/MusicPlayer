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
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class PlayListPageController {
	@FXML
	private AnchorPane AnchorPane_playListPage;//整个页面的基底

	@FXML
	private AnchorPane AnchorPane_titlePane;//标题栏基底
	
	@FXML
	private AnchorPane AnchorPane_infoArea;//内容栏基底
	
	@FXML
	private Button Button_playListClose;//右上角关闭按钮id

	@FXML
	private Button Button_playListClear;//清空按钮id
	
	@FXML
	private TableView<MusicUtils> TableView_playList;//播放列表里歌表id

	@FXML
	private TableColumn<MusicUtils, String> TableColumn_song;//播放列表里歌表歌名栏id

	@FXML
	private TableColumn<MusicUtils, String> TableColumn_singer;//播放列表里歌表歌手栏id

	@FXML
	private TableColumn<MusicUtils, String> TableColumn_length;//播放列表里歌表时长栏id

	@FXML
	private Label Label_title;//标题栏标题文本id

	@FXML
	private Label Label_playListNum;//历史记录里当前歌曲的总数文本id

	//
	@FXML
	private void onButtonPlayListClose(ActionEvent event){//右上角“关闭”按钮响应方法
		
	}

	@FXML
	private void onButtonPlayListClear(ActionEvent event){//播放列表里“清空”按钮响应方法
		
	}
	
	@FXML
	public void initialize(){//本页面的初始化方法
		Label_title.getStyleClass().add("lightLabel");
		Label_playListNum.getStyleClass().add("lightLabel");
		
		AnchorPane_playListPage.getStyleClass().add("paneStyleSet");
		AnchorPane_infoArea.getStyleClass().add("paneStyleSet");
//		AnchorPane_titlePane.getStyleClass().add("paneStyleSet");
		
		Button_playListClose.getStyleClass().remove(0);
		Button_playListClear.getStyleClass().remove(0);
	}
}
