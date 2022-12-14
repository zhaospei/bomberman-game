package entity.animateentity.character.enemy;

import entity.animateentity.character.Bomber;
import graphics.Sprite;
import map.Map;
import path.SpeedDistancePath;
import variables.Variables.DIRECTION;

import static variables.Variables.DIRECTION.*;

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
        this.speed = 1;
        this.defaultCntMove = 5;
        this.defaultChangeSpeed = 10;
    }
    public DIRECTION path(Map map, Bomber player, Enemy enemy){
        SpeedDistancePath speedRandomPath = new SpeedDistancePath(map, player, enemy);
        DIRECTION _direction = speedRandomPath.path();
        return _direction;
    }

    @Override
    public void delete() {
        this.remove();
    }
}
