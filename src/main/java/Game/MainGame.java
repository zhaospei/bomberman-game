package Game;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import Map.Map;
import static Graphics.Sprite.SCALED_SIZE;
import static Variables.Variables.*;

public class MainGame extends Application {
    private static Map map = Map.getGameMap();
    private GraphicsContext graphicsContext;
    private Canvas canvas;

    private final long timePerFrame = 100000000;
    public static long time;
    private long startTime;

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle(GAME_TITLE);
        canvas = new Canvas(SCALED_SIZE * WIDTH, SCALED_SIZE * HEIGHT);
        graphicsContext = canvas.getGraphicsContext2D();

        Group root = new Group();
        root.getChildren().add(canvas);

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        startTime = System.nanoTime();
        map.createMap(MAP_URLS[0]);
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long currentTime) {
                graphicsContext.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                time = (long) ((currentTime - startTime)) / timePerFrame;
                System.out.println(time);
                map.updateMap();
                map.renderMap(graphicsContext);
            }
        };
        timer.start();
    }

    public static void main(String[] args) {
        launch();
    }
}