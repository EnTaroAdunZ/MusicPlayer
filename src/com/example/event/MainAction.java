package com.example.event;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.example.controller.*;
import com.example.entity.SongMenu;
import com.example.gui.GUI;
import com.example.service.SongMenuOperate;
import com.example.service.SongOperate;

import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.event.*;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.stage.*;
import javafx.util.Duration;
import javafx.stage.Stage;

public class MainAction {
	static Button addlistbtn;
	
	static TextField tf = new TextField();
	static Button btn = new Button("+");
	static HBox hb = new HBox();
	static VBox vb;
	static PageQueue pq;
	
 	public void back(Button b, IntegerProperty i, PageQueue pq, ActionEvent e) {
		//FIXME
		Parent root;
		//FIXME
		root = pq.backward().getPage();
		//FIXME
	}
	
	public void fore(Button b, IntegerProperty i, PageQueue pq, ActionEvent e) {
		//FIXME
		Parent root;
		//FIXME
		root = pq.forward().getPage();
		//FIXME
	}
	
	public static boolean presskey(TextField t, KeyEvent e) {
		if(e.getCode() == KeyCode.ENTER) {
			t.deletePreviousChar();
			if (t.getText().length() > 0) {
				return true;
			}
		}
		if(e.getCode() == KeyCode.TAB) {
			t.deletePreviousChar();
		}
		return false;
	}
	
	public void local() {
		if(pq.getPage() instanceof Page.LocalPage ) 
			return;
		Page p = gui.giveLocal();
		pq.add(p);
		gui.permanent.setCenter(p.getPage());
		if(gui.permanent.getLeft() == null){
			gui.permanent.setLeft(gui.leftlist);
		}
	}
	
	public void last(Button b, MediaPlayer mp, ActionEvent e) {
		
	}
	
	public void play(Button b, TopAndBottomPageController tbc, ActionEvent e) {
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
	
	public void addMusicList(Button b, ListView<Button> l) {
		String t = b.getText();
		if(t.equals("+")) {
			vb.getChildren().add(2, hb);
			b.setText("x");
		}
		if(t.equals("x")) {
			vb.getChildren().removeAll(hb);
			b.setText("+");
			tf.clear();
		}
	}
	
	public void addLocalMusic() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("打开音乐文件");
		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("MP3", "*.mp3"),
				new FileChooser.ExtensionFilter("flac", "*.flac*"),
				new FileChooser.ExtensionFilter("所有文件", "*.*"));
		List<File> selectedFile = fileChooser.showOpenMultipleDialog(new Stage());
		for(File file : selectedFile)
			SongOperate.addSong(file.getAbsolutePath(),"我的最爱");
	}
	
	public void addLocalDirectory() {
		DirectoryChooser fileChooser = new DirectoryChooser();
		fileChooser.setTitle("打开音乐文件");
		File selectedFile = fileChooser.showDialog(new Stage());
		ArrayList<File> fl = new ArrayList<>();
		loopDirectory(selectedFile, fl);
		for(File file : fl)
			SongOperate.addSong(file.getAbsolutePath(),"我的最爱");
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
	private String formatTime(Duration elapsed, Duration duration) {
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

	public MainAction(GUI gui) {
		this.gui = gui;
		pq = gui.pageManager;
		vb = gui.llC.getVBox_leftMainField();
		addlistbtn = gui.llC.getButton_addMusicList();
		hb.getChildren().addAll(tf, btn);
		btn.setPrefHeight(45);btn.setPrefWidth(53);
		tf.setPrefWidth(212);tf.setMaxWidth(212);tf.setPrefHeight(50);tf.setMaxHeight(50);
		btn.setOnAction(e ->{
			if(tf.getText().length() > 0) {
				//示范
				try {
					SongMenuOperate.addSongMenu(tf.getText().toString());
					gui.llC.getListView_musicList().getItems().add(new Button(tf.getText()));
					addlistbtn.fire();
				} catch (RuntimeException e2) {
			        Alert _alert = new Alert(Alert.AlertType.ERROR,e2.getMessage(),new ButtonType("返回", ButtonBar.ButtonData.YES));
			        _alert.show();
				}
				
			}
		});
		tf.setOnKeyPressed(new EnterAction(tf, btn));
	}
	private GUI gui;

	public GUI getGui() {
		return gui;
	}
	
}
