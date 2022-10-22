package Texture;

import Entity.Animate.Character.Bomber;
import Entity.Animate.Character.Enemy.Balloom;
import Entity.Entity;
import Graphics.Sprite;

public class AnimateTexture {
    public static Entity getAnimate(char c, int i, int j) {
        switch (c) {
            case 'p':
                return (new Bomber(j, i,Sprite.player_right_1));
            case '1':
                return (new Balloom(j, i, Sprite.BALLOOM_LEFT[0]));
            default:
                return null;
        }
    }
}
