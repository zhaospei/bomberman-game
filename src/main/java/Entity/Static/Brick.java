package Entity.Static;

import Entity.Animate.AnimateEntity;
import Graphics.Sprite;

public class Brick extends StaticEntity {
    public Brick(int x, int y, Sprite sprite) {
        super(x,y,sprite);
        this.block = true;
    }

    @Override
    public void update() {

    }
}