package texture;

import entity.animateentity.character.Bomber;
import entity.animateentity.character.Character;
import entity.animateentity.character.enemy.*;
import graphics.Sprite;
import input.PlayerInput;

public class CharacterTexture {
    public static Character setCharacter(char c, int i, int j) {
        switch (c) {
            case 'p':
                return new Bomber(j, i, Sprite.PLAYER_DOWN[0], new PlayerInput());
            case '1':
                return new Balloom(j, i, Sprite.BALLOOM_LEFT[0]);
            case '2':
                return new Oneal(j, i, Sprite.ONEAL_LEFT[0]);
            case '3':
                return new Doll(j, i, Sprite.DOLL_LEFT[0]);
            case '4':
                return new Minvo(j, i, Sprite.MINVO_LEFT[0]);
            case '5':
                return new Kondoria(j, i, Sprite.KONDORIA_LEFT[0]);
            default:
                return null;
        }
    }
}