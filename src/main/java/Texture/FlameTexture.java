package Texture;

import Entity.Animate.Flame;
import Graphics.Sprite;
import static Variables.Variables.FLAME_SHAPE.*;

public class FlameTexture {
    public static Flame setFlame(String c, int i, int j) {
        switch (c) {
            case "be":
                return new Flame(i, j, Sprite.BOMB_EXPLODED[0], BOMB_EXPLODED);
            case "v":
                return new Flame(i, j, Sprite.EXPLOSION_VERTICAL[0], VERTICAL);
            case "h":
                return new Flame(i, j, Sprite.EXPLOSION_HORIZONTAL[0], HORIZONTAL);
            case "hll":
                return new Flame(i, j, Sprite.EXPLOSION_HORIZONTAL_LEFT_LAST[0], HORIZONTAL_LEFT_LAST);
            case "hrl":
                return new Flame(i, j, Sprite.EXPLOSION_HORIZONTAL_RIGHT_LAST[0], HORIZONTAL_RIGHT_LAST);
            case "vtl":
                return new Flame(i, j, Sprite.EXPLOSION_VERTICAL_TOP_LAST[0], VERTICAL_TOP_LAST);
            case "vdl":
                return new Flame(i, j, Sprite.EXPLOSION_VERTICAL_DOWN_LAST[0], VERTICAL_DOWN_LAST);
            default:
                return null;
        }
    }
}
