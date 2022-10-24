package Entity.Animate.Character.Enemy;

import Entity.Animate.Character.Bomber;
import Entity.Animate.Character.Character;
import Graphics.Sprite;
import Map.Map;
import Path.RandomPath;
import Variables.Variables;
import static Variables.Variables.DIRECTION.*;

import static Variables.Variables.*;

public abstract class Enemy extends Character {
    protected int cntMove;
    protected int defaultCntMove;
    public Enemy(int x, int y, Sprite sprite) {
        super(x, y, sprite);
    }

    public abstract DIRECTION path(Map map, Bomber player, Enemy enemy);
    public void setDirection() {
        direction = path(map, map.getPlayer(), this);
        switch (direction) {
            case UP -> this.setVelocity(0, -defaultVel);
            case DOWN -> this.setVelocity(0, defaultVel);
            case LEFT -> this.setVelocity(-defaultVel, 0);
            case RIGHT -> this.setVelocity(defaultVel, 0);
            default -> this.setVelocity(0,0);
        }
        currentAnimate = animation.get(direction);
    }




}
