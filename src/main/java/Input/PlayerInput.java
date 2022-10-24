package Input;

import java.util.Set;
import Variables.Variables.DIRECTION;
import static Variables.Variables.DIRECTION.*;

public class PlayerInput implements KeyInput {

    public void initialization() {
        keyInput.put("A", false);
        keyInput.put("D", false);
        keyInput.put("W", false);
        keyInput.put("S", false);
        keyInput.put("SPACE", false);
    }

    @Override
    public DIRECTION handleKeyInput() {
        Set<String> keySet = keyInput.keySet();
        for (String code : keySet) {
            if (keyInput.get(code)) {
                switch (code) {
                    case ("W"):
                        return UP;
                    case ("D"):
                        return RIGHT;
                    case ("S"):
                        return DOWN;
                    case ("A"):
                        return LEFT;
                    case ("SPACE"):
                        return PLACEBOMB;
                }
            }
        }
        return NONE;
    }
}
