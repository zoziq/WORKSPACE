/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package JBackgammon11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dogru
 */
public class FUNC_2 implements YapayZeka {
    Arayuz arayuz;
    LinkedList<DiceNode> diceNodeList = new LinkedList<DiceNode>();
    LinkedList<DiceNode> secondDiceList = new LinkedList<DiceNode>();
    int benim_pullar[];  //board üzerindeki sütunlarda kaç pul bulunduğu tutulmaktadır.
    int rakip_pullar[];
    static int dice1;
    static int dice2;
    static int diceID;
    static int[] veri = new int[8];   //bunları verileri göndermek için kullanıyoruz

    public FUNC_2(Arayuz arayuz) {
        this.arayuz=arayuz;
    }
    
    @Override
    public void setDiceNodeList(LinkedList diceNodeList, LinkedList secondDiceList, int benim_pullar[], int rakip_pullar[]) {
        this.benim_pullar = benim_pullar;
        this.rakip_pullar = rakip_pullar;

//        for (Iterator it = diceNodeList.iterator(); it.hasNext();) {
//
//            DiceNode temp = (DiceNode) it.next();
//            this.diceNodeList.add(temp);
//        }

        this.diceNodeList = diceNodeList;
        this.secondDiceList = secondDiceList;
    }

    @Override
    public DiceNode getDice() {
        DiceNode node = null;
        for (Iterator it = diceNodeList.iterator(); it.hasNext();) {
            node = (DiceNode) it.next();
            if (diceID == node.getDiceID()) {
                return node;
            }
        }
        System.out.println("Zar listesi boş zar seçilemedi!");
        return node;
    }

    @Override
    public void calculate() {
        DiceNode secilenZar;
        System.out.println("dices");
        DiceNode avantajliZar = new DiceNode(KullanicidanDegerOku(), KullanicidanDegerOku(), -1);

        for (Iterator it = diceNodeList.iterator(); it.hasNext();) {
            secilenZar = (DiceNode) it.next();
            if ((secilenZar.getDice1() == avantajliZar.getDice1() && secilenZar.getDice2() == avantajliZar.getDice2())||(secilenZar.getDice2() == avantajliZar.getDice1() && secilenZar.getDice1() == avantajliZar.getDice2())) {
                avantajliZar.setDiceID(secilenZar.getDiceID());
            }
        }

        diceID = avantajliZar.getDiceID();
        dice1 = avantajliZar.getDice1();
        dice2 = avantajliZar.getDice2();
        
    }

    public void Play1(int baseSpike, int dice) {
        veri[0] = baseSpike;
        veri[1] = dice;
    }

    public void Play2(int baseSpike, int dice) {
        veri[2] = baseSpike;
        veri[3] = dice;
    }

    public void Play3(int baseSpike, int dice) {
        veri[4] = baseSpike;
        veri[5] = dice;
    }

    public void Play4(int baseSpike, int dice) {
        veri[6] = baseSpike;
        veri[7] = dice;
    }

    @Override
    public int[] kos() {
        calculate();
        System.out.println("dice1:" + dice1 + "dice2" + dice2);
        
        System.out.println("Play1 için:");
        Play1(KullanicidanDegerOku(), KullanicidanDegerOku());
        System.out.println("Play2 için:");
        Play2(KullanicidanDegerOku(), KullanicidanDegerOku());

        if (dice1 == dice2) {
            System.out.println("Play3 için:");
            Play3(KullanicidanDegerOku(), KullanicidanDegerOku());

            System.out.println("Play4 için:");
            Play4(KullanicidanDegerOku(), KullanicidanDegerOku());
        }

        return veri;
    }

    public int KullanicidanDegerOku() {  //klavyerden int değer almak için tanımalnmış oyunun işleyişini etkileyen bir metod değil
        Integer okunan = 0;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String line;
            System.out.println("Sayi gir:");
            line = reader.readLine();
            okunan = Integer.parseInt(line);
        } catch (IOException ex) {
            Logger.getLogger(JBackgammon.class.getName()).log(Level.SEVERE, null, ex);
        }
        return okunan;
    }

    @Override
    public boolean buHamleOlurmu(int spike, int dice) {
        return arayuz.buHamleOlurmu(spike, dice);
    }
}
