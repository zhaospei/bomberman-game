package path;

import entity.animateentity.character.Bomber;
import entity.animateentity.character.enemy.Enemy;
import map.Map;
import variables.Variables.DIRECTION;

import java.util.ArrayList;
import java.util.Random;

public class RandomPath extends Path {
    public RandomPath(Map map, Bomber player, Enemy enemy) {
        super(map, player, enemy);
    }
    public DIRECTION path() {
        if (enemy.isCollider() || enemy.isInATile()) {
            ArrayList<DIRECTION> canDirections = new ArrayList<>();
            for (int k = 0; k < 4; k++) {
                if (!enemy.checkTileCollider(intToDirection(k), false)) {
                    canDirections.add(intToDirection(k));
                }
            }
            if (canDirections.size() == 0) {
                return enemy.getDirection();
            }
            int random = new Random().nextInt(canDirections.size());
            return canDirections.get(random);
        } else {
            return enemy.getDirection();
        }
    }
}
