package com.example.controller;

import com.example.event.EnterAction;
import com.example.event.MainAction;
import com.example.gui.GUI;

import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
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
	private Button Button_minimize;//最小化按钮的id
	
	@FXML
	private Button Button_close;//关闭按钮的id
	
	@FXML
	private Button Button_playList;//按钮“播放列表”的id
	
	@FXML
	private Label Label_playListNum;//文本“播放列表”按钮旁边的播放列表歌曲数量id
	
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

	public Button getButton_playList() {
		return Button_playList;
	}

	public Label getLabel_currentTime() {
		return Label_currentTime;
	}

	public Label getLabel_playListNum() {
		return Label_playListNum;
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
		try {
			ma.last();
		} catch (InterruptedException e) {
		}
	}
	
	@FXML
	private void onButtonPause(ActionEvent event){//按钮“暂停”的响应方法
		ma.pause();		
//		System.out.println("??");System.out.println("??");
	}
	
	@FXML
	private void onButtonNext(ActionEvent event){//按钮“下一首”的响应方法
		try {
			ma.next();
		} catch (InterruptedException e) {
		}
	}
	
	@FXML
	private void onButtonModeSwitch(ActionEvent event){//按钮“Switch”的响应方法
		ma.modeSwitch(Button_modeSwitch);
	}
	
	@FXML
	private void onBack(ActionEvent event){//按钮“<”的响应方法
		ma.back();
	}
	
	@FXML
	private void onForward(ActionEvent event){//按钮“>”的响应方法
		ma.fore();
	}

	@FXML
	private void onButtonSearch(ActionEvent event){//按钮“Search”的响应方法
		ma.search(Button_search, TextField_searchSong, event);
	}
	
	@FXML
	private void onButtonSetting(ActionEvent event){//按钮“Setting”的响应方法
		
	}
	
	@FXML
	private void onButtonMinimize(ActionEvent event){//按钮“最小化”的响应方法
		GUI.staticStage.setIconified(true);
	}
	
	@FXML
	private void onButtonPlayList(ActionEvent event){//右下角按钮“播放列表”的响应方法
		ma.playList();
	}
	
	@FXML
	private void onButtonClose(ActionEvent event){//按钮“关闭程序”id
		Platform.exit();
		System.exit(0);
	}
	
	public void initData(MainAction ma){//初始化数据，待实现
		setCss();
		this.ma = ma;
		Slider_volumn.setValue(100);
		ma.modiVolume(100);
		TextField_searchSong.setOnKeyPressed(new EnterAction(TextField_searchSong, Button_search));
		Slider_songProgress.setOnMouseClicked(e ->{
			double d = Slider_songProgress.getValue();
			if(d!=progressOldValue){
				ma.modiProgress(d);
			}
			
			progressOldValue=d;
		});
		Slider_volumn.valueProperty().addListener((o, ov, nv)->{
			int i = Math.round((float)(double)nv);
			ma.modiVolume(i);
		});
		sum.addListener((o, ov, nv) ->{
			Label_playListNum.setText("总"+ (int) nv+"首");
		});
	}
	
	private void setCss(){
		
		Button_last.getStyleClass().remove(0);
		Button_next.getStyleClass().remove(0);
		Button_search.getStyleClass().remove(0);
		Button_setting.getStyleClass().remove(0);
		Button_back.getStyleClass().remove(0);
		Button_forward.getStyleClass().remove(0);
		Button_minimize.getStyleClass().remove(0);
		Button_close.getStyleClass().remove(0);
		Button_playList.getStyleClass().remove(0);
		
		Button_modeSwitch.getStyleClass().set(0, "buttonOrderPlay");
		Button_pause.getStyleClass().set(0, "buttonPlay");
		
		Label_FXName.getStyleClass().add("lightLabel");
		Label_currentTime.getStyleClass().add("lightLabel");
		Label_totalTime.getStyleClass().add("lightLabel");
		Label_playListNum.getStyleClass().add("lightLabel");
		TextField_searchSong.getStyleClass().add("ListField");
	}
	
	private MainAction ma;
	private double progressOldValue;
	private IntegerProperty sum = new SimpleIntegerProperty(0);
}

