package Entity.Animate.Character.Enemy;

import Entity.Animate.Character.Character;
import Graphics.Sprite;
import Path.RandomPath;
import Variables.Variables;
import static Variables.Variables.DIRECTION.*;

import static Variables.Variables.DIRECTION.*;

public abstract class Enemy extends Character {
    public Enemy(int x, int y, Sprite sprite) {
        super(x, y, sprite);
        this.direction = UP;
    }



}
