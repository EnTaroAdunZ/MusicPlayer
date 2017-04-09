package com.example.event;

import javafx.beans.property.ObjectProperty;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class TagClickAction implements EventHandler<MouseEvent>{
	
	/*
	private Node target = null;
	public TagClickAction(Node target) {
		super();
		this.target = target;
	}
	*/
	private ContextBox cb = new ContextBox();
	public ContextBox getCb() {
		return cb;
	}

	@Override
	public void handle(MouseEvent event) {
		Node source = (Node)event.getSource();
		ContextMenu cm;
		if(source instanceof Button) cm = cb.getListContext();
		else cm = cb.getSongContext();
		if(event.getButton() == MouseButton.SECONDARY) {
			cm.show(source, event.getScreenX(), event.getScreenY());
		}
		if(event.getButton() == MouseButton.PRIMARY) {
			int count = event.getClickCount();
			if(count == 2) {
				//TODO
			}
		}
	}
	
}
class ContextBox {
	private ContextMenu listContext = new ContextMenu();
	private ContextMenu songContext = new ContextMenu();
	public static MenuItem play = new MenuItem("\u64ad\u653e");
	public static MenuItem play_all = new MenuItem("\u5168\u90e8\u64ad\u653e");
	public static MenuItem play_next = new MenuItem("\u4e0b\u4e00\u9996\u64ad\u653e");
	public static MenuItem path = new MenuItem("\u6253\u5f00\u6587\u4ef6\u76ee\u5f55");
	public static MenuItem remove_song = new MenuItem("\u79fb\u9664\u6b4c\u66f2");
	public static MenuItem remove_list = new MenuItem("\u79fb\u9664\u6b4c\u5355");
	
	public ContextBox() {//FIXME
		listContext.getItems().addAll(play_all, new SeparatorMenuItem(), remove_list);
		songContext.getItems().addAll(play, play_next, path, new SeparatorMenuItem(), remove_song);
		
		remove_list.setOnAction(e ->{
			Button target = (Button)listContext.getOwnerNode();
			String s = target.getText();
			System.out.println(s);
		});
		play.setOnAction(e ->{
			//FIXME
		});
	}

	public ContextMenu getListContext() {
		return listContext;
	}

	public ContextMenu getSongContext() {
		return songContext;
	}
	
	
}
