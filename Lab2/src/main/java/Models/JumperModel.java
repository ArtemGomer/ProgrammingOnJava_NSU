package Models;

import Models.JumperModelElements.*;
import Models.JumperModelElements.Character;
import Observers.Observable;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;
import java.util.prefs.Preferences;

import static Constants.JumperConstants.*;

public class JumperModel implements Observable {
    private Character character;
    private ArrayList<Block> blocks;
    private boolean lost;
    private Integer score = 0;

    public JumperModel() {
        character = new Character();
        blocks = new ArrayList<>();
    }

    public void addBlock() {
        Random random = new Random();
        int x = random.nextInt(FRAME_WIDTH - BLOCK_WIDTH - 2 * BORDER_DISTANCE) + BORDER_DISTANCE;
        int y = blocks.get(blocks.size() - 1).getY() - MIN_DISTANCE - random.nextInt(MAX_JUMP_HEIGHT - MIN_DISTANCE);
        int typeOfBlock = random.nextInt(TYPE_RANGE);
        if (typeOfBlock <= STANDARD_RANGE) {
            blocks.add(new StandardBlock(x, y));
        } else if (typeOfBlock <= SAND_RANGE) {
            blocks.add(new SandBlock(x, y));
        } else {
            blocks.add(new SnowBlock(x, y));
        }
    }

    private void moveBlocks() {
        if (character.getY() < FRAME_HEIGHT / 2 && character.getYSpeed() > 0) {
            character.setY(FRAME_HEIGHT / 2);
            score += character.getYSpeed();
            for (Block block : blocks) {
                block.setY(block.getY() + character.getYSpeed());
            }
        }
    }

    private void replaceBlocks() {
        blocks.removeIf(block -> block.getY() > FRAME_HEIGHT || block.getDurability() == 0);
        while (blocks.size() < MAX_AMOUNT_OF_BLOCKS) {
            addBlock();
        }
    }

    public boolean makeGameStep() {
        notifyObservers();
        character.move(blocks);
        moveBlocks();
        replaceBlocks();
        if (character.isOnBottom()) {
            lost = true;
            saveScore();
        }
        return lost;
    }

    public void initGame() {
        score = 0;
        character.toStartPosition();
        blocks.add(new StandardBlock(FRAME_WIDTH / 2 - BLOCK_WIDTH / 2, FRAME_HEIGHT - 20));
        for (int i = 0; i < MAX_AMOUNT_OF_BLOCKS; i++) {
            addBlock();
        }
    }

    public ArrayList<Block> getBlocks() {
        return blocks;
    }

    public Character getCharacter() {
        return character;
    }

    public void keyPressed(KeyEvent e) {
        character.keyPressed(e);
    }

    public void keyReleased(KeyEvent e) {
        character.keyReleased(e);
    }

    public Integer getScore() {
        return score/100;
    }

    public void saveScore(){
        Preferences prefs = Preferences.userRoot().node("records");
        prefs.putInt("0", getScore());

        for (int pos = 10; pos > 0; pos--){
            if (prefs.getInt(Integer.valueOf(pos).toString(), 0) >= getScore()){
                if (pos + 1 <= 10){
                    for (int shift = 10; shift >= pos + 2; shift--){
                        prefs.putInt(Integer.valueOf(shift).toString(), prefs.getInt(Integer.valueOf(shift - 1).toString(), 0));
                    }
                    prefs.putInt(Integer.valueOf(pos + 1).toString(), getScore());
                    return;
                }
            }
        }
        for (int shift = 10; shift >= 2; shift--){
            prefs.putInt(Integer.valueOf(shift).toString(), prefs.getInt(Integer.valueOf(shift - 1).toString(), 0));
        }
        prefs.putInt(Integer.valueOf(1).toString(), getScore());
    }
}