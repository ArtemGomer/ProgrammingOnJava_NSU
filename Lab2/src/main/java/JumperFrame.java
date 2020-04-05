import Canvases.*;

import javax.swing.*;
import java.awt.*;


public class JumperFrame extends JFrame {

    public static void main(String[] args) {
        try {
            String systemLookAndFeelClassName = UIManager.getSystemLookAndFeelClassName();
            UIManager.setLookAndFeel(systemLookAndFeelClassName);
        } catch (UnsupportedLookAndFeelException e) {
            System.err.println("Can't use the specified look and feel on this platform.");
        } catch (Exception e) {
            System.err.println("Couldn't get specified look and feel, for some reason.");
        }
        new JumperFrame();
    }

    private JumperFrame() {
        setTitle("Jumper");
        setIconImage(new ImageIcon(getClass().getResource("/characters/Male_icon.png")).getImage());
        setBackground(Color.BLACK);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().add(new MainMenuCanvas(getContentPane()));
        getContentPane().revalidate();
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }
}
