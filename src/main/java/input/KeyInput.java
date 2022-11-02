package input;

import java.util.HashMap;

import static variables.Variables.DIRECTION;

public interface KeyInput {
    HashMap<String, Boolean> keyInput = new HashMap<>();

    public abstract void initialization();

    public abstract DIRECTION handleKeyInput();
}
