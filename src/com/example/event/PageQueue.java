package com.example.event;

import java.util.*;

import javafx.beans.property.*;
import javafx.event.*;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;


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
		index = new SimpleIntegerProperty(-1);
	}
	
	public void bind(IntegerProperty i) {
		i.bind(index);
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
