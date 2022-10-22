package Map;

import Entity.Animate.AnimateEntity;
import Entity.Entity;;
import Texture.AnimateTexture;
import Texture.StaticTexture;
import javafx.scene.canvas.GraphicsContext;

import java.io.File;
import java.io.FileNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Map {
    private static Map gameMap;
    private int levelNumber;
    private int WIDTH;
    private int HEIGHT;
    public static Entity[][] staticEntities;

    public List<Entity> animateEntities = new ArrayList<Entity>();

    public static Map getGameMap() {
        if (gameMap == null) {
            gameMap = new Map();
        }
        return gameMap;
    }

    private void resetEntities() {
        staticEntities = new Entity[HEIGHT][WIDTH];
        animateEntities = new ArrayList<Entity>();
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
                Entity animateEntity = AnimateTexture.getAnimate(c, i, j);
                if (animateEntity != null) {
                    animateEntities.add(animateEntity);
                }
            }
        }
    }

    public void updateMap() {
        animateEntities.forEach(entity -> {
            entity.update();
        });
    }

    public void renderMap(GraphicsContext graphicsContext) {
        graphicsContext.clearRect(0, 0, WIDTH, HEIGHT);
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                staticEntities[i][j].render(graphicsContext);
            }
        }
        animateEntities.forEach(animateEntity -> {
            animateEntity.render(graphicsContext);
        });
    }

}