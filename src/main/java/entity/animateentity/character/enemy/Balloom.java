package entity.animateentity.character.enemy;

import entity.animateentity.character.Bomber;
import graphics.Sprite;
import map.Map;
import path.RandomPath;
import variables.Variables.DIRECTION;

import static variables.Variables.DIRECTION.*;
import static graphics.Sprite.*;

public class Balloom extends Enemy{
    public Balloom(int x, int y, Sprite sprite) {
        super(x, y, sprite);
        animation.put(LEFT, BALLOOM_LEFT);
        animation.put(UP, BALLOOM_LEFT);
        animation.put(RIGHT, BALLOOM_RIGHT);
        animation.put(DOWN, BALLOOM_RIGHT);
        animation.put(DESTROYED, BALLOOM_DESTROYED);
        currentAnimate = animation.get(UP);
        this.direction = UP;
        this.defaultVel = 1;
        this.speed = 1;
        this.defaultCntMove = 5;
    }
    @Override
    public DIRECTION path(Map map, Bomber player, Enemy enemy){
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
