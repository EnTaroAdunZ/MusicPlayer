package com.example.controller;

import java.awt.Color;
import java.util.ArrayList;

import com.example.event.MainAction;
import com.example.gui.MusicUtils;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

/**
 * 
 * @author Tony Yao
 * 该类是LocalMusicPage.fxml 即程序本地音乐列表里控件的id和常用方法的操作类 
 */
public class LocalMusicPageController implements Controller{
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
	private Label Label_localMusic;//“本地音乐”
	
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
	
	public AnchorPane getAnchorPane_center() {
		return AnchorPane_center;
	}

	public VBox getVBox_LocalPage() {
		return VBox_LocalPage;
	}

	public AnchorPane getTopLocalTitle() {
		return topLocalTitle;
	}

	public HBox getHBox_localButton() {
		return HBox_localButton;
	}

	public Label getLabel_musicNum() {
		return Label_musicNum;
	}

	public Button getButton_selectDirectory() {
		return Button_selectDirectory;
	}

	public Button getButton_playAll() {
		return Button_playAll;
	}

	public TextField getTextField_localSearchText() {
		return TextField_localSearchText;
	}

	public Button getButton_localSearch() {
		return Button_localSearch;
	}

	public ScrollPane getScrollPane_localMusicList() {
		return ScrollPane_localMusicList;
	}

	public HBox getHBox_centerMusicListAndScroll() {
		return HBox_centerMusicListAndScroll;
	}

	public TableView<MusicUtils> getTableView_musicTable() {
		return TableView_musicTable;
	}

	public TableColumn<MusicUtils, String> getTableColumn_musicID() {
		return TableColumn_musicID;
	}

	public TableColumn<MusicUtils, String> getTableColumn_musicTitle() {
		return TableColumn_musicTitle;
	}

	public TableColumn<MusicUtils, String> getTableColumn_musicSinger() {
		return TableColumn_musicSinger;
	}

	public TableColumn<MusicUtils, String> getTableColumn_albumName() {
		return TableColumn_albumName;
	}

	public TableColumn<MusicUtils, String> getTableColumn_musicTimeLength() {
		return TableColumn_musicTimeLength;
	}

	public TableColumn<MusicUtils, String> getTableColumn_musicSize() {
		return TableColumn_musicSize;
	}

	@FXML
	private void onSelectDirectory(ActionEvent event){//“选择目录”按钮的响应方法
		df.show();
	}
	
	@FXML
	private void onPlayAll(ActionEvent event){//“播放全部”按钮的响应方法
		
	}
	
	@FXML
	private void onLocalSearch(ActionEvent event){//“本地搜索”按钮的响应方法
		
	}
	
	public void initData(MainAction ma){//初始化数据，待实现
		setCss();
		this.ma = ma;
		ArrayList<String> list = new ArrayList<>();
		list.add("C:\\");
		df.init(list);
	}
	private MainAction ma;
	static Dirfilter df = new Dirfilter();
	
	private void setCss(){
		TableView_musicTable.getSelectionModel().setSelectionMode(javafx.scene.control.SelectionMode.MULTIPLE);
		Button_localSearch.getStyleClass().remove(0);
		
		Label_localMusic.getStyleClass().add("lightLabel");
		Label_musicNum.getStyleClass().add("lightLabel");
		Button_selectDirectory.getStyleClass().add("lightButton");
		Button_playAll.getStyleClass().add("lightButton");
		TextField_localSearchText.getStyleClass().add("ListField");
		
		TableColumn_musicID.getStyleClass().add("tableColumn");
		TableColumn_musicTitle.getStyleClass().add("tableColumn");
		TableColumn_musicSinger.getStyleClass().add("tableColumn");
		TableColumn_albumName.getStyleClass().add("tableColumn");
		TableColumn_musicTimeLength.getStyleClass().add("tableColumn");
		TableColumn_musicSize.getStyleClass().add("tableColumn");
		
	}
 }

class Dirfilter{
	static final String titlestr = "选择本地音乐文件夹",
			introstr = "将自动扫描你勾选的目录，文件增删实时同步。";
	static final Label title = new Label(titlestr), intro = new Label(introstr);
	static final Button ok = new Button("确认"),
			add = new Button("添加文件夹");
	private HBox hb = new HBox();
	private VBox vb = new VBox();
	private ArrayList<String> dirList;
	private ListView<CheckBox> diropt = new ListView<>();
	private Stage stage;
	
	void init(ArrayList<String> list) {
		dirList = list;
		for(String s : dirList) {
			CheckBox cb = new CheckBox(s);
			cb.getStyleClass().add("checkbox");
			diropt.getItems().add(cb);
		}
		setCss();
		hb.getChildren().addAll(ok,add);
		vb.getChildren().addAll(title,intro,diropt,hb);
		
		stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		Scene scene = new Scene(vb,400,400);
		scene.getStylesheets().addAll("com/example/css/local/LocalMusicPageCss.css");
		//stage.initStyle(StageStyle.UNDECORATED); //待后期实现按钮功能后去掉标题栏
		stage.setScene(scene);
	}
	
	void show() {
		stage.show();
		stage.requestFocus();
	}
	
	void setCss(){
		title.getStyleClass().add("lightLabel");
		title.setFont(Font.font(20));
		intro.getStyleClass().add("lightLabel");
		ok.getStyleClass().add("lightButton");
		add.getStyleClass().add("lightButton");
		hb.setAlignment(Pos.CENTER); hb.setSpacing(150);
		hb.getStyleClass().add("lightLabel");
		vb.getStyleClass().add("lightLabel");
	}
}