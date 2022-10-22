package Map;

import Entity.Entity;;
import Texture.AnimateTexture;
import Texture.StaticTexture;
import javafx.scene.canvas.GraphicsContext;

import java.io.File;
import java.io.FileNotFoundException;

import java.util.Scanner;

public class Map {
    private static Map gameMap;
    private int levelNumber;
    private int WIDTH;
    private int HEIGHT;
    private static Entity[][] staticEntities;
    private static Entity[][] animateEntities;

    public static Map getGameMap() {
        if (gameMap == null) {
            gameMap = new Map();
        }
        return gameMap;
    }

    private void resetEntities() {
        staticEntities = new Entity[HEIGHT][WIDTH];
        animateEntities = new Entity[HEIGHT][WIDTH];
    }

    public void createMap(String mapPath) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(mapPath));
        levelNumber = scanner.nextInt();
        HEIGHT = scanner.nextInt();
        WIDTH = scanner.nextInt();
        scanner.nextLine();
        resetEntities();
        for (int i = 0; i < HEIGHT; i++) {
            String string = scanner.nextLine();
            for (int j = 0; j < WIDTH; j++) {
                char c = string.charAt(j);
                staticEntities[i][j] = StaticTexture.getStatic(c, i, j);
                animateEntities[i][j] = AnimateTexture.getAnimate(c, i, j);
            }
        }
    }

    public void updateMap() {
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                if (animateEntities[i][j] != null) {
                    animateEntities[i][j].update();
                }
            }
        }
    }

    public void renderMap(GraphicsContext graphicsContext) {
        graphicsContext.clearRect(0, 0, WIDTH, HEIGHT);
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                if (staticEntities[i][j] != null) {
                    staticEntities[i][j].render(graphicsContext);
                }
            }
        }
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                if (animateEntities[i][j] != null) {
                    animateEntities[i][j].render(graphicsContext);
                }
            }
        }
    }

}