package Texture;

import Entity.Static.Score;
import Game.MainGame;
import Graphics.Sprite;

public class ScoreTexture {
    public static Score setScore(char c, int i, int j) {
        switch (c) {
            case 'b':
                MainGame.setNewScore(100);
                return new Score(i, j, Sprite.Balloom_score);
            case 'o':
                MainGame.setNewScore(200);
                return new Score(i, j, Sprite.Oneal_score);
            case 'd':
                MainGame.setNewScore(400);
                return new Score(i, j, Sprite.Doll_score);
            case 'm':
                MainGame.setNewScore(800);
                return new Score(i, j, Sprite.Minvo_score);
            case 'k':
                MainGame.setNewScore(1000);
                return new Score(i, j, Sprite.Kondoria_score);
            case 'v':
                MainGame.setNewScore(2000);
                return new Score(i, j, Sprite.Ovapi_score);
            case 'p':
                MainGame.setNewScore(4000);
                return new Score(i, j, Sprite.Pass_score);
            case 'n':
                MainGame.setNewScore(8000);
                return new Score(i, j, Sprite.Pontan_score);
            default:
                return null;
        }
    }
}
