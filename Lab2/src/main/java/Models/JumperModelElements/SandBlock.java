package Models.JumperModelElements;

public class SandBlock extends Block {

    public SandBlock(int x, int y) {
        super(x, y);
        durability = 2;
        type = "sand";
    }

    @Override
    public void changeState() {
        durability--;
    }

    public int getDurability() {
        return durability;
    }
}
