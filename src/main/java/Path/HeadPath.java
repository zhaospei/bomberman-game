package Path;

import Entity.Animate.Character.Bomber;
import Entity.Animate.Character.Enemy.Enemy;
import Map.Map;
import Variables.Variables.DIRECTION;
import static Variables.Variables.DIRECTION.*;

public class HeadPath extends Path{
    public HeadPath(Map map, Bomber player, Enemy enemy) {
        super(map, player, enemy);
    }

    @Override
    public DIRECTION path() {
        return NONE;
    }
}
