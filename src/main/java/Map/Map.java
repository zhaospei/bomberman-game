package Map;

import Entity.Animate.Bomb;
import Entity.Animate.Brick;
import Entity.Animate.Character.Bomber;
import Entity.Animate.Character.Character;
import Entity.Animate.Character.Enemy.Enemy;
import Entity.Animate.Flame;
import Entity.Entity;;
import Entity.Static.Item;
import Entity.Static.StaticEntity;
import Graphics.Sprite;
import Input.PlayerInput;
import Texture.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import javax.imageio.event.IIOReadProgressListener;
import javax.swing.plaf.synth.SynthTextAreaUI;
import java.io.File;
import java.io.FileNotFoundException;

import java.util.ArrayList;
import java.util.Scanner;

import static Variables.Variables.*;
import static Graphics.Sprite.*;

public class Map {
    private static Map map;
    private int levelNumber;
    private Entity[][] tiles;
    private ArrayList<Enemy> enemies;
    private ArrayList<Bomb> bombs;
    private ArrayList<Flame> flames;
    private ArrayList<Item> items;
    private Bomber player;

    private boolean revival;
    private int renderX;
    private int renderY;

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
        items = new ArrayList<>();
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public void createMap(String mapPath) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(mapPath));
        scanner.nextLine();
        resetEntities();
        revival = false;
        for (int i = 0; i < HEIGHT; i++) {
            String string = scanner.nextLine();
            for (int j = 0; j < WIDTH; j++) {
                char c = string.charAt(j);
                tiles[i][j] = StaticTexture.setStatic(c, i, j);
                if(tiles[i][j] instanceof Item) {
                    items.add((Item) tiles[i][j]);
                }
                if (c == '*') {
                    tiles[i][j] = BrickTexture.setBrick(i, j);
                }
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

    private void removeEntities() {
        ArrayList<Enemy> removedEnemies = new ArrayList<>();
        ArrayList<Bomb> removedBombs = new ArrayList<>();
        ArrayList<Flame> removedFlames = new ArrayList<>();
        ArrayList<Item> removedItems = new ArrayList<>();
        items.forEach(item -> {
            if(item.isRemoved()) {
                removedItems.add(item);
            }
        });
        enemies.forEach(enemy -> {
            if (enemy.isRemoved()) {
                removedEnemies.add(enemy);
            }
        });
        bombs.forEach(bomb -> {
            if (bomb.isRemoved()) {
                removedBombs.add(bomb);
            }
        });
        flames.forEach(flame -> {
            if (flame.isRemoved()) {
                removedFlames.add(flame);
            }
        });
        if (player.isRemoved()) player = null;
        removedEnemies.forEach(enemy -> {
            enemies.remove(enemy);
        });
        removedBombs.forEach(bomb -> {
            bombs.remove(bomb);
        });
        removedFlames.forEach(flame -> {
            flames.remove(flame);
        });
        removedItems.forEach(item -> {
            items.remove(item);
        });
    }


    public void updateMap() {
        if (revival) return;
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                //if(tiles[i][j] instanceof Brick == false) {
                    tiles[i][j].update();
                //}
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
        items.forEach(item -> {
            item.update();
        });
        removeEntities();
    }

    private void renderRevival(GraphicsContext graphicsContext) {
        if (renderX == 0 && renderY == 0) {
            revival = false;
            return;
        } else {
            renderX = Math.max(0, renderX - player.getTimeRevival());
            renderY = Math.max(0, renderY - player.getTimeRevival());
        }
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                //if (tiles[i][j] instanceof Brick == false) {
                    tiles[i][j].render(graphicsContext);
                //}
            }
        }
        enemies.forEach(enemy -> {
            enemy.render(graphicsContext);
        });
        player.render(graphicsContext);

    }

    private void updateRenderXY() {
        renderX = player.getPixelX() - (WIDTH_SCREEN / 2) * SCALED_SIZE;
        renderY = player.getPixelY() - (HEIGHT_SCREEN / 2) * SCALED_SIZE;
        if (renderX < 0) {
            renderX = 0;
        }
        if (renderX > WIDTH * SCALED_SIZE - WIDTH_SCREEN * SCALED_SIZE) {
            renderX = WIDTH * SCALED_SIZE - WIDTH_SCREEN * SCALED_SIZE;
        }
        if (renderY < 0) {
            renderY = 0;
        }
        if (renderY > HEIGHT * SCALED_SIZE - HEIGHT_SCREEN * SCALED_SIZE) {
            renderY = HEIGHT * SCALED_SIZE - HEIGHT_SCREEN * SCALED_SIZE;
        }
    }

    public void renderMap(GraphicsContext graphicsContext) {
//        if (flames.size() > 0) {
//            for (Flame flame : flames) {
//                System.out.println(flame.getPixelX() + " " + flame.getPixelY());
//            }
//            System.out.println("------------------------");
//        }

        if (revival) {
            renderRevival(graphicsContext);
            return;
        }
        updateRenderXY();
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                //if(tiles[i][j] instanceof Brick == false) {
                    tiles[i][j].render(graphicsContext);
                //}
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
        items.forEach(item -> {
            item.render(graphicsContext);
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

    public ArrayList<Item> getItems() {
        return items;
    }

    public int getRenderX() {
        return renderX;
    }

    public int getRenderY() {
        return renderY;
    }

    public void setRevival(boolean revival) {
        this.revival = revival;
    }
}