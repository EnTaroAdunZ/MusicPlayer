package com.example.Main;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

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
	private Slider Slider_songProgress;//当前播放歌曲的进度条id
	
	@FXML
	private Slider Slider_volumn;//当前播放歌曲的音量条id
	
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
	private void onButtonSearch(ActionEvent event){ //按下“search”按钮的接口方法，待实现
		
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
}
