package Entity.Animate;

import Game.MainGame;
import Graphics.Sprite;
import Variables.Variables;

import static Variables.Variables.BOMB_STATUS.*;
public class Bomb extends AnimateEntity{
    protected int timetoExplode = 120;
    public static int limit = 3;
    public Bomb(int x, int y, Sprite sprite) {
        super(x, y, sprite);
        animation.put(NOTEXPLODEDYET, Sprite.BOMB);
        currentAnimate = animation.get(NOTEXPLODEDYET);
        block = false;
    }
    @Override
    public void update() {
        if(timetoExplode > 0) {
            updateAnimation();
            timetoExplode--;
        } else {
            delete();
        }
    }

    @Override
    public void delete() {
        this.remove();
    }
}
