package Balls;

import java.awt.*;

public abstract class Ball {
    protected int X;
    protected int Y;
    protected int D;
    public Ball(int X, int Y, int D){
        this.X = X;
        this.Y = Y;
        this.D = D;
    }
    public abstract void paint(Graphics graphics);
    public abstract void move();

    public int getY() {
        return Y;
    }

    public int getX() {
        return X;
    }

    public int getD() {
        return D;
    }
}
