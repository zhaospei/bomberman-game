package Entity.Animate.Character.Enemy;

import Game.MainGame;
import Graphics.Sprite;
import Map.Map;
import Path.RandomPath;
import Variables.Variables.*;
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
    }
}
