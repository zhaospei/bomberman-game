package Entity.Animate;

import Entity.Static.Grass;
import Graphics.Sprite;
import Texture.FlameTexture;

import java.util.concurrent.atomic.AtomicBoolean;

import static Variables.Variables.STATUS.*;

public class Bomb extends AnimateEntity {
    protected int timetoExplode = 120;
    public static int limit = 1;

    public Bomb(int x, int y, Sprite sprite) {
        super(x, y, sprite);
        animation.put(NOTEXPLODEDYET, Sprite.BOMB);
        currentAnimate = animation.get(NOTEXPLODEDYET);
        block = false;
    }

    @Override
    public void update() {
        if (timetoExplode > 0) {
            updateAnimation();
            timetoExplode--;
        } else {
            delete();
            Flame flm = FlameTexture.setFlame("be", this.tileX, this.tileY);
            map.getFlames().add(flm);
            AtomicBoolean up = new AtomicBoolean(true);
            AtomicBoolean left = new AtomicBoolean(true);
            AtomicBoolean right = new AtomicBoolean(true);
            AtomicBoolean down = new AtomicBoolean(true);
            for (int i = 1; i <= flm.flameLength; i++) {
                int x = flm.getTileX();
                int y = flm.getTileY();
                int ii = i;
                if (down.get()) {
                    map.getBombs().forEach(bomb -> {
                        if (bomb.getTileX() == x && bomb.getTileY() == (y + ii)) {
                            bomb.setTimetoExplode(0);
                        }
                    });
                    map.getFlames().forEach(flame -> {
                        if (flame.getTileX() == x && flame.getTileY() == (y + ii)) {
                            down.set(false);
                        }
                    });
                    if (map.getTile(x, y + i) instanceof Grass == false) {
                        flm.interactWith(map.getTile(x, y + i));
                        down.set(false);
                    }
                }

                if (up.get()) {
                    map.getBombs().forEach(bomb -> {
                        if (bomb.getTileX() == x && bomb.getTileY() == (y - ii)) {
                            bomb.setTimetoExplode(0);
                        }
                    });
                    map.getFlames().forEach(flame -> {
                        if (flame.getTileX() == x && flame.getTileY() == (y - ii)) {
                            up.set(false);
                        }
                    });
                    if (map.getTile(x, y - i) instanceof Grass == false) {
                        flm.interactWith(map.getTile(x, y - i));
                        up.set(false);
                    }
                }

                if (right.get()) {
                    map.getBombs().forEach(bomb -> {
                        if (bomb.getTileX() == (x + ii) && bomb.getTileY() == (y)) {
                            bomb.setTimetoExplode(0);
                        }
                    });
                    map.getFlames().forEach(flame -> {
                        if (flame.getTileX() == (x + ii) && flame.getTileY() == (y)) {
                            right.set(false);
                        }
                    });
                    if (map.getTile(x + i, y) instanceof Grass == false) {
                        flm.interactWith(map.getTile(x + i, y));
                        right.set(false);

                    }
                }
                if (left.get()) {
                    map.getBombs().forEach(bomb -> {
                        if (bomb.getTileX() == (x - ii) && bomb.getTileY() == (y)) {
                            bomb.setTimetoExplode(0);
                        }
                    });
                    map.getFlames().forEach(flame -> {
                        if (flame.getTileX() == (x - ii) && flame.getTileY() == (y)) {
                            left.set(false);
                        }
                    });
                    if (map.getTile(x - i, y) instanceof Grass == false) {
                        flm.interactWith(map.getTile(x - i, y));
                        left.set(false);
                    }
                }

                if (i == flm.flameLength) {
                    if (down.get()) {
                        Flame vdl = FlameTexture.setFlame("vdl", x, y + i);
                        map.getFlames().add(vdl);
                        vdl.checkCollison();
                    }
                    if (up.get()) {
                        Flame vtl = FlameTexture.setFlame("vtl", x, y - i);
                        map.getFlames().add(vtl);
                        vtl.checkCollison();
                    }
                    if (left.get()) {
                        Flame hll = FlameTexture.setFlame("hll", x - i, y);
                        map.getFlames().add(hll);
                        hll.checkCollison();
                    }
                    if (right.get()) {
                        Flame hrl = FlameTexture.setFlame("hrl", x + i, y);
                        map.getFlames().add(hrl);
                        hrl.checkCollison();
                    }

                } else {
                    if (down.get()) {
                        Flame vd = FlameTexture.setFlame("v", x, y + i);
                        map.getFlames().add(vd);
                        vd.checkCollison();
                    }
                    if (up.get()) {
                        Flame vt = FlameTexture.setFlame("v", x, y - i);
                        map.getFlames().add(vt);
                        vt.checkCollison();
                    }
                    if (left.get()) {
                        Flame hl = FlameTexture.setFlame("h", x - i, y);
                        map.getFlames().add(hl);
                        hl.checkCollison();
                    }
                    if (right.get()) {
                        Flame hr = FlameTexture.setFlame("h", x + i, y);
                        map.getFlames().add(hr);
                        hr.checkCollison();
                    }
                }
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
