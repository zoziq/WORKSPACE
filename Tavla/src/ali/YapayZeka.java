/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ali;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Dogru
 */
public interface YapayZeka {

    void setDiceNodeList(LinkedList diceNodeList, LinkedList secondDiceList, int benim_pullar[], int rakip_pullar[]);
    DiceNode getDice(); //seçilen zarı geri verir.
    void calculate();
    boolean buHamleOlurmu(int spike, int dice);
    int[] kos();
}
