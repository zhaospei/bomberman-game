package entity.staticentity;

import graphics.Sprite;

public class Portal extends StaticEntity {
    private boolean accessAble = false;
    public Portal(int x, int y, Sprite sprite) {
        super(x, y, sprite);
        setBlock(true);
    }

    public boolean isAccessAble() {
        return accessAble;
    }

    @Override
    public void update() {
        if(map.getEnemies().size() == 0 && !isBlock()) {
            accessAble = true;
        }
    }
}
