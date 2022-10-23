package Path;

import Map.Map;
import Entity.Animate.Character.Bomber;
import Entity.Animate.Character.Enemy.Enemy;
import static Variables.Variables.*;
public abstract class Path {
    protected Map map;
    protected Bomber player;
    protected Enemy enemy;

    public Path(Map map, Bomber player, Enemy enemy) {
        this.map = map;
        this.player = player;
        this.enemy = enemy;
    }

    public abstract DIRECTION path();
}

