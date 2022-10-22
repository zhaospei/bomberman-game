package Entity;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import Graphics.Sprite;
import static Graphics.Sprite.*;

public abstract class Entity {
    public int pixelX;
    public int pixelY;
    public int tileX;
    public int tileY;

    protected Sprite sprite;
    protected Image image;

    public Entity(int x, int y, Sprite sprite) {
        this.tileX = x;
        this.tileY = y;
        this.pixelX = x * SCALED_SIZE;
        this.pixelY = y * SCALED_SIZE;
        this.sprite = sprite;
        this.image = sprite.getFxImage();
    }

    public Rectangle2D getBorder() {
        return new Rectangle2D(pixelX, pixelY, SCALED_SIZE, SCALED_SIZE);
    }

    public boolean isCollider(Entity entity) {
        if (entity == null) return false;
        return getBorder().intersects(entity.getBorder());
    }
    public void render(GraphicsContext graphicsContext) {
        graphicsContext.drawImage(image, pixelX, pixelY);
    }

    public abstract void update();
}