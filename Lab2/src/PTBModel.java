import Balls.BadBall;
import Balls.Ball;
import Balls.ClassicBall;
import Balls.WanderingBall;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class PTBModel {
    private ArrayList<Ball> balls = new ArrayList<>();
    private int score = 0;
    public void addRandomBall(){
        Random random = new Random();
        int type = random.nextInt(3);
        int d = random.nextInt(20) + 60;
        int x = random.nextInt(600 - d);
        int y = random.nextInt(600 - d);
        if (type == 0){
            balls.add(new ClassicBall(x, y, d));
        } else if (type == 1){
            balls.add(new BadBall(x, y, d));
        } else {
            balls.add(new WanderingBall(x, y, d));
        }
    }

    public void paintBalls(Graphics g){
        for (Ball ball : balls){
            ball.paint(g);
        }
    }

    public void moveBalls(){
        for (Ball ball : balls){
            ball.move();
        }
    }

    public void deleteBall(int x, int y){
        for (int i = balls.size() - 1; i > -1; i--) {
            double dx = balls.get(i).getX() + balls.get(i).getD()/2d - x;
            double dy = balls.get(i).getY()  + balls.get(i).getD()/2d - y;
            double d = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
            if (d < balls.get(i).getD()/2d) {
                balls.remove(i);
                score++;
                break;
            }
        }
    }

    public int getBallsCount(){
        return balls.size();
    }

    public int getScore() {
        return score;
    }
}
