package GameController;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import static Variables.Variables.*;

public class Bomberman extends Application {
    public static Canvas canvas = new Canvas();

    public static GraphicsContext gc = canvas.getGraphicsContext2D();

    private final double FPS = 60.0;
    private final double timePerFrame = 1000000000.0 / FPS;
    long lastUpdate = System.currentTimeMillis();
    int frameRate = 0;

    public static Stage stage;

    private final long[] frameTimes = new long[100];
    private int frameTimeIndex = 0 ;
    private boolean arrayFilled = false ;

    @Override
    public void start(Stage stage) {
        Label label = new Label();
        AnimationTimer frameRateMeter = new AnimationTimer() {

            @Override
            public void handle(long now) {
                long oldFrameTime = frameTimes[frameTimeIndex] ;
                frameTimes[frameTimeIndex] = now ;
                frameTimeIndex = (frameTimeIndex + 1) % frameTimes.length ;
                if (frameTimeIndex == 0) {
                    arrayFilled = true ;
                }
                if (arrayFilled) {
                    long elapsedNanos = now - oldFrameTime ;
                    long elapsedNanosPerFrame = elapsedNanos / frameTimes.length ;
                    double frameRate = 1_000_000_000.0 / elapsedNanosPerFrame ;
                    label.setText(String.format("Current frame rate: %.3f", frameRate));
                }
            }
        };

        frameRateMeter.start();

        stage.setScene(new Scene(new StackPane(label), 450, 250));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}