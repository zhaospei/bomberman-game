package entity.staticentity;
import graphics.Sprite;

public class Wall extends StaticEntity {
    public Wall(int x, int y, Sprite sprite) {
        super(x, y, sprite);
        block = true;
    }
}