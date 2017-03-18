package com.example.event;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;

public class SongMenuAlterAction implements EventHandler<ActionEvent>{
	
	private Parent pane;
	
	public SongMenuAlterAction(Parent pane) {
		this.pane = pane;
	}
	/**
	 * Waiting for further information from GUI and to go on designing
	 */
	public void handle(ActionEvent event) {
		//TODO
	};
}
