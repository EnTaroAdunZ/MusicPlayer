package com.example.controller;


import java.io.IOException;
import java.io.InputStream;

import com.example.Main.Main;
import com.example.Main.MusicUtils;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * @author Tony Yao
 * 该类是MusicPlayMain.fxml 即程序主页面里控件的id和常用方法的操作类
 */
public class MainPageController {
	
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
	private Button Button_addMusicList;//添加歌单按钮的id
	
	@FXML
	private Button Button_search;//查找歌曲的按钮id
	
	@FXML
	private Button Button_setting;//设置按钮的id
	
	@FXML
	private Button Button_localMusic;//“本地音乐”按钮的id
	
	@FXML
	private Button Button_addLocalMusic;//“添加本地音乐”按钮id
	
	@FXML
	private Button Button_addLocalDirectory;//添加本地音乐文件夹按钮id
	
	@FXML
	private Button Button_back;//“后退”按钮的id
	
	@FXML
	private Button Button_forward;//“前进”按钮的id
	
	@FXML
	private Slider Slider_songProgress;//当前播放歌曲的进度条id
	
	@FXML
	private Slider Slider_volumn;//当前播放歌曲的音量条id
	
	@FXML
	private Label Label_FXName;//左上方该程序的名字；
	
	@FXML
	private Label Label_musicListTitle;//左侧歌单栏栏头
	
	@FXML
	private Label Label_currentTime;//当前播放歌曲的当前播放时间id
	
	@FXML
	private Label Label_totalTime;//当前播放歌曲的总时长id
	
	@FXML
	private Label Label_musicTitle;//当前播放歌曲的音乐标题id
	
	@FXML
	private Label Label_singer;//当前播放歌曲的作者名字id
	
	@FXML
	private ImageView ImageView_albumCover;//当前播放歌曲的专辑封面id
	
	@FXML
	private TextField TextField_searchSong;//查找歌曲的文本框id
	
	@FXML
	private ListView<Button> ListView_musicList;//歌单列表的区域id
	
	@FXML
	private TableView<MusicUtils> TableView_musicTable;//歌曲总表的总区域id
	
	@FXML
	private TableColumn<MusicUtils, String> TableColumn_musicID;//歌曲“ID”列的分区域

	@FXML
	private TableColumn<MusicUtils, String> TableColumn_musicTitle;//歌曲“音乐标题”列的分区域

	@FXML
	private TableColumn<MusicUtils, String> TableColumn_musicSinger;//歌曲“歌手”列的分区域

	@FXML
	private TableColumn<MusicUtils, String> TableColumn_albumName;//歌曲“专辑名称”列的分区域

	@FXML
	private TableColumn<MusicUtils, String> TableColumn_musicTimeLength;//歌曲“时长”列的分区域

	@FXML
	private TableColumn<MusicUtils, String> TableColumn_musicSize;//歌曲“大小”列的分区域

	@FXML
	private ScrollBar ScrollBar_musicList;//歌单列表的拖动条id
	
	@FXML
	private ScrollBar ScrollBar_music;//歌曲总表的拖动条id
	
	//以下是不太会用到的pane的id，以防万一会使用到
	@FXML
	private BorderPane BorderPane_mainPage;//主界面的底层paneid
	
	@FXML
	private AnchorPane AnchorPane_top;//顶层部分的区域id（标题栏区域）
	
	@FXML
	private AnchorPane AnchorPane_left;//左边部分的区域id（歌单区域）
	
	@FXML
	private AnchorPane AnchorPane_center;//中间部分的区域id（歌表区域）
	
	@FXML
	private AnchorPane AnchorPane_bottom;//底层部分的区域id（播放条区域）
	
	@FXML
	private AnchorPane AnchorPane_CurrentSongInfo;//左下当前播放歌曲的信息区域id
	
	@FXML
	private HBox Hbox_topItemHbox;//控制顶层部件横向排列的id
	
	@FXML
	private HBox HBox_leftButtonAndScroll;//控制左侧按钮们和滚动条横向排列的id
	
	@FXML 
	private HBox HBox_leftManageField;//控制“我的歌单”Label和“添加歌单”按钮横向排列的id
	
	@FXML
	private HBox Hbox_leftImageAndTitle;//控制左下专辑图片和标题歌手横向排列的id
	
	@FXML
	private HBox HBox_centerMusicListAndScroll;//控制歌表和滚动条横向排列的id
	
	@FXML
	private HBox HBox_bottomItems;//控制底层部件横向排列的id
	
	@FXML
	private VBox VBox_leftItems;//控制左侧部件竖向排列的id
	
	@FXML
	private VBox VBox_leftMainField;//控制左侧按钮们和“我的歌单”竖向排列的id

	@FXML
	private VBox VBox_leftButtons;//控制左侧按钮们竖向排列的id
	
	@FXML
	private VBox Vbox_leftTitleAndSinger;//控制左侧当前播放音乐标题和歌手竖向排列的id
	
	//以下是部分控件的使用方法id
	@FXML
	private void onButtonLast(ActionEvent event){ //按下“上一首”按钮的接口方法，待实现
		
	}
	
	@FXML
	private void onButtonPause(ActionEvent event){ //按下“播放”按钮的接口方法，待实现
		
	}
	
	@FXML
	private void onButtonNext(ActionEvent event){ //按下“下一首”按钮的接口方法，待实现
		
	}
	
	@FXML
	private void onButtonModeSwitch(ActionEvent event){ //按下“switch”按钮的接口方法，待实现
		
	}
	
	@FXML
	private void onButtonAddMusicList(ActionEvent event){ //按下“添加歌单”按钮的接口方法，待实现
		
	}
	
	@FXML
	private void onButtonSearch(ActionEvent event) { //按下“search”按钮的接口方法，待实现
		
	}
	
	@FXML
	private void onButtonSetting(ActionEvent event){ //按下“setting”按钮的接口方法，待实现
		
	}
	
	@FXML
	private void onAddLocalMusic(ActionEvent event){//按下“添加本地音乐”按钮的接口方法，待实现
		
	}
	
	@FXML
	private void onAddLocalDirectory(ActionEvent event){//按下“添加本地音乐文件夹”按钮的接口方法，待实现
		
	}
	
	@FXML
	private void onBack(ActionEvent event){//按下“后退”按钮的接口方法，待实现
		
	}
	
	@FXML
	private void onForward(ActionEvent event){//按下“前进”按钮的接口方法，待实现
		
	}
	
	public void initData(){//用于初始化所有控件的方法接口
		
	}
}
