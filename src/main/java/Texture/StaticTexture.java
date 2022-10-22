package Texture;

import Entity.Entity;
import Entity.Static.Grass;
import Entity.Static.Wall;
import Graphics.Sprite;

public class StaticTexture {
    public static Entity getStatic(char c, int i, int j) {
        switch (c) {
            case '#':
                return (new Wall(j, i, Sprite.wall));
            default:
                return (new Grass(j, i, Sprite.grass));
        }
    }
}
