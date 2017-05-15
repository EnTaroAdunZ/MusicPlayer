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
	static Page P = new Page();
	
	private int type;
	public int getType() {
		return type;
	}
	protected void setType(int type) {
		this.type = type;
	}

	private Parent page;
	public Parent getPage() {
		return page;
	}
	protected void setPage(Parent page) {
		this.page = page;
	}

	private Controller ctrl;
	public Controller getCtrl() {
		return ctrl;
	}
	protected void setCtrl(Controller ctrl) {
		this.ctrl = ctrl;
	}


	public class SearchPage extends Page{
		
		public SearchPage(SearchPageController controller) {
			super();
			setCtrl(controller);
			setType(Controller.SEARCH);
		}
		private String key;
		public String getKey() {
			return key;
		}
		public void setKey(String name) {
			this.key = name;
		}
		
	}

	public class LocalPage extends Page{
		
		public LocalPage(LocalMusicPageController controller) {
			super();
			setCtrl(controller);
			setType(Controller.LOCAL);
		}
	}
	
	public class MusicListPage extends Page{
		
		public MusicListPage(MusicListPageController controller) {
			super();
			setCtrl(controller);
			setController(controller);
			setType(Controller.MUSICLIST);
		}
		
		private String key;
		public String getKey() {
			return key;
		}
		public void setKey(String name) {
			this.key = name;
		}
		private MusicListPageController controller;
		public MusicListPageController getController() {
			return controller;
		}
		protected void setController(MusicListPageController controller) {
			this.controller = controller;
		}
		
	}
	
	public class PlayPage extends Page{
		
		public PlayPage(PlayPageController controller) {
			super();
			setCtrl(controller);
			setType(Controller.PLAY);
		}
	}
	
	/**
	 * The special Page which will occupy whole the GUI
	 * @author Y
	 *	
	 */
	public class SettingPage extends Page{
		public SettingPage() {
			
			setType(Controller.SETTING);
		}
	}


	public static Page newPage(int type, Parent page, Controller controller) {
		Page p;
		switch (type) {
		default:
		case Controller.MUSICLIST:
			p = P.new MusicListPage((MusicListPageController) controller);
			break;
		case Controller.SEARCH:
			p = P.new SearchPage((SearchPageController) controller);
			break;
		case Controller.LOCAL:
			p = P.new LocalPage((LocalMusicPageController) controller);
			break;
		case Controller.SETTING:
			p = P.new SettingPage();
			break;
		case Controller.PLAY:
			p = P.new PlayPage((PlayPageController) controller);
			break;
		}
		p.setType(type);
		p.setPage(page);
		return p;
				
	}

	
}
