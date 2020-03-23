package Balls;

import java.awt.*;

public class BadBall extends Ball {
    private Color color;
    public BadBall(int X, int Y, int D) {
        super(X, Y, D);
        color = Color.red;
    }
    @Override
    public void paint(Graphics graphics){
        graphics.setColor(color);
        graphics.fillOval(X, Y, D, D);
        graphics.setColor(Color.black);
        graphics.drawOval(X, Y, D, D);
    }

    @Override
    public void move() {
    }
}