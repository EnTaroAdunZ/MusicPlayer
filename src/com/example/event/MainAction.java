package com.example.event;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import com.example.Global.GlobalVariable;
import com.example.Global.PlayState;
import com.example.controller.*;
import com.example.controller.Page.SearchPage;
import com.example.entity.Song;
import com.example.gui.GUI;
import com.example.gui.MusicUtils;
import com.example.service.*;
import com.example.service.LrcAnalyzer.LrcData;
import com.example.util.SongUtil;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.*;
import javafx.util.Callback;

import static com.example.Global.GlobalVariable.*;

public class MainAction {
	
	//-----------------------------------------Top------------------------
	public boolean back() {
 		PageQueue pq = gui.getPageManager();
 		IntegerProperty i = gui.getIndex(); IntegerProperty s = gui.getSize();
 		int ii = i.get();
 		if(ii < 1)
 			return false;
		Page p;int ss = s.get();
		if(ss < 2) 
			return false;
		p = pq.backward();
		show(p);
		return true;
	}
	
	public void fore() {
 		PageQueue pq = gui.getPageManager();
 		IntegerProperty i = gui.getIndex(); IntegerProperty s = gui.getSize();
		int ii = i.get();int ss = s.get();
 		if(ii >= ss - 1)
 			return;
		Page p = pq.forward();
		show(p);
	}
	
	public void search(Button b, TextField tf, ActionEvent e) {//FIXME
		String key = tf.getText(); 
		ArrayList<MusicUtils> sl = searchsong(key);
		//FIXME
		Page p = giveSearch(key);
		((Page.SearchPage)p).setKey(key);
		pq.add(p);
		show(p);
	}
	
	//-----------------------------------------Bottom---------------------
 	public void last() throws InterruptedException {
		Thread.sleep(100);
		ps.setState_PREMUSIC();
		Thread.sleep(100);	
	}
	
	public void pause() {
		int s = PlayState.getPlayState().getCurrent_state();
		if(s == PAUSINGMUSIC) {
			System.out.println("播放");
			ps.setState_PLAYMUSIC();
		}
		else if(s == PLAYINGMUSIC) {
			System.out.println("暂停");
			ps.setState_PAUSEMUSIC();
		}
		refresh(s);
	}
	
	public void next() throws InterruptedException {
		Thread.sleep(100);
		ps.setState_NEXTMUSIC();
		Thread.sleep(100);
	}
	
	public void modiProgress(double progress) {
		System.out.println("用户点击了:"+progress);
		ps.setProgress(progress);
	}
	
	public void modiVolume(double volume) {
		int v = Math.round((float)volume);
		ps.setCurrent_volume(v);
	}
	
	public void modeSwitch(Button b) {
		String mode = b.getStyleClass().get(0);
		b.getStyleClass().remove(0);
		switch (mode) {
		case "buttonRepeatInOne":
			b.getStyleClass().add("buttonRepeat");	
			ps.setPlAYMODE_LISTLOOP();
			break;
		case "buttonRepeat":
			b.getStyleClass().add("buttonOrderPlay");
			ps.setPlAYMODE_SEQUENCEPLAY();
			break;
		case "buttonOrderPlay":
			b.getStyleClass().add("buttonRandomPlay");
			ps.setPlAYMODE_RAMDOMPLAY();
			break;
		case "buttonRandomPlay":
			b.getStyleClass().add("buttonRepeatInOne");
			ps.setPlAYMODE_SINGLELOOP();
			break;
		default:	break;
		}
	}
	
	public static void progressFeedBack(double progress) {
		int sec = progressTotalSec(tt.getText(), progress);
		ct.setText(progressCal(sec, progress));
		sl.setValue(progress * 100);
		if(loadLrc())
			scrollLrc(sec, progress);
	}
	
	private static String progressCal(int sec,double progress) {
		int sum = sec;
		sum *= progress;
		int rm = sum / 60, rs = sum % 60;
		String r = String.format("%02d:%02d", rm, rs);
		return r;
	}
	
	private static int progressTotalSec(String timeLength, double progress) {
		int  mc = 0, sc =0;
		String m, s;
		String[] t = timeLength.split(":");
		m = t[0];s = t[1];
		mc = Integer.valueOf(m);sc = Integer.valueOf(s);
		int sum = mc * 60 + sc;
		return sum;
	}
	
	//-----------------------------------------Left-----------------------
	public void local() {
		if(pq.getSize() > 0 && pq.getPage() instanceof Page.LocalPage ) 
			return;
		Page p = giveLocal();
		pq.add(p);
		show(p);
	}
	
	public void musiclist(String key) {
		if(currentMenu.get() == key && currentMenu.get().equals("")) 
			return;
		currentMenu.set(key);
//		SongMenu sm = SongMenu.findSongmenuByName(key);
//		String name = sm.getName();FIXME
//		Date d = sm.getCreateDate();
//		
		String date = df.format(new Date());//FIXME
		Page p = giveMusicList(key, date);
		((Page.MusicListPage)p).setKey(key);
		pq.add(p);
		((Page.MusicListPage)p).getController().getLabel_ListName().setText(key);
		show(p);
	}
	
	public void addMusicList(Button b, ListView<Button> l) {
		String t = b.getStyleClass().get(0);
		b.getStyleClass().remove(0);
		if(t.equals("AddList")) {
			vb.getChildren().add(2, hb);
			b.getStyleClass().add("CloseList");
			//tf.requestFocus();
		}
		if(t.equals("CloseList")) {
			vb.getChildren().removeAll(hb);
			b.getStyleClass().add("AddList");
			tf.clear();
		}
	}
	
	public void addLocalMusic() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("打开音乐文件");
		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("MP3", "*.mp3"),
				new FileChooser.ExtensionFilter("flac", "*.flac*"),
				new FileChooser.ExtensionFilter("所有文件", "*.*"));
		List<File> selectedFile = fileChooser.showOpenMultipleDialog(GUI.staticStage);
		List<MusicUtils> ml = new ArrayList<>();
		if(selectedFile != null)
			for(File file : selectedFile) {
				Song s = SongOperate.addSong(file.getAbsolutePath(),currentMenu.get());
				MusicUtils mu = SongUtil.songToMucic(s);
				ml.add(mu);
			}
		TableView<MusicUtils> tv = GlobalVariable.currentCtrl.getTableView_musicTable();
		tv.getItems().addAll(FXCollections.observableArrayList(ml));
	}
	
	public void addLocalDirectory() {
		DirectoryChooser fileChooser = new DirectoryChooser();
		fileChooser.setTitle("打开音乐文件夹");
		File selectedFile = fileChooser.showDialog(GUI.staticStage);
		if(selectedFile != null) {
			ArrayList<File> fl = new ArrayList<>();
			loopDirectory(selectedFile, fl);
			for(File file : fl) {
				SongOperate.addSong(file.getAbsolutePath(),currentMenu.get());
			}
		}
	}
	
	private void loopDirectory(File file, ArrayList<File> fl) {
		for(File f : file.listFiles()) {
			if(f.isDirectory()) {
				loopDirectory(f, fl);
			}
			if(f.isFile()) {
				String s = f.getName();
				if(s.matches(".*\\.(?i)mp3$"))
					fl.add(f);
				if(s.matches(".*\\.(?i)flac$"))
					fl.add(f);
			}
		}
	}

	public void createMusicList(Button nb) {
		nb.getStyleClass().remove(0);
		nb.getStyleClass().add("listButton");
		ml.getItems().add(nb);
		nb.setOnAction(nbe -> {
			musiclist(nb.getText());
		});
		nb.setOnMouseClicked(tca);
	}

	private class Extension implements EventHandler<ActionEvent>{//FIXME
		@Override
		public void handle(ActionEvent event) {
			if(tf.getText().length() > 0) {
				try {
					String key = tf.getText();
					SongMenuOperate.addSongMenu(key/*, new Date()*/);
					Button nb = new Button(key);
					createMusicList(nb);
					addl.fire();
				} catch (RuntimeException e2) {
			        Alert _alert = new Alert(Alert.AlertType.ERROR,e2.getMessage(),new ButtonType("返回", ButtonBar.ButtonData.YES));
			        _alert.show();
				}
				
			}
		}
	}
	
	//-----------------------------------------Play------------------------
	public void reverse() {
		if(!back())
			show(null);
	}
	
	private static boolean loadLrc() {
		if(isExist()) 
			return false;
		String name = ps.getCurrent_song().getPath();
		StringBuilder sb = new StringBuilder(name);
		sb.delete(name.lastIndexOf('.'), name.length());
		sb.append(".lrc");
		boolean exist = false;
		try {
			lyric = new LrcAnalyzer(sb.toString()).LrcGetList();
			exist = true;
		} catch (Exception e) {
			Alert _alert = new Alert(Alert.AlertType.INFORMATION);
			_alert.setTitle("消息");
			_alert.setHeaderText("该歌曲没有同名的lrc文件在同一目录哦  w(ﾟДﾟ)w");
			 _alert.setContentText(e.getMessage());
			 _alert.show();
			 exist = false;
		} finally {
			if(exist) {				
				playingRefresh();
				tl.getItems().clear();
				tl.getItems().addAll(lyric);
			}
			currentLrc = ps.getCurrent_song().getMusicTitle();
		}
		return exist;
	}
	
	private static void playingRefresh() {
		if(isExist()) 
			return;
		MusicUtils m = ps.getCurrent_song();
		String name = m.getPath();
		StringBuilder sb = new StringBuilder(name);
		sb.delete(name.lastIndexOf('.'), name.length());
		sb.append(".jpg");
		String url = new File(sb.toString()).toURI().toString();
		Image image = new Image(url);
		piv.setImage(image);
		liv.setImage(image);
		pmt.setText(m.getMusicTitle());
		lmt.setText(m.getMusicTitle());
		pmb.setText(m.getAlbumName());
		pms.setText(m.getMusicSinger());
		lms.setText(m.getMusicSinger());
		pmc.setText(currentMenu.get());
	}
	
	private static boolean isExist() {
		String name = ps.getCurrent_song().getMusicTitle();
		return name.equals(currentLrc);
	}
	
	private static void scrollLrc(int totalSec, double progress) {
		long cm = (long)(progress * 1000 * totalSec), pm;
		int index = 0;
		for(int i = 0; i < lyric.size(); i++) {
			LrcData ld = lyric.get(i);
			if (ld.type != LrcAnalyzer.LRC_ZONE)
				continue;
			if(i-1 >= 0 ) pm = lyric.get(i-1).timeMs; else pm =0;
			if (cm < ld.timeMs && cm >= pm) {
				index = i-1;
				break;
			}
		}
		if(index < 9 || index >= lyric.size() - 9){
			int x = index;
			if(index < 9 ) x = 0;
			if(index >= lyric.size() - 9) x = lyric.size() - 18;
			if(x < 0) x = 0;
			tl.scrollTo(x);
		}else {
			tl.scrollTo(index - 9);
		}
		tl.getSelectionModel().select(index);
	}
	// -----------------------------------------List------------------------
	public void playAll() {
		tca.getCb().setMl(SongMenuOperate.getSongsByMenuName(currentMenu.get()));
		ContextBox.play_all.fire();
	}

	public void playAllNext() {
		tca.getCb().setMl(SongMenuOperate.getSongsByMenuName(currentMenu.get()));
		ContextBox.play_all_next.fire();
	}

	// -----------------------------------------PlayList---------------------
	public void playList() {
		listOn = !listOn;
		gui.getPlaylist().setVisible(listOn);
		gui.getPlC().getTableView_playList().getSelectionModel().select(currentSong);
	}

	//-----------------------------------------Item------------------------
	private Page giveLocal() {
		LocalMusicPageController lmC = null;
		StackPane localmusic = null;
		try {
			FXMLLoader lm = new FXMLLoader(GUI.class.getResource("LocalMusicPage.fxml"),
					ResourceBundle.getBundle("ini"));
			localmusic = (StackPane) lm.load();
			localmusic.getChildren().add(gui.getPlaylist());
			lmC = lm.getController();
			List<MusicUtils> list = new ArrayList<MusicUtils>();
			//FIXME 读入
			lmC.initData(this,list);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Page.newPage(Controller.LOCAL, localmusic, lmC);
    }
    
	private Page giveMusicList(String name, String date) {
		MusicListPageController mlC = null;
		StackPane musiclist = null;
		List<MusicUtils> list = SongMenuOperate.getSongsByMenuName(name);
		try {
			FXMLLoader ml = new FXMLLoader(GUI.class.getResource("MusicListPage.fxml"),
					ResourceBundle.getBundle("ini"));
			musiclist = (StackPane) ml.load();
			musiclist.getChildren().add(gui.getPlaylist());
			mlC = ml.getController();
			mlC.initData(this, name, date, list);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Page.newPage(Controller.MUSICLIST, musiclist, mlC);
    }
 
	private Page giveSearch(String name) {
		StackPane searchpage = null;
		SearchPageController spC = null;
		try {
			FXMLLoader sp = new FXMLLoader(GUI.class.getResource("SearchPage.fxml"), ResourceBundle.getBundle("ini"));
			searchpage = (StackPane) sp.load();
			searchpage.getChildren().add(gui.getPlaylist());
			spC = sp.getController();
			List<MusicUtils> list = new ArrayList<MusicUtils>();
			//FIXME 搜索
			spC.initData(this, name, list);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Page.newPage(Controller.SEARCH, searchpage, spC);
    }
    
	public static boolean presskey(TextField t, KeyEvent e) {
		if(e.getCode() == KeyCode.ENTER) {
			t.deleteNextChar();
			if (t.getText().length() > 0) {
				return true;
			}
		}
		if(e.getCode() == KeyCode.TAB) {
			t.deleteNextChar();
		}
		return false;
	}
	
	public static void show(Page p) {
		if(p ==null) {
			gui.getPermanent().setCenter(null);
			gui.getPermanent().leftProperty().set(gui.getLeftlist());
		}
		else if(p instanceof Page.PlayPage) {
			gui.getPermanent().setCenter(gui.getPlaypage());
			gui.getPermanent().leftProperty().set(null);
			return;
		}
		else if(p instanceof Page.SettingPage) {
			gui.getPermanent().leftProperty().set(null);
			return;
		}
		else {
			gui.getPermanent().setCenter(p.getPage());
			gui.getPermanent().leftProperty().set(gui.getLeftlist());
		}
		refresh(p);
	}
	
	private static void refresh(Page p) {
		//back and fore
		int ii = i.get();
		int ss = s.get();
		if (ii > 0)
			b.setDisable(false);
		else
			b.setDisable(true);
		if (ii < ss - 1)
			f.setDisable(false);
		else
			f.setDisable(true);
		
		if(p == null) return;
		GlobalVariable.PageType = p.getType();
		//adds and addd
		if(GlobalVariable.PageType == Controller.LOCAL ||
				GlobalVariable.PageType == Controller.MUSICLIST) {
			adds.setDisable(false);addd.setDisable(false);
			GlobalVariable.currentCtrl = p.getCtrl();
		}else {
			adds.setDisable(true);addd.setDisable(true);
			GlobalVariable.currentCtrl = null;
		}
		if(p instanceof SearchPage) {
			String key =((SearchPage)p).getKey();
			if(currentSearch.get() == key)
				return;
			currentSearch.set(key);
		}
	}
		
	private static void refresh(int s) {
		//pb and lb and nb
		if(s == PLAYINGMUSIC){
			pb.getStyleClass().remove(0);
			pb.getStyleClass().add("buttonPlay");
		}
		if(s == PAUSINGMUSIC){
			pb.getStyleClass().remove(0);
			pb.getStyleClass().add("buttonPause");
		}
		List<MusicUtils> l = ps.getCurrent_songMenu();
		if(l == null || l.size() == 0) 
			buttonDis(true, pb, lb, nb);
		else 
			buttonDis(false, pb, lb, nb);
		//tt
		String n = ps.getCurrent_song().getMusicTimeLength();
		tt.setText(n);
	}

	public static ArrayList<MusicUtils> searchsong(String key){//FIXME
		ArrayList<MusicUtils> sl = new ArrayList<>();
		
		return sl;
	}
	
	public static void setCurrentList(List<MusicUtils> list) {
		currentList = list;
		currentSum.set(list.size());
		gui.getPlC().getTableView_playList().getItems().clear();
		gui.getPlC().getTableView_playList().getItems().addAll(list);
	}
	
	public MainAction(GUI gui) {
		MainAction.gui = gui;
		tca = new TagClickAction(this, gui.getLlC().getListView_musicList());
		pq = gui.getPageManager();
		ps = PlayState.getPlayState();
		ps.setPlAYMODE_SEQUENCEPLAY();
		//--------main---------
		i = new SimpleIntegerProperty();
		s = new SimpleIntegerProperty();
		i.bind(gui.getIndex());
		s.bind(gui.getSize());
		b = gui.getTabC().getButton_back();
		f = gui.getTabC().getButton_forward();
		pb = gui.getTabC().getButton_pause();
		lb = gui.getTabC().getButton_last();
		nb = gui.getTabC().getButton_next();
		sl = gui.getTabC().getSlider_songProgress();
		ct = gui.getTabC().getLabel_currentTime();
		tt = gui.getTabC().getLabel_totalTime();
		cp = new SimpleDoubleProperty(0);
		cp.unbind();
		cp.bind(PlayOperate.cur_p);
		//--------left---------
		adds = gui.getLlC().getButton_addLocalMusic();
		addd = gui.getLlC().getButton_addLocalDirectory();
		addl = gui.getLlC().getButton_addMusicList();
		vb = gui.getLlC().getVBox_leftMainField();	
		ml = gui.getLlC().getListView_musicList();
		liv = gui.getLlC().getImageView_albumCover();
		lmt = gui.getLlC().getLabel_musicTitle();
		lms = gui.getLlC().getLabel_singer();
		//--------playlist-----
		pl = gui.getPlaylist();
		//--------play---------
		tl = gui.getPpC().getTableView_lyricsArea();
		pmt = gui.getPpC().getLabel_musicTitle();
		pmb = gui.getPpC().getLabel_albumName();
		pms = gui.getPpC().getLabel_singer();
		pmc = gui.getPpC().getLabel_source();
		piv = gui.getPpC().getImageView_albumCover();
		
		buttonDis(true, adds, addd, pb, lb, nb);

		pb.getStyleClass().set(0, "buttonPlay");		
		tf.setPromptText("请输入歌单名称");
		hb.getChildren().addAll(tf, btn);
		btn.getStyleClass().remove(0); btn.getStyleClass().add("AddList");
		tf.getStyleClass().add("ListField");
		tf.setPrefHeight(80);tf.setPrefWidth(250);
		btn.setOnAction(new Extension());
		tf.setOnKeyPressed(new EnterAction(tf, btn));
		cp.addListener((o, ov, nv) ->{
			progressFeedBack((double)nv);
			currentSong = ps.getCurrent_song();
		});
		pl.focusedProperty().addListener((o, ov, nv) ->{
			if(nv == false) {
				gui.getTabC().getButton_playList().fire();
			}
		});
		currentSum.addListener((o, ov, nv) ->{
			gui.getTabC().getLabel_playListNum().setText(nv.toString());
			gui.getPlC().getLabel_playListNum().setText("总"+nv.toString()+"首");
		});
	}
	public static GUI gui;
	private static MainAction ma;
	public static PageQueue pq;
	public static PlayState ps;
	public static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd\u521b\u5efa");
	public TagClickAction tca;
	private boolean listOn = false;
	private static List<LrcData> lyric;
	
	static TextField tf = new TextField();//扩展输入栏
	static Button btn = new Button();//扩展添加按钮
	static HBox hb = new HBox();//扩展横箱
	
	static Button addl;//指定left中的添加歌单
	static Button adds, addd;//指定left中的添加歌曲和添加目录
	//static ImageView iv;//指定left中的专面
	static VBox vb;//指定left中的竖箱
	static ListView<Button> ml;//指定left中的列表
	static ImageView liv;//指定left中封面
	static Label lmt, lms;//指定left中的标题，歌手
	static IntegerProperty i, s;//指定gui中的目录和大小
	static DoubleProperty cp;//指定进度条的值
	static Button b, f;//指定topandbottom中的后退和前进
	static Button pb,lb,nb;//指定topandbottom中的播放,上一首和下一首
	static Slider sl;//指定topandbottom中的进度条
	static Label ct, tt;//指定topandbottom中的当前时间和总时间
	static AnchorPane pl;//指定playlist版面
	static TableView<LrcData> tl;//指定play的歌词版
	static Label pmt, pmb, pms, pmc;//指定play中的标题，专辑，歌手，来源
	static ImageView piv;//指定play中的封面

 	public static void buttonDis(boolean r, Button... bl) {
		for(Button b : bl) 
			b.setDisable(r);
	}
	
	public static class IndexFactory<S> implements Callback<CellDataFeatures<S, Integer>, ObservableValue<Integer>> {
		private TableView<S> tv;

		public TableView<S> getTv() {
			return tv;
		}

		public IndexFactory(TableView<S> tv) {
			super();
			this.tv = tv;
		}

		@Override
		public ObservableValue<Integer> call(CellDataFeatures<S, Integer> param) {
			S value = param.getValue();
			Integer i = new Integer(tv.getItems().indexOf(value) + 1);
			ObservableValue<Integer> index = new ReadOnlyObjectWrapper<Integer>(i);
			return index;
		}
	}
		
	public static class likeCheckBox implements Callback<Integer,ObservableValue<Boolean>>{
		
		private TableView<MusicUtils> tv;
		
		public TableView<MusicUtils> getTv() {
			return tv;
		}

		public likeCheckBox(TableView<MusicUtils> tv) {
			this.tv = tv;
		}
		
		@Override
		public ObservableValue<Boolean> call(Integer param) {
			MusicUtils m = tv.getItems().get(param);
			ObservableValue<Boolean> ob = new SimpleBooleanProperty(m.isLike());
			return ob;
		}
	}
	
	public static class TableCleaner<S> implements ChangeListener<Boolean>{
		private TableView<S> tv;
		public TableView<S> getTv() {
			return tv;
		}

		public TableCleaner(TableView<S> tv) {
			super();
			this.tv = tv;
		}
		
		@Override
		public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
			if(newValue == false)
				tv.getSelectionModel().clearSelection();
		}
	}
	
	public GUI getGui() {
		//初始化播放服務d
		PlayOperate.getPlayOperate();
		return gui;
	}
	
	public static MainAction getMa() {
		if(ma == null)
			ma = new MainAction(GUI.getGui());
		return ma;
	}
}
