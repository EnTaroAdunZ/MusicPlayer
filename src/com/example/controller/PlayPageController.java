package com.example.controller;

import com.example.controller.Controller.*;
import com.example.event.MainAction;
import com.example.service.LrcAnalyzer.LrcData;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 * @author Tony Yao
 * 该类是fxml文件PlayPage.fxml 的控制类，用于声明各控件的id和方法接口  
 */
public class PlayPageController implements Controller, StackController{
	//以下是各控件的ID
	@FXML
	private Button Button_PlayPage_back;//按钮“返回”的id
	
	@FXML
	private Label Label_musicTitle;//文本音乐标题的id
	
	@FXML
	private Label Label_albumName;//文本专辑名字的id
	
	@FXML
	private Label Label_singer;//文本歌手的id
	
	@FXML
	private Label Label_source;//文本来源的id

	@FXML
	private TableView<LrcData> TableView_lyricsArea;//歌词滚动板的id
	
	@FXML
	private TableColumn<LrcData, String> TableColumn_lyric;//歌词行列的id
	
	@FXML
	private ImageView ImageView_albumCover;//专辑封面的id
	
	@FXML
	private StackPane StackPane_playPage;//整个播放页面的paneid
	
	@FXML
	private AnchorPane AnchorPane_leftPlayPage;//左半部分页面的paneid
	
	@FXML
	private AnchorPane AnchorPane_lyricBottom;//歌词滚动页底部
	
	@FXML
	private HBox HBox_totalHBox;//整个页面的Hbox
	
	@FXML
	private HBox HBox_buttonHBox;//左下按钮排版的Hbox

	@FXML
	private HBox HBox_titleHBox;//音乐标题排版的Hbox
	
	@FXML
	private HBox HBox_infoHBox;//音乐简略信息的Hbox
	
	@FXML
	private VBox VBox_leftVBox;//左部分布局的Vbox
	
	@FXML
	private VBox VBox_rightVBox;//右部分布局的Vbox
	
	//以下是响应方法的接口
	
	public Button getButton_PlayPage_back() {
		return Button_PlayPage_back;
	}

	public Label getLabel_musicTitle() {
		return Label_musicTitle;
	}

	public Label getLabel_albumName() {
		return Label_albumName;
	}

	public Label getLabel_singer() {
		return Label_singer;
	}

	public Label getLabel_source() {
		return Label_source;
	}

	public TableView<LrcData> getTableView_lyricsArea() {
		return TableView_lyricsArea;
	}

	public ImageView getImageView_albumCover() {
		return ImageView_albumCover;
	}

	public StackPane getStackPane_playPage() {
		return StackPane_playPage;
	}

	public AnchorPane getAnchorPane_leftPlayPage() {
		return AnchorPane_leftPlayPage;
	}

	public HBox getHBox_totalHBox() {
		return HBox_totalHBox;
	}

	public HBox getHBox_buttonHBox() {
		return HBox_buttonHBox;
	}

	public HBox getHBox_titleHBox() {
		return HBox_titleHBox;
	}

	public HBox getHBox_infoHBox() {
		return HBox_infoHBox;
	}

	public VBox getVBox_leftVBox() {
		return VBox_leftVBox;
	}

	public VBox getVBox_rightVBox() {
		return VBox_rightVBox;
	}

	@Override
	public StackPane getStackPane() {
		return getStackPane_playPage();
	}
	
	@FXML
	private void onPlayPageBack(ActionEvent event){//按钮“返回”的响应方法
		ma.reverse();
	}
	
	public void initData(MainAction ma){//初始化数据，待实现
		setCss();
		this.ma = ma;
		TableColumn_lyric.setCellValueFactory(new PropertyValueFactory<>("lrcLine"));
		TableColumn_lyric.setSortable(false);
		
	}
	
	public void setCss(){
		Label_musicTitle.getStyleClass().set(0, "lightLabel");
		Label_albumName.getStyleClass().set(0, "lightLabel");
		Label_singer.getStyleClass().set(0, "lightLabel");
		Label_source.getStyleClass().set(0, "lightLabel");
		ImageView_albumCover.setImage(new Image("com/example/css/play/demoLarge.jpg"));
		
		Button_PlayPage_back.getStyleClass().set(0, "playclose");
		TableView_lyricsArea.getStyleClass().add("tableViewBackGround");
	}
	
	private MainAction ma;
}
