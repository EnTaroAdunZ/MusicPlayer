package com.example.event;

import java.io.IOException;

import com.example.Global.GlobalVariable;
import com.example.gui.MusicUtils;
import com.example.service.SongMenuOperate;
import com.example.service.SongOperate;

import javafx.beans.property.ObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class TagClickAction implements EventHandler<MouseEvent>{
	
	
	public TagClickAction(MainAction ma, ListView<Button> listVessel) {
		super();
		this.ma = ma;
		this.listVessel = listVessel;
	}

	private ContextBox cb = new ContextBox(this);	//右键菜单对象
	public ContextBox getCb() {
		return cb;
	}
	
	private MainAction ma;	
	public MainAction getMa() {
		return ma;
	}

	private ListView<Button> listVessel;
	public ListView<Button> getListVessel() {
		return listVessel;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void handle(MouseEvent event) {
		Node source = (Node)event.getSource();
		ContextMenu cm;
		if(source instanceof Button) cm = cb.getListContext();
		else cm = cb.getSongContext();
		if(event.getButton() == MouseButton.SECONDARY) {
			cm.show(source, event.getScreenX(), event.getScreenY());
			if(cm == cb.getSongContext() &&
					cm.getOwnerNode() instanceof TableView &&
					((TableView)cm.getOwnerNode()).getSelectionModel().getSelectedItems().isEmpty()) {
				cm.hide();
			}
		}
		if(event.getButton() == MouseButton.PRIMARY) {
			cm.hide();
			int count = event.getClickCount();
			if(count == 2) {
				//TODO
			}
		}
	}
	
}

class ContextBox {
	private TagClickAction action;
	private ObservableList<MusicUtils> ml = FXCollections.observableArrayList();
	private ContextMenu listContext = new ContextMenu();
	private ContextMenu songContext = new ContextMenu();
	public static Menu add = new Menu("\u6dfb\u52a0\u5230\u6b4c\u5355");
	public static MenuItem play = new MenuItem("\u64ad\u653e");
	public static MenuItem play_all = new MenuItem("\u5168\u90e8\u64ad\u653e");
	public static MenuItem play_next = new MenuItem("\u4e0b\u4e00\u9996\u64ad\u653e");
	public static MenuItem path = new MenuItem("\u6253\u5f00\u6587\u4ef6\u76ee\u5f55");
	public static MenuItem remove_song = new MenuItem("\u79fb\u9664\u6b4c\u66f2");
	public static MenuItem remove_list = new MenuItem("\u79fb\u9664\u6b4c\u5355");
	
	@SuppressWarnings({ "rawtypes" })
	public ContextBox(TagClickAction action) {//FIXME
		this.action = action;
		
		listContext.getItems().addAll(play_all, new SeparatorMenuItem(), remove_list);
		songContext.getItems().addAll(play, play_next, new SeparatorMenuItem(), add, path, new SeparatorMenuItem(), remove_song);
		songContext.setOnShowing(e ->{
			refreshMenu();
		});
		
		play_all.setOnAction(e ->{
			//FIXME
		});
		play.setOnAction(e ->{
			//FIXME
		});
		play_next.setOnAction(e ->{
			//FIXME
		});
		remove_list.setOnAction(e ->{
			Button target = (Button)listContext.getOwnerNode();
			if(target == null) return;
			SongMenuOperate.deleteSongMenuByName(target.getText());
			action.getListVessel().getItems().remove(target);
		});
		remove_song.setOnAction(e ->{
			TableView target = (TableView)songContext.getOwnerNode();
			if(target == null) return;
			for(MusicUtils m : ml) {
				
				//FIXME SongOperate.
			}
		});
		play.setOnAction(e ->{
			//FIXME
			
		});
		path.setOnAction(e ->{
			String p = ml.get(0).getPath();
			System.err.println(p);
			try {
				Runtime.getRuntime().exec("explorer /select,"+ p);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
		
	}
	
	@SuppressWarnings("unchecked")
	private void refreshMenu() {
		ObservableList<Button> bl = action.getListVessel().getItems();
		ml.setAll(((TableView<MusicUtils>)songContext.getOwnerNode()).getSelectionModel().getSelectedItems());
		add.getItems().clear();
		for(Button b : bl) {
			String text = b.getText();
			MenuItem i = new MenuItem(text);
			i.setOnAction(ie ->{
				for(MusicUtils m : ml) {
					String p = m.getPath();
					String n = b.getText();
					SongOperate.addSong(p, n);
				}
			});
			add.getItems().add(i);
		}
		if(ml.size() == 1) {
			path.setDisable(false);
		}else {
			path.setDisable(true);
		}
	}
	
	public ContextMenu getListContext() {
		return listContext;
	}	
	public ContextMenu getSongContext() {
		return songContext;
	}
	public TagClickAction getAction() {
		return action;
	}
			
}
