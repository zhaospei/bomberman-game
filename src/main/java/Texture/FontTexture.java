package Texture;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import static Variables.Variables.FONT_URLS;

public class FontTexture {
    public static Text createText(String string, int pos, int size, Color color) {
        Font font = Font.loadFont(FONT_URLS[pos], size);
        Text text = new Text(string);
        text.setFont(font);
        text.setFill(color);
        return text;
    }
}
