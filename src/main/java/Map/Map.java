package Map;

import Entity.Animate.Bomb;
import Entity.Animate.Character.Bomber;
import Entity.Animate.Character.Character;
import Entity.Animate.Character.Enemy.Enemy;
import Entity.Animate.Flame;
import Entity.Entity;;
import Entity.Static.StaticEntity;
import Graphics.Sprite;
import Input.PlayerInput;
import Texture.BombTexture;
import Texture.CharacterTexture;
import Texture.FlameTexture;
import Texture.StaticTexture;
import javafx.scene.canvas.GraphicsContext;

import javax.swing.plaf.synth.SynthTextAreaUI;
import java.io.File;
import java.io.FileNotFoundException;

import java.util.*;

import static Variables.Variables.*;

public class Map {
    private static Map map;
    private int levelNumber;
    private Entity[][] tiles;
    private ArrayList<Enemy> enemies;
    private ArrayList<Bomb> bombs;
    private Bomber player;
    private ArrayList<Flame> flames;

    public static Map getGameMap() {
        if (map == null) {
            map = new Map();
        }
        return map;
    }

    private void resetEntities() {
        tiles = new Entity[HEIGHT][WIDTH];
        enemies = new ArrayList<>();
        bombs = new ArrayList<>();
        flames = new ArrayList<>();
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
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
                    if (c == 'p') {
                        player = (Bomber) character;
                    } else {
                        enemies.add((Enemy) character);
                    }
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

        enemies.forEach(enemy -> {
            enemy.update();
        });
        player.update();
        bombs.forEach(bomb -> {
            bomb.update();
        });
        flames.forEach(flame -> {
            flame.update();
        });
    }

    public void renderMap(GraphicsContext graphicsContext) {
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                tiles[i][j].render(graphicsContext);
            }
        }
        enemies.forEach(enemy -> {
            enemy.render(graphicsContext);
        });
        player.render(graphicsContext);
        bombs.forEach(bomb -> {
            bomb.render(graphicsContext);
        });
        flames.forEach(flame -> {
            flame.render(graphicsContext);
        });
    }

    public void setTile(int x, int y, Entity entity) {
        tiles[x][y] = entity;
    }

    public Entity getTile(int x, int y) {
        return tiles[y][x];
    }

    public Bomber getPlayer() {
        return this.player;
    }

    public ArrayList<Bomb> getBombs() {
        return bombs;
    }

    public ArrayList<Flame> getFlames() {
        return flames;
    }
}