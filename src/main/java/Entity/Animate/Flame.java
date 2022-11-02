package Entity.Animate;

import Entity.Entity;
import Entity.Static.*;
import Graphics.Sprite;
import Variables.Variables;

import static Variables.Variables.FLAME_SHAPE.*;

public class Flame extends AnimateEntity {
    public static int flameLength = 1;
    protected int flameShape = 0;

    public Flame(int x, int y, Sprite sprite, Variables.FLAME_SHAPE fs) {
        super(x, y, sprite);
        animation.put(BOMB_EXPLODED, Sprite.BOMB_EXPLODED);
        animation.put(VERTICAL, Sprite.EXPLOSION_VERTICAL);
        animation.put(HORIZONTAL, Sprite.EXPLOSION_HORIZONTAL);
        animation.put(HORIZONTAL_LEFT_LAST, Sprite.EXPLOSION_HORIZONTAL_LEFT_LAST);
        animation.put(HORIZONTAL_RIGHT_LAST, Sprite.EXPLOSION_HORIZONTAL_RIGHT_LAST);
        animation.put(VERTICAL_TOP_LAST, Sprite.EXPLOSION_VERTICAL_TOP_LAST);
        animation.put(VERTICAL_DOWN_LAST, Sprite.EXPLOSION_VERTICAL_DOWN_LAST);
        currentAnimate = animation.get(fs);
    }

    @Override
    public void update() {
        checkCollison();
        updateAnimation();
        updateDestroyAnimation();
    }

    @Override
    public void updateDestroyAnimation() {
        checkCollison();
        if (timeDestroy == 0) {
            delete();
        } else {
            timeDestroy--;
            updateAnimation();
        }
    }

    public void interactWith(Entity entity) {
        if (entity instanceof Brick) {
            ((Brick) entity).destroyed = true;
        } else if (entity instanceof Item) {
            entity.setBlock(false);
            if (entity instanceof SpeedItem) {
                entity.setSprite(Sprite.powerup_speed);
            }
            if (entity instanceof BombItem) {
                entity.setSprite(Sprite.powerup_bombs);
            }
            if (entity instanceof FlameItem) {
                entity.setSprite(Sprite.powerup_flames);
            }
        } else if (entity instanceof Portal) {
            entity.setBlock(false);
            entity.setSprite(Sprite.portal);
        }
    }

    public void checkCollison() {
        map.getEnemies().forEach(enemy -> {
            if (this.isCollider(enemy)) {
                enemy.destroy();
            }
        });
        if (this.isCollider(map.getPlayer()) && map.getPlayer().getImmortal() == 0 && !map.getPlayer().isDestroyed()) {
            map.getPlayer().destroy();
        }
    }

    @Override
    public void delete() {
        this.remove();
    }
}
