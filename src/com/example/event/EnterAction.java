package com.example.event;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class EnterAction implements EventHandler<KeyEvent>{
	
	public EnterAction(TextField t, Button b) {
		this.t = t;
		this.b = b;
	}
	private TextField t;
	private Button b;
	public TextField getT() {
		return t;
	}
	public Button getB() {
		return b;
	}
	@Override
	public void handle(KeyEvent event) {
		if (MainAction.presskey(t, event)) {
			b.fire();
			if (b.getText().length() == 1)
				t.clear();
		}
	}
}