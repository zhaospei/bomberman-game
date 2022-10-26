package Entity.Static;

import Entity.Animate.AnimateEntity;
import Entity.Static.StaticEntity;
import Graphics.Sprite;

public class Brick extends StaticEntity {
    public Brick(int x, int y, Sprite sprite) {
        super(x,y,sprite);
        this.block = false;
    }

    @Override
    public void update() {

    }
}