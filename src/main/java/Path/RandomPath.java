package Path;

import Variables.Variables.DIRECTION;

import java.util.Random;
import static Variables.Variables.DIRECTION.*;

public class RandomPath implements Path{
    public DIRECTION getRandomDirection() {
        int random = new Random().nextInt(4);
        switch (random) {
            case 0:
                return UP;
            case 1:
                return DOWN;
            case 2:
                return LEFT;
            case 3:
                return RIGHT;
            default:
                return DESTROYED;
        }
    }
}
