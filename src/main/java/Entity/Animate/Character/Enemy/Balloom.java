package Entity.Animate.Character.Enemy;

import Game.MainGame;
import Graphics.Sprite;
import Map.Map;
import Path.RandomPath;
import Variables.Variables.*;
import static Variables.Variables.DIRECTION.*;
import static Graphics.Sprite.*;

public class Balloom extends Enemy{
    public Balloom(int x, int y, Sprite sprite) {
        super(x, y, sprite);
        animation.put(LEFT, BALLOOM_LEFT);
        animation.put(UP, BALLOOM_LEFT);
        animation.put(RIGHT, BALLOOM_RIGHT);
        animation.put(DOWN, BALLOOM_RIGHT);
        animation.put(DESTROYED, BALLOOM_DESTROYED);
        currentAnimate = animation.get(UP);
        this.speed = 0;
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
        if (direction != NONE) {
            currentAnimate = animation.get(direction);
            updateAnimation();
        }
    }

}
