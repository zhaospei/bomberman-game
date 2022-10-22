package Entity;

import Graphics.Sprite;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import static Graphics.Sprite.SCALED_SIZE;

public abstract class Entity {
    protected int tileX, tileY;
    protected int pixelX, pixelY;
    protected Sprite sprite;
    protected Image img;

    public Entity(int x, int y, Sprite sprite) {
        this.tileX = x;
        this.tileY = y;
        this.pixelX = this.tileX * SCALED_SIZE;
        this.pixelY = this.tileY * SCALED_SIZE;
        this.sprite = sprite;
        this.img = sprite.getFxImage();
    }

    public Rectangle2D getBorder() {
        return new Rectangle2D(pixelX, pixelY, SCALED_SIZE, SCALED_SIZE);
    }

    public void render(GraphicsContext graphicsContext) {
        graphicsContext.drawImage(img, pixelX, pixelY);
    }

    public abstract void update();

}
