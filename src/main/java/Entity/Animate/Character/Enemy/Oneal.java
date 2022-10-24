package Entity.Animate.Character.Enemy;

import Entity.Animate.Character.Bomber;
import Graphics.Sprite;
import Map.Map;
import Path.SpeedRandomPath;
import Variables.Variables.DIRECTION;

import java.util.Random;

import static Variables.Variables.DIRECTION.*;

public class Oneal extends Enemy{
    public Oneal(int x, int y, Sprite sprite) {
        super(x, y, sprite);
        animation.put(UP, Sprite.ONEAL_LEFT);
        animation.put(DOWN, Sprite.ONEAL_RIGHT);
        animation.put(LEFT, Sprite.ONEAL_LEFT);
        animation.put(RIGHT, Sprite.ONEAL_RIGHT);
        animation.put(DESTROYED, Sprite.ONEAL_DESTROYED);
        this.direction = UP;
        this.defaultVel = 1;
        this.speed = 2;
        this.defaultCntMove = 5;
        this.defaultChangeSpeed = 10;
    }
    public DIRECTION path(Map map, Bomber player, Enemy enemy){
        if (!enemy.isCollider() && cntMove > 0) {
            cntMove--;
            return enemy.getDirection();
        }
        cntMove = defaultCntMove;
        if (changeSpeed == 0) {
            speed = 1 + new Random().nextInt(2);
            changeSpeed = defaultChangeSpeed;
        } else {
            changeSpeed--;
        }
        SpeedRandomPath speedRandomPath = new SpeedRandomPath(map, player, enemy);
        return speedRandomPath.path();
    }

    @Override
    public void delete() {

    }
}
