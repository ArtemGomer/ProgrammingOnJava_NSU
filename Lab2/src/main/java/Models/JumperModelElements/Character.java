package Models.JumperModelElements;

import java.util.ArrayList;

import static Constants.JumperConstants.*;

public class Character {
    private int x, y, xSpeed, ySpeed;
    private boolean moveRight, moveLeft;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getXSpeed() {
        return xSpeed;
    }

    public int getYSpeed() {
        return ySpeed;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void move(ArrayList<Block> blocks) {
        if (isOnBlock(blocks)){
            ySpeed = DEFAULT_Y_SPEED;
        }
        if (x > FRAME_WIDTH) {
            x = 0;
        }
        if (x < -CHARACTER_WIDTH) {
            x = FRAME_WIDTH - CHARACTER_WIDTH;
        }
        if (moveRight) {
            if (xSpeed < MAX_X_SPEED){
                xSpeed++;
            }
        }
        if (moveLeft) {
            if (xSpeed > MIN_X_SPEED){
                xSpeed--;
            }
        }

        if (!moveLeft && !moveRight){
            if (xSpeed < 0){
                xSpeed++;
            } else if (xSpeed > 0){
                xSpeed--;
            }
        }

        if (y >= FRAME_HEIGHT - CHARACTER_HEIGHT) {
            ySpeed = DEFAULT_Y_SPEED;
        }
        x += xSpeed;
        y -= ySpeed;
        ySpeed -= ACCELERATION;
    }

    private boolean isOnBlock(ArrayList<Block> blocks){
        if (ySpeed <= 0) {
            for (Block block : blocks) {
                if ((x + CHARACTER_WIDTH > block.getX()) && (x < block.getX() + BLOCK_WIDTH)) {
                    if ((y + CHARACTER_HEIGHT < block.getY() + BLOCK_HEIGHT + INACCURACY) &&
                            (y + CHARACTER_HEIGHT > block.getY() - INACCURACY)) {
                        block.changeState();
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void toStartPosition(){
        this.x = FRAME_WIDTH / 2 - CHARACTER_WIDTH / 2;
        this.y = FRAME_HEIGHT - CHARACTER_HEIGHT - BLOCK_HEIGHT;
        this.xSpeed = DEFAULT_X_SPEED;
        this.ySpeed = DEFAULT_Y_SPEED;
    }

    public boolean isOnBottom(){
        return y >= FRAME_HEIGHT - CHARACTER_HEIGHT;
    }

    public void setMoveRight(boolean enable){
        moveRight = enable;
    }

    public void setMoveLeft(boolean enable){
        moveLeft = enable;
    }

}
