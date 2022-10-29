package Path;

import Entity.Animate.Bomb;
import Entity.Animate.Character.Bomber;
import Entity.Animate.Character.Enemy.Enemy;
import Map.Map;
import Variables.Variables.DIRECTION;

import static Graphics.Sprite.SCALED_SIZE;
import static Variables.Variables.DIRECTION.*;

public class HeadPath extends Path{
    public HeadPath(Map map, Bomber player, Enemy enemy) {
        super(map, player, enemy);
    }

    @Override
    public DIRECTION path() {
        if (player.getTileX() != enemy.getTileX() && player.getTileY() != enemy.getTileY()) {
            return NONE;
        }
        if (player.getTileX() == enemy.getTileX()) {
            if (enemy.getPixelX() % SCALED_SIZE != 0) {
                return NONE;
            }
            boolean check = false;
            for (int i = -8; i <= 8; i++) {
                if ((player.getPixelX() + i) % SCALED_SIZE == 0) {
                    check = true;
                }
            }
            if (!check) {
                return NONE;
            }
            if (player.getTileY() < enemy.getTileY()) {
                for (Bomb bomb: map.getBombs()) {
                    if (bomb.getTileY() >= player.getTileY() && bomb.getTileY() <= enemy.getTileY()) {
                        return NONE;
                    }
                }
                for (int i = player.getTileY(); i < enemy.getTileY(); i++) {
                    if (map.getTile(player.getTileX(), i).isBlock()) {
                        return NONE;
                    }
                }
            } else {
                for (Bomb bomb: map.getBombs()) {
                    if (bomb.getTileY() <= player.getTileY() && bomb.getTileY() >= enemy.getTileY()) {
                        return NONE;
                    }
                }
                for (int i = enemy.getTileY(); i < player.getTileY(); i++) {
                    if (map.getTile(player.getTileX(), i).isBlock()) {
                        return NONE;
                    }
                }
            }
        } else {
            if (enemy.getPixelY() % SCALED_SIZE != 0) {
                return NONE;
            }
            boolean check = false;
            for (int i = -8; i <= 8; i++) {
                if ((player.getPixelY() + i) % SCALED_SIZE == 0) {
                    check = true;
                }
            }
            if (!check) {
                return NONE;
            }
            if (player.getTileX() < enemy.getTileX()) {
                for (Bomb bomb: map.getBombs()) {
                    if (bomb.getTileX() >= player.getTileX() && bomb.getTileX() <= enemy.getTileX()) {
                        return NONE;
                    }
                }
                for (int i = player.getTileX(); i < enemy.getTileX(); i++) {
                    if (map.getTile(i, player.getTileY()).isBlock()) {
                        return NONE;
                    }
                }
            } else {
                for (Bomb bomb: map.getBombs()) {
                    if (bomb.getTileX() <= player.getTileX() && bomb.getTileX() >= enemy.getTileX()) {
                        return NONE;
                    }
                }
                for (int i = enemy.getTileX(); i < player.getTileX(); i++) {
                    if (map.getTile(i, player.getTileY()).isBlock()) {
                        return NONE;
                    }
                }
            }
        }

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
    }
}
