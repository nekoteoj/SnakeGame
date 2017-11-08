package controller;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import view.GamePane;

public class EventManager {
	
	private Scene scene;
	private GamePane gp;
	private AnimationTimer atime;
	
	public EventManager(Scene scene, GamePane gp, AnimationTimer atime) {
		this.scene = scene;
		this.gp = gp;
		this.atime = atime;
	}
	
	public void addSnakeHandler() {
		scene.addEventHandler(KeyEvent.KEY_PRESSED, key -> {
			KeyCode in = key.getCode();
			if (in == KeyCode.SPACE) {
				gp.setGameRun(!gp.isGameRun());
				if (gp.isGameRun()) {
					atime.start();
				} else {
					atime.stop();
					gp.pause();
				}
			}
			if (in == KeyCode.UP && gp.getSnake().getDirection() != 2) {
				gp.getSnake().setDirection(0);
			} else if (in == KeyCode.DOWN && gp.getSnake().getDirection() != 0) {
				gp.getSnake().setDirection(2);
			} else if (in == KeyCode.LEFT && gp.getSnake().getDirection() != 1) {
				gp.getSnake().setDirection(3);
			} else if (in == KeyCode.RIGHT && gp.getSnake().getDirection() != 3) {
				gp.getSnake().setDirection(1);
			}
		});
	}
	
}
