package Entity.Animate.Character;

import Entity.Animate.AnimateEntity;
import Entity.Animate.Character.Enemy.Enemy;
import Entity.Entity;
import Entity.Static.Grass;
import Entity.Static.StaticEntity;
import Graphics.Sprite;
import Map.Map;

import static Variables.Variables.*;

import static Graphics.Sprite.*;
import static Variables.Variables.DIRECTION.NONE;

public abstract class Character extends AnimateEntity {
    protected int velocityX;
    protected int velocityY;
    protected int defaultVel;
    protected int speed;
    protected DIRECTION direction;
    protected boolean isCollision;
    protected boolean stand;
    protected int life;

    public Character(int x, int y, Sprite sprite) {
        super(x, y, sprite);
        defaultVel = 0;
        velocityX = 0;
        velocityY = 0;
        life = 0;
        speed = 0;
        isCollision = false;
        stand = true;
        direction = NONE;
    }

    public void setVelocity(int velocityX, int velocityY) {
        this.velocityX = velocityX;
        this.velocityY = velocityY;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public DIRECTION getDirection() {
        return direction;
    }

    public boolean isCollider() {
        return isCollision;
    }


    public void addVelocity(int velocityX, int velocityY) {
        this.velocityX += velocityX;
        this.velocityY += velocityY;
    }

    public void move() {
        if (this instanceof Bomber && (velocityX != 0 || velocityY != 0)) {
            System.out.println(pixelX + " " + pixelY);
        }
        pixelX += velocityX;
        pixelY += velocityY;
        tileX = pixelX / SCALED_SIZE;
        tileY = pixelY / SCALED_SIZE;
    }

    public void checkCollision() {
        isCollision = false;
        pixelX += this.velocityX;
        pixelY += this.velocityY;

        for (int i = 0; i < HEIGHT; ++i) {
            for (int j = 0; j < WIDTH; ++j) {
                Entity entity = map.getTiles()[i][j];
                if (entity.isBlock() && this.isCollider(entity)) {
                    isCollision = true;
                }
            }
        }
        stand = (velocityX == 0 && velocityY == 0) || isCollision;
        pixelX -= this.velocityX;
        pixelY -= this.velocityY;
    }

    @Override
    public void update() {
        for (int i = 0; i < speed; i++) {
            setDirection();
            checkCollision();
            if (!stand || this instanceof Enemy) {
                updateAnimation();
            }
            if (!isCollision) {
                move();
            }
        }
    }
    public int getLife() {
        return life;
    }

    public abstract void setDirection();
}
