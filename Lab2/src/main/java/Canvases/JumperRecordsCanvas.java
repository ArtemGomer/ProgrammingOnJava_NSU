package Canvases;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.prefs.Preferences;

import static Constants.JumperConstants.*;

public class JumperRecordsCanvas extends JPanel {
    public JumperRecordsCanvas(Container panes){
        setLayout(null);
        Preferences prefs = Preferences.userRoot().node("records");
        for (int i = 0; i < 10; i++){
            JLabel label = new JLabel(Integer.valueOf(i + 1).toString() + ":   " + prefs.get(Integer.valueOf(i + 1).toString(), "0"));
            label.setFont(new Font("Ink Free", Font.BOLD, 22));
            label.setHorizontalAlignment(SwingConstants.LEFT);
            label.setBounds(50, 150 + 30 * i, BUTTON_WIDTH,  BUTTON_HEIGHT);
            add(label);
        }

        JButton backToMenu = new JButton();
        backToMenu.setBounds(170, 520, BUTTON_WIDTH, BUTTON_HEIGHT);
        backToMenu.setIcon(new ImageIcon(getClass().getResource("/buttons/mainMenuButton.png")));
        backToMenu.setOpaque(false);
        backToMenu.setContentAreaFilled(false);
        backToMenu.setBorderPainted(false);

        backToMenu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                panes.removeAll();
                panes.add(new JumperMainMenuCanvas(panes));
                panes.revalidate();
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

        JButton clearRecords = new JButton(new ImageIcon(getClass().getResource("/buttons/red_boxCross.png")));
        clearRecords.setBounds(50, 520, 36, 36);
        clearRecords.setOpaque(false);
        clearRecords.setContentAreaFilled(false);
        clearRecords.setBorderPainted(false);

        clearRecords.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                try {
                    Preferences.userRoot().node("records").removeNode();
                } catch (Exception ignored) {
                }
                panes.removeAll();
                panes.add(new JumperRecordsCanvas(panes));
                panes.revalidate();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                clearRecords.setIcon(new ImageIcon(getClass().getResource("/buttons/red_boxCrossSelected.png")));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                clearRecords.setIcon(new ImageIcon(getClass().getResource("/buttons/red_boxCross.png")));
            }
        });
        add(clearRecords);
        add(backToMenu);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Preferences prefs = Preferences.userRoot().node("options");
        Image image = new ImageIcon(getClass().getResource("/characters/" + prefs.get("sex", "Male")+"_ready.png")).getImage();
        g.drawImage(new ImageIcon(getClass().getResource("/backgrounds/backgroundRecords.png")).getImage(), 0, 0, FRAME_WIDTH, FRAME_HEIGHT, null);
        g.drawImage(image, 200, 200, image.getWidth(null), image.getHeight(null), null);
    }
}
