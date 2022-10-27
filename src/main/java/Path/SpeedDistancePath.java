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
//        if (!enemy.isCollider() && cntMove > 0) {
//            cntMove--;
//            return enemy.getDirection();
//        }
//        cntMove = defaultCntMove;
//        if (changeSpeed == 0) {
//            speed = 1 + new Random().nextInt(1);
//            changeSpeed = defaultChangeSpeed;
//        } else {
//            changeSpeed--;
//        }
        if (Distance(enemy.getTileY(), enemy.getTileX(), player.getTileY(), player.getTileX()) <= 5) {
            enemy.setSpeed(2);
            enemy.setCntMove(5);
            return new DistancePath(map, player, enemy).path();
        } else {
            int cntMove = enemy.getCntMove();
            int changeSpeed = enemy.getChangeSpeed();
            //System.out.println(changeSpeed);
            if (!enemy.isCollider() && cntMove > 0) {
                enemy.setCntMove(cntMove - 1);
                if (changeSpeed == 0) {
                    enemy.setSpeed(1 + new Random().nextInt(2));
                    enemy.setChangeSpeed(50);
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