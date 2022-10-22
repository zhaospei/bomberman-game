package Entity.Animate;

import Entity.Entity;
import Game.MainGame;
import Graphics.Sprite;

public abstract class AnimateEntity extends Entity {
    protected Sprite[] currentAnimate;
    public AnimateEntity(int x, int y, Sprite sprite) {
        super(x, y, sprite);
    }

    public void updateAnimation() {
        sprite = Sprite.movingSprite(currentAnimate, 3, MainGame.time);
        image = sprite.getFxImage();
    }

    public abstract void update();
}