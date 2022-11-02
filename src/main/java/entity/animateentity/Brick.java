package entity.animateentity;

import entity.staticentity.Grass;
import game.MainGame;
import graphics.Sprite;

import static variables.Variables.STATUS.EXPLODING;
import static variables.Variables.STATUS.NOTEXPLODEDYET;

public class Brick extends AnimateEntity {
    public Brick(int x, int y, Sprite sprite) {
        super(x, y, sprite);
        animation.put(NOTEXPLODEDYET, Sprite.BRICK);
        animation.put(EXPLODING, Sprite.BRICK_EXPLODED);
        currentAnimate = animation.get(NOTEXPLODEDYET);
        timeDestroy = 30;
        this.block = true;
    }

    @Override
    public void update() {
        if (destroyed == true) {
            currentAnimate = animation.get(EXPLODING);
            if (timeDestroy > 0) {
                updateDestroyAnimation();
                timeDestroy--;
            } else {
                delete();
            }
        }
        else {
            updateAnimation();
        }
    }

    @Override
    public void updateAnimation() {
    }
    @Override
    public void updateDestroyAnimation() {
        long time = MainGame.time;
        sprite = Sprite.movingSprite(currentAnimate, 3, time);
        image = sprite.getFxImage();
    }

    @Override
    public void delete() {
        map.setTile(this.tileY, this.tileX, new Grass(this.tileX, this.tileY, Sprite.grass));
    }
}