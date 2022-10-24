package Entity.Animate.Character.Enemy;

import Entity.Animate.Character.Bomber;
import Graphics.Sprite;
import Map.Map;
import Path.RandomPath;
import Variables.Variables.DIRECTION;
import org.controlsfx.control.tableview2.filter.filtereditor.SouthFilter;

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

    }
}
