package entity.animateentity;

import entity.Entity;
import game.MainGame;
import graphics.Sprite;

import java.util.HashMap;

import static variables.Variables.DIRECTION.DESTROYED;

public abstract class AnimateEntity extends Entity {
    protected Sprite[] currentAnimate;

    public HashMap<Enum, Sprite[]> animation = new HashMap<>();

    protected int timeDestroy = 30;
    protected boolean destroyed;

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