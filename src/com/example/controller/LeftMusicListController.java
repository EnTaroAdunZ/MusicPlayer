package com.example.controller;

import java.util.Date;
import java.util.List;

import com.example.entity.SongMenu;
import com.example.event.MainAction;
import com.example.event.TagClickAction;
import com.example.service.PlayOperate;
import com.example.service.SongMenuOperate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import jdk.nashorn.internal.ir.Labels;

/**
 * 
 * @author Tony Yao
 * 该类是LeftMusicList.fxml 即程序左侧歌单列表里控件的id和常用方法的操作类 
 */
public class LeftMusicListController implements Controller{
	//以下是各控件的id
	@FXML
	private AnchorPane AnchorPane_left;//整个歌单列表的底部paneid
	
	@FXML
	private VBox VBox_items;//控制各控件纵向排序的id
	
	
	@FXML
	private VBox VBox_leftMainField;//左侧区域纵向排序的id
	
	@FXML
	private VBox VBox_leftButtons;//按钮们纵向排序的id
	
	@FXML
	private Button Button_localMusic;//“本地音乐”按钮的id
	
	@FXML
	private Button Button_addLocalMusic;//“添加本地音乐”按钮的id
	
	@FXML
	private Button Button_addLocalDirectory;//“添加本地音乐文件夹”按钮的id
	
	@FXML
	private HBox HBox_leftManageField;//左中标题栏横向排序id
	
	@FXML
	private Label Label_musicListTitle;//“我的歌单：”文本id
	
	@FXML
	private Button Button_addMusicList;//按钮“+”按钮id
	
	@FXML
	private ListView<Button> ListView_musicList;//歌单按钮列表的id
	
	@FXML
	private AnchorPane AnchorPane_CurrentSongInfo;//当前播放歌曲小窗口id
	
	@FXML
	private HBox Hbox_leftImageAndTitle;//控制排序id
	
	@FXML
	private ImageView ImageView_albumCover;//专辑封面的id
	
	@FXML
	private VBox Vbox_leftTitleAndSinger;//控制纵向排序的id
	
	@FXML
	private Label Label_musicTitle;//音乐标题的id
	
	@FXML
	private Label Label_singer;//歌手的id
	
	public AnchorPane getAnchorPane_left() {
		return AnchorPane_left;
	}

	public VBox getVBox_items() {
		return VBox_items;
	}


	public VBox getVBox_leftMainField() {
		return VBox_leftMainField;
	}

	public VBox getVBox_leftButtons() {
		return VBox_leftButtons;
	}

	public Button getButton_localMusic() {
		return Button_localMusic;
	}

	public Button getButton_addLocalMusic() {
		return Button_addLocalMusic;
	}

	public Button getButton_addLocalDirectory() {
		return Button_addLocalDirectory;
	}

	public HBox getHBox_leftManageField() {
		return HBox_leftManageField;
	}

	public Label getLabel_musicListTitle() {
		return Label_musicListTitle;
	}

	public Button getButton_addMusicList() {
		return Button_addMusicList;
	}

	public ListView<Button> getListView_musicList() {
		return ListView_musicList;
	}

	public AnchorPane getAnchorPane_CurrentSongInfo() {
		return AnchorPane_CurrentSongInfo;
	}

	public HBox getHbox_leftImageAndTitle() {
		return Hbox_leftImageAndTitle;
	}

	public ImageView getImageView_albumCover() {
		return ImageView_albumCover;
	}

	public VBox getVbox_leftTitleAndSinger() {
		return Vbox_leftTitleAndSinger;
	}

	public Label getLabel_musicTitle() {
		return Label_musicTitle;
	}

	public Label getLabel_singer() {
		return Label_singer;
	}

	//以下是方法接口
	@FXML
	private void onLocalMusic(ActionEvent event){//按钮“本地音乐”的id
		ma.local();
	}
	
	@FXML
	private void onAddLocalMusic(ActionEvent event){//按钮“添加本地音乐”的id
		ma.addLocalMusic();
	}
	
	@FXML
	private void onAddLocalDirectory(ActionEvent event){//按钮“添加本地音乐文件夹”的id
		ma.addLocalDirectory();
	}
	
	@FXML
	private void onButtonAddMusicList(ActionEvent event){//按钮“+”的id
		ma.addMusicList(Button_addMusicList, ListView_musicList);
	}
	
	
    
    public static void initPlayer() {
    	 PlayOperate.getPlayOperate();
    	
    }
	
	public void initData(MainAction ma){//初始化数据，待实现
		setCss();
		initSongMenu(ma);
		this.ma = ma;
		pp = Page.newPage(Controller.PLAY, ma.getGui().getPlaypage(), ma.getGui().getPpC());
		ImageView_albumCover.setOnMouseClicked(e ->{
			MainAction.pq.add(pp);
			MainAction.show(pp);
		});
//		initPlayer();
	}
	
	private void setCss(){
		Label_musicTitle.getStyleClass().add("lightLabel");
		Label_singer.getStyleClass().add("lightLabel");
		Label_musicListTitle.getStyleClass().add("lightLabel");
		Button_localMusic.getStyleClass().add("lightButton");
		Button_addLocalMusic.getStyleClass().add("lightButton");
		Button_addLocalDirectory.getStyleClass().add("lightButton");
		
		Button_addMusicList.getStyleClass().remove(0);
		Button_addMusicList.getStyleClass().add("AddList");
		
		ImageView_albumCover.setImage(new Image("com/example/css/left/demo.jpg"));
		
	}
	
	private void initSongMenu(MainAction ma){	//调用歌单接口在初始化时加载所有本地歌单
		List<SongMenu> list = SongMenuOperate.getAllSongMenu();
		if(!list.isEmpty()){
			for(SongMenu item : list){
				Button nb = new Button(item.getSongMenuName());
				ma.createMusicList(nb);//FIXME new date先假设
			}
		}
	}
	
	private MainAction ma;
	private Page pp;
}
