package Entity.Static;

import Graphics.Sprite;
import Map.Map;

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

    public int getIncreasedSpeed() {
        return increasedSpeed;
    }

    public void setIncreasedSpeed(int increasedSpeed) {
        this.increasedSpeed = increasedSpeed;
    }
}
