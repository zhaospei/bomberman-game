package entity.staticentity;

import graphics.Sprite;

public class SpeedItem extends Item {
    public static int increasedSpeed = 3;

    public SpeedItem(int x, int y, Sprite sprite) {
        super(x, y, sprite);
    }

    @Override
    public void update() {
        if (isActivated) {
            isActivated = false;
            increasedSpeed++;
        }
    }
}
