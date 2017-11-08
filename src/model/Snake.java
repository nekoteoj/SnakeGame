package model;

import java.util.LinkedList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import model.utility.Pair;

public class Snake {
	
	private LinkedList<Pair> snake;
	/**
	 * direction
	 * 0 for UP
	 * 1 for RIGHT
	 * 2 for DOWN
	 * 3 for LEFT
	 */
	private int direction;
	private int tick = 0;
	
	public Snake() {
		snake = new LinkedList<>();
		direction = 0;
		snake.add(new Pair(12, 12));
	}
	
	public void move() {
		if (tick > 3) {
			addLength();
			snake.removeLast();
			tick = 0;
		} else {
			tick++;
		}
	}
	
	public void addLength() {
		Pair head = snake.getFirst();
		int row = head.getY();
		int col = head.getX();
		if (direction == 0) {
			row = ((row - 1) + 25) % 25;
		} else if (direction == 1) {
			col = (col + 1) % 25;
		} else if (direction == 2) {
			row = (row + 1) % 25;
		} else if (direction == 3) {
			col = ((col - 1) + 25) % 25;
		}
		snake.addFirst(new Pair(col, row));
	}
	
	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}
	
	public boolean isCollide(Pair other) {
		for (Pair p : snake) {
			if (p.getX() == other.getX() && p.getY() == other.getY()) {
				return true;
			}
		}
		return false;
	}
	
	public boolean isDead() {
		Pair head = snake.getFirst();
		for (int i = 1; i < snake.size(); i++) {
			Pair o = snake.get(i);
			if (head.getX() == o.getX() && head.getY() == o.getY()) {
				return true;
			}
		}
		return false;
	}

	public void drawSnake(GraphicsContext gc) {
		gc.setFill(Color.ORANGE);
		for (Pair p : snake) {
			int x = p.getX() * 16;
			int y = p.getY() * 16;
			//gc.fillRect(x, y, 16, 16);
			//gc.fillOval(x, y, 16, 16);
			gc.fillRoundRect(x, y, 16, 16, 10, 10);
			gc.setFill(Color.AQUA);
		}
	}
	
}
