package Entity.Animate.Character.Enemy;

import Entity.Animate.Brick;
import Entity.Animate.Character.Bomber;
import Entity.Entity;
import Entity.Static.Portal;
import Graphics.Sprite;
import Map.Map;
import Path.DodgePath;
import Path.RandomPath;
import Path.RightPath;
import Variables.Variables.DIRECTION;

import static Graphics.Sprite.*;
import static Variables.Variables.DIRECTION.*;
import static Variables.Variables.DIRECTION.RIGHT;
import static Variables.Variables.HEIGHT;
import static Variables.Variables.WIDTH;

public class Kondoria extends Enemy {
    public Kondoria(int x, int y, Sprite sprite) {
        super(x, y, sprite);
        animation.put(LEFT, KONDORIA_LEFT);
        animation.put(UP, KONDORIA_LEFT);
        animation.put(RIGHT, KONDORIA_RIGHT);
        animation.put(DOWN, KONDORIA_RIGHT);
        animation.put(DESTROYED, KONDORIA_DESTROYED);
        currentAnimate = animation.get(UP);
        this.direction = UP;
        this.defaultVel = 1;
        this.speed = 1;
        this.defaultCntMove = 5;
    }
    @Override
    public DIRECTION path(Map map, Bomber player, Enemy enemy) {
        DodgePath dodgePath = new DodgePath(map, map.getPlayer(),this);
        return dodgePath.path();
    }

    @Override
    public void checkCollision() {
        isCollision = false;
        pixelX += this.velocityX;
        pixelY += this.velocityY;
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                Entity entity = map.getTile(j, i);
                if (entity instanceof Brick) continue;
                if (entity.isBlock() && this.isCollider(entity)) {
                    isCollision = true;
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
        pixelX -= this.velocityX;
        pixelY -= this.velocityY;
    }

    @Override
    public void delete() {
        this.remove();
    }
}
