package Texture;

import Entity.Animate.Brick;
import Graphics.Sprite;

public class BrickTexture {
    public static Brick setBrick(int i, int j) {
        return new Brick(j, i, Sprite.BRICK[0]);
    }
}
