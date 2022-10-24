package Texture;

import Entity.Animate.Bomb;
import Graphics.Sprite;

public class BombTexture {
    public static Bomb setBomb(int i, int j) {
        return new Bomb(i, j, Sprite.BOMB[0]);
    }
}
