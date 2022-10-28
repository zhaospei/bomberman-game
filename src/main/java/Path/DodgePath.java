package Path;

import Entity.Animate.Brick;
import Entity.Animate.Character.Bomber;
import Entity.Animate.Character.Enemy.Enemy;
import Entity.Static.Grass;
import Map.Map;
import Variables.Variables.DIRECTION;

import static Variables.Variables.*;
import static Variables.Variables.DIRECTION.*;

public class DodgePath extends Path {
    public DodgePath(Map map, Bomber player, Enemy enemy) {
        super(map, player, enemy);
    }

    @Override
    public DIRECTION path() {
        if (enemy.isInATile()) {
            if (Distance(enemy.getTileY(), enemy.getTileX(), player.getTileY(), player.getTileX(), true) == 1) {
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
                if (map.getTile(enemy.getTileX() + dx[k], enemy.getTileY() + dy[k]) instanceof Grass
                    || map.getTile(enemy.getTileX() + dx[k], enemy.getTileY() + dy[k]) instanceof Brick) {
                    int curDistance = Distance(enemy.getTileY() + dy[k], enemy.getTileX() + dx[k],
                            player.getTileY(), player.getTileX(), true);
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
