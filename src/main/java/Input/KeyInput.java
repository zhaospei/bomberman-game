package Input;

import java.util.HashMap;

import static Variables.Variables.DIRECTION;

public interface KeyInput {
    HashMap<String, Boolean> keyInput = new HashMap<>();

    public abstract void initialization();

    public abstract DIRECTION handleKeyInput();
}
