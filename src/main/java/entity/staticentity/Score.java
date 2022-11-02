package entity.staticentity;

import graphics.Sprite;

public class Score extends StaticEntity{
    protected int displayTime = 30;
    public Score(int x, int y, Sprite sprite) {
        super(x, y, sprite);
    }

    @Override
    public void update() {
        if (displayTime > 0) {
            displayTime--;
        }
        else this.remove();
    }
}
