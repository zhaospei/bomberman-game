package Input;

import java.util.Set;
import Variables.Variables.DIRECTION;
import static Variables.Variables.DIRECTION.*;

public class MenuInput implements KeyInput {

    public void initialization() {
        keyInput.put("W", false);
        keyInput.put("S", false);
        keyInput.put("ENTER", false);
    }

    @Override
    public DIRECTION handleKeyInput() {
        Set<String> keySet = keyInput.keySet();
        for (String code : keySet) {
            if (keyInput.get(code)) {
                switch (code) {
                    case ("W"):
                        return UP;
                    case ("S"):
                        return DOWN;
                    case ("ENTER"):
                        return DESTROYED;
                }
            }
        }
        return NONE;
    }
}
