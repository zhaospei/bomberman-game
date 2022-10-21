package GameController;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class Bomberman extends Application {
    public static Canvas canvas = new Canvas();

    public static GraphicsContext gc = canvas.getGraphicsContext2D();



    @Override
    public void start(Stage stage) {
        stage.setTitle("creating canvas");

        // create a canvas
        Canvas canvas = new Canvas(100.0f, 100.0f);

        // graphics context
        GraphicsContext graphics_context =
                canvas.getGraphicsContext2D();

        // set fill for rectangle
        graphics_context.setFill(Color.RED);
        graphics_context.fillRect(20, 20, 70, 70);

        // set fill for oval
        graphics_context.setFill(Color.BLUE);
        graphics_context.fillOval(30, 30, 70, 70);

        // create a Group
        Group group = new Group(canvas);

        // create a scene
        Scene scene = new Scene(group, 200, 200);

        // set the scene
        stage.setScene(scene);

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}