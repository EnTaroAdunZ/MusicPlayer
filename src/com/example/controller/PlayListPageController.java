package com.example.controller;

import java.util.ArrayList;

import com.example.Global.GlobalVariable;
import com.example.event.MainAction;
import com.example.gui.MusicUtils;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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

	public AnchorPane getAnchorPane_playListPage() {
		return AnchorPane_playListPage;
	}

	public AnchorPane getAnchorPane_titlePane() {
		return AnchorPane_titlePane;
	}

	public AnchorPane getAnchorPane_infoArea() {
		return AnchorPane_infoArea;
	}

	public Button getButton_playListClose() {
		return Button_playListClose;
	}

	public Button getButton_playListClear() {
		return Button_playListClear;
	}

	public TableView<MusicUtils> getTableView_playList() {
		return TableView_playList;
	}

	public TableColumn<MusicUtils, String> getTableColumn_song() {
		return TableColumn_song;
	}

	public TableColumn<MusicUtils, String> getTableColumn_singer() {
		return TableColumn_singer;
	}

	public TableColumn<MusicUtils, String> getTableColumn_length() {
		return TableColumn_length;
	}

	public Label getLabel_title() {
		return Label_title;
	}

	public Label getLabel_playListNum() {
		return Label_playListNum;
	}

	//
	@FXML
	private void onButtonPlayListClose(ActionEvent event){//右上角“关闭”按钮响应方法
		ma.getGui().getTabC().getButton_playList().fire();
	}

	@FXML
	private void onButtonPlayListClear(ActionEvent event){//播放列表里“清空”按钮响应方法
		MainAction.setCurrentList(defaultEmptyList);
	}
	
	
	public void initData(MainAction ma){//本页面的初始化方法
		setCss();
		this.ma = ma;
		
		TableView_playList.getItems().addAll(GlobalVariable.currentList);
		TableColumn_song.setCellValueFactory(new PropertyValueFactory<>("musicTitle"));
		TableColumn_singer.setCellValueFactory(new PropertyValueFactory<>("musicSinger"));
		TableColumn_length.setCellValueFactory(new PropertyValueFactory<>("musicTimeLength"));
		TableView_playList.setOnMouseClicked(ma.tca);
		sum.addListener((o, ov, nv) ->{
			Label_playListNum.setText("总"+ (int) nv+"首");
		});
	}
	
	private void setCss() {
		Label_title.getStyleClass().add("lightLabel");
		Label_playListNum.getStyleClass().add("lightLabel");
		
		Button_playListClose.getStyleClass().remove(0);
		Button_playListClear.getStyleClass().remove(0);
		
	}
	
	public static ArrayList<MusicUtils> defaultEmptyList = new ArrayList<>();
	private MainAction ma;
	private IntegerProperty sum = new SimpleIntegerProperty(0);
}
