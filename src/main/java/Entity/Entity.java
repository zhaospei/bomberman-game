package Entity;

import Map.Map;
import Sound.Sound;
import Variables.Variables;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import Graphics.Sprite;
import javafx.util.Pair;

import static Graphics.Sprite.*;
import static Variables.Variables.*;

public abstract class Entity {
    protected static Map map = Map.getGameMap();
    protected int pixelX;
    protected int pixelY;
    protected int tileX;
    protected int tileY;

    protected Sprite sprite;
    protected Image image;

    protected boolean block;

    protected boolean removed;

    public Entity(int x, int y, Sprite sprite) {
        this.tileX = x;
        this.tileY = y;
        this.pixelX = x * SCALED_SIZE;
        this.pixelY = y * SCALED_SIZE;
        this.sprite = sprite;
        this.image = sprite.getFxImage();
        this.block = false;
        this.removed = false;
    }

    public static void setGameMap(Map map) {
        Entity.map = map;
    }

    public Rectangle2D getBorder() {

        return new Rectangle2D(pixelX, pixelY, sprite.getRealWidth(), sprite.getRealHeight());
    }

    public void remove() {
        removed = true;
    }

    public boolean isRemoved() {
        return removed;
    }

    public boolean isCollider(Entity entity) {
        if (entity == null) return false;
        return getBorder().intersects(entity.getBorder());
    }

    public void render(GraphicsContext graphicsContext) {
        graphicsContext.drawImage(image, pixelX - map.getRenderX(), pixelY - map.getRenderY());
    }

    public abstract void update();

    public void setPosition(int x, int y) {
        pixelX = x;
        pixelY = y;
        tileX = pixelX / SCALED_SIZE;
        tileY = pixelY / SCALED_SIZE;
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
    public void setTile(int x, int y) {
        tileX = x;
        tileY = y;
        pixelX = tileX * SCALED_SIZE;
        pixelY = tileY * SCALED_SIZE;
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