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
        if (Distance(enemy.tileY, enemy.tileX, player.tileY, player.tileX) <= 4) {
            if (enemy.isInATile()) {
                int minDistance = INF;
                DIRECTION nowDirection = UP;
                if (map.getTile(enemy.getTileX(), enemy.getTileY() - 1) instanceof Grass) {
                    int curDistance = Distance(enemy.tileY - 1, enemy.tileX, player.tileY, player.tileX);
                    if (minDistance > curDistance) {
                        minDistance = curDistance;
                        nowDirection = UP;
                    }
                }
                if (map.getTile(enemy.getTileX(), enemy.getTileY() + 1) instanceof Grass) {
                    int curDistance = Distance(enemy.tileY + 1, enemy.tileX, player.tileY, player.tileX);
                    if (minDistance > curDistance) {
                        minDistance = curDistance;
                        nowDirection = DOWN;
                    }
                }
                if (map.getTile(enemy.getTileX() - 1, enemy.getTileY()) instanceof Grass) {
                    int curDistance = Distance(enemy.tileY, enemy.tileX - 1, player.tileY, player.tileX);
                    if (minDistance > curDistance) {
                        minDistance = curDistance;
                        nowDirection = LEFT;
                    }
                }
                if (map.getTile(enemy.getTileX() + 1, enemy.getTileY()) instanceof Grass) {
                    int curDistance = Distance(enemy.tileY, enemy.tileX + 1, player.tileY, player.tileX);
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
