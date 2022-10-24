package Entity.Animate;

import Entity.Entity;
import Game.MainGame;
import Graphics.Sprite;
import com.sun.tools.javac.Main;

import java.util.HashMap;

import static Variables.Variables.*;
import static Variables.Variables.DIRECTION.DESTROYED;

public abstract class AnimateEntity extends Entity {
    protected Sprite[] currentAnimate;
    protected int timeDestroy;
    protected boolean destroyed;
    public HashMap<DIRECTION, Sprite[]> animation = new HashMap<>();
    public AnimateEntity(int x, int y, Sprite sprite) {
        super(x, y, sprite);
        destroyed = false;
    }

    public void updateAnimation() {
        long time = MainGame.time;
        sprite = Sprite.movingSprite(currentAnimate, 3, time);
        image = sprite.getFxImage();
    }

    public void destroy() {
        currentAnimate = animation.get(DESTROYED);
        timeDestroy = 30;
        destroyed = true;
    }

    public void updateDestroyAnimation() {
        if (timeDestroy == 0) {
            delete();
        } else {
            timeDestroy--;
            updateAnimation();
        }
    }

    public boolean isDestroyed() {
        return destroyed;
    }
    public abstract void delete();
}