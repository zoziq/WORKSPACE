/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ali;


import java.util.LinkedList;


/**
 *
 * @author Dogru
 */
public class Arayuz {

    YapayZeka araZeka;
    JBackgammon game;
    LinkedList<DiceNode> diceNodeList;
    LinkedList<DiceNode> secondDiceList;
    private int count[];  //board üzerindeki sütunlarda kaç pul bulunduğu tutulmaktadır.
    int type[];  //her sütundaki taşın rengi tutulur
    int current_player;  //white:1 black:2 olarak tanımlı
    int white = 1;
    int black = 2;
    int white_bar = 0;  //kırılmış taş oluduğu zaman burada tutuluyor.
    int black_bar = 0;
    int white_bear = 0;
    int black_bear = 0;
    int benim_pullar[];
    int rakip_pullar[];

    public void setBlack_bar_bearoff(int black_bar, int black_bear) {
        this.black_bar = black_bar;
        this.black_bear = black_bear;
    }

    public void setCount(int[] count) {
        this.count = count;
    }

    public void setCurrent_player(int current_player) {
        this.current_player = current_player;
    }

    public void setDiceNodeList(LinkedList<DiceNode> diceNodeList) {
        this.diceNodeList = diceNodeList;
    }
    
    public void setSecondDiceList(LinkedList<DiceNode> secondDiceList) {
        this.secondDiceList = secondDiceList;
    }

    public void setType(int[] type) {
        this.type = type;
    }

    public void setWhite_bar_bearoff(int white_bar, int white_bear) {
        this.white_bar = white_bar;
        this.white_bear = white_bear;
    }

    public int[] setBenimPullar(int zeka) {
        int[] benim_pullar = new int[27];
        if (current_player == white) {
            for (int i = 1; i < 25; i++) {
                if (type[i] == white) {
                    benim_pullar[i] = count[i];
                }
            }
            benim_pullar[25] = white_bar;
            benim_pullar[26] = white_bear;
        } else {
            int j=24;
            for (int i = 1; i < 25; i++) {
                if (type[i] == black) {
                    benim_pullar[j] = count[i];
                }
                j--;
            }
            benim_pullar[25] = black_bar;
            benim_pullar[26] = black_bear;
        }
        return benim_pullar;
    }

    public int[] setRakipPullar(int zeka) {
        int[] rakip_pullar = new int[27];
        if (current_player == white) {
            
            for (int i = 1; i < 25; i++) {
                if (type[i] == black) {
                    rakip_pullar[i] = count[i];
                }
                
            }
            rakip_pullar[25] = black_bar;
            rakip_pullar[26] = black_bear;
        } else {
            int j=24;
            for (int i = 1; i < 25; i++) {
                if (type[i] == white) {
                    rakip_pullar[j] = count[i];
                }
                j--;
            }
            rakip_pullar[25] = white_bar;
            rakip_pullar[26] = white_bear;
        }
        return rakip_pullar;
    }

    public boolean buHamleOlurmu(int spike, int dice){
        if(current_player==black){
            spike=25-spike;
        }
        return game.buHamleOlurmu(spike,dice);
    }

    public Arayuz(JBackgammon game) {
        this.game = game;
    }

    public void zekaSec(int zeka) {
        benim_pullar = setBenimPullar(zeka);
        rakip_pullar = setRakipPullar(zeka);
        
        if (zeka == 1) {
            araZeka = new FUNC_1(this);
            araZeka.setDiceNodeList(diceNodeList, secondDiceList,benim_pullar, rakip_pullar);
        } else if (zeka == 2) {
            araZeka = new FUNC_2(this);
            araZeka.setDiceNodeList(diceNodeList, secondDiceList,benim_pullar, rakip_pullar);
        }
    }


}