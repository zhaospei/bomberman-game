package Entity.Animate.Character.Enemy;

import Entity.Animate.Character.Character;
import Graphics.Sprite;
import Path.RandomPath;

import static Variables.Variables.DIRECTION.*;

public abstract class Enemy extends Character {
    public Enemy(int x, int y, Sprite sprite) {
        super(x, y, sprite);
    }

    @Override
    public void setDirection() {
        direction = new RandomPath().getRandomDirection();
        switch (direction) {
            case UP:
                this.setVelocity(0,-defaultVel);
                currentAnimate = animation.get(LEFT);
                break;
            case DOWN:
                this.setVelocity(0, defaultVel);
                currentAnimate = animation.get(RIGHT);
                break;
            case LEFT:
                this.setVelocity(-defaultVel,0);
                currentAnimate = animation.get(LEFT);
                break;
            case RIGHT:
                this.setVelocity(defaultVel, 0);
                currentAnimate = animation.get(RIGHT);
                break;
            default:
                break;
        }

    }
}
