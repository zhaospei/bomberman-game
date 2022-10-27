package Entity.Animate;

import Entity.Animate.AnimateEntity;
import Entity.Static.Grass;
import Entity.Static.StaticEntity;
import Game.MainGame;
import Graphics.Sprite;
import Texture.StaticTexture;
import javafx.scene.control.Spinner;

import static Variables.Variables.STATUS.EXPLODING;
import static Variables.Variables.STATUS.NOTEXPLODEDYET;

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
                updateAnimation();
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
    public void destroy() {
        currentAnimate = animation.get(EXPLODING);
        updateDestroyAnimation();
    }

    @Override
    public void updateDestroyAnimation() {
        while(timeDestroy != 0) {
            timeDestroy--;
            updateAnimation();
            if(timeDestroy == 0) {
                delete();
            }
        }
        updateAnimation();
    }

    @Override
    public void delete() {
        map.setTile(this.tileY, this.tileX, new Grass(this.tileX, this.tileY, Sprite.grass));
    }
}