package Entity.Animate.Character.Enemy;

import Entity.Animate.Character.Bomber;
import Graphics.Sprite;
import Map.Map;
import Variables.Variables.DIRECTION;

import static Variables.Variables.DIRECTION.*;

public class Oneal extends Enemy{
    public Oneal(int x, int y, Sprite sprite) {
        super(x, y, sprite);
    }
    public DIRECTION path(Map map, Bomber player, Enemy enemy){
        return NONE;
    }

    @Override
    public void delete() {

    }
}
