package Variables;

import Graphics.Sprite;

public class Variables {
    public static final String GAME_TITLE = "BOMBERMAN";

    public static final int WIDTH = 31;
    public static final int HEIGHT = 13;

    public static final int dx[] = {-1, 1, 0, 0};
    public static final int dy[] = {0, 0, -1, 1};

    public static final int INF = 1000000000;

    public static enum DIRECTION {
        UP,
        DOWN,
        LEFT,
        RIGHT,
        PLACEBOMB,
        DESTROYED,
        NONE,

    }
    public static enum BOMB_STATUS {
        NOTEXPLODEDYET,
    }

    public static final String[] MAP_URLS = {
            Variables.class.getResource("/levels/Level1.txt").getPath(),
    };
}
