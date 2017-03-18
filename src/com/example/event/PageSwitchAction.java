package com.example.event;

import java.util.*;

import javafx.beans.property.*;
import javafx.event.*;
import javafx.scene.Parent;
import javafx.scene.control.Button;

public class PageSwitchAction implements EventHandler<ActionEvent>{
	
	private PageQueue pagequeue;
	
	@Override
	public void handle(ActionEvent event) {
		// FIXME Auto-generated method stub
		Button b = (Button)event.getSource();
		String s = b.getText();
		if(s.equals("<")) {
			pagequeue.backward();
		}else {
			pagequeue.forward();
		}
		
	}
	
	
	
	/**
	 * The stack for storing pages
	 * @author Y
	 *
	 */
	public class PageQueue{
		private LinkedList<Page> pagestack;
		

		/**
		 * What the variable expresses, it means which page should be presented.
		 * Supposed to use this variable to control the button "<" or ">" to be
		 * disable whether or not.
		 */
		private IntegerProperty index;
		public IntegerProperty IndexProperty() {
			return index;
		}
		public int getIndex() {
			return index.get();
		}
		private int ppindex() {
			index.set(index.get() + 1);
			return index.get();
		}
		private int ssindex() {
			index.set(index.get() - 1);
			return index.get();
		}
		
		public PageQueue() {
			pagestack = new LinkedList<>();
			index = new SimpleIntegerProperty(0);
		}
		
		/**
		 *  Adds a new page into the stack
		 */
		public void add(Page page) {
			pagestack.add(index.get() + 1, page);
			ppindex();
			if(pagestack.size() > 10 && index.get() == 10) pagestack.removeFirst();
			save();
		}
		
		private void save() {
			while(pagestack.size() > index.get() + 1) {
				pagestack.removeLast();
			}
		}
		
		/**
		 *  Travels backward and retrieves the page if it exists.
		 */
		public Page backward() {
			return index.get() > 0 ? pagestack.get(ssindex()) : null;
		}
		
		/**
		 *  Travels forward and retrieves the page if it exists.
		 */
		public Page forward() {
			return index.get() < 9 ? pagestack.get(ppindex()) : null;
		}
		
		
	}
	
	/**
	 * One kind of the pages which appears after searching
	 * @author Y
	 * 
	 */
	public class TabPage extends Page{
		
		public TabPage(int type, Parent page) {
			super();
			this.type = type;
			super.setPage(page);
		}
		
		/**
		 * This determine the page is the result of by what searching
		 */
		public static final int
			name = 0, artist = 1, album = 2, sheet = 3, lyric = 4;
		private int type;
		public int getType() {
			return type;
		}
				
	}
	
	public class MenuPage extends Page{
		public MenuPage(Parent page) {
			super();
			super.setPage(page);
		}
	}
	/**
	 * The special Page which will occupy whole the GUI
	 * @author Y
	 *	
	 */
	public class SettingPage extends Page{

		public SettingPage(Parent page) {
			super();
			super.setPage(page);
		}
		
		//TODO
	}
	/**
	 * The part of the GUI 
	 * @author Y
	 *  
	 */
	public abstract class Page{
		
		private Parent page;
		protected Parent getPage() {
			return page;
		}
		protected void setPage(Parent page) {
			this.page = page;
		}
	}
	
}
