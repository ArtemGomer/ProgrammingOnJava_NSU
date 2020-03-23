import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PTBController extends JFrame {
    private Canvas canvas;
    private PTBModel ptbModel;

    private int delay = 1000;
    public static void main(String[] args) {
        new PTBController().startGame();
    }

    public PTBController(){
        setTitle("Pop the ball!");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        ptbModel = new PTBModel();
        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(600, 600));
        canvas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                ptbModel.deleteBall(e.getX(), e.getY());
                canvas.repaint();
            }
        });

        add(canvas);
        pack();
        setVisible(true);
    }

    private class Canvas extends JPanel implements ActionListener {

        Timer timer = new Timer(20, this);
        public Canvas(){
            timer.start();
        }

        @Override
        public void paint(Graphics g) {
            super.paint(g);
            ptbModel.paintBalls(g);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            ptbModel.moveBalls();
            repaint();
        }
    }

    private void startGame(){
        while(true){
            if (ptbModel.getBallsCount() > 5){
                break;
            } else {
                ptbModel.addRandomBall();
                canvas.repaint();
                System.out.println(delay);
                System.out.println(ptbModel.getScore());
                if (ptbModel.getScore() > 0 && ptbModel.getScore() % 10 == 0 && delay > 100){
                    delay = 1000 - 100 * (ptbModel.getScore() / 10);
                }
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException ignore){

                }
            }
        }
    }
}
