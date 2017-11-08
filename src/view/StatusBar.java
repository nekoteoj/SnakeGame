package view;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class StatusBar extends HBox {
	
	private static final int height = 32;
	private static final int width = 400;
	
	private int score = 0;
	Label l = new Label("Score : 0");
	
	public StatusBar() {
		super();
		this.setPrefWidth(width);
		this.setPrefHeight(height);
		this.setStyle("-fx-background-color: #EEEEEE");
		updateStatusBar();
		this.getChildren().addAll(l);
		this.setPadding(new Insets(2));
		this.setSpacing(10);
	}
	
	public void increaseScore(int amount) {
		score += amount;
		updateStatusBar();
	}
	
	public void resetScore() {
		score = 0;
		updateStatusBar();
	}
	
	public int getScore() {
		return score;
	}
	
	private void updateStatusBar() {
		l.setText("Score : " + score);
	}
}
