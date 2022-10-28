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
                    enemy.setChangeSpeed(30);
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
