package Models.JumperModelElements;

import javax.swing.*;
import java.awt.*;


public class StandardBlock extends Block {
    private Image image = new ImageIcon("src/main/resources/Blocks/ground_grass.png").getImage();
    public StandardBlock(int x, int y) {
        super(x, y);
        type = "standard";
    }
}
