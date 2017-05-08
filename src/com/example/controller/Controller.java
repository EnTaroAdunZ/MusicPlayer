/**
 * 
 */
package com.example.controller;

import com.example.gui.MusicUtils;

import javafx.scene.control.TableView;
import javafx.scene.layout.StackPane;

/**
 * The interface of controllers
 * @author Y
 *
 */
public interface Controller{

	public static final int MAIN = 0, PLAY = 1, SEARCH = 2, LOCAL = 3, MUSICLIST = 4, SETTING = 5;
	
	public interface ContentController extends Controller{
		TableView<MusicUtils> getTableView_musicTable();
	}
	
	public interface StackController extends Controller{
		StackPane getStackPane();
	}
}
