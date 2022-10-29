package Path;

import Entity.Animate.Character.Bomber;
import Entity.Animate.Character.Enemy.Enemy;
import Map.Map;
import Variables.Variables.DIRECTION;
import static Graphics.Sprite.SCALED_SIZE;

import static Variables.Variables.DIRECTION.*;

public class RightPath extends Path{
    public RightPath(Map map, Bomber player, Enemy enemy) {
        super(map, player, enemy);
    }

    @Override
    public DIRECTION path() {
        DIRECTION headPath = new HeadPath(map, player, enemy).path();
        if (headPath == NONE) {
            enemy.setSpeed(1);
            if (enemy.isCollider()) {
                if (enemy.checkTileCollider(RIGHT, false)) {
                    return LEFT;
                } else {
                    return RIGHT;
                }
            } else {
                return enemy.getDirection();
            }
        } else {
            enemy.setSpeed(2);
            return headPath;
        }
    }
}
