package Balls;

import java.awt.*;
import java.util.Random;

public class WanderingBall extends Ball {
    private Color color;
    private int speedX, speedY;
    public WanderingBall(int X, int Y, int D) {
        super(X, Y, D);
        color = Color.blue;
        Random random = new Random();
        speedX = random.nextInt(20) - 10;
        speedY = random.nextInt(20) - 10;
    }

    @Override
    public void paint(Graphics graphics){
        graphics.setColor(color);
        graphics.fillOval(X, Y, D, D);
        graphics.setColor(Color.black);
        graphics.drawOval(X, Y, D, D);
    }

    @Override
    public void move(){
        if ((X >= 600 - D) || (X <= 0)){
            speedX *= -1;
        }
        if ((Y >= 600 - D) || (Y <= 0)){
            speedY *= -1;
        }
        X += speedX;
        Y += speedY;
    }
}
