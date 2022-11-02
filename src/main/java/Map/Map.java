package Map;

import Entity.Animate.Bomb;
import Entity.Animate.Brick;
import Entity.Animate.Character.Bomber;
import Entity.Animate.Character.Character;
import Entity.Animate.Character.Enemy.*;
import Entity.Animate.Flame;
import Entity.Entity;;
import Entity.Static.Item;
import Entity.Static.Score;
import Entity.Static.StaticEntity;
import Game.MainGame;
import Game.Menu;
import Graphics.Sprite;
import Input.PlayerInput;
import Texture.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import javax.imageio.event.IIOReadProgressListener;
import javax.swing.plaf.synth.SynthTextAreaUI;
import java.io.File;
import java.io.FileNotFoundException;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import static Variables.Variables.*;
import static Graphics.Sprite.*;

public class Map {
    private static Map map;
    private static int levelNumber;
    private int time = 60 * 200;
    private Image topInfoImage;
    private Entity[][] tiles;
    private ArrayList<Enemy> enemies;
    private ArrayList<Bomb> bombs;
    private ArrayList<Flame> flames;
    private ArrayList<Item> items;
    private ArrayList<Score> scores;
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
        scores = new ArrayList<>();
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }
    public void resetNumber() {
        Flame.flameLength = 1;
        Bomb.limit = 1;
        MainGame.setNewScore(-MainGame.getScore());
        player.setSpeed(2);
        time = 60*200;
    }
    public void createMap(String mapPath) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(mapPath));
        topInfoImage = new Image("/top_info.png");
        String _string = scanner.nextLine();
        levelNumber = _string.charAt(0) - '0';
        resetEntities();
        revival = false;
        for (int i = 0; i < HEIGHT; i++) {
            String string = scanner.nextLine();
            for (int j = 0; j < WIDTH; j++) {
                char c = string.charAt(j);
                tiles[i][j] = StaticTexture.setStatic(c, i, j);
                if (tiles[i][j] instanceof Item) {
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
        ArrayList<Score> removedScores = new ArrayList<>();
        scores.forEach(score -> {
            if (score.isRemoved()) {
                removedScores.add(score);
            }
        });
        items.forEach(item -> {
            if (item.isRemoved()) {
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
            if (enemy instanceof Balloom) {
                Score score = ScoreTexture.setScore('b', enemy.getTileX(), enemy.getTileY());
                scores.add(score);
            } else if (enemy instanceof Oneal) {
                Score score = ScoreTexture.setScore('o', enemy.getTileX(), enemy.getTileY());
                scores.add(score);
            } else if (enemy instanceof Doll) {
                Score score = ScoreTexture.setScore('d', enemy.getTileX(), enemy.getTileY());
                scores.add(score);
            } else if (enemy instanceof Minvo) {
                Score score = ScoreTexture.setScore('m', enemy.getTileX(), enemy.getTileY());
                scores.add(score);
            } else if (enemy instanceof Kondoria) {
                Score score = ScoreTexture.setScore('k', enemy.getTileX(), enemy.getTileY());
                scores.add(score);
            }
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
        removedScores.forEach(score -> {
            scores.remove(score);
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
        scores.forEach(score -> {
            score.update();
        });
        removeEntities();
    }

    public void renderTopInfo(GraphicsContext graphicsContext) {
        graphicsContext.drawImage(topInfoImage, 0, 0);
        graphicsContext.fillText("Score: " + String.valueOf(MainGame.getScore()), 0.6 * SCALED_SIZE, SCALED_SIZE * 0.8);
        if(MainGame.getScore() > Menu.getHighscore()) {
            try {
                PrintWriter writer = new PrintWriter("src/main/resources/menu/highscore.txt");
                writer.print("");
                writer.print(MainGame.getScore());
                writer.close();
            } catch (FileNotFoundException e) {
                System.out.println(e);
            }
        }
        if (time != 0) {
            graphicsContext.fillText("Time: " + String.valueOf((time--) / 60), 0.6 * SCALED_SIZE, SCALED_SIZE * 1.6);
        } else {
            graphicsContext.fillText("Time: " + String.valueOf(time), 0.6 * SCALED_SIZE, SCALED_SIZE * 1.6);
            MainGame.setBackToMenu(true);
        }
        graphicsContext.fillText("Stage: " + String.valueOf(levelNumber), 10.6 * SCALED_SIZE, SCALED_SIZE * 0.8);
        graphicsContext.fillText("Life: " + String.valueOf(player.getLife()), 10.6 * SCALED_SIZE, SCALED_SIZE * 1.6);
        if(player.getLife() == 0) {
            MainGame.setBackToMenu(true);
        }
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
        scores.forEach(score -> {
            score.render(graphicsContext);
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

    public static int getLevelNumber() {
        return levelNumber;
    }
}