package Game;

import Input.KeyInput;
import Texture.FontTexture;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import Map.Map;
import static Graphics.Sprite.SCALED_SIZE;
import static Variables.Variables.*;

public class MainGame extends Application {
    private static Map map = Map.getGameMap();
    private GraphicsContext graphicsContext;
    private Canvas canvas;
    private final double FPS = 120.0;
    private final long timePerFrame = (long) (1000000000 / FPS);
    private long lastFrame;
    private int frames;
    public static long time;
    private long startTime;
    private long lastTime;

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle(GAME_TITLE);
        canvas = new Canvas(WIDTH_SCREEN * SCALED_SIZE, HEIGHT_SCREEN * SCALED_SIZE);
        graphicsContext = canvas.getGraphicsContext2D();
        //HBox info = new HBox(FontTexture.createText("hello", 0, 40, Color.BLACK));
        VBox root = new VBox(canvas);
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setResizable(false);
        stage.getIcons().add(new Image("/icon.png"));
        stage.show();
        startTime = System.nanoTime();
        lastFrame = 0;
        lastTime = 0;
        map.createMap(MAP_URLS[0]);
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long currentTime) {
                long now = currentTime - startTime;
                if (now - lastFrame >= timePerFrame) {
                    lastFrame = now;
                    map.updateMap();
                    map.renderMap(graphicsContext);
                    scene.setOnKeyPressed(keyEvent -> {
                        String code = keyEvent.getCode().toString();
                        KeyInput.keyInput.put(code, true);
                    });;
                    scene.setOnKeyReleased(keyEvent -> {
                        String code = keyEvent.getCode().toString();
                        KeyInput.keyInput.put(code, false);
                    });
                    frames ++;

                }

                if (now - lastTime >= 1000000000) {
                    stage.setTitle(GAME_TITLE + " | " + frames + " FPS" + " | LIFES: " + map.getPlayer().getLife());
                    frames = 0;
                    lastTime = now;
                }
                time = (long) ((currentTime - startTime)) / 60000000 + 1;
            }
        };
        timer.start();
    }

    public static void main(String[] args) {
        launch();
    }
}