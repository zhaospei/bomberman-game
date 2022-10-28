package Entity.Animate.Character.Enemy;

import Entity.Animate.Character.Bomber;
import Graphics.Sprite;
import Map.Map;
import Path.RandomPath;
import Path.RightPath;
import Variables.Variables.DIRECTION;

import static Graphics.Sprite.*;
import static Variables.Variables.DIRECTION.*;
import static Variables.Variables.DIRECTION.RIGHT;

public class Minvo extends Enemy {
    public Minvo(int x, int y, Sprite sprite) {
        super(x, y, sprite);
        animation.put(LEFT, MINVO_LEFT);
        animation.put(UP, MINVO_LEFT);
        animation.put(RIGHT, MINVO_RIGHT);
        animation.put(DOWN, MINVO_RIGHT);
        animation.put(DESTROYED, MINVO_DESTROYED);
        currentAnimate = animation.get(UP);
        this.direction = UP;
        this.defaultVel = 1;
        this.speed = 1;
        this.defaultCntMove = 5;
    }
    @Override
    public DIRECTION path(Map map, Bomber player, Enemy enemy) {
        if (!enemy.isCollider() && cntMove > 0) {
            cntMove--;
            return enemy.getDirection();
        }
        cntMove = defaultCntMove;
        RandomPath randomPath = new RandomPath(map, map.getPlayer(),this);
        return randomPath.path();
    }

    @Override
    public void delete() {
        this.remove();
    }
}
