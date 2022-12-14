package graphics;

import javafx.scene.image.*;

/**
 * Lưu trữ thông tin các pixel của 1 sprite (hình ảnh game)
 */
public class Sprite {

    public static final int DEFAULT_SIZE = 16;
    public static final int SCALED_SIZE = DEFAULT_SIZE * 2;
    private static final int TRANSPARENT_COLOR = 0xffff00ff;
    public final int SIZE;
    private int _x, _y;
    public int[] _pixels;
    protected int _realWidth;
    protected int _realHeight;
    private SpriteSheet _sheet;

    /*
    |--------------------------------------------------------------------------
    | Board sprites
    |--------------------------------------------------------------------------
     */
    public static Sprite grass = new Sprite(DEFAULT_SIZE, 6, 0, SpriteSheet.tiles, 16, 16);
    public static Sprite[] BRICK = {
            new Sprite(DEFAULT_SIZE, 7, 0, SpriteSheet.tiles, 16, 16),
    };
    public static Sprite wall = new Sprite(DEFAULT_SIZE, 5, 0, SpriteSheet.tiles, 16, 16);
    public static Sprite portal = new Sprite(DEFAULT_SIZE, 4, 0, SpriteSheet.tiles, 14, 14);

    /*
    |--------------------------------------------------------------------------
    | Bomber Sprites
    |--------------------------------------------------------------------------
     */
    public static Sprite[] PLAYER_RIGHT = {
            new Sprite(DEFAULT_SIZE, 1, 0, SpriteSheet.tiles, 13, 16),
            new Sprite(DEFAULT_SIZE, 1, 1, SpriteSheet.tiles, 13, 16),
            new Sprite(DEFAULT_SIZE, 1, 2, SpriteSheet.tiles, 13, 16),
    };

    public static Sprite[] PLAYER_LEFT = {
            new Sprite(DEFAULT_SIZE, 3, 0, SpriteSheet.tiles, 13, 16),
            new Sprite(DEFAULT_SIZE, 3, 1, SpriteSheet.tiles, 13, 16),
            new Sprite(DEFAULT_SIZE, 3, 2, SpriteSheet.tiles, 13, 16),
    };


    public static Sprite[] PLAYER_UP = {
            new Sprite(DEFAULT_SIZE, 0, 0, SpriteSheet.tiles, 13, 16),
            new Sprite(DEFAULT_SIZE, 0, 1, SpriteSheet.tiles, 13, 16),
            new Sprite(DEFAULT_SIZE, 0, 2, SpriteSheet.tiles, 13, 16),
    };

    public static Sprite[] PLAYER_DOWN = {
            new Sprite(DEFAULT_SIZE, 2, 0, SpriteSheet.tiles, 13, 16),
            new Sprite(DEFAULT_SIZE, 2, 1, SpriteSheet.tiles, 13, 16),
            new Sprite(DEFAULT_SIZE, 2, 2, SpriteSheet.tiles, 13, 16),
    };

    public static Sprite[] PLAYER_DESTROYED = {
            new Sprite(DEFAULT_SIZE, 4, 2, SpriteSheet.tiles, 16, 13),
            new Sprite(DEFAULT_SIZE, 5, 2, SpriteSheet.tiles, 16, 13),
            new Sprite(DEFAULT_SIZE, 6, 2, SpriteSheet.tiles, 16, 13),
    };

    /*
    |--------------------------------------------------------------------------
    | Character
    |--------------------------------------------------------------------------
     */
    //BALLOOM
    public static Sprite[] BALLOOM_LEFT = {
            new Sprite(DEFAULT_SIZE, 9, 0, SpriteSheet.tiles, 16, 16),
            new Sprite(DEFAULT_SIZE, 9, 1, SpriteSheet.tiles, 16, 16),
            new Sprite(DEFAULT_SIZE, 9, 2, SpriteSheet.tiles, 16, 16),
    };

    public static Sprite[] BALLOOM_RIGHT = {
            new Sprite(DEFAULT_SIZE, 10, 0, SpriteSheet.tiles, 16, 16),
            new Sprite(DEFAULT_SIZE, 10, 1, SpriteSheet.tiles, 16, 16),
            new Sprite(DEFAULT_SIZE, 10, 2, SpriteSheet.tiles, 16, 16),
    };

    public static Sprite[] BALLOOM_DESTROYED = {
            new Sprite(DEFAULT_SIZE, 9, 3, SpriteSheet.tiles, 16, 16),
            new Sprite(DEFAULT_SIZE, 9, 3, SpriteSheet.tiles, 16, 16),
            new Sprite(DEFAULT_SIZE, 9, 3, SpriteSheet.tiles, 16, 16),
    };

    //ONEAL
    public static Sprite[] ONEAL_LEFT = {
            new Sprite(DEFAULT_SIZE, 11, 0, SpriteSheet.tiles, 16, 16),
            new Sprite(DEFAULT_SIZE, 11, 1, SpriteSheet.tiles, 16, 16),
            new Sprite(DEFAULT_SIZE, 11, 2, SpriteSheet.tiles, 16, 16),
    };

    public static Sprite[] ONEAL_RIGHT = {
            new Sprite(DEFAULT_SIZE, 12, 0, SpriteSheet.tiles, 16, 16),
            new Sprite(DEFAULT_SIZE, 12, 1, SpriteSheet.tiles, 16, 16),
            new Sprite(DEFAULT_SIZE, 12, 2, SpriteSheet.tiles, 16, 16),
    };
    public static Sprite[] ONEAL_DESTROYED = {
            new Sprite(DEFAULT_SIZE, 11, 3, SpriteSheet.tiles, 16, 16),
            new Sprite(DEFAULT_SIZE, 11, 3, SpriteSheet.tiles, 16, 16),
            new Sprite(DEFAULT_SIZE, 11, 3, SpriteSheet.tiles, 16, 16),
    };

    //Doll
    public static Sprite[] DOLL_LEFT = {
            new Sprite(DEFAULT_SIZE, 13, 0, SpriteSheet.tiles, 16, 16),
            new Sprite(DEFAULT_SIZE, 13, 1, SpriteSheet.tiles, 16, 16),
            new Sprite(DEFAULT_SIZE, 13, 2, SpriteSheet.tiles, 16, 16),
    };

    public static Sprite[] DOLL_RIGHT = {
            new Sprite(DEFAULT_SIZE, 14, 0, SpriteSheet.tiles, 16, 16),
            new Sprite(DEFAULT_SIZE, 14, 1, SpriteSheet.tiles, 16, 16),
            new Sprite(DEFAULT_SIZE, 14, 2, SpriteSheet.tiles, 16, 16),
    };

    public static Sprite[] DOLL_DESTROYED = {
            new Sprite(DEFAULT_SIZE, 13, 3, SpriteSheet.tiles, 16, 16),
            new Sprite(DEFAULT_SIZE, 13, 3, SpriteSheet.tiles, 16, 16),
            new Sprite(DEFAULT_SIZE, 13, 3, SpriteSheet.tiles, 16, 16),
    };

    //Minvo
    public static Sprite[] MINVO_LEFT = {
            new Sprite(DEFAULT_SIZE, 8, 5, SpriteSheet.tiles, 16, 16),
            new Sprite(DEFAULT_SIZE, 8, 6, SpriteSheet.tiles, 16, 16),
            new Sprite(DEFAULT_SIZE, 8, 7, SpriteSheet.tiles, 16, 16),
    };

    public static Sprite[] MINVO_RIGHT = {
            new Sprite(DEFAULT_SIZE, 9, 5, SpriteSheet.tiles, 16, 16),
            new Sprite(DEFAULT_SIZE, 9, 6, SpriteSheet.tiles, 16, 16),
            new Sprite(DEFAULT_SIZE, 9, 7, SpriteSheet.tiles, 16, 16),
    };

    public static Sprite[] MINVO_DESTROYED = {
            new Sprite(DEFAULT_SIZE, 8, 8, SpriteSheet.tiles, 16, 16),
            new Sprite(DEFAULT_SIZE, 8, 8, SpriteSheet.tiles, 16, 16),
            new Sprite(DEFAULT_SIZE, 8, 8, SpriteSheet.tiles, 16, 16),
    };
    //Kondoria
    public static Sprite[] KONDORIA_LEFT = {
            new Sprite(DEFAULT_SIZE, 10, 5, SpriteSheet.tiles, 16, 16),
            new Sprite(DEFAULT_SIZE, 10, 6, SpriteSheet.tiles, 16, 16),
            new Sprite(DEFAULT_SIZE, 10, 7, SpriteSheet.tiles, 16, 16),
    };

    public static Sprite[] KONDORIA_RIGHT = {
            new Sprite(DEFAULT_SIZE, 11, 5, SpriteSheet.tiles, 16, 16),
            new Sprite(DEFAULT_SIZE, 11, 6, SpriteSheet.tiles, 16, 16),
            new Sprite(DEFAULT_SIZE, 11, 7, SpriteSheet.tiles, 16, 16),
    };

    public static Sprite[] KONDORIA_DESTROYED = {
            new Sprite(DEFAULT_SIZE, 10, 8, SpriteSheet.tiles, 16, 16),
            new Sprite(DEFAULT_SIZE, 10, 8, SpriteSheet.tiles, 16, 16),
            new Sprite(DEFAULT_SIZE, 10, 8, SpriteSheet.tiles, 16, 16),
    };
    //ALL
    public static Sprite mob_dead1 = new Sprite(DEFAULT_SIZE, 15, 0, SpriteSheet.tiles, 16, 16);
    public static Sprite mob_dead2 = new Sprite(DEFAULT_SIZE, 15, 1, SpriteSheet.tiles, 16, 16);
    public static Sprite mob_dead3 = new Sprite(DEFAULT_SIZE, 15, 2, SpriteSheet.tiles, 16, 16);

    /*
    |--------------------------------------------------------------------------
    | Bomb Sprites
    |--------------------------------------------------------------------------
     */
    public static Sprite[] BOMB = {
            new Sprite(DEFAULT_SIZE, 0, 3, SpriteSheet.tiles, 15, 15),
            new Sprite(DEFAULT_SIZE, 1, 3, SpriteSheet.tiles, 13, 15),
            new Sprite(DEFAULT_SIZE, 2, 3, SpriteSheet.tiles, 12, 14),

    };

    /*
    |--------------------------------------------------------------------------
    | FlameSegment Sprites
    |--------------------------------------------------------------------------
     */
    public static Sprite[] BOMB_EXPLODED = {
            new Sprite(DEFAULT_SIZE, 0, 4, SpriteSheet.tiles, 16, 16),
            new Sprite(DEFAULT_SIZE, 0, 5, SpriteSheet.tiles, 16, 16),
            new Sprite(DEFAULT_SIZE, 0, 6, SpriteSheet.tiles, 16, 16),
    };

    public static Sprite[] EXPLOSION_VERTICAL = {
            new Sprite(DEFAULT_SIZE, 1, 5, SpriteSheet.tiles, 16, 16),
            new Sprite(DEFAULT_SIZE, 2, 5, SpriteSheet.tiles, 16, 16),
            new Sprite(DEFAULT_SIZE, 3, 5, SpriteSheet.tiles, 16, 16),
    };

    public static Sprite[] EXPLOSION_HORIZONTAL = {
            new Sprite(DEFAULT_SIZE, 1, 7, SpriteSheet.tiles, 16, 16),
            new Sprite(DEFAULT_SIZE, 1, 8, SpriteSheet.tiles, 16, 16),
            new Sprite(DEFAULT_SIZE, 1, 9, SpriteSheet.tiles, 16, 16),
    };

    public static Sprite[] EXPLOSION_HORIZONTAL_LEFT_LAST = {
            new Sprite(DEFAULT_SIZE, 0, 7, SpriteSheet.tiles, 16, 16),
            new Sprite(DEFAULT_SIZE, 0, 8, SpriteSheet.tiles, 16, 16),
            new Sprite(DEFAULT_SIZE, 0, 9, SpriteSheet.tiles, 16, 16),
    };

    public static Sprite[] EXPLOSION_HORIZONTAL_RIGHT_LAST = {
            new Sprite(DEFAULT_SIZE, 2, 7, SpriteSheet.tiles, 16, 16),
            new Sprite(DEFAULT_SIZE, 2, 8, SpriteSheet.tiles, 16, 16),
            new Sprite(DEFAULT_SIZE, 2, 9, SpriteSheet.tiles, 16, 16),
    };

    public static Sprite[] EXPLOSION_VERTICAL_TOP_LAST = {
            new Sprite(DEFAULT_SIZE, 1, 4, SpriteSheet.tiles, 16, 16),
            new Sprite(DEFAULT_SIZE, 2, 4, SpriteSheet.tiles, 16, 16),
            new Sprite(DEFAULT_SIZE, 3, 4, SpriteSheet.tiles, 16, 16),
    };

    public static Sprite[] EXPLOSION_VERTICAL_DOWN_LAST = {
            new Sprite(DEFAULT_SIZE, 1, 6, SpriteSheet.tiles, 16, 16),
            new Sprite(DEFAULT_SIZE, 2, 6, SpriteSheet.tiles, 16, 16),
            new Sprite(DEFAULT_SIZE, 3, 6, SpriteSheet.tiles, 16, 16),
    };
    /*
    |--------------------------------------------------------------------------
    | Brick FlameSegment
    |--------------------------------------------------------------------------
     */
    public static Sprite[] BRICK_EXPLODED = {
            new Sprite(DEFAULT_SIZE, 7, 1, SpriteSheet.tiles, 16, 16),
            new Sprite(DEFAULT_SIZE, 7, 2, SpriteSheet.tiles, 16, 16),
            new Sprite(DEFAULT_SIZE, 7, 3, SpriteSheet.tiles, 16, 16),
    };
    /*
    |--------------------------------------------------------------------------
    | Powerups
    |--------------------------------------------------------------------------
     */
    public static Sprite powerup_bombs = new Sprite(DEFAULT_SIZE, 0, 10, SpriteSheet.tiles, 16, 16);
    public static Sprite powerup_flames = new Sprite(DEFAULT_SIZE, 1, 10, SpriteSheet.tiles, 16, 16);
    public static Sprite powerup_speed = new Sprite(DEFAULT_SIZE, 2, 10, SpriteSheet.tiles, 16, 16);
    public static Sprite powerup_wallpass = new Sprite(DEFAULT_SIZE, 3, 10, SpriteSheet.tiles, 16, 16);
    public static Sprite powerup_detonator = new Sprite(DEFAULT_SIZE, 4, 10, SpriteSheet.tiles, 16, 16);
    public static Sprite powerup_bombpass = new Sprite(DEFAULT_SIZE, 5, 10, SpriteSheet.tiles, 16, 16);
    public static Sprite powerup_flamepass = new Sprite(DEFAULT_SIZE, 6, 10, SpriteSheet.tiles, 16, 16);
    public static Sprite powerup_mystery = new Sprite(DEFAULT_SIZE, 7, 10, SpriteSheet.tiles, 16, 16);
    /*
    |--------------------------------------------------------------------------
    | Scores
    |--------------------------------------------------------------------------
     */
    public static Sprite balloom_score = new Sprite(DEFAULT_SIZE, 8, 10, SpriteSheet.tiles, 16, 16);
    public static Sprite oneal_score = new Sprite(DEFAULT_SIZE, 9, 10, SpriteSheet.tiles, 16, 16);
    public static Sprite doll_score = new Sprite(DEFAULT_SIZE, 10, 10, SpriteSheet.tiles, 16, 16);
    public static Sprite minvo_score = new Sprite(DEFAULT_SIZE, 11, 10, SpriteSheet.tiles, 16, 16);
    public static Sprite kondoria_score = new Sprite(DEFAULT_SIZE, 12, 10, SpriteSheet.tiles, 16, 16);
    public static Sprite ovapi_score = new Sprite(DEFAULT_SIZE, 13, 10, SpriteSheet.tiles, 16, 16);
    public static Sprite pass_score = new Sprite(DEFAULT_SIZE, 14, 10, SpriteSheet.tiles, 16, 16);
    public static Sprite pontan_score = new Sprite(DEFAULT_SIZE, 15, 10, SpriteSheet.tiles, 16, 16);

    public Sprite(int size, int x, int y, SpriteSheet sheet, int rw, int rh) {
        SIZE = size;
        _pixels = new int[SIZE * SIZE];
        _x = x * SIZE;
        _y = y * SIZE;
        _sheet = sheet;
        _realWidth = rw * 2;
        _realHeight = rh * 2;
        load();
    }

    public Sprite(int size, int color) {
        SIZE = size;
        _pixels = new int[SIZE * SIZE];
        setColor(color);
    }

    private void setColor(int color) {
        for (int i = 0; i < _pixels.length; i++) {
            _pixels[i] = color;
        }
    }

    private void load() {
        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                _pixels[x + y * SIZE] = _sheet._pixels[(x + _x) + (y + _y) * _sheet.SIZE];
            }
        }
    }

    public static Sprite movingSprite(Sprite[] sprites, int animate, long time) {
        return sprites[(int) ((time + 3) / 3 % animate)];
    }

    public int getSize() {
        return SIZE;
    }

    public int getPixel(int i) {
        return _pixels[i];
    }

    public Image getFxImage() {
        WritableImage wr = new WritableImage(SIZE, SIZE);
        PixelWriter pw = wr.getPixelWriter();
        for (int x = 0; x < SIZE; x++) {
            for (int y = 0; y < SIZE; y++) {
                if (_pixels[x + y * SIZE] == TRANSPARENT_COLOR) {
                    pw.setArgb(x, y, 0);
                } else {
                    pw.setArgb(x, y, _pixels[x + y * SIZE]);
                }
            }
        }
        Image input = new ImageView(wr).getImage();
        return resample(input, SCALED_SIZE / DEFAULT_SIZE);
    }

    private Image resample(Image input, int scaleFactor) {
        final int W = (int) input.getWidth();
        final int H = (int) input.getHeight();
        final int S = scaleFactor;

        WritableImage output = new WritableImage(
                W * S,
                H * S
        );

        PixelReader reader = input.getPixelReader();
        PixelWriter writer = output.getPixelWriter();

        for (int y = 0; y < H; y++) {
            for (int x = 0; x < W; x++) {
                final int argb = reader.getArgb(x, y);
                for (int dy = 0; dy < S; dy++) {
                    for (int dx = 0; dx < S; dx++) {
                        writer.setArgb(x * S + dx, y * S + dy, argb);
                    }
                }
            }
        }

        return output;
    }

    public int getRealWidth() {
        return _realWidth;
    }

    public int getRealHeight() {
        return _realHeight;
    }

}