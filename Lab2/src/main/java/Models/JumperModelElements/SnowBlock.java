package Models.JumperModelElements;

public class SnowBlock extends Block {

    public SnowBlock(int x, int y) {
        super(x, y);
        type = "snow";
    }

    @Override
    public void changeState() {
        durability--;
    }

}
