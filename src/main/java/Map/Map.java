package Map;

import Entity.Animate.Character.Bomber;
import Entity.Animate.Character.Character;
import Entity.Entity;;
import Entity.Static.StaticEntity;
import Graphics.Sprite;
import Input.PlayerInput;
import Texture.CharacterTexture;
import Texture.StaticTexture;
import javafx.scene.canvas.GraphicsContext;

import javax.swing.plaf.synth.SynthTextAreaUI;
import java.io.File;
import java.io.FileNotFoundException;

import java.util.ArrayList;
import java.util.Scanner;
import static Variables.Variables.*;

public class Map {
    private static Map map;
    private int levelNumber;
    private Entity[][] tiles;
    private ArrayList<Character> characters;
    private Bomber player;
    public static Map getGameMap() {
        if (map == null) {
            map = new Map();
        }
        return map;
    }

    private void resetEntities() {
        tiles = new Entity[HEIGHT][WIDTH];
        characters = new ArrayList<>();
    }

    public ArrayList<Character> getCharacters() {
        return characters;
    }

    public void createMap(String mapPath) throws FileNotFoundException {
        System.out.println("CREATE A MAP");
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
                if (c == 'p') player = new Bomber(i, j, Sprite.PLAYER_RIGHT[0], new PlayerInput());
            }
        }
        characters.add(player);
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

    public void setTile(int x, int y, Entity entity) {
        tiles[x][y] = entity;
    }

    public Entity getTile(int x, int y) {
        return tiles[x][y];
    }

    public Entity[][] getTiles(){
        return tiles;
    }

    public Bomber getPlayer() {
        return this.player;
    }
}