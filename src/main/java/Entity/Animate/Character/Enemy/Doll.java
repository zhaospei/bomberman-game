package Entity.Animate.Character.Enemy;

import Entity.Animate.Character.Bomber;
import Graphics.Sprite;
import Map.Map;
import Path.RightPath;
import Variables.Variables.DIRECTION;

import static Variables.Variables.DIRECTION.*;

public class Doll extends Enemy{
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
        RightPath rightPath = new RightPath(map, player, enemy);
        return rightPath.path();
    }

    public void delete() {
        life --;
        destroyed = false;
        if (life == 0) {
            map.getEnemies().remove(this);
        }
    }
}
