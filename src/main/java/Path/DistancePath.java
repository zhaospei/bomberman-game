package Path;

import Entity.Animate.Character.Bomber;
import Entity.Animate.Character.Enemy.Enemy;
import Entity.Static.Grass;
import Map.Map;
import Variables.Variables.DIRECTION;
import org.controlsfx.control.tableview2.filter.filtereditor.SouthFilter;

import java.util.ArrayList;
import java.util.Random;

import static Variables.Variables.DIRECTION.*;
import static Variables.Variables.*;
import static Graphics.Sprite.*;

public class DistancePath extends Path {
    public DistancePath(Map map, Bomber player, Enemy enemy) {
        super(map, player, enemy);
    }

    public DIRECTION path() {
        if (enemy.isInATile()) {
            if (Distance(enemy.getTileY(), enemy.getTileX(), player.getTileY(), player.getTileX(), false) == 1) {
                int enemyPixelX = enemy.getPixelX();
                int enemyPixelY = enemy.getPixelY();
                DIRECTION nowDirection = UP;
                for (int k = 0; k < 4; k++) {
                    enemy.setTile(enemy.getTileX() + dx[k], enemy.getTileY() + dy[k]);
                    if (player.isCollider(enemy)) {
                        nowDirection = intToDirection(k);
                    }
                    enemy.setPosition(enemyPixelX, enemyPixelY);
                }
                return nowDirection;
            }
            int minDistance = INF;
            DIRECTION nowDirection = UP;
            for (int k = 0; k < 4; k++) {
                if (map.getTile(enemy.getTileX() + dx[k], enemy.getTileY() + dy[k]) instanceof Grass) {
                    int curDistance = Distance(enemy.getTileY() + dy[k], enemy.getTileX() + dx[k],
                            player.getTileY(), player.getTileX(), false);
                    if (enemy.checkTileCollider(intToDirection(k), true)) {
                        continue;
                    }
                    if (minDistance > curDistance) {
                        minDistance = curDistance;
                        nowDirection = intToDirection(k);
                    }
                }
            }
            return nowDirection;
        } else {
            return enemy.getDirection();
        }
    }
}
