package com.example.Global;

import java.util.Observable;

/** 
* @author ZTF  
* @date 2017年4月20日 下午9:18:18 
* @version 1.0 
* @Description:   观察者
*/
public class ObserverManage extends Observable{
	private static ObserverManage myObserverManage=null;
	
	public static ObserverManage getObserver() {
		if (myObserverManage == null) {
			myObserverManage = new ObserverManage();
			System.out.println("ObserverManage被创建了");
		}
		return myObserverManage;
	}
	
	public void setMessage(Object data) {
		System.out.println("myObserverManage被设置了");
		myObserverManage.setChanged();
		myObserverManage.notifyObservers(data);
	}
}
 