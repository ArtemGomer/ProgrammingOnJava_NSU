package Models.JumperModelElements;



public abstract class Block {
    protected int x, y, durability = 1;
    protected String type;
    public Block(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDurability() {
        return durability;
    }

    public void changeState(){
    }

    public String getType() {
        return type;
    }

}
