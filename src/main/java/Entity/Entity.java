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

    public void setPosition(int x, int y) {
        pixelX = x;
        pixelY = y;
        tileX = pixelX / SCALED_SIZE;
        tileY = pixelY / SCALED_SIZE;
    }


    public Pair<Integer, Integer> getTile() {
        return new Pair(tileX, tileY);
    }

    public int getTileX() {
        return tileX;
    }

    public int getTileY() {
        return tileY;
    }

    public int getPixelX() {
        return pixelX;
    }

    public int getPixelY() {
        return pixelY;
    }

    public boolean isInATile() {
        return (pixelX % SCALED_SIZE == 0 && pixelY % SCALED_SIZE == 0);
    }

    public boolean isBlock() {
        return block;
    }

    public void setBlock(boolean block) {
        this.block = block;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
        image = sprite.getFxImage();
    }
}