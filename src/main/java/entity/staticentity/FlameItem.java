package entity.staticentity;

import entity.animateentity.Flame;
import graphics.Sprite;

public class FlameItem extends Item{
    public FlameItem(int x, int y, Sprite sprite) {
        super(x, y, sprite);
    }

    @Override
    public void update() {
        if (isActivated) {
            isActivated = false;
            Flame.flameLength++;
        }
    }
}
