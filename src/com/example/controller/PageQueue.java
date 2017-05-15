package com.example.controller;

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
	private int pp(IntegerProperty n) {
		n.set(n.get() + 1);
		return n.get();
	}
	private int ss(IntegerProperty n) {
		n.set(n.get() - 1);
		return n.get();
	}
	
	private IntegerProperty size;
	public IntegerProperty sizeProperty() {
		return size;
	}
	public int getSize() {
		return size.get();
	}
	 
	
	public PageQueue() {
		pagestack = new LinkedList<>();
		index = new SimpleIntegerProperty(-1);
		size = new SimpleIntegerProperty(0);
	}
	
	public void bind(IntegerProperty i, IntegerProperty s) {
		i.bind(index);
		s.bind(size);
	}
	
	/**
	 *  Adds a new page into the stack
	 */
	public void add(Page page) {
		pagestack.add(pp(index), page);
		size.set(pagestack.size()); 
		if(pagestack.size() > 10 ) {
			pagestack.removeFirst();
			System.gc();
			ss(size);
			ss(index);
		}
		save();
	}
	
	private void save() {
		while(pagestack.size() > index.get() + 1) {
			pagestack.removeLast();
			System.gc();
			ss(size);
		}
	}
	
	/**
	 *  Travels backward and retrieves the page if it exists.
	 */
	public Page backward() {
		return index.get() > 0 ? pagestack.get(ss(index)) : null;
	}
	
	/**
	 *  Travels forward and retrieves the page if it exists.
	 */
	public Page forward() {
		return index.get() < 9 ? pagestack.get(pp(index)) : null;
	}
	
	public Page getPage() {
		return pagestack.get(getIndex());
	}
}
