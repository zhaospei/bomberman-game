package Entity.Animate;

import Graphics.Sprite;
import Variables.Variables;

import static Variables.Variables.FLAME_SHAPE.*;

public class Flame extends AnimateEntity {
    protected int flameLength = 2;
    protected int flameShape = 0;

    public Flame(int x, int y, Sprite sprite, Variables.FLAME_SHAPE fs) {
        super(x, y, sprite);
        animation.put(BOMB_EXPLODED, Sprite.BOMB_EXPLODED);
        animation.put(VERTICAL, Sprite.EXPLOSION_VERTICAL);
        animation.put(HORIZONTAL, Sprite.EXPLOSION_HORIZONTAL);
        animation.put(HORIZONTAL_LEFT_LAST, Sprite.EXPLOSION_HORIZONTAL_LEFT_LAST);
        animation.put(HORIZONTAL_RIGHT_LAST, Sprite.EXPLOSION_HORIZONTAL_RIGHT_LAST);
        animation.put(VERTICAL_TOP_LAST, Sprite.EXPLOSION_VERTICAL_TOP_LAST);
        animation.put(VERTICAL_DOWN_LAST, Sprite.EXPLOSION_VERTICAL_DOWN_LAST);
        currentAnimate = animation.get(fs);
    }

    @Override
    public void update() {
        updateAnimation();
        updateDestroyAnimation();
    }

    @Override
    public void updateDestroyAnimation() {
        if (timeDestroy == 0) {
            delete();
        } else {
            timeDestroy--;
            updateAnimation();
        }
    }

    @Override
    public void delete() {
        this.remove();
    }
}
