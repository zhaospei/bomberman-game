package texture;

import entity.animateentity.Brick;
import graphics.Sprite;

public class BrickTexture {
    public static Brick setBrick(int i, int j) {
        return new Brick(j, i, Sprite.BRICK[0]);
    }
}
