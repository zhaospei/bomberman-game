package texture;

import entity.animateentity.Bomb;
import graphics.Sprite;

public class BombTexture {
    public static Bomb setBomb(int i, int j) {
        return new Bomb(i, j, Sprite.BOMB[0]);
    }
}
