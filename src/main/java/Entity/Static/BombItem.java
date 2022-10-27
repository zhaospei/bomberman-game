package Entity.Static;

import Entity.Animate.Bomb;
import Graphics.Sprite;

public class BombItem extends Item{
    public BombItem(int x, int y, Sprite sprite) {
        super(x, y, sprite);
    }

    @Override
    public void update() {
        if (isActivated) {
            isActivated = false;
            Bomb.limit++;
        }
    }
}
