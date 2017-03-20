package com.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

/**
 * @author Tony Yao
 * 该类是fxml文件TopAndBottomPageController.fxml 的控制类，
 * 即程序上下侧控制列表里控件的id和常用方法的操作类 
 */
public class TopAndBottomPageController implements Controller{
	//以下是各控件的ID
	@FXML
	private Button Button_last;//“上一首”按钮的id
	
	@FXML
	private Button Button_pause;//“暂停/播放”按钮的id
	
	@FXML
	private Button Button_next;//“下一首”按钮的id
	
	@FXML
	private Button Button_modeSwitch;//切换播放模式的按钮id
	
	@FXML
	private Button Button_back;//“后退”按钮的id
	
	@FXML
	private Button Button_forward;//“前进”按钮的id
	
	@FXML
	private Button Button_search;//查找歌曲的按钮id
	
	@FXML
	private Button Button_setting;//设置按钮的id
	
	@FXML
	private Label Label_currentTime;//当前播放歌曲的当前播放时间id
	
	@FXML
	private Label Label_totalTime;//当前播放歌曲的总时长id

	@FXML
	private Label Label_FXName;//左上方该程序的名字；
	
	@FXML
	private TextField TextField_searchSong;//查找歌曲的文本框id
	
	@FXML
	private Slider Slider_songProgress;//当前播放歌曲的进度条id
	
	@FXML
	private Slider Slider_volumn;//当前播放歌曲的音量条id
	
	@FXML
	private AnchorPane AnchorPane_top;//顶部区域的id
	
	@FXML
	private AnchorPane AnchorPane_bottom;//底部区域的id
	
	@FXML
	private BorderPane BorderPane_mainPage;//主页面的底层id
	
	@FXML
	private HBox Hbox_topItemHbox;//顶部横向排序的id
	
	@FXML
	private HBox HBox_bottomItems;//底部横向排序的id
	
	//以下是方法接口
	
	public Button getButton_last() {
		return Button_last;
	}

	public Button getButton_pause() {
		return Button_pause;
	}

	public Button getButton_next() {
		return Button_next;
	}

	public Button getButton_modeSwitch() {
		return Button_modeSwitch;
	}

	public Button getButton_back() {
		return Button_back;
	}

	public Button getButton_forward() {
		return Button_forward;
	}

	public Button getButton_search() {
		return Button_search;
	}

	public Button getButton_setting() {
		return Button_setting;
	}

	public Label getLabel_currentTime() {
		return Label_currentTime;
	}

	public Label getLabel_totalTime() {
		return Label_totalTime;
	}

	public Label getLabel_FXName() {
		return Label_FXName;
	}

	public TextField getTextField_searchSong() {
		return TextField_searchSong;
	}

	public Slider getSlider_songProgress() {
		return Slider_songProgress;
	}

	public Slider getSlider_volumn() {
		return Slider_volumn;
	}

	public AnchorPane getAnchorPane_top() {
		return AnchorPane_top;
	}

	public AnchorPane getAnchorPane_bottom() {
		return AnchorPane_bottom;
	}

	public BorderPane getBorderPane_mainPage() {
		return BorderPane_mainPage;
	}

	public HBox getHbox_topItemHbox() {
		return Hbox_topItemHbox;
	}

	public HBox getHBox_bottomItems() {
		return HBox_bottomItems;
	}

	@FXML
	private void onButtonLast(ActionEvent event){//按钮“上一首”的响应方法
		
	}
	
	@FXML
	private void onButtonPause(ActionEvent event){//按钮“暂停”的响应方法
		
	}
	
	@FXML
	private void onButtonNext(ActionEvent event){//按钮“下一首”的响应方法
		
	}
	
	@FXML
	private void onButtonModeSwitch(ActionEvent event){//按钮“Switch”的响应方法
		
	}
	
	@FXML
	private void onBack(ActionEvent event){//按钮“<”的响应方法
		
	}
	
	@FXML
	private void onForward(ActionEvent event){//按钮“>”的响应方法
		
	}

	@FXML
	private void onButtonSearch(ActionEvent event){//按钮“Search”的响应方法
		
	}
	
	@FXML
	private void onButtonSetting(ActionEvent event){//按钮“Setting”的响应方法
		
	}
	
	public void initData(){//初始化数据，待实现
		
	}
}
