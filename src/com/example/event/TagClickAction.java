package com.example.event;

import javafx.beans.property.ObjectProperty;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class TagClickAction implements EventHandler<MouseEvent>{
	
	public TagClickAction(TagContextMenu tcm) {
		tagContextMenu = tcm;
	}
	
	private TagContextMenu tagContextMenu;
	
	@Override
	public void handle(MouseEvent event) {
		Node target = ((Node)event.getSource());
		if(event.getButton() == MouseButton.SECONDARY) {
			tagContextMenu.getContextMenu().show(target, event.getSceneX(), event.getScreenY());
		}
	}
	
	public class TagContextMenu {
		
		public TagContextMenu() {
			contextMenu = new ContextMenu();
			contextMenu.getItems().addAll(play, playnext, add, delete, path);
			init();
		}

		void init() {
			play.setOnAction(null);
			playnext.setOnAction(null);
			add.setOnAction(null);
			delete.setOnAction(null);
			path.setOnAction(null);
			// TODO
		}
		
		private ContextMenu contextMenu;
		public ContextMenu getContextMenu() {
			return contextMenu;
		}
		public void setContextMenu(ContextMenu contextMenu) {
			this.contextMenu = contextMenu;
		}
		
		private MenuItem play = new MenuItem("play");
		private MenuItem playnext = new MenuItem("play next");
		private MenuItem add = new MenuItem("add to the sheet of...");
		private MenuItem delete = new MenuItem("delete from the sheet");
		private MenuItem path = new MenuItem("show the parent path");
		
		//TODO
		private ObjectProperty<Object> target;
		
	}
}
