package Entity.Static;

import Graphics.Sprite;

public class Item extends StaticEntity{
    protected boolean isActivated = false;
    public Item(int x, int y, Sprite sprite) {
        super(x, y, sprite);
        setBlock(true);
    }

    public boolean isActivated() {
        return isActivated;
    }

    public void setActivated(boolean activated) {
        isActivated = activated;
    }

    @Override
    public void update() {

    }

    public void delete() {
        map.setTile(this.tileY, this.tileX, new Grass(this.tileX, this.tileY, Sprite.grass));
    }
}
