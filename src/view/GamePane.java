package view;

import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import model.Food;
import model.Snake;

public class GamePane extends Canvas {

	private static final int width = 400;
	private static final int height = 400;

	private Snake snake;
	private Food food;
	private StatusBar statusBar;
	
	private boolean isEnd;
	private boolean isGameRun;

	public GamePane(StatusBar statusBar) {
		super(width, height);
		this.statusBar = statusBar;
		isGameRun = false;
		isEnd = false;
	}

	public boolean isGameRun() {
		return isGameRun;
	}

	public void setGameRun(boolean isGameRun) {
		this.isGameRun = isGameRun;
	}

	public void init() {
		drawBackground();
		snake = new Snake();
		food = new Food(snake);
	}

	public void draw() {
		drawBackground();
		snake.move();
		if (snake.isCollide(food.getCurrPos())) {
			food.moveFood();
			snake.addLength();
			statusBar.increaseScore(1);
		}
		if (snake.isDead()) {
			isEnd = true;
		}
		food.drawFood(getGraphicsContext2D());
		snake.drawSnake(getGraphicsContext2D());
	}
	
	public void pause() {
		GraphicsContext gc = getGraphicsContext2D();
		gc.setFill(Color.rgb(0, 200, 30, 0.2));
		gc.fillRect(0, 0, width, height);
		gc.setTextAlign(TextAlignment.CENTER);
    gc.setTextBaseline(VPos.CENTER);
    gc.setFill(Color.WHITE);
    gc.setFont(new Font("Fira Mono", 48));
    gc.fillText("PAUSED", width/2, height/2);
	}
	
	public void welcome() {
		GraphicsContext gc = getGraphicsContext2D();
		gc.setFill(Color.rgb(10, 150, 200, 0.8));
		gc.fillRect(0, 0, width, height);
		gc.setTextAlign(TextAlignment.CENTER);
    gc.setTextBaseline(VPos.CENTER);
    gc.setFill(Color.WHITE);
    gc.setFont(new Font("Fira Mono", 26));
    gc.fillText("Joetoken's Snake Game!!!", width/2, 100);
    gc.setFont(new Font("Fira Mono", 16));
    gc.fillText("Press spacebar to start", width/2, 130);
	}
	
	public void gameOver() {
		GraphicsContext gc = getGraphicsContext2D();
		gc.setFill(Color.rgb(255, 0, 30, 0.2));
		gc.fillRect(0, 0, width, height);
		gc.setTextAlign(TextAlignment.CENTER);
    gc.setTextBaseline(VPos.CENTER);
    gc.setFill(Color.WHITE);
    gc.setFont(new Font("Fira Mono", 48));
    gc.fillText("GAME OVER", width/2, 100);
    gc.setFont(new Font("Fira Mono", 32));
    gc.fillText("Your Score " + statusBar.getScore(), width/2, 156);
    gc.setFont(new Font("Fira Mono", 16));
    gc.fillText("Press spacebar to try again...", width/2, 194);
	}

	private void drawBackground() {
		GraphicsContext gc = this.getGraphicsContext2D();
		gc.setFill(Color.DARKSLATEGREY);
		gc.fillRect(0, 0, width, height);
		gc.setFill(Color.LIGHTSLATEGREY);
		for (int i = 0; i <= 384; i += 16) {
			for (int j = i % 32 == 0 ? 0 : 16; j <= 384; j += 32) {
				gc.fillRect(i, j, 16, 16);
			}
		}
	}
	
	public void resetGame() {
		snake = new Snake();
		food = new Food(snake);
		isEnd = false;
		isGameRun = false;
		statusBar.resetScore();
		this.requestFocus();
	}

	public Snake getSnake() {
		return snake;
	}
	
	public boolean isEnd() {
		return isEnd;
	}

}
