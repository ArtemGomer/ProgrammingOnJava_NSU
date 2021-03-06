package Canvases;

import Models.JumperModel;
import Models.JumperModelElements.Block;
import Observers.Observer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.prefs.Preferences;

import static Constants.JumperConstants.*;

public class JumperGameCanvas extends JPanel implements Observer {
    private final JumperModel model;
    private final Image character, standardBlock, sandNormalBlock, sandBrokenBlock, snowBlock, background;
    private final Container panes;
    private final JLabel score = new JLabel("0");

    public JumperGameCanvas(Container panes){
        setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        this.panes = panes;
        Preferences prefs = Preferences.userRoot().node("options");

        character = new ImageIcon(getClass().getResource("/characters/" + prefs.get("sex", "Male") + "_icon.png")).getImage();
        standardBlock = new ImageIcon(getClass().getResource("/blocks/" + prefs.get("block", "Grass") + "_icon.png")).getImage();
        sandNormalBlock = new ImageIcon(getClass().getResource("/blocks/ground_sand.png")).getImage();
        sandBrokenBlock = new ImageIcon(getClass().getResource("/blocks/ground_sand_broken.png")).getImage();
        snowBlock = new ImageIcon(getClass().getResource("/blocks/ground_snow.png")).getImage();
        background = new ImageIcon(getClass().getResource("/backgrounds/backgroundGame.png")).getImage();

        model = new JumperModel();
        model.addObserver(this);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case (KeyEvent.VK_RIGHT): {
                        model.setRightDirection(true);
                        break;
                    }
                    case (KeyEvent.VK_LEFT): {
                        model.setLeftDirection(true);
                        break;
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case (KeyEvent.VK_RIGHT): {
                        model.setRightDirection(false);
                        break;
                    }
                    case (KeyEvent.VK_LEFT): {
                        model.setLeftDirection(false);
                        break;
                    }
                }
            }
        });

        score.setBounds(0, 20, FRAME_WIDTH, 20);
        score.setHorizontalAlignment(SwingConstants.CENTER);
        score.setFont(new Font("Ink Free", Font.BOLD, 40));
        add(score);
        model.initGame();
    }

    @Override
    public void update() {
        repaint();
        if (model.isLost()){
            panes.removeAll();
            JPanel panel = new JumperRestartMenuCanvas(panes);
            panes.add(panel);
            panes.revalidate();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        score.setText(model.getScore().toString());
        g.drawImage(background, 0, 0, FRAME_WIDTH, FRAME_HEIGHT, null);
        for (Block block : model.getBlocks()){
            switch (block.getType()) {
                case "standard":
                    g.drawImage(standardBlock, block.getX(), block.getY(), BLOCK_WIDTH, BLOCK_HEIGHT, null);
                    break;
                case "sand":
                    if (block.getDurability() == 2) {
                        g.drawImage(sandNormalBlock, block.getX(), block.getY(), BLOCK_WIDTH, BLOCK_HEIGHT, null);
                    } else {
                        g.drawImage(sandBrokenBlock, block.getX(), block.getY(), BLOCK_WIDTH, BLOCK_HEIGHT, null);
                    }
                    break;
                case "snow":
                    g.drawImage(snowBlock, block.getX(), block.getY(), BLOCK_WIDTH, BLOCK_HEIGHT, null);
                    break;
            }
        }
        g.drawImage(character, model.getCharacter().getX(), model.getCharacter().getY(), CHARACTER_WIDTH, CHARACTER_HEIGHT, null);
    }
}
