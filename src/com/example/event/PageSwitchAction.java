package com.example.event;

import java.util.*;
import javafx.event.*;
import javafx.scene.Parent;
import javafx.scene.control.*;

public class PageSwitchAction implements EventHandler<ActionEvent>{
	
	
	@Override
	public void handle(ActionEvent event) {
		// TODO Auto-generated method stub
		//Button b = (Button)event.getSource();
		//String s = b.getText();
		/*
		if(s.equals("<")) {
			//TODO backward();
		}else {
			//TODO forward();
		}
		*/
	}
	
	public class QueuePage{
		private LinkedList<Parent> pagestack;
		
		private int index;
		
		public QueuePage() {
			pagestack = new LinkedList<>();
			index = 0;
		}
		
		/**
		 *  adds a new page into the stack
		 */
		public void add(Parent page) {
			pagestack.add(index + 1, page);
			index++;
			if(pagestack.size() > 5 && index == 5) pagestack.removeFirst();
			save();
		}
		
		private void save() {
			while(pagestack.size() > index + 1) {
				pagestack.removeLast();
			}
		}
		
		/**
		 *  Travels backward and retrieves the page if it exists.
		 */
		public Parent backward() {
			return index > 0 ? pagestack.get(--index) : null;
		}
		
		/**
		 *  Travels forward and retrieves the page if it exists.
		 */
		public Parent forward() {
			return index < 4 ? pagestack.get(++index) : null;
		}
		
		/**
		 *  supposed to use this variable to control the button "<" or
		 * ">" to be disable whether or not.
		 */
		public int getIndex() {
			return index;
		}
		
	}
	
	public class TabPage{
		private TabPane name, artist, album, sheet, lyric;

		public TabPane getName() {
			return name;
		}

		public TabPane getArtist() {
			return artist;
		}

		public TabPane getAlbum() {
			return album;
		}

		public TabPane getSheet() {
			return sheet;
		}

		public TabPane getLyric() {
			return lyric;
		}
		
		
	}

}
