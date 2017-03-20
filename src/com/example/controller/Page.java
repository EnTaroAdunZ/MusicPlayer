package com.example.controller;


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
	private int type;
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}

	private Parent page;
	public Parent getPage() {
		return page;
	}
	public void setPage(Parent page) {
		this.page = page;
	}

	public class SearchPage extends Page{
		
		public SearchPage(SearchPageController controller) {
			super();
			this.controller = controller;
		}
		private SearchPageController controller;
		public SearchPageController getController() {
			return controller;
		}
	}

	public class LocalPage extends Page{
		
		public LocalPage(LocalMusicPageController controller) {
			super();
			this.controller = controller;
		}
		private LocalMusicPageController controller;
		public LocalMusicPageController getController() {
			return controller;
		}
	}
	
	public class PlayPage extends Page{
		
		public PlayPage(PlayPageController controller) {
			super();
			this.controller = controller;
		}
		private PlayPageController controller;
		public PlayPageController getController() {
			return controller;
		}
	}
	
	/**
	 * The special Page which will occupy whole the GUI
	 * @author Y
	 *	
	 */
	public class SettingPage extends Page{
		
	}


	public Page newPage(int type, Parent page, Controller controller) {
		Page p;
		setType(type);
		setPage(page);
		switch (type) {
		default:
		case Controller.SEARCH:
			p = new SearchPage((SearchPageController) controller);
			break;
		case Controller.LOCAL:
			p = new LocalPage((LocalMusicPageController) controller);
			break;
		case Controller.SETTING:
			p = new SettingPage();
			break;
		case Controller.PLAY:
			p = new PlayPage((PlayPageController) controller);
			break;
		}
		return p;
				
	}
}
