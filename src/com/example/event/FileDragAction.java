package com.example.event;

import java.io.File;

import com.example.entity.*;
import com.example.service.SongOperate;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.input.*;

public class FileDragAction implements EventHandler<DragEvent>{
	
	
	
	public FileDragAction(ObjectProperty<SongMenu> menu) {
		this.menu.bind(menu);
	}

	private TableView<Tag> target;
	private ObjectProperty<SongMenu> menu = new SimpleObjectProperty<>();
	
	@Override
	public void handle(DragEvent event) {
		Object o = event.getGestureTarget();
		if(o instanceof TableView<?>)
			target = (TableView<Tag>)o;
		else return;
		if (event.getEventType() == DragEvent.DRAG_DROPPED) {
			Dragboard dragboard = event.getDragboard();
			ObservableList<Tag> tagList = target.getItems(); 
			Tag tag;
			if (dragboard.hasFiles()) {
				for(File file : dragboard.getFiles()) {
					//try {
						if (file != null) {
							String path = file.getAbsolutePath();
							Song newsong = SongOperate.addSong(path, menu.get().getSongMenuName());
							tag = newsong != null ? newsong.getTag() : null;
							if(tag != null) tagList.add(tag);
						}
				//	} catch (Exception e) {
				//		e.printStackTrace();
				//	}
				}
			}
		}
		if(event.getEventType() == DragEvent.DRAG_OVER)
			if (event.getGestureSource() != target){
            event.acceptTransferModes(TransferMode.ANY);
        }
	}

}
