package Entity.Animate;

import Entity.Entity;
import Game.MainGame;
import Graphics.Sprite;
import com.sun.tools.javac.Main;

import java.util.HashMap;

import static Variables.Variables.*;

public abstract class AnimateEntity extends Entity {
    protected Sprite[] currentAnimate;
    public HashMap<DIRECTION, Sprite[]> animation = new HashMap<>();
    public AnimateEntity(int x, int y, Sprite sprite) {
        super(x, y, sprite);
    }

    public void updateAnimation() {
        long time = MainGame.time;
        sprite = Sprite.movingSprite(currentAnimate, 3, time);
        image = sprite.getFxImage();
    }
}