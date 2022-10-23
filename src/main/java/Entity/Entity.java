package Entity;

import Map.Map;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import Graphics.Sprite;
import javafx.util.Pair;

import static Graphics.Sprite.*;

public abstract class Entity {
    protected static Map map = Map.getGameMap();
    public int pixelX;
    public int pixelY;
    public int tileX;
    public int tileY;

    protected Sprite sprite;
    protected Image image;

    protected boolean block;

    public Entity(int x, int y, Sprite sprite) {
        this.tileX = x;
        this.tileY = y;
        this.pixelX = x * SCALED_SIZE;
        this.pixelY = y * SCALED_SIZE;
        this.sprite = sprite;
        this.image = sprite.getFxImage();
        this.block = false;
    }

    public static void setGameMap(Map map) {
        Entity.map = map;
    }

    public Rectangle2D getBorder() {

        return new Rectangle2D(pixelX, pixelY, sprite.getRealWidth(), sprite.getRealHeight());
    }

    public boolean isCollider(Entity entity) {
        if (entity == null) return false;
        return getBorder().intersects(entity.getBorder());
    }
    public void render(GraphicsContext graphicsContext) {
        graphicsContext.drawImage(image, pixelX, pixelY);
    }

    public abstract void update();

    public Pair<Integer,Integer> getTile() {
        return new Pair(tileX, tileY);
    }
    public int getTileX() {
        return tileX;
    }

    public int getTileY() {
        return tileY;
    }

    public boolean isBlock() {
        return block;
    }

}