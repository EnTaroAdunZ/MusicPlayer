package com.example.controller;

import java.util.List;

import com.example.Global.GlobalVariable;
import com.example.controller.Controller.*;
import com.example.event.MainAction;
import com.example.gui.MusicUtils;

import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 * @author Tony Yao
 * 该类是fxml文件SearchPage.fxml 的控制类，用于声明各控件的id和方法接口 
 */
public class SearchPageController implements ContentController, StackController{
	@FXML
	private StackPane StackPane_searchPage;//整个搜索页面的底层pane
	
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
	private TableView<MusicUtils> TableView_musicTable;//单曲栏下的歌表id
	
	@FXML
	private TableColumn<MusicUtils, Integer> TableColumn_search_ID;//歌表中隐藏的“ID”栏的id
	
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

	public StackPane getStackPane_searchPage() {
		return StackPane_searchPage;
	}

	public VBox getVBox_vbox() {
		return VBox_vbox;
	}

	public Label getLabel_searchResult() {
		return Label_searchResult;
	}

	public TabPane getTabPane_tabPane() {
		return TabPane_tabPane;
	}

	public Tab getTab_singleMusic() {
		return Tab_singleMusic;
	}

	public Tab getTab_musicSinger() {
		return Tab_musicSinger;
	}

	public Tab getTab_album() {
		return Tab_album;
	}

	public Tab getTab_musicList() {
		return Tab_musicList;
	}

	public Tab getTab_musicLyrics() {
		return Tab_musicLyrics;
	}

	public TableView<MusicUtils> getTableView_musicTable() {
		return TableView_musicTable;
	}

	public TableColumn<MusicUtils, Integer> getTableColumn_search_ID() {
		return TableColumn_search_ID;
	}

	public TableColumn<MusicUtils, String> getTableColumn_search_musicTitle() {
		return TableColumn_search_musicTitle;
	}

	public TableColumn<MusicUtils, String> getTableColumn_search_singer() {
		return TableColumn_search_singer;
	}

	public TableColumn<MusicUtils, String> getTableColumn_search_albumName() {
		return TableColumn_search_albumName;
	}

	public TableColumn<MusicUtils, String> getTableColumn_search_timeLength() {
		return TableColumn_search_timeLength;
	}

	public ListView<Button> getListView_SingerPage() {
		return ListView_SingerPage;
	}

	public ListView<Button> getListView_albumPage() {
		return ListView_albumPage;
	}

	public ListView<Button> getListView_musicListPage() {
		return ListView_musicListPage;
	}

	public ListView<Button> getListView_lyricsPage() {
		return ListView_lyricsPage;
	}
	
	@Override
	public StackPane getStackPane() {
		return getStackPane_searchPage();
	}
	
	public void initData(MainAction ma, String key, List<MusicUtils> list){//初始化数据，待实现
		this.ma = ma;
		setCss();
		target = key;
		ChangingTab ct = new ChangingTab(TabPane_tabPane);
		
		TableView_musicTable.setItems(FXCollections.observableArrayList(list));
		TableColumn_search_ID.setCellValueFactory(new MainAction.IndexFactory<MusicUtils>(TableView_musicTable));
		TableColumn_search_musicTitle.setCellValueFactory(new PropertyValueFactory<>("musicTitle"));
		TableColumn_search_singer.setCellValueFactory(new PropertyValueFactory<>("musicSinger"));
		TableColumn_search_albumName.setCellValueFactory(new PropertyValueFactory<>("albumName"));
		TableColumn_search_timeLength.setCellValueFactory(new PropertyValueFactory<>("musicTimeLength"));
		
		TableColumn_search_ID.setSortable(false);
		TableView_musicTable.setOnMouseClicked(ma.tca);
		
		Tab_singleMusic.setOnSelectionChanged(ct);
		Tab_musicSinger.setOnSelectionChanged(ct);
		Tab_album.setOnSelectionChanged(ct);
	}
	
	class ChangingTab implements EventHandler<Event>{
		
		public ChangingTab(TabPane tp) {
			this.tp = tp;
		}
		private TabPane tp;
		
		public TabPane getTp() {
			return tp;
		}

		@Override
		public void handle(Event event) {
			int select = tp.getSelectionModel().getSelectedIndex();
			for(Tab t : tp.getTabs())
				t.setContent(null);
			Tab st =tp.getSelectionModel().getSelectedItem();
			st.setContent(TableView_musicTable);
			switch (select) {
			default:
				select = GlobalVariable.SEARCHMODE_SONGNAME;
				break;
			case 1:
				select = GlobalVariable.SEARCHMODE_SINGER;
				break;
			case 2:
				select = GlobalVariable.SEARCHMODE_ALBUM;
				break;
			}
			List<MusicUtils> ml = MainAction.searchsong(target, select);
			TableView_musicTable.getItems().clear();
			TableView_musicTable.getItems().addAll(ml);
			Label_searchResult.setText("搜索\""+ target +"\", 找到" + ml.size() + "首单曲");
		}
	}
	
	private String target = null;
	
	private void setCss(){
		TableView_musicTable.getSelectionModel().setSelectionMode(javafx.scene.control.SelectionMode.MULTIPLE);
		
		TableColumn_search_ID.getStyleClass().add("tableColumn"); 
		TableColumn_search_musicTitle.getStyleClass().add("tableColumn"); 
		TableColumn_search_singer.getStyleClass().add("tableColumn"); 
		TableColumn_search_albumName.getStyleClass().add("tableColumn"); 
		TableColumn_search_timeLength.getStyleClass().add("tableColumn"); 
		
		Label_searchResult.getStyleClass().add("lightLabel"); 
	}
	
	private MainAction ma;
}
