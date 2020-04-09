package Canvases;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static Constants.JumperConstants.*;
import static Constants.JumperConstants.BUTTON_HEIGHT;

public class JumperMainMenuCanvas extends JPanel{

    public JumperMainMenuCanvas(Container panes){
        setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        setLayout(null);

        JButton startButton = new JButton();
        startButton.setBounds(100, 150, BUTTON_WIDTH, BUTTON_HEIGHT);
        startButton.setIcon(new ImageIcon(getClass().getResource("/buttons/startGameButton.png")));

        startButton.setOpaque(false);
        startButton.setContentAreaFilled(false);
        startButton.setBorderPainted(false);

        startButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                panes.removeAll();
                JPanel panel = new JumperGameCanvas(panes);
                panes.add(panel);
                panes.revalidate();
                panel.requestFocus();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                startButton.setIcon(new ImageIcon(getClass().getResource("/buttons/startGameButtonSelected.png")));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                startButton.setIcon(new ImageIcon(getClass().getResource("/buttons/startGameButton.png")));
            }
        });
        add(startButton);

        JButton recordsButton = new JButton();
        recordsButton.setBounds(100, 230, BUTTON_WIDTH, BUTTON_HEIGHT);
        recordsButton.setIcon(new ImageIcon(getClass().getResource("/buttons/recordsButton.png")));

        recordsButton.setOpaque(false);
        recordsButton.setContentAreaFilled(false);
        recordsButton.setBorderPainted(false);

        recordsButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                panes.removeAll();
                panes.add(new JumperRecordsCanvas(panes));
                panes.revalidate();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                recordsButton.setIcon(new ImageIcon(getClass().getResource("/buttons/recordsButtonSelected.png")));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                recordsButton.setIcon(new ImageIcon(getClass().getResource("/buttons/recordsButton.png")));
            }
        });

        add(recordsButton);

        JButton optionsButton = new JButton();
        optionsButton.setBounds(100, 310, BUTTON_WIDTH, BUTTON_HEIGHT);
        optionsButton.setIcon(new ImageIcon(getClass().getResource("/buttons/optionsButton.png")));

        optionsButton.setOpaque(false);
        optionsButton.setContentAreaFilled(false);
        optionsButton.setBorderPainted(false);

        optionsButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                panes.removeAll();
                panes.add(new JumperOptionCanvas(panes));
                panes.revalidate();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                optionsButton.setIcon(new ImageIcon(getClass().getResource("/buttons/optionsButtonSelected.png")));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                optionsButton.setIcon(new ImageIcon(getClass().getResource("/buttons/optionsButton.png")));
            }
        });
        add(optionsButton);


        JButton exitButton = new JButton();
        exitButton.setBounds(100, 390, BUTTON_WIDTH, BUTTON_HEIGHT);
        exitButton.setIcon(new ImageIcon(getClass().getResource("/buttons/exitButton.png")));

        exitButton.setOpaque(false);
        exitButton.setContentAreaFilled(false);
        exitButton.setBorderPainted(false);

        exitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                System.exit(0);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                exitButton.setIcon(new ImageIcon(getClass().getResource("/buttons/exitButtonSelected.png")));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                exitButton.setIcon(new ImageIcon(getClass().getResource("/buttons/exitButton.png")));
            }
        });

        add(exitButton);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image img = new ImageIcon(getClass().getResource("/backgrounds/backgroundMenu1.png")).getImage();
        g.drawImage(img, 0, 0, FRAME_WIDTH, FRAME_HEIGHT, null);
    }
}
