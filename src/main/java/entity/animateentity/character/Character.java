package entity.animateentity.character;

import entity.animateentity.AnimateEntity;
import entity.animateentity.Bomb;
import entity.animateentity.character.enemy.Enemy;
import entity.Entity;
import entity.staticentity.Portal;
import entity.staticentity.Wall;
import game.MainGame;
import graphics.Sprite;

import static variables.Variables.*;

import static graphics.Sprite.*;
import static variables.Variables.DIRECTION.*;

public abstract class Character extends AnimateEntity {
    protected int velocityX;
    protected int velocityY;
    protected int defaultVel;
    protected int speed;

    protected DIRECTION direction;
    protected boolean isCollision;
    protected boolean stand;
    protected int life;

    protected int immortal;

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
        immortal = 0;
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
        pixelX += velocityX;
        pixelY += velocityY;
        tileX = pixelX / SCALED_SIZE;
        tileY = pixelY / SCALED_SIZE;
    }

    public void checkCollision() {
        isCollision = false;
        pixelX += this.velocityX;
        pixelY += this.velocityY;
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                Entity entity = map.getTile(j, i);
                if (entity.isBlock() && this.isCollider(entity)) {
                    isCollision = true;
                }
                if (this instanceof Bomber && this.isCollider(entity) && entity instanceof Portal && ((Portal) entity).isAccessAble() && entity.getTileX() == j && entity.getTileY() == i) {
                    MainGame.setBackToMenu(true);
                    MainGame.setWin(true);
                }
            }
        }
        map.getBombs().forEach(bomb -> {
            Entity entity1 = bomb;
            if (entity1.isBlock() && this.isCollider(entity1)) {
                if (immortal == 0) {
                    isCollision = true;
                }
            }
            if(this.isCollider(entity1) && this instanceof Enemy) {
                if (immortal == 0) {
                    isCollision = true;
                }
            }
        });
        stand = (velocityX == 0 && velocityY == 0) || isCollision;
        pixelX -= this.velocityX;
        pixelY -= this.velocityY;
    }

    @Override
    public void update() {
        if (isDestroyed()) {
            updateDestroyAnimation();
        } else {
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
    }

    public int getLife() {
        return life;
    }

    public boolean checkTileCollider(DIRECTION direction, boolean dodge) {
        boolean ok = false;
        int k = 0;
        switch (direction) {
            case UP -> k = 0;
            case DOWN -> k = 1;
            case LEFT -> k = 2;
            case RIGHT -> k = 3;
        }
        int lastPixelX = this.getPixelX();
        int lastPixelY = this.getPixelY();
        this.setTile(this.getTileX() + dx[k], this.getTileY() + dy[k]);
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                Entity entity = map.getTile(j, i);
                if (entity.isBlock() && this.isCollider(entity)) {
                    if (dodge) {
                        if (entity instanceof Wall) {
                            ok = true;
                        }
                    } else {
                        ok = true;
                    }
                }
                for (Bomb bomb: map.getBombs()) {
                    if (this instanceof Enemy) {
                        if (this.isCollider(bomb)) {
                            ok = true;
                        }
                    }
                    if (bomb.isBlock() && this.isCollider(bomb)) {
                        ok = true;
                    }
                }
            }
        }
        this.setPosition(lastPixelX, lastPixelY);
        return ok;
    }

    public int getImmortal() {
        return immortal;
    }

    public void setImmortal(int immortal) {
        this.immortal = immortal;
    }

    @Override
    public void updateAnimation() {
        long time = MainGame.time;
        sprite = Sprite.movingSprite(currentAnimate, 3, time * this.speed);
        image = sprite.getFxImage();
    }

    public abstract void setDirection();
}
