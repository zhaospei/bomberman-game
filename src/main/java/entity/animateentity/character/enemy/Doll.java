package entity.animateentity.character.enemy;

import entity.animateentity.character.Bomber;
import graphics.Sprite;
import map.Map;
import path.RightPath;
import variables.Variables.DIRECTION;

import static variables.Variables.DIRECTION.*;

public class Doll extends Enemy {
    public Doll(int x, int y, Sprite sprite) {
        super(x, y, sprite);
        animation.put(UP, Sprite.DOLL_LEFT);
        animation.put(DOWN, Sprite.DOLL_RIGHT);
        animation.put(LEFT, Sprite.DOLL_LEFT);
        animation.put(RIGHT, Sprite.DOLL_RIGHT);
        animation.put(DESTROYED, Sprite.DOLL_DESTROYED);
        life = 2;
        this.direction = RIGHT;
        this.defaultVel = 1;
        this.speed = 1;
        this.defaultCntMove = 5;
        this.defaultChangeSpeed = 10;
    }

    public DIRECTION path(Map map, Bomber player, Enemy enemy) {
        setSpeed(3);
        RightPath rightPath = new RightPath(map, player, enemy);
        return rightPath.path();
    }

    public void delete() {
        life --;
        destroyed = false;
        if (life == 0) {
            this.remove();
        }
    }
}
