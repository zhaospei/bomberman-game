package Entity.Animate;

import Entity.Static.Grass;
import Graphics.Sprite;
import Sound.Sound;
import Sound.SoundPlay;
import Texture.FlameTexture;

import java.util.concurrent.atomic.AtomicBoolean;

import static Variables.Variables.STATUS.*;

public class Bomb extends AnimateEntity {
    protected int timetoExplode = 120;
    public static int limit = 1;
    private boolean up = true;
    private boolean left = true;
    private boolean right = true;
    private boolean down = true;
    private int cnt = 0;

    public Bomb(int x, int y, Sprite sprite) {
        super(x, y, sprite);
        animation.put(NOTEXPLODEDYET, Sprite.BOMB);
        currentAnimate = animation.get(NOTEXPLODEDYET);
        block = false;
    }

    @Override
    public void update() {
        if (timetoExplode != 0) {
            updateAnimation();
            timetoExplode--;
        } else {
            delete();
            Flame flm = FlameTexture.setFlame("be", this.tileX, this.tileY);
            map.getFlames().add(flm);
            for (int i = 1; i <= flm.flameLength; i++) {
                int x = flm.getTileX();
                int y = flm.getTileY();
                int ii = i;
                if (down) {
                    map.getBombs().forEach(bomb -> {
                        if (bomb.getTileX() == x && bomb.getTileY() == (y + ii)) {
                            bomb.setTimetoExplode(0);
                            down = false;
                            if(bomb.up) {
                                cnt++;
                            }
                        }
                    });
                    map.getFlames().forEach(flame -> {
                        if (flame.getTileX() == x && flame.getTileY() == (y+ii)) {
                            down = false;
                        }
                    });
                    if (map.getTile(x, y + i) instanceof Grass == false) {
                        flm.interactWith(map.getTile(x, y + i));
                        down = false;
                    }
                }

                if (up) {
                    map.getBombs().forEach(bomb -> {
                        if (bomb.getTileX() == x && bomb.getTileY() == (y - ii)) {
                            bomb.setTimetoExplode(0);
                            up = false;
                            if(bomb.down) {
                                cnt++;
                            }
                        }
                    });
                    map.getFlames().forEach(flame -> {
                        if (flame.getTileX() == x && flame.getTileY() == (y-ii)) {
                            up = false;
                        }
                    });
                    if (map.getTile(x, y - i) instanceof Grass == false) {
                        flm.interactWith(map.getTile(x, y - i));
                        up = false;
                    }
                }

                if (right) {
                    map.getBombs().forEach(bomb -> {
                        if (bomb.getTileX() == (x + ii) && bomb.getTileY() == (y)) {
                            bomb.setTimetoExplode(0);
                            right = false;
                            if(bomb.left) {
                                cnt++;
                            }
                        }
                    });
                    map.getFlames().forEach(flame -> {
                        if (flame.getTileX() == (x+ii) && flame.getTileY() == (y)) {
                            right = false;
                        }
                    });
                    if (map.getTile(x + i, y) instanceof Grass == false) {
                        flm.interactWith(map.getTile(x + i, y));
                        right = false;

                    }
                }
                if (left) {
                    map.getBombs().forEach(bomb -> {
                        if (bomb.getTileX() == (x - ii) && bomb.getTileY() == (y)) {
                            bomb.setTimetoExplode(0);
                            left = false;
                            if(bomb.right) {
                                cnt++;
                            }
                        }
                    });
                    map.getFlames().forEach(flame -> {
                        if (flame.getTileX() == (x-ii) && flame.getTileY() == (y)) {
                            left = false;
                        }
                    });
                    if (map.getTile(x - i, y) instanceof Grass == false) {
                        flm.interactWith(map.getTile(x - i, y));
                        left = false;
                    }
                }

                if (i == flm.flameLength) {
                    if (down) {
                        Flame vdl = FlameTexture.setFlame("vdl", x, y + i);
                        map.getFlames().add(vdl);
                    }
                    if (up) {
                        Flame vtl = FlameTexture.setFlame("vtl", x, y - i);
                        map.getFlames().add(vtl);
                    }
                    if (left) {
                        Flame hll = FlameTexture.setFlame("hll", x - i, y);
                        map.getFlames().add(hll);
                    }
                    if (right) {
                        Flame hrl = FlameTexture.setFlame("hrl", x + i, y);
                        map.getFlames().add(hrl);
                    }

                } else {
                    if (down) {
                        Flame vd = FlameTexture.setFlame("v", x, y + i);
                        map.getFlames().add(vd);
                    }
                    if (up) {
                        Flame vt = FlameTexture.setFlame("v", x, y - i);
                        map.getFlames().add(vt);
                    }
                    if (left) {
                        Flame hl = FlameTexture.setFlame("h", x - i, y);
                        map.getFlames().add(hl);
                    }
                    if (right) {
                        Flame hr = FlameTexture.setFlame("h", x + i, y);
                        map.getFlames().add(hr);
                    }
                }
            }
            if(cnt == 0) {
                Sound.bomb_explosion.play();
            }
        }
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public void setTimetoExplode(int timetoExplode) {
        this.timetoExplode = timetoExplode;
    }

    @Override
    public void delete() {
        this.remove();
    }

}
