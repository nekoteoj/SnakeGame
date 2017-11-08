package model;

import java.util.Random;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import model.utility.Pair;

public class Food {
	
	private Random random;
	private Pair currPos;
	private Snake snake;
	
	
	public Food(Snake snake) {
		this.snake = snake;
		currPos = new Pair();
		random = new Random();
		moveFood();
	}
	
	public void moveFood() {
		int row, col;
		do {
			row = random.nextInt(25);
			col = random.nextInt(25);
			currPos.set(col, row);
		} while (snake.isCollide(currPos));
	}
	
	public void drawFood(GraphicsContext gc) {
		int col = currPos.getX() * 16;
		int row = currPos.getY() * 16;
		gc.setFill(Color.PINK);
		gc.fillRect(col, row, 16, 16);
	}
	
	public Pair getCurrPos() {
		return currPos;
	}
}
