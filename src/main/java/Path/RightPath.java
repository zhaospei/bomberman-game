package Path;

import Entity.Animate.Character.Bomber;
import Entity.Animate.Character.Enemy.Enemy;
import Map.Map;
import Variables.Variables.DIRECTION;
import static Graphics.Sprite.SCALED_SIZE;

import static Variables.Variables.DIRECTION.*;

public class RightPath extends Path{
    public RightPath(Map map, Bomber player, Enemy enemy) {
        super(map, player, enemy);
    }

    @Override
    public DIRECTION path() {
        boolean pursue = true;
        if (player.getTileX() != enemy.getTileX() && player.getTileY() != enemy.getTileY()) {
            pursue = false;
        }
        if (player.getTileX() == enemy.getTileX()) {
            if (enemy.getPixelX() % SCALED_SIZE != 0 || player.getPixelX() % SCALED_SIZE != 0) {
                pursue = false;
            }
            if (player.getTileY() < enemy.getTileY()) {
                for (int i = player.getPixelY(); i < enemy.getTileY(); i++) {
                    if (map.getTile(player.getTileX(), i).isBlock()) {
                        pursue = false;
                    }
                }
            } else {
                for (int i = enemy.getPixelY(); i < player.getTileY(); i++) {
                    if (map.getTile(player.getTileX(), i).isBlock()) {
                        pursue = false;
                    }
                }
            }
        } else {
            if (enemy.getPixelY() % SCALED_SIZE != 0 || player.getPixelY() % SCALED_SIZE != 0) {
                pursue = false;
            }
            if (player.getTileX() < enemy.getTileX()) {
                for (int i = player.getPixelX(); i < enemy.getTileX(); i++) {
                    if (map.getTile(i, player.getTileY()).isBlock()) {
                        pursue = false;
                    }
                }
            } else {
                for (int i = enemy.getPixelX(); i < player.getTileX(); i++) {
                    if (map.getTile(i, player.getTileY()).isBlock()) {
                        pursue = false;
                    }
                }
            }
        }
        if (pursue) {
            enemy.setSpeed(2);
            if (player.getTileX() == enemy.getTileX() && player.getTileY() == enemy.getTileY()) {
                if (Math.abs(player.getPixelX() - enemy.getPixelX()) > Math.abs(player.getPixelY() - enemy.getPixelY())) {
                    if (player.getPixelX() < enemy.getPixelX()) {
                        return LEFT;
                    } else {
                        return RIGHT;
                    }
                } else {
                    if (player.getPixelY() < enemy.getPixelY()) {
                        return UP;
                    } else {
                        return DOWN;
                    }
                }
            }
            if (player.getTileX() == enemy.getTileX()) {
                if (player.getTileY() < enemy.getTileY()) {
                    return UP;
                } else {
                    return DOWN;
                }
            } else {
                if (player.getTileX() < enemy.getTileX()) {
                    return LEFT;
                } else {
                    return RIGHT;
                }
            }
        } else {
            enemy.setSpeed(1);
            if (enemy.isCollider()) {
                if (map.getTile(enemy.getTileX() + 1, enemy.getTileY()).isBlock()) {
                    return LEFT;
                } else {
                    return RIGHT;
                }
            } else {
                return enemy.getDirection();
            }
        }
    }
}
