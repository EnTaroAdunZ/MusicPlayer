package com.example.event;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import com.example.Global.GlobalVariable;
import com.example.controller.*;
import com.example.entity.Song;
import com.example.gui.GUI;
import com.example.gui.MusicUtils;
import com.example.service.*;
import com.example.util.SongUtil;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.image.*;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaPlayer;
import javafx.stage.*;
import javafx.util.Callback;

public class MainAction {
	static Button addlistbtn;
	static TextField tf = new TextField();//扩展输入栏
	static Button btn = new Button();//扩展添加按钮
	static HBox hb = new HBox();//扩展横箱
	
	static VBox vb;//指定left中的竖箱
	static IntegerProperty i, s;//指定gui中的目录和大小
	static Button b, f;//指定topandbottom中的后退和前进
	static Button adds, addd;//指定left中的添加歌曲和添加目录
	//static ImageView iv;//指定left中的专面
	
	//-----------------------------------------Top------------------------
 	public void back() {
 		PageQueue pq = gui.getPageManager();
 		IntegerProperty i = gui.getIndex(); IntegerProperty s = gui.getSize();
 		int ii = i.get();
 		if(ii < 1)
 			return;
		Page p;int ss = s.get();
		if(ss < 2) 
			return;
		p = pq.backward();
		show(p);
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
		if(GlobalVariable.currentSearch == key)
			return;
		GlobalVariable.currentSearch = key;
		ArrayList<MusicUtils> sl = searchsong();
		//FIXME
		Page p = giveSearch(key);
		((Page.SearchPage)p).setKey(key);
		pq.add(p);
		show(p);
	}
	
	//-----------------------------------------Bottom---------------------
 	public void last(Button b, MediaPlayer mp, ActionEvent e) {//FIXME
		
	}
	
	public void play(Button b, TopAndBottomPageController tbc, ActionEvent e) {//FIXME
		/*
		MediaPlayer mp = tbc.mp;
		boolean atEndOfMedia = tbc.atEndOfMedia;
		
		updateValues(tbc);
		Status status = mp.getStatus();

        if (status == Status.UNKNOWN
                || status == Status.HALTED) {
            System.out.println("Player is in a bad or unknown state, can't play.");
            return;
        }

        if (status == Status.PAUSED
                || status == Status.READY
                || status == Status.STOPPED) {
            // rewind the movie if we're sitting at the end
            if (atEndOfMedia) {
                mp.seek(mp.getStartTime());
                atEndOfMedia = false;
                b.setText(">");
                updateValues(tbc);
            }
            mp.play();
            b.setText("||");
        } else {
            mp.pause();
        }*/
	}
	
	public void next(Button b, MediaPlayer mp, ActionEvent e) {//FIXME
		
	}

	//-----------------------------------------Left-----------------------
	public void local() {
		if(pq.getSize() > 0 && pq.getPage() instanceof Page.LocalPage ) 
			return;
		Page p = giveLocal();
		pq.add(p);
		show(p);
	}
	
	public void musiclist(String key, String date) {
		if(GlobalVariable.currentMenu == key) 
			return;
		GlobalVariable.currentMenu = key;
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
				Song s = SongOperate.addSong(file.getAbsolutePath(),GlobalVariable.currentMenu);
				MusicUtils mu = SongUtil.songToMucic(s);
				ml.add(mu);
			}
		GlobalVariable.currentCtrl.getTableView_musicTable().getItems().addAll(FXCollections.observableArrayList(ml));
	}
	
	public void addLocalDirectory() {
		DirectoryChooser fileChooser = new DirectoryChooser();
		fileChooser.setTitle("打开音乐文件夹");
		File selectedFile = fileChooser.showDialog(GUI.staticStage);
		if(selectedFile != null) {
			ArrayList<File> fl = new ArrayList<>();
			loopDirectory(selectedFile, fl);
			for(File file : fl)
			SongOperate.addSong(file.getAbsolutePath(),"我的最爱");
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

	public void createMusicList(Button nb, Date createDate) {
		nb.getStyleClass().remove(0);
		nb.getStyleClass().add("listButton");
		String date = df.format(createDate);
		gui.getLlC().getListView_musicList().getItems().add(nb);
		nb.setOnAction(nbe -> {
			musiclist(nb.getText(), date);// FIXME
		});
		nb.setOnMouseClicked(tca);
	}

	/*
	protected void updateValues(TopAndBottomPageController tbc) {
		MediaPlayer mp = tbc.mp;
		Duration duration = tbc.duration;
		Slider timeSlider = tbc.timeSlider;
		Label playTime = tbc.playTime;
		Slider volumeSlider = tbc.volumeSlider;
		
		
		
		if (playTime != null && timeSlider != null && volumeSlider != null) {
            Platform.runLater(new Runnable() {

                @Override
                public void run() {
                    Duration currentTime = mp.getCurrentTime();
                    playTime.setText(formatTime(currentTime, duration));
                    timeSlider.setDisable(duration.isUnknown());
                    if (!timeSlider.isDisabled() && duration.greaterThan(Duration.ZERO) && !timeSlider.isValueChanging()) {
                        timeSlider.setValue(currentTime.divide(duration.toMillis()).toMillis() * 100.0);
                    }
                    if (!volumeSlider.isValueChanging()) {
                        volumeSlider.setValue((int) Math.round(mp.getVolume() * 100));
                    }
                }
            });
        }
    }
	*/
	/*
	private String formatTime(Duration elapsed, Duration duration) {//FIXME
        int intElapsed = (int) Math.floor(elapsed.toSeconds());
        int elapsedHours = intElapsed / (60 * 60);
        if (elapsedHours > 0) {
            intElapsed -= elapsedHours * 60 * 60;
        }
        int elapsedMinutes = intElapsed / 60;
        int elapsedSeconds = intElapsed - elapsedHours * 60 * 60 - elapsedMinutes * 60;

        if (duration.greaterThan(Duration.ZERO)) {
            int intDuration = (int) Math.floor(duration.toSeconds());
            int durationHours = intDuration / (60 * 60);
            if (durationHours > 0) {
                intDuration -= durationHours * 60 * 60;
            }
            int durationMinutes = intDuration / 60;
            int durationSeconds = intDuration - durationHours * 60 * 60 - durationMinutes * 60;

            if (durationHours > 0) {
                return String.format("%d:%02d:%02d/%d:%02d:%02d",
                        elapsedHours, elapsedMinutes, elapsedSeconds,
                        durationHours, durationMinutes, durationSeconds);
            } else {
                return String.format("%02d:%02d/%02d:%02d",
                        elapsedMinutes, elapsedSeconds,
                        durationMinutes, durationSeconds);
            }
        } else {
            if (elapsedHours > 0) {
                return String.format("%d:%02d:%02d",
                        elapsedHours, elapsedMinutes, elapsedSeconds);
            } else {
                return String.format("%02d:%02d",
                        elapsedMinutes, elapsedSeconds);
            }
        }
    }
	*/
	
	
	private class Extension implements EventHandler<ActionEvent>{//FIXME
		@Override
		public void handle(ActionEvent event) {
			if(tf.getText().length() > 0) {
				try {
					String key = tf.getText();
					Button nb = new Button(key);
					createMusicList(nb, new Date());
					//nb.setContextMenu(tca.getCb().getListContext());
					//System.out.println(":" + nb.getContextMenu().getOwnerWindow()+":");
					addlistbtn.fire();
				} catch (RuntimeException e2) {
			        Alert _alert = new Alert(Alert.AlertType.ERROR,e2.getMessage(),new ButtonType("返回", ButtonBar.ButtonData.YES));
			        _alert.show();
				}
				
			}
		}
	}
	
	//-----------------------------------------Play------------------------
	public void reverse() {
		back();
	}
	
	//-----------------------------------------Item------------------------
	private Page giveLocal() {
		LocalMusicPageController lmC = null;
		AnchorPane localmusic = null;
		try {
			FXMLLoader lm = new FXMLLoader(GUI.class.getResource("LocalMusicPage.fxml"),
					ResourceBundle.getBundle("ini"));
			localmusic = (AnchorPane) lm.load();
			lmC = lm.getController();
			List<MusicUtils> list = new ArrayList<MusicUtils>();
			//FIXME
			lmC.initData(this,list);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Page.newPage(Controller.LOCAL, localmusic, lmC);
    }
    
	private Page giveMusicList(String name, String date) {
		MusicListPageController mlC = null;
		AnchorPane musiclist = null;
		List<MusicUtils> list = SongMenuOperate.getSongsByMenuName(name);
		try {
			FXMLLoader ml = new FXMLLoader(GUI.class.getResource("MusicListPage.fxml"),
					ResourceBundle.getBundle("ini"));
			musiclist = (AnchorPane) ml.load();
			mlC = ml.getController();
			mlC.initData(this, name, date, list);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Page.newPage(Controller.MUSICLIST, musiclist, mlC);
    }
 
	private Page giveSearch(String name) {
		AnchorPane searchpage = null;
		SearchPageController spC = null;
		try {
			FXMLLoader sp = new FXMLLoader(GUI.class.getResource("SearchPage.fxml"), ResourceBundle.getBundle("ini"));
			searchpage = (AnchorPane) sp.load();
			spC = sp.getController();
			List<MusicUtils> list = new ArrayList<MusicUtils>();
			//FIXME
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
		if(p instanceof Page.PlayPage) {
			gui.getPermanent().setCenter(gui.getPlaypage());
			gui.getPermanent().leftProperty().set(null);
			return;
		}
		if(p instanceof Page.SettingPage) {
			gui.getPermanent().leftProperty().set(null);
			return;
		}
		gui.getPermanent().setCenter(p.getPage());
		gui.getPermanent().leftProperty().set(gui.getLeftlist());
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
	}

	public static ArrayList<MusicUtils> searchsong(){//FIXME
		ArrayList<MusicUtils> sl = new ArrayList<>();
		
		return sl;
	}
	
	public MainAction(GUI gui) {//FIXME
		MainAction.gui = gui;
		pq = gui.getPageManager();
		vb = gui.getLlC().getVBox_leftMainField();
		i = new SimpleIntegerProperty();
		s = new SimpleIntegerProperty();
		i.bind(gui.getIndex());
		s.bind(gui.getSize());
		b = gui.getTabC().getButton_back();
		f = gui.getTabC().getButton_forward();
		adds = gui.getLlC().getButton_addLocalMusic();
		addd = gui.getLlC().getButton_addLocalDirectory();
		//iv = gui.getLlC().getImageView_albumCover();
		
		adds.setDisable(true);addd.setDisable(true);
		
		tf.setPromptText("请输入歌单名称");
		addlistbtn = gui.getLlC().getButton_addMusicList();
		hb.getChildren().addAll(tf, btn);
		btn.getStyleClass().remove(0); btn.getStyleClass().add("AddList");
		tf.getStyleClass().add("ListField");
		tf.setPrefHeight(70);tf.setPrefWidth(230);
		btn.setOnAction(new Extension());
		tf.setOnKeyPressed(new EnterAction(tf, btn));
	}
	public static GUI gui;
	public static PageQueue pq;
	public static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd\u521b\u5efa");
	private TagClickAction tca = new TagClickAction();

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
	
	public GUI getGui() {
		return gui;
	}
	
	
}
