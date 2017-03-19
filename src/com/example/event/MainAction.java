package com.example.event;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

import com.example.service.SongOperate;

import javafx.event.*;
import javafx.scene.control.*;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.stage.*;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class MainAction {
	
	public static void last(Button b,MediaPlayer mp, ActionEvent e) {
		
	}
	
	public static void play(Button b,MediaPlayer mp, ActionEvent e) {
		//updateValues();
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
            //if (atEndOfMedia) {
            //    mp.seek(mp.getStartTime());
            //    atEndOfMedia = false;
            //    b.setText(">");
            //    updateValues();
            //}
            //mp.play();
           // b.setText("||");
        } else {
            mp.pause();
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
}
