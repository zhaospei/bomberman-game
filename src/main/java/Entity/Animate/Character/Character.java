package Entity.Animate.Character;

import Entity.Animate.AnimateEntity;
import Entity.Animate.Bomb;
import Entity.Animate.Brick;
import Entity.Animate.Character.Enemy.Balloom;
import Entity.Animate.Character.Enemy.Doll;
import Entity.Animate.Character.Enemy.Enemy;
import Entity.Animate.Character.Enemy.Oneal;
import Entity.Entity;
import Entity.Static.Grass;
import Entity.Static.Portal;
import Entity.Static.StaticEntity;
import Game.MainGame;
import Graphics.Sprite;
import Input.PlayerInput;
import Map.Map;

import static Variables.Variables.*;

import static Graphics.Sprite.*;
import static Variables.Variables.DIRECTION.*;

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
//        if (this instanceof Bomber && (velocityX != 0 || velocityY != 0)) {
//            System.out.println(pixelX + " " + pixelY);
//        }
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
                    System.out.println("WIN");
                }
            }
        }
        map.getBombs().forEach(bomb -> {
            Entity entity1 = bomb;
            if (entity1.isBlock() && this.isCollider(entity1)) {
                isCollision = true;
            }
            if(this.isCollider(entity1) && this instanceof Enemy) {
                isCollision = true;
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
                        if (!(entity instanceof Brick)) {
                            ok = true;
                        }
                    } else {
                        ok = true;
                    }
                }
                for (Bomb bomb: map.getBombs()) {
                    if (bomb.isBlock() && this.isCollider(bomb)) {
                        ok = true;
                    }
                }
            }
        }
        this.setPosition(lastPixelX, lastPixelY);
        return ok;
    }
    @Override
    public void updateAnimation() {
        long time = MainGame.time;
        sprite = Sprite.movingSprite(currentAnimate, 3, time * this.speed);
        image = sprite.getFxImage();
    }

    public abstract void setDirection();
}
