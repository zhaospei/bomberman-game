package Entity.Animate.Character;
import Entity.Animate.AnimateEntity;
import Entity.Entity;
import Graphics.Sprite;
import Input.KeyInput;
import javafx.geometry.Rectangle2D;

import static Variables.Variables.DIRECTION.*;

public class Bomber extends Character {
    public KeyInput keyInput;

    public Bomber(int x, int y, Sprite sprite, KeyInput keyInput) {
        super( x, y, sprite);
        animation.put(LEFT, Sprite.PLAYER_LEFT);
        animation.put(RIGHT, Sprite.PLAYER_RIGHT);
        animation.put(UP, Sprite.PLAYER_UP);
        animation.put(DOWN, Sprite.PLAYER_DOWN);
        animation.put(DESTROYED, Sprite.PLAYER_DESTROYED);
        currentAnimate = animation.get(DOWN);
        this.keyInput = keyInput;
        this.keyInput.initialization();
        this.defaultVel = 1;
        this.speed = 1;
    }

    @Override
    public void checkCollision() {
        if (direction == NONE) {
            stand = true;
            return;
        }

        super.checkCollision();

        if (isCollision) {
            for (int i = -5 - speed; i <= 5 + speed; i++) {
                switch (direction) {
                    case UP, DOWN -> pixelX += i;
                    case LEFT, RIGHT -> pixelY += i;
                }
                super.checkCollision();
                if (!isCollision) {
                    break;
                }
                switch (direction) {
                    case UP, DOWN -> pixelX -= i;
                    case LEFT, RIGHT -> pixelY -= i;
                }
            }
        }
        tileX = pixelX / Sprite.SCALED_SIZE;
        tileY = pixelY / Sprite.SCALED_SIZE;
    }

    @Override
    public void setDirection() {
        direction = keyInput.handleKeyInput();
        this.setVelocity(0, 0);
        switch (direction) {
            case NONE -> this.setVelocity(0, 0);
            case LEFT -> this.addVelocity(-defaultVel, 0);
            case RIGHT -> this.addVelocity(defaultVel, 0);
            case UP -> this.addVelocity(0, -defaultVel);
            case DOWN -> this.addVelocity(0, defaultVel);
        }
        if (direction != NONE) {
            currentAnimate = animation.get(direction);
            updateAnimation();
        }
    }

}