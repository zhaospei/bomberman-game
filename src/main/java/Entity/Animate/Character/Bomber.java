package Entity.Animate.Character;

import Entity.Animate.AnimateEntity;
import Entity.Animate.Bomb;
import Entity.Entity;
import Entity.Static.Grass;
import Graphics.Sprite;
import Input.KeyInput;
import Texture.BombTexture;
import javafx.geometry.Rectangle2D;

import static Variables.Variables.DIRECTION.*;

public class Bomber extends Character {
    public KeyInput keyInput;
    public boolean standInBomb = false;

    public Bomber(int x, int y, Sprite sprite, KeyInput keyInput) {
        super(x, y, sprite);
        animation.put(LEFT, Sprite.PLAYER_LEFT);
        animation.put(RIGHT, Sprite.PLAYER_RIGHT);
        animation.put(UP, Sprite.PLAYER_UP);
        animation.put(DOWN, Sprite.PLAYER_DOWN);
        animation.put(DESTROYED, Sprite.PLAYER_DESTROYED);
        currentAnimate = animation.get(DOWN);
        this.keyInput = keyInput;
        this.keyInput.initialization();
        this.defaultVel = 1;
        this.speed = 2;
        this.life = 3;
    }
    public void placeBombAt(int x, int y) {
        if (map.getTiles()[x][y] instanceof Grass && map.getBombs().size() <= Bomb.limit) {
            Bomb bomb1 = BombTexture.setBomb(x, y);
            map.getBombs().add(bomb1);
        }
    }

    @Override
    public void checkCollision() {
        super.checkCollision();
        map.getEnemies().forEach(enemy -> {
            if (this.isCollider(enemy)) {
                destroy();
            }
        });
        if (isCollision) {
            for (int i = -8 - speed; i <= 8 + speed; i++) {
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


    tileX =pixelX /Sprite.SCALED_SIZE;
    tileY =pixelY /Sprite.SCALED_SIZE;
}


    @Override
    public void setDirection() {
        direction = keyInput.handleKeyInput();
        this.setVelocity(0, 0);
        switch (direction) {
            case NONE -> this.setVelocity(0, 0);
            case LEFT -> this.setVelocity(-defaultVel, 0);
            case RIGHT -> this.setVelocity(defaultVel, 0);
            case UP -> this.setVelocity(0, -defaultVel);
            case DOWN -> this.setVelocity(0, defaultVel);
            case PLACEBOMB -> placeBombAt(getTileX(), getTileY());
        }
        if (direction != NONE && direction != PLACEBOMB) {
            currentAnimate = animation.get(direction);
            updateAnimation();
        }
    }

    @Override
    public void delete() {
        this.life--;
        setPosition(32, 32);
        destroyed = false;
        direction = NONE;
        setSprite(Sprite.PLAYER_DOWN[0]);
    }
}