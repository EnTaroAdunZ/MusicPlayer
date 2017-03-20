package com.example.event;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.example.controller.MainPageController;
import com.example.controller.PageQueue;
import com.example.gui.MusicUtils;
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
	static TextField tf = new TextField();
 	public static void back(Button b, IntegerProperty i, PageQueue pq, ActionEvent e) {
		//FIXME
		Parent root;
		//FIXME
		root = pq.backward().getPage();
		//FIXME
	}
	
	public static void fore(Button b, IntegerProperty i, PageQueue pq, ActionEvent e) {
		//FIXME
		Parent root;
		//FIXME
		root = pq.forward().getPage();
		//FIXME
	}
	
	public static String enterkey(TextField t, KeyEvent e) {
		if(e.getCode() == KeyCode.ENTER) {
			String s = t.getText();
			if (s.length() > 1) {
				s = s.substring(0, s.length()-2);
				return s;
			}
			return null;
		}
		return null;
	}
	
	public static void text() {
		
	}
	
	public static void last(Button b, MediaPlayer mp, ActionEvent e) {
		
	}
	
	public static void play(Button b, MainPageController mpc, ActionEvent e) {
		
		MediaPlayer mp = mpc.mp;
		boolean atEndOfMedia = mpc.atEndOfMedia;
		
		updateValues(mpc);
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
                updateValues(mpc);
            }
            mp.play();
            b.setText("||");
        } else {
            mp.pause();
        }
	}
	
	public static void addMusicList(Button b, VBox v, ListView<Button> l) {
		String t = b.getText();
		if(t.equals("+")) {
			tf.setPrefWidth(270);tf.setMaxWidth(270);tf.setPrefHeight(50);tf.setMaxHeight(50);
			v.getChildren().add(2, tf);
			b.setText("x");
			tf.setOnKeyTyped(new EnterAction());
		}
		if(t.equals("x")) {
			v.getChildren().removeAll(tf);
			b.setText("+");
			tf.clear();
		}
	}
	
	public static void addLocalMusic() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("打开音乐文件");
		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("MP3", "*.mp3"),
				new FileChooser.ExtensionFilter("flac", "*.flac*"),
				new FileChooser.ExtensionFilter("所有文件", "*.*"));
		List<File> selectedFile = fileChooser.showOpenMultipleDialog(new Stage());
		for(File file : selectedFile)
			SongOperate.addSong(file.getAbsolutePath(),"我的最爱");
	}
	
	public static void addLocalDirectory() {
		DirectoryChooser fileChooser = new DirectoryChooser();
		fileChooser.setTitle("打开音乐文件");
		File selectedFile = fileChooser.showDialog(new Stage());
		ArrayList<File> fl = new ArrayList<>();
		loopDirectory(selectedFile, fl);
		for(File file : fl)
			SongOperate.addSong(file.getAbsolutePath(),"我的最爱");
	}
	
	private static void loopDirectory(File file, ArrayList<File> fl) {
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
	
	protected static void updateValues(MainPageController mpc) {
		MediaPlayer mp = mpc.mp;
		Duration duration = mpc.duration;
		Slider timeSlider = mpc.timeSlider;
		Label playTime = mpc.playTime;
		Slider volumeSlider = mpc.volumeSlider;
		
		
		
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
	
	private static String formatTime(Duration elapsed, Duration duration) {
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

	
}
class EnterAction implements EventHandler<KeyEvent>{
	@Override
	public void handle(KeyEvent event) {
		String s = MainAction.enterkey(MainAction.tf, event);
		if(s != null) {
			
		}
	}
}