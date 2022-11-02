package texture;

import entity.staticentity.Score;
import game.MainGame;
import graphics.Sprite;

public class ScoreTexture {
    public static Score setScore(char c, int i, int j) {
        switch (c) {
            case 'b':
                MainGame.setNewScore(100);
                return new Score(i, j, Sprite.balloom_score);
            case 'o':
                MainGame.setNewScore(200);
                return new Score(i, j, Sprite.oneal_score);
            case 'd':
                MainGame.setNewScore(400);
                return new Score(i, j, Sprite.doll_score);
            case 'm':
                MainGame.setNewScore(800);
                return new Score(i, j, Sprite.minvo_score);
            case 'k':
                MainGame.setNewScore(1000);
                return new Score(i, j, Sprite.kondoria_score);
            case 'v':
                MainGame.setNewScore(2000);
                return new Score(i, j, Sprite.ovapi_score);
            case 'p':
                MainGame.setNewScore(4000);
                return new Score(i, j, Sprite.pass_score);
            case 'n':
                MainGame.setNewScore(8000);
                return new Score(i, j, Sprite.pontan_score);
            default:
                return null;
        }
    }
}
