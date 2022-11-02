package Variables;

import static Graphics.Sprite.*;

public class Variables {
    public static final String GAME_TITLE = "BOMBERMAN";

    public static final int UP_BORDER = 2;

    public static final int WIDTH = 31;
    public static final int HEIGHT = 13;

    public static final int WIDTH_SCREEN = 16;
    public static final int HEIGHT_SCREEN = HEIGHT;
    public static final int dx[] = {0, 0, -1, 1};
    public static final int dy[] = {-1, 1, 0, 0};

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
    public static enum STATUS {
        NOTEXPLODEDYET,
        EXPLODING,
    }
    public static enum FLAME_SHAPE {
        BOMB_EXPLODED,
        VERTICAL,
        HORIZONTAL,
        HORIZONTAL_LEFT_LAST,
        HORIZONTAL_RIGHT_LAST,
        VERTICAL_TOP_LAST,
        VERTICAL_DOWN_LAST,
    }
    public static final String ICON_PATH = "/icon.png";

    public static final String[] MAP_URLS = {
           Variables.class.getResource("/levels/Level1.txt").getPath(),
    };

    public static final String[] FONT_URLS = {
            Variables.class.getResource("/fonts/ThaleahFat.ttf").toExternalForm(),
    };
}
