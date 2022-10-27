package Texture;

import Entity.Animate.Brick;
import Entity.Static.*;
import Graphics.Sprite;

public class StaticTexture {
    public static StaticEntity setStatic(char c, int i, int j) {
        switch (c) {
            case 's':
                return new SpeedItem(j, i, Sprite.BRICK[0]);
            case 'f':
                return new FlameItem(j, i, Sprite.BRICK[0]);
            case 'b':
                return new BombItem(j ,i, Sprite.BRICK[0]);
            case '#':
                return new Wall(j, i, Sprite.wall);
            case 'x':
                return new Portal(j, i, Sprite.BRICK[0]);
            default:
                return new Grass(j, i, Sprite.grass);
        }
    }
}
