/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package JBackgammon11;

/**
 *
 * @author Dogru
 */
class DiceNode {
    private int dice1,dice2,DiceID;
    
    public DiceNode(int dice1, int dice2, int DiceID) {
        this.dice1 = dice1;
        this.dice2 = dice2;
        this.DiceID = DiceID;
    }
    
    public int getDiceID() {
        return DiceID;
    }

    public void setDiceID(int DiceID) {
        this.DiceID = DiceID;
    }

    public int getDice1() {
        return dice1;
    }
    
    public void setDice1(int dice1) {
        this.dice1 = dice1;
    }

    public int getDice2() {
        return dice2;
    }

    public void setDice2(int dice2) {
        this.dice2 = dice2;
    }
}
