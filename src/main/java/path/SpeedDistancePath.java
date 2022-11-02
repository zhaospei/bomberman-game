package path;

import entity.animateentity.character.Bomber;
import entity.animateentity.character.enemy.Enemy;
import map.Map;
import variables.Variables.DIRECTION;

import java.util.Random;

public class SpeedDistancePath extends Path {
    public SpeedDistancePath(Map map, Bomber player, Enemy enemy) {
        super(map, player, enemy);
    }

    public DIRECTION path() {
        if (Distance(enemy.getTileY(), enemy.getTileX(), player.getTileY(), player.getTileX(), false) <= 5) {
            enemy.setCntMove(5);
            return new DistancePath(map, player, enemy).path();
        } else {
            int cntMove = enemy.getCntMove();
            int changeSpeed = enemy.getChangeSpeed();
            if (!enemy.isCollider() && cntMove > 0) {
                enemy.setCntMove(cntMove - 1);
                if (changeSpeed == 0) {
                    enemy.setSpeed(1 + new Random().nextInt(player.getSpeed()));
                    if (enemy.getSpeed() == 1) {
                        enemy.setChangeSpeed(30);
                    } else {
                        enemy.setChangeSpeed(10);
                    }
                } else {
                    enemy.setChangeSpeed(changeSpeed - 1);
                }
                return enemy.getDirection();
            }
            enemy.setCntMove(5);
            return new RandomPath(map, player, enemy).path();
        }
    }
}
