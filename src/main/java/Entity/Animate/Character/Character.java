package Entity.Animate.Character;

import Entity.Animate.AnimateEntity;
import Graphics.Sprite;
import static Graphics.Sprite.*;

public abstract class Character extends AnimateEntity {
    protected int velocityX = 0;
    protected int velocityY = 0;

    public Character(int x, int y, Sprite sprite) {
        super(x, y, sprite);
    }

    public void setVelocity(int velocityX, int velocityY) {
        this.velocityX = velocityX;
        this.velocityY = velocityY;
    }

    public void move() {
        pixelX += velocityX;
        pixelY += velocityY;
        tileX = pixelX / SCALED_SIZE;
        tileY = pixelY / SCALED_SIZE;
    }

    public void update() {
        updateAnimation();
        move();
    }

}
