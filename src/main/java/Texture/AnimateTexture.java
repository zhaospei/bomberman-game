package Texture;

import Entity.Animate.Character.Bomber;
import Entity.Animate.Brick;
import Entity.Entity;
import Graphics.Sprite;

public class AnimateTexture {
    public static Entity getAnimate(char c, int i, int j) {
        switch (c) {
            case '*':
                return (new Brick(j, i, Sprite.brick));
            case 'p':
                return (new Bomber(j, i,Sprite.player_right_1));
            default:
                return null;
        }
    }
}
