import controller.EventManager;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import view.GamePane;
import view.StatusBar;

public class App extends Application {

	GamePane gamePane;
	AnimationTimer atime;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane root = new BorderPane();
		
		StatusBar statusBar = new StatusBar();
		root.setBottom(statusBar);
		
		gamePane = new GamePane(statusBar);
		
		gamePane.init();
		startGame();
		root.setCenter(gamePane);
		
		Scene scene = new Scene(root);
		
		EventManager eventManager = new EventManager(scene, gamePane, atime);
		eventManager.addSnakeHandler();
		
		primaryStage.setTitle("Joetoken's Snake Game");
		primaryStage.setScene(scene);
		primaryStage.show();
		gamePane.requestFocus();
	}
	
	public void startGame() {
		atime = new AnimationTimer() {
			@Override
			public void handle(long time) {
				if (gamePane.isEnd()) {
					this.stop();
					gamePane.gameOver();
					gamePane.resetGame();
					gamePane.setGameRun(false);
				} else {
					gamePane.draw();
				}
			}
		};
		gamePane.welcome();
	}

	public static void main(String[] args) {
		Application.launch(args);
	}

}
