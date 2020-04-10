package Canvases;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.prefs.Preferences;

import static Constants.JumperConstants.*;

public class JumperRestartMenuCanvas extends JPanel {
    public JumperRestartMenuCanvas(Container panes){
        setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        setLayout(null);
        JButton restartButton = new JButton(new ImageIcon(getClass().getResource("/buttons/button_blue_repeat.png")));
        restartButton.setOpaque(false);
        restartButton.setBorderPainted(false);
        restartButton.setContentAreaFilled(false);

        restartButton.setBounds(136, 200, 128, 128);
        restartButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                restartButton.setIcon(new ImageIcon(getClass().getResource("/buttons/button_blue_repeatSelected.png")));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                restartButton.setIcon(new ImageIcon(getClass().getResource("/buttons/button_blue_repeat.png")));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                panes.removeAll();
                JPanel panel = new JumperGameCanvas(panes);
                panes.add(panel);
                panes.revalidate();
                panel.requestFocus();
            }
        });

        JButton backToMenu = new JButton(new ImageIcon(getClass().getResource("/buttons/homeButton.png")));
        backToMenu.setOpaque(false);
        backToMenu.setContentAreaFilled(false);
        backToMenu.setBorderPainted(false);

        backToMenu.setBounds(300, 500, 60, 60);
        backToMenu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                panes.removeAll();
                panes.add(new JumperMainMenuCanvas(panes));
                panes.revalidate();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                backToMenu.setIcon(new ImageIcon(getClass().getResource("/buttons/homeButtonSelected.png")));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                backToMenu.setIcon(new ImageIcon(getClass().getResource("/buttons/homeButton.png")));
            }
        });

        Preferences prefs = Preferences.userRoot().node("records");
        JLabel score = new JLabel("Your score = " + prefs.get("0","0"));
        score.setFont(new Font("Ink Free", Font.BOLD, 30));
        score.setHorizontalAlignment(SwingConstants.CENTER);
        score.setBounds(0, 15, 400, 25);

        add(score);
        add(backToMenu);
        add(restartButton);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Preferences prefs = Preferences.userRoot().node("options");
        Image image = new ImageIcon(getClass().getResource("/characters/" + prefs.get("sex", "Male") + "_hurt.png")).getImage();
        g.drawImage(new ImageIcon(getClass().getResource("/backgrounds/backgroundRestart.png")).getImage(), 0, 0, FRAME_WIDTH, FRAME_HEIGHT, null);
        g.drawImage(image, 125, 350, image.getWidth(null), image.getHeight(null), null);
    }
}
