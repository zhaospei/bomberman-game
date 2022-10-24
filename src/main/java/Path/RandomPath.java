package Path;

import Entity.Animate.Character.Bomber;
import Entity.Animate.Character.Enemy.Enemy;
import Entity.Static.Grass;
import Map.Map;
import Variables.Variables.DIRECTION;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Random;
import static Variables.Variables.DIRECTION.*;
import static Variables.Variables.HEIGHT;
import static Variables.Variables.WIDTH;

public class RandomPath extends Path {
    public RandomPath(Map map, Bomber player, Enemy enemy) {
        super(map, player, enemy);
    }
    public DIRECTION path() {
        if (enemy.isCollider() || enemy.getDirection() == NONE) {
            ArrayList<DIRECTION> canDirections = new ArrayList<>();
            if (map.getTile(enemy.getTileX(), enemy.getTileY() - 1) instanceof Grass) {
                canDirections.add(UP);
            }
            if (map.getTile(enemy.getTileX(), enemy.getTileY() + 1) instanceof Grass) {
                canDirections.add(DOWN);
            }
            if (map.getTile(enemy.getTileX() - 1, enemy.getTileY()) instanceof Grass) {
                canDirections.add(LEFT);
            }
            if (map.getTile(enemy.getTileX() + 1, enemy.getTileY()) instanceof Grass) {
                canDirections.add(RIGHT);
            }
            int random = new Random().nextInt(canDirections.size());
            return canDirections.get(random);
        } else {
            return enemy.getDirection();
        }
    }
}
