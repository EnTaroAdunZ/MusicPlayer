package com.example.event;


import javafx.scene.Parent;

/**
 * One kind of the pages which appears after searching
 * @author Y
 * 
 */

/**
 * The part of the GUI 
 * @author Y
 *  
 */
public class Page{
	
	public static final int
	tab = 0, menu = 1, setting = 2;
	
	private Parent page;
	protected Parent getPage() {
		return page;
	}
	protected void setPage(Parent page) {
		this.page = page;
	}
	
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


	public Page newPage(int type, Parent root) {
		Page p;
		switch(type) {
		default:
		case 0: p = new TabPage(type, root);break;
		case 1: p = new MenuPage(root);break;
		case 2: p = new SettingPage(root);break;
		}
		return p;
				
	}
}
