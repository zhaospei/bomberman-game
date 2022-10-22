package Path;

import Variables.Variables;

import java.util.Random;
import static Variables.Variables.*;

public class RandomPath implements Path{
    public DIRECTION getRandomDirection() {
        int random = new Random().nextInt(4);
        switch (random) {
            case 0:
                return DIRECTION.UP;
            case 1:
                return DIRECTION.DOWN;
            case 2:
                return DIRECTION.LEFT;
            case 3:
                return DIRECTION.RIGHT;
            default:
                return DIRECTION.DESTROYED;
        }
    }
}
