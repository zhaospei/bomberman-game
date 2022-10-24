package Texture;

import Entity.Animate.Character.Bomber;
import Entity.Animate.Character.Character;
import Entity.Animate.Character.Enemy.*;
import Entity.Entity;
import Graphics.Sprite;
import Input.PlayerInput;

public class CharacterTexture {
    public static Character setCharacter(char c, int i, int j) {
        switch (c) {
            case 'p':
                return new Bomber(i, j, Sprite.PLAYER_RIGHT[0], new PlayerInput());
            case '1':
                return new Balloom(j, i, Sprite.BALLOOM_RIGHT[0]);
            default:
                return null;
        }
    }
}