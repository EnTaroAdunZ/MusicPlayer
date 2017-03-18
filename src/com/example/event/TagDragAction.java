package com.example.event;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.*;

public class TagDragAction implements EventHandler<MouseDragEvent>{
	@Override
	public void handle(MouseDragEvent event) {
		Node target = (Node)event.getGestureSource();
		if(event.getEventType() == MouseDragEvent.MOUSE_DRAG_RELEASED) {
			//TODO
		}
		if(event.getEventType() == MouseDragEvent.MOUSE_DRAG_EXITED_TARGET) {
			//TODO
		}
		if(event.getEventType() == MouseDragEvent.MOUSE_DRAG_OVER) {
			//TODO
			
		}
	}
}
