package Entity.Animate;

import Entity.Static.Grass;
import Game.MainGame;
import Graphics.Sprite;
import Texture.FlameTexture;
import Variables.Variables;

import static Variables.Variables.BOMB_STATUS.*;

public class Bomb extends AnimateEntity {
    protected int timetoExplode = 120;
    public static int limit = 2;

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
            boolean up = true;
            boolean left = true;
            boolean right = true;
            boolean down = true;
            for (int i = 1; i <= flm.flameLength; i++) {
                int x = flm.getTileX();
                int y = flm.getTileY();
                if (down)
                    if (map.getTile(x, y + i) instanceof Grass == false) {
                        down = false;
                    }
                if (up)
                    if (map.getTile(x, y - i) instanceof Grass == false) {
                        up = false;
                    }
                if (right)
                    if (map.getTile(x + i, y) instanceof Grass == false) {
                        right = false;
                    }
                if (left)
                    if (map.getTile(x - i, y) instanceof Grass == false) {
                        left = false;
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
        }
    }

    @Override
    public void delete() {
        this.remove();
    }
}
