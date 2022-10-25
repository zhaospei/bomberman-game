package Path;

import Entity.Animate.Character.Bomber;
import Entity.Animate.Character.Enemy.Enemy;
import Entity.Static.Grass;
import Map.Map;
import Variables.Variables.DIRECTION;

import java.util.ArrayList;
import java.util.Random;

import static Variables.Variables.DIRECTION.*;
import static Variables.Variables.INF;

public class SpeedRandomPath extends Path{
    public SpeedRandomPath (Map map, Bomber player, Enemy enemy) {
        super(map, player, enemy);
    }


    public DIRECTION path() {
//        System.out.println(Distance(enemy.getTileY(), enemy.getTileX(), player.getTileY(), player.getTileX()));
        if (Distance(enemy.getTileY(), enemy.getTileX(), player.getTileY(), player.getTileX()) <= 20) {
            if (player.getTileX() == enemy.getTileX() && player.getTileY() == enemy.getTileY()) {
                if (Math.abs(player.getPixelX() - enemy.getPixelX()) > Math.abs(player.getPixelY() - enemy.getPixelY())) {
                    if (player.getPixelX() < enemy.getPixelX()) {
                        return LEFT;
                    } else {
                        return RIGHT;
                    }
                } else {
                    if (player.getPixelY() < enemy.getPixelY()) {
                        return UP;
                    } else {
                        return DOWN;
                    }
                }
            }
            if (enemy.isInATile()) {
                int minDistance = INF;
                DIRECTION nowDirection = UP;
                if (map.getTile(enemy.getTileX(), enemy.getTileY() - 1) instanceof Grass) {
                    int curDistance = Distance(enemy.getTileY() - 1, enemy.getTileX(), player.getTileY(), player.getTileX());
                    if (minDistance > curDistance) {
                        minDistance = curDistance;
                        nowDirection = UP;
                    }
                }
                if (map.getTile(enemy.getTileX(), enemy.getTileY() + 1) instanceof Grass) {
                    int curDistance = Distance(enemy.getTileY() + 1, enemy.getTileX(), player.getTileY(), player.getTileX());
                    if (minDistance > curDistance) {
                        minDistance = curDistance;
                        nowDirection = DOWN;
                    }
                }
                if (map.getTile(enemy.getTileX() - 1, enemy.getTileY()) instanceof Grass) {
                    int curDistance = Distance(enemy.getTileY(), enemy.getTileX() - 1, player.getTileY(), player.getTileX());
                    if (minDistance > curDistance) {
                        minDistance = curDistance;
                        nowDirection = LEFT;
                    }
                }
                if (map.getTile(enemy.getTileX() + 1, enemy.getTileY()) instanceof Grass) {
                    int curDistance = Distance(enemy.getTileY(), enemy.getTileX() + 1, player.getTileY(), player.getTileX());
                    if (minDistance > curDistance) {
                        minDistance = curDistance;
                        nowDirection = RIGHT;
                    }
                }
                return nowDirection;
            } else {
                return enemy.getDirection();
            }
        } else {
            return new RandomPath(map, player, enemy).path();
        }
    }
}
