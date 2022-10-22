package Entity.Animate;

import Entity.Entity;
import GameController.Bomberman;
import Graphics.Sprite;

import java.util.HashMap;

public abstract class AnimateEntity extends Entity {
    public HashMap<Enum, Sprite[]> animation = new HashMap<>();
    protected Sprite[] currentAnimate;

    public AnimateEntity(int x, int y, Sprite sprite) {
        super(x, y, sprite);
    }

    public void updateAnimation() {
        this.sprite = Sprite.movingSprite(currentAnimate, 3, 10);

    }
}
