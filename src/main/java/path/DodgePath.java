package path;

import entity.animateentity.character.Bomber;
import entity.animateentity.character.enemy.Enemy;
import entity.staticentity.Wall;
import map.Map;
import variables.Variables.DIRECTION;

import static variables.Variables.*;
import static variables.Variables.DIRECTION.*;

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
                if (!(map.getTile(enemy.getTileX() + dx[k], enemy.getTileY() + dy[k]) instanceof Wall)) {
                    if (enemy.checkTileCollider(intToDirection(k), true)) {
                        continue;
                    }
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
