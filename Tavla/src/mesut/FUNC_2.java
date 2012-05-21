/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mesut;

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
        int[] __benim_pullar = benim_pullar;
        int[] __rakip_pullar = rakip_pullar;
        DiceNode __oynanacak_zar = null;
        
        if (__benim_pullar[25] != 0){
            __oynanacak_zar = kirik_tas_icin_zar_sec(__benim_pullar, __rakip_pullar, __oynanacak_zar);
            dice1 = __oynanacak_zar.getDice1();
            dice2 = __oynanacak_zar.getDice2();
            diceID = __oynanacak_zar.getDiceID();
            int kirik_pul = __benim_pullar[25];
            int retval = kirik_pul_icin_oyna(__benim_pullar, __rakip_pullar, __oynanacak_zar, 0, 0);
            if (kirik_pul == 1){
                normal_oyna(__benim_pullar, __rakip_pullar, __oynanacak_zar, 2, retval);
                return veri;
            }
            else if (kirik_pul > 1)
                return veri;
            
            
        }
        
        /*else if (taslari_topla(__benim_pullar)){
            __oynanacak_zar = tas_toplama_icin_zar_sec(__benim_pullar, __oynanacak_zar);
            tas_toplama_icin_oyna(__benim_pullar, __oynanacak_zar);
            return veri;
                    
        }*/
        else{
            __oynanacak_zar = normal_zar_sec(__benim_pullar, __rakip_pullar, __oynanacak_zar);
            dice1 = __oynanacak_zar.getDice1();
            dice2 = __oynanacak_zar.getDice2();
            diceID = __oynanacak_zar.getDiceID();
        }
        
        if (taslari_topla(__benim_pullar)){
            if (__oynanacak_zar.getDice1() != __oynanacak_zar.getDice2()){
                tas_topla_tek(__benim_pullar, __oynanacak_zar, 0, 0);
                return veri;
            }
        }
        
        System.out.println(__oynanacak_zar.getDiceID());
        
        if (__oynanacak_zar.getDice1() == __oynanacak_zar.getDice2())
            cift_oyna(__benim_pullar, __rakip_pullar, __oynanacak_zar, 0);
        
        
        else
            normal_oyna(__benim_pullar, __rakip_pullar, __oynanacak_zar, 0, 0);
            
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

    public DiceNode kirik_tas_icin_zar_sec(int[] __benim_pullar, int[] __rakip_pullar, DiceNode __oynanacak_zar) {
        LinkedList<Integer> olasi_yerler = new LinkedList<Integer>();
        int zar_1;
        
        for (int j = 1; j < 7; j++) {
            if (__rakip_pullar[j] < 2 && __rakip_pullar[j] >0)
                olasi_yerler.add(j);
        }
                
        for (int j = 1; j < 7; j++) {   
            if (__rakip_pullar[j] == 0)
                olasi_yerler.add(j);
        }
            
        LinkedList<DiceNode> olasi_zarlar = new LinkedList<DiceNode>();
        
        if (olasi_yerler.size() > 0){
            for (DiceNode dn: diceNodeList){
                //TODO: Daha iyi yerleri kontrol et 
                if (__benim_pullar[25] > 1 && (dn.getDice1() == olasi_yerler.get(0) && dn.getDice2() == olasi_yerler.get(1)) || (dn.getDice1() == olasi_yerler.get(1) && dn.getDice2() == olasi_yerler.get(0))){
                    olasi_zarlar.add(dn);
                }
                else if (dn.getDice1() == olasi_yerler.get(0) || dn.getDice2() == olasi_yerler.get(0))
                    olasi_zarlar.add(dn);
           
            }
        }
        
        if (olasi_zarlar.size() > 0)
            //TODO: en iyi ikinci zara bak
            return olasi_zarlar.get(0);

       return null;
    }

    private DiceNode normal_zar_sec(int[] __benim_pullar, int[] __rakip_pullar, DiceNode __oynanacak_zar) {
        LinkedList<DiceNode> olasi_zarlar = new LinkedList<DiceNode>();

        
        int toplam_zar = 0;
        DiceNode seciliZar = null;
       
        for (DiceNode dn: diceNodeList){
            if (dn.getDice1() == dn.getDice2()){
                if (dn.getDice1()*4 > toplam_zar){
                     seciliZar = dn;
                     toplam_zar = dn.getDice1()*4;
                }
            }
            if (dn.getDice1()+dn.getDice2() > toplam_zar) {
                seciliZar = dn;
                toplam_zar = dn.getDice1()+dn.getDice2();
            }               
        }       
        if (seciliZar != null)
            return seciliZar;       
         return null;
    }

    private void cift_oyna(int[] __benim_pullar, int[] __rakip_pullar, DiceNode __oynanacak_zar, int sira) {
        // iki pul varsa onları oyna, yoksa tek tek yolla, daha fazla iyileştirilebilir.
        int oyn_zar = __oynanacak_zar.getDice1();
        int oynanacak_pul = 1;
       
        for (int i = 1; i < 25; i++) {
            if (__benim_pullar[i] > 0){
                if (i+oyn_zar > 24){

                    if (sira == 8)
                        return;
                    oynanacak_pul = i;
                    veri[sira] = oynanacak_pul;
                    veri[sira+1] = oyn_zar;
                    __benim_pullar[i]--;
                        cift_oyna(__benim_pullar, __rakip_pullar, __oynanacak_zar, sira+2);
                    
                    break;
                  }
                
                else if (__rakip_pullar[i+oyn_zar] < 2){
                    if (sira == 8)
                        return;
                    oynanacak_pul = i;
                    veri[sira] = oynanacak_pul;
                    veri[sira+1] = oyn_zar;
                    __benim_pullar[i]--;
                    __benim_pullar[i+oyn_zar]++;
                        cift_oyna(__benim_pullar, __rakip_pullar, __oynanacak_zar, sira+2);
                    
                    break;
                  }
            }
        }
        
       
   }

    private void normal_oyna(int[] __benim_pullar, int[] __rakip_pullar, DiceNode __oynanacak_zar, int sira, int ham) {

        
        int pul1=1, pul2=1;
        
        int zar1 = __oynanacak_zar.getDice1();
        int zar2 = __oynanacak_zar.getDice2();
        
        for (int i = 1; i < 25; i++){
            if (i+zar1 > 24 || i+zar2 > 24){
                if (__benim_pullar[i] > 0 && ham != 1){ 
                if (sira == 4)
                    return;
                veri[sira] = i;
                veri[sira+1] = zar1;
                __benim_pullar[i]--;
  
                    
                    normal_oyna(__benim_pullar, __rakip_pullar, __oynanacak_zar, sira+2, 1);

                break;
            }
                
                
            
             else if (__benim_pullar[i] > 0  && ham != 2){
                if (sira == 4)
                    return;
                veri[sira] = i;
                veri[sira+1] = zar2;
                __benim_pullar[i]--;

                    normal_oyna(__benim_pullar, __rakip_pullar, __oynanacak_zar, sira+2, 2);
                break;
            }
        }
        
        else{
            if (__benim_pullar[i] > 0 && __rakip_pullar[i+zar1] < 2 && ham != 1){ 
                if (sira == 4)
                    return;
                veri[sira] = i;
                veri[sira+1] = zar1;
                __benim_pullar[i]--;
                __benim_pullar[i+zar1]++;
  
                    
                    normal_oyna(__benim_pullar, __rakip_pullar, __oynanacak_zar, sira+2, 1);

                break;
            }
            else if (__benim_pullar[i] > 0 && __rakip_pullar[i+zar2] < 2 && ham != 2){
                if (sira == 4)
                    return;
                veri[sira] = i;
                veri[sira+1] = zar2;
                __benim_pullar[i]--;
                __benim_pullar[i+zar2]++;

                    normal_oyna(__benim_pullar, __rakip_pullar, __oynanacak_zar, sira+2, 2);
                break;
            }
            }
        }
    }

    private int kirik_pul_icin_oyna(int[] __benim_pullar, int[] __rakip_pullar, DiceNode __oynanacak_zar, int sira, int zar) {
        for (int i = 0; i < __benim_pullar[25]; i++) {
            if (__rakip_pullar[__oynanacak_zar.getDice1()] < 2 && zar != 1){
                if (__oynanacak_zar.getDice1() != __oynanacak_zar.getDice2() && sira == 4)
                    return zar;
                else if (sira == 8)
                    return zar;
                veri[sira] = 25;
                veri[sira+1] = __oynanacak_zar.getDice1();
                __benim_pullar[25]--;
                return kirik_pul_icin_oyna(__benim_pullar, __rakip_pullar, __oynanacak_zar, sira+2, 1);
            }
            
            else if (__rakip_pullar[__oynanacak_zar.getDice2()] < 2 && zar != 2){
                if (__oynanacak_zar.getDice1() != __oynanacak_zar.getDice2() && sira == 4)
                    return zar;
                else if (sira == 8)
                    return zar;
                veri[sira] = 25;
                veri[sira+1] = __oynanacak_zar.getDice2();
                __benim_pullar[25]--;
                return kirik_pul_icin_oyna(__benim_pullar, __rakip_pullar, __oynanacak_zar, sira+2, 2);
            }
        }
        return zar;
    }

    private boolean taslari_topla(int[] __benim_pullar) {
        for (int i = 0; i< 19; i++)
            if (__benim_pullar[i] > 0)
                return false;
        return true;
    }
/*
    private DiceNode tas_toplama_icin_zar_sec(int[] __benim_pullar, DiceNode __oynanacak_zar) {
         LinkedList<Integer> toplama_list = new LinkedList<Integer>();
         
         for (int i = 19 ; i<25 ; i++)
             if (__benim_pullar[i] > 1)
                 toplama_list.add(i);
         for (DiceNode dn: diceNodeList){
             if (dn.getDice1() == toplama_list.get(0) || dn.getDice2() == toplama_list.get(0))
             
         }
    }*/

    private void tas_topla_tek(int[] __benim_pullar, DiceNode __oynanacak_zar, int sira, int zar) {
        for (int i = 19; i < 25 ; i++){
            if (__oynanacak_zar.getDice1() != __oynanacak_zar.getDice2() && sira == 4)
                return;
            else if (__benim_pullar[__oynanacak_zar.getDice1()] > 0 && zar != 1){
                veri[sira] = __oynanacak_zar.getDice1();
                veri[sira+1] = i;
                __benim_pullar[i]--;
                if (__oynanacak_zar.getDice1() == __oynanacak_zar.getDice1())
                tas_topla_tek(__benim_pullar, __oynanacak_zar, sira+2, 1);
            }
            else if (__benim_pullar[__oynanacak_zar.getDice2()] > 0 && zar != 2){
                veri[sira] = __oynanacak_zar.getDice2();
                veri[sira+1] = i;
                __benim_pullar[i]--;
                tas_topla_tek(__benim_pullar, __oynanacak_zar, sira+2, 2);
            }
        }
    }
}
