package com.example.controller;

import com.example.Main.MusicUtils;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * @author Tony Yao
 * 该类是fxml文件SearchPage.fxml 的控制类，用于声明各控件的id和方法接口 
 */
public class SearchPageController {
	@FXML
	private AnchorPane AnchorPane_searchPage;//整个搜索页面的底层pane
	
	@FXML
	private VBox VBox_vbox;//搜索页面的排列框
	
	@FXML
	private Label Label_searchResult;//搜索页面里显示搜索简略信息的文本Label的Id
	
	@FXML
	private TabPane TabPane_tabPane;//搜索页面的标签栏pane的id
	
	@FXML
	private Tab Tab_singleMusic;//“单曲”标签的id
	
	@FXML
	private Tab Tab_musicSinger;//“歌手”标签的id
	
	@FXML
	private Tab Tab_album;//“专辑”标签的id
	
	@FXML
	private Tab Tab_musicList;//“歌单”标签的id
	
	@FXML
	private Tab Tab_musicLyrics;//“歌词”标签的id
	
	@FXML
	private TableView<MusicUtils> TableView_searchMusicPage;//单曲栏下的歌表id
	
	@FXML
	private TableColumn<MusicUtils, String> TableColumn_search_ID;//歌表中隐藏的“ID”栏的id
	
	@FXML
	private TableColumn<MusicUtils, String> TableColumn_search_musicTitle;//歌表中的“音乐标题”栏的id
	
	@FXML
	private TableColumn<MusicUtils, String> TableColumn_search_singer;//歌表中的“歌手”栏的id
	
	@FXML
	private TableColumn<MusicUtils, String> TableColumn_search_albumName;//歌表中的“专辑”栏的id

	@FXML
	private TableColumn<MusicUtils, String> TableColumn_search_timeLength;//歌表中的“时长”栏的id
	
	@FXML
	private ListView<Button> ListView_SingerPage;//歌手栏下的按钮列表id

	@FXML
	private ListView<Button> ListView_albumPage;//专辑栏下的按钮列表id

	@FXML
	private ListView<Button> ListView_musicListPage;//歌单栏下的按钮列表id

	@FXML
	private ListView<Button> ListView_lyricsPage;//歌词栏下的按钮列表id
	
}
