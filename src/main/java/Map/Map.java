package Map;

import Entity.Animate.Character.Bomber;
import Entity.Animate.Character.Character;
import Entity.Entity;;
import Entity.Static.StaticEntity;
import Texture.CharacterTexture;
import Texture.StaticTexture;
import javafx.scene.canvas.GraphicsContext;

import java.io.File;
import java.io.FileNotFoundException;

import java.util.ArrayList;
import java.util.Scanner;
import static Variables.Variables.*;

public class Map {
    private static Map gameMap;
    private int levelNumber;
    private Entity[][] tiles;
    private ArrayList<Character> characters;
    private Bomber player;
    public static Map getGameMap() {
        if (gameMap == null) {
            gameMap = new Map();
        }
        return gameMap;
    }

    private void resetEntities() {
        tiles = new Entity[HEIGHT][WIDTH];
        characters = new ArrayList<>();
    }

    public ArrayList<Character> getCharacters() {
        return characters;
    }

    public void createMap(String mapPath) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(mapPath));
        scanner.nextLine();
        resetEntities();
        for (int i = 0; i < HEIGHT; i++) {
            String string = scanner.nextLine();
            for (int j = 0; j < WIDTH; j++) {
                char c = string.charAt(j);
                tiles[i][j] = StaticTexture.setStatic(c, i, j);
                Character character = CharacterTexture.setCharacter(c, i, j);
                if (character != null) {
                    characters.add(character);
                }
            }
        }
    }

    public void updateMap() {
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                tiles[i][j].update();
            }
        }

        characters.forEach(character -> {
            character.update();
        });
    }

    public void renderMap(GraphicsContext graphicsContext) {
        graphicsContext.clearRect(0, 0, WIDTH, HEIGHT);
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                tiles[i][j].render(graphicsContext);
            }
        }
        characters.forEach(character -> {
            character.render(graphicsContext);
        });
    }

    public void setTiles(int x, int y, Entity entity) {
        tiles[x][y] = entity;
    }

    public Entity[][] getTiles(){
        return tiles;
    }
}