package Path;

import Entity.Animate.Bomb;
import Entity.Animate.Brick;
import Entity.Entity;
import Entity.Static.Portal;
import Entity.Static.Wall;
import Map.Map;
import Entity.Animate.Character.Bomber;
import Entity.Animate.Character.Enemy.Enemy;
import javafx.scene.SubScene;
import javafx.util.Pair;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static Variables.Variables.*;
import static Variables.Variables.DIRECTION.*;

public abstract class Path {
    protected Map map;
    protected Bomber player;
    protected Enemy enemy;
    private class Vertex {
        int x;
        int y;
        int value;

        Vertex(int x, int y, int value) {
            this.x = x;
            this.y = y;
            this.value = value;
        }
    }

    public Path(Map map, Bomber player, Enemy enemy) {
        this.map = map;
        this.player = player;
        this.enemy = enemy;
    }

    private boolean isValid(int x, int y) {
        return (x >= 0 && x < HEIGHT && y >= 0 && y < WIDTH);
    }


    public int Distance(int x1, int y1, int x2, int y2, boolean dodge) {
        if (map.getTile(y2, x2).isBlock()) {
            return INF;
        }
        if (map.getTile(y1, x1).isBlock()) {
            if (dodge) {
                if (map.getTile(y1, x1) instanceof Wall) {
                    return INF;
                }
            } else {
                return INF;
            }
        }
        int statusTiles[][] = new int[HEIGHT][WIDTH];
        int distanceTiles[][] = new int [HEIGHT][WIDTH];
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                distanceTiles[i][j] = INF;
                Entity tile = map.getTile(j, i);
                if (tile.isBlock()) {
                    statusTiles[i][j] = 1;
                    if (dodge && !(tile instanceof Wall)) {
                        statusTiles[i][j] = 0;
                    }
                } else {
                    statusTiles[i][j] = 0;
                }
            }
        }

        for (Bomb bomb: map.getBombs()) {
            statusTiles[bomb.getTileY()][bomb.getTileY()] = 1;
        }

        Queue<Vertex> pq = new LinkedList<>();
        pq.add(new Vertex(x1, y1, 0));
        distanceTiles[x1][y1] = 0;
        while (!pq.isEmpty()) {
            Vertex cur = pq.poll();
            for (int k = 0; k < 4; k++) {
                int _x = cur.x + dx[k];
                int _y = cur.y + dy[k];
                if (isValid(_x, _y) && statusTiles[_x][_y] == 0 && distanceTiles[_x][_y] == INF) {
                    distanceTiles[_x][_y] = cur.value + 1;
                    pq.add(new Vertex(_x, _y, cur.value + 1));
                }
            }
        }
        return distanceTiles[x2][y2];
    }

    public DIRECTION intToDirection(int x) {
        switch (x) {
            case 0:
                return UP;
            case 1:
                return DOWN;
            case 2:
                return LEFT;
            case 3:
                return RIGHT;
            default:
                return NONE;
        }

    }
    public abstract DIRECTION path();
}


