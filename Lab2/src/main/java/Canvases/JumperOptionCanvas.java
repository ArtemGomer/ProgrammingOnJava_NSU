package Canvases;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.prefs.Preferences;

import static Constants.JumperConstants.*;

public class JumperOptionCanvas extends JPanel {

    public JumperOptionCanvas(Container container){

        Preferences prefs = Preferences.userRoot().node("options");
        setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        setLayout(null);

        JButton backToMenu = new JButton();
        backToMenu.setBounds(170, 520, BUTTON_WIDTH, BUTTON_HEIGHT);
        backToMenu.setIcon(new ImageIcon(getClass().getResource("/buttons/mainMenuButton.png")));
        backToMenu.setOpaque(false);
        backToMenu.setContentAreaFilled(false);
        backToMenu.setBorderPainted(false);

        backToMenu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                container.removeAll();
                container.add(new MainMenuCanvas(container));
                container.revalidate();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                backToMenu.setIcon(new ImageIcon(getClass().getResource("/buttons/mainMenuButtonSelected.png")));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                backToMenu.setIcon(new ImageIcon(getClass().getResource("/buttons/mainMenuButton.png")));
            }
        });
        add(backToMenu);

        JRadioButton radioMale = new JRadioButton("Male");
        radioMale.setFont(new Font("Ink Free", Font.BOLD, 15));
        radioMale.setBounds(50, 200, 80, 20);
        radioMale.setOpaque(false);
        JRadioButton radioFemale = new JRadioButton("Female");
        radioFemale.setFont(new Font("Ink Free", Font.BOLD, 15));
        radioFemale.setBounds(150, 200, 80, 20);
        radioFemale.setOpaque(false);
        ButtonGroup sex = new ButtonGroup();
        sex.add(radioFemale);
        sex.add(radioMale);
        JLabel characterIcon = new JLabel(new ImageIcon(getClass().getResource("/characters/" + prefs.get("sex", "Male") + "_icon.png")));
        characterIcon.setBounds(280, 154, CHARACTER_WIDTH, CHARACTER_HEIGHT);

        if (prefs.get("sex", "Male").equals("Male")){
            radioMale.setSelected(true);
        } else {
            radioFemale.setSelected(true);
        }

        radioMale.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                characterIcon.setIcon(new ImageIcon(getClass().getResource("/characters/Male_icon.png")));
                prefs.put("sex", "Male");
            }
        });

        radioFemale.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                characterIcon.setIcon(new ImageIcon(getClass().getResource("/characters/Female_icon.png")));
                prefs.put("sex", "Female");
            }
        });
        add(radioMale);
        add(radioFemale);
        add(characterIcon);

        JRadioButton radioGrass = new JRadioButton("Grass");
        radioGrass.setFont(new Font("Ink Free", Font.BOLD, 15));
        radioGrass.setBounds(50, 300, 80, 20);
        radioGrass.setOpaque(false);
        JRadioButton radioCake = new JRadioButton("Cake");
        radioCake.setFont(new Font("Ink Free", Font.BOLD, 15));
        radioCake.setBounds(150, 300, 80, 20);
        radioCake.setOpaque(false);
        ButtonGroup blocks = new ButtonGroup();
        blocks.add(radioCake);
        blocks.add(radioGrass);
        JLabel blockIcon = new JLabel(new ImageIcon(getClass().getResource("/blocks/" + prefs.get("block", "Grass") + "_icon.png")));
        blockIcon.setBounds(260, 300, 80, 20);

        if (prefs.get("block", "Grass").equals("Grass")){
            radioGrass.setSelected(true);
        } else {
            radioCake.setSelected(true);
        }

        radioGrass.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                blockIcon.setIcon(new ImageIcon(getClass().getResource("/blocks/Grass_icon.png")));
                prefs.put("block", "Grass");
            }
        });

        radioCake.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                blockIcon.setIcon(new ImageIcon(getClass().getResource("/blocks/Cake_icon.png")));
                prefs.put("block", "Cake");
            }
        });

        add(radioGrass);
        add(radioCake);
        add(blockIcon);

        JLabel background = new JLabel();
        background.setBounds(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
        background.setIcon(new ImageIcon(getClass().getResource("/backgrounds/backgroundGame.png")));
        add(background);
    }
}
