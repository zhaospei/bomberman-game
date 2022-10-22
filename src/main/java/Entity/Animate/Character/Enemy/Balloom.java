package Entity.Animate.Character.Enemy;

import Game.MainGame;
import Graphics.Sprite;
import Path.RandomPath;
import static Variables.Variables.*;

public class Balloom extends Enemy{
    public Balloom(int x, int y, Sprite sprite) {
        super(x, y, sprite);
        animation.put(DIRECTION.LEFT, Sprite.BALLOOM_LEFT);
        animation.put(DIRECTION.UP, Sprite.BALLOOM_LEFT);
        animation.put(DIRECTION.RIGHT, Sprite.BALLOOM_RIGHT);
        animation.put(DIRECTION.DOWN, Sprite.BALLOOM_RIGHT);
        animation.put(DIRECTION.DESTROYED, Sprite.BALLOOM_DESTROYED);
        currentAnimate = animation.get(DIRECTION.UP);
        defaultVel = 2;
    }
    @Override
    public void setDirection() {
        direction = new RandomPath().getRandomDirection();
        if (direction == DIRECTION.UP) {
            setVelocity(0, -defaultVel);
            currentAnimate = animation.get(DIRECTION.UP);
        }

        if (direction == DIRECTION.DOWN) {
            setVelocity(0, defaultVel);
            currentAnimate = animation.get(DIRECTION.DOWN);
        }

        if (direction == DIRECTION.LEFT) {
            setVelocity(-defaultVel, 0);
            currentAnimate = animation.get(DIRECTION.LEFT);
        }

        if (direction == DIRECTION.RIGHT) {
            setVelocity(defaultVel, 0);
            currentAnimate = animation.get(DIRECTION.RIGHT);
        }

        if (direction == DIRECTION.DESTROYED) {
            setVelocity(0, 0);
        }
    }

    @Override
    public void update() {
        setDirection();
        updateAnimation();
        move();
    }

}
