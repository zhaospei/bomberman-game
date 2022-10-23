package Entity.Animate.Character.Enemy;

import Entity.Animate.Character.Bomber;
import Graphics.Sprite;
import Map.Map;
import Path.RandomPath;
import Variables.Variables.DIRECTION;

import static Variables.Variables.DIRECTION.*;
import static Graphics.Sprite.*;

public class Balloom extends Enemy{
    public Balloom(int x, int y, Sprite sprite) {
        super(x, y, sprite);
        animation.put(LEFT, BALLOOM_LEFT);
        animation.put(UP, BALLOOM_LEFT);
        animation.put(RIGHT, BALLOOM_RIGHT);
        animation.put(DOWN, BALLOOM_RIGHT);
        animation.put(DESTROYED, BALLOOM_DESTROYED);
        currentAnimate = animation.get(UP);
        this.defaultVel = 1;
        this.speed = 1;
        this.cntMove = 10;
    }
    @Override
    public DIRECTION path(Map map, Bomber player, Enemy enemy){
        RandomPath randomPath = new RandomPath(map, map.getPlayer(),this);
        return randomPath.path();
    }

    @Override
    public void update() {

    }
}
