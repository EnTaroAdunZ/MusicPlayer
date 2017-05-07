package com.example.controller;

import java.util.List;

import com.example.controller.Controller.ContentController;
import com.example.event.MainAction;
import com.example.gui.MusicUtils;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class MusicListPageController implements ContentController{
	@FXML
	private StackPane StackPane_MusicListPage;//底层paneid
	
	@FXML
	private ScrollPane ScrollPane_totalPage;//整个歌单滚动页面id
	
	@FXML
	private AnchorPane AnchorPane_topPage;//上部分页面id
	
	@FXML
	private ImageView ImageView_ListCover;//歌单封面id
	
	@FXML
	private VBox VBox_MusicListPage;//纵向排序控件
	
	@FXML
	private Label Label_ListName;//歌单名字
	
	@FXML
	private Button Button_PlayAll;//“播放全部”按钮id
	
	@FXML
	private Button Button_searchMusic;//“搜索音乐”按钮id
	
	@FXML
	private Button Button_addMusic;//“+”添加音乐按钮id
	
	@FXML
	private TextField TextField_searchListMusic;//“搜索音乐”文本框id
	
	@FXML
	private Label Label_ListCreateTime;//歌单创建时间id
	
	@FXML
	private AnchorPane AnchorPane_bottomPage;//下部分页面id
	
	@FXML
	private TableView<MusicUtils> TableView_musicTable;//歌表id
	
	@FXML
	private TableColumn<MusicUtils, Integer> TableColumn_musicID;//id列id

	@FXML
	private TableColumn<MusicUtils, Boolean> TableColumn_like;//“喜欢”按钮列id

	@FXML
	private TableColumn<MusicUtils, String> TableColumn_musicTitle;//音乐标题列id

	@FXML
	private TableColumn<MusicUtils, String> TableColumn_musicSinger;//歌手列id

	@FXML
	private TableColumn<MusicUtils, String> TableColumn_albumName;//专辑列id

	@FXML
	private TableColumn<MusicUtils, String> TableColumn_musicTimeLength;//时长列id
	
	
	public StackPane getStackPane_MusicListPage() {
		return StackPane_MusicListPage;
	}

	public ScrollPane getScrollPane_totalPage() {
		return ScrollPane_totalPage;
	}

	public AnchorPane getAnchorPane_topPage() {
		return AnchorPane_topPage;
	}

	public ImageView getImageView_ListCover() {
		return ImageView_ListCover;
	}

	public VBox getVBox_MusicListPage() {
		return VBox_MusicListPage;
	}

	public Label getLabel_ListName() {
		return Label_ListName;
	}

	public Button getButton_PlayAll() {
		return Button_PlayAll;
	}

	public Button getButton_searchMusic() {
		return Button_searchMusic;
	}

	public TextField getTextField_searchListMusic() {
		return TextField_searchListMusic;
	}

	public Label getLabel_ListCreateTime() {
		return Label_ListCreateTime;
	}

	public AnchorPane getAnchorPane_bottomPage() {
		return AnchorPane_bottomPage;
	}

	public TableView<MusicUtils> getTableView_musicTable() {
		return TableView_musicTable;
	}

	public TableColumn<MusicUtils, Integer> getTableColumn_musicID() {
		return TableColumn_musicID;
	}

	public TableColumn<MusicUtils, Boolean> getTableColumn_like() {
		return TableColumn_like;
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
	
	@FXML
	private void onPlayAll(ActionEvent event){//“播放全部”按钮id
		
	}
	
	public void initData(MainAction ma, String name, String date, List<MusicUtils> list){
		setCss();
		this.ma = ma;
		Label_ListName.setText(name);
		Label_ListCreateTime.setText(date);
		TableView_musicTable.setItems(FXCollections.observableArrayList(list));
		TableColumn_musicID.setCellValueFactory(new MainAction.IndexFactory<MusicUtils>(TableView_musicTable));
		TableColumn_musicTitle.setCellValueFactory(new PropertyValueFactory<>("musicTitle"));
		TableColumn_musicSinger.setCellValueFactory(new PropertyValueFactory<>("musicSinger"));
		TableColumn_albumName.setCellValueFactory(new PropertyValueFactory<>("albumName"));
		TableColumn_musicTimeLength.setCellValueFactory(new PropertyValueFactory<>("musicTimeLength"));
		TableColumn_like.setCellFactory(CheckBoxTableCell.forTableColumn(new MainAction.likeCheckBox(TableView_musicTable)));
		
		TableColumn_like.setEditable(true);TableView_musicTable.setEditable(true);
		TableColumn_like.setSortable(false);TableColumn_musicID.setSortable(false);
		
		TableView_musicTable.setOnMouseClicked(ma.tca);
		TableView_musicTable.focusedProperty().addListener(new MainAction.TableCleaner<MusicUtils>(TableView_musicTable));
	}
	
	public void onSearchMusic(ActionEvent event){//“查找音乐”按钮id
		
	}
	
	public void onAddMusic(ActionEvent event){//“添加音乐”按钮方法
		
	}
	
	private void setCss(){
		TableView_musicTable.getSelectionModel().setSelectionMode(javafx.scene.control.SelectionMode.MULTIPLE);
				
		Label_ListName.getStyleClass().add("lightLabel");
		Label_ListCreateTime.getStyleClass().add("lightLabel");
		ImageView_ListCover.setImage(new Image("com/example/css/musiclist/demoMusicList.jpg"));
		
		TableColumn_musicID.getStyleClass().add("tableColumn");
		TableColumn_like.getStyleClass().add("tableColumn");
		TableColumn_musicTitle.getStyleClass().add("tableColumn");
		TableColumn_musicSinger.getStyleClass().add("tableColumn");
		TableColumn_albumName.getStyleClass().add("tableColumn");
		TableColumn_musicTimeLength.getStyleClass().add("tableColumn");
		
		Button_PlayAll.getStyleClass().set(0, "lightButton");
		Button_addMusic.getStyleClass().set(0, "lightButton");
		Button_searchMusic.getStyleClass().remove(0);
		
		TextField_searchListMusic.getStyleClass().add("ListField");
	}
	
	private MainAction ma;
}
