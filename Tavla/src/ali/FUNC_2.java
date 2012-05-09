/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ali;

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
	
	public static int zar1;
	public static int zar2;
	
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
        DiceNode avantajliZar = new DiceNode(zar1, zar2, -1);

        for (Iterator it = diceNodeList.iterator(); it.hasNext();) {
            secilenZar = (DiceNode) it.next();
            if (secilenZar.getDice1() == avantajliZar.getDice1() && secilenZar.getDice2() == avantajliZar.getDice2()) {
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
//        calculate();
//        System.out.println("dice1:" + dice1 + "dice2" + dice2);
//        
//        System.out.println("Play1 için:");
//        Play1(KullanicidanDegerOku(), KullanicidanDegerOku());
//        System.out.println("Play2 için:");
//        Play2(KullanicidanDegerOku(), KullanicidanDegerOku());
//
//        System.out.println("Play3 için:");
//        Play3(KullanicidanDegerOku(), KullanicidanDegerOku());
//
//        System.out.println("Play4 için:");
//        Play4(KullanicidanDegerOku(), KullanicidanDegerOku());
        

    	
    	
        
//        int sayac = 0;
//        while(true) {
//        	for (int i = 24; i > 0; i--) {
//        		for (int k = 0; k < diceNodeList.size(); k++) {   
//        			if (diceNodeList.get(k).getDice1() != diceNodeList.get(k).getDice2()) {
//        				if (rakip_pullar[i] == 2 && buHamleOlurmu(i, diceNodeList.get(k).getDice1()) && buHamleOlurmu(i, diceNodeList.get(k).getDice2())) {
//        					diceID = k;System.out.println(k);
//        					Play1(i, diceNodeList.get(k).getDice1());
//                			Play2(i, diceNodeList.get(k).getDice2());
//                			return veri;
//                		}
//					}
//            		
//            	}
//			}
//        	
//        }
    	
    	
//    	int i = 0;
//    	while(i<25) {
//        	for (int k = 0; k < diceNodeList.size(); k++) {   
//    			if (diceNodeList.get(k).getDice1() != diceNodeList.get(k).getDice2()) {
//    				System.out.println("zar id = " + k);
//    				if (benim_pullar[i] == 2 && buHamleOlurmu(i, diceNodeList.get(k).getDice1()) && buHamleOlurmu(i, diceNodeList.get(k).getDice2())) {
//    					diceID = k;
//    					System.out.println(i + " . tas " + diceNodeList.get(k).getDice1() + " ileri");
//    					System.out.println(i + " . tas " + diceNodeList.get(k).getDice2() + " ileri");
//    					Play1(i, diceNodeList.get(k).getDice1());
//    					Play2(i, diceNodeList.get(k).getDice2());
//    					return veri;
//    				}
//    			}
//    			
//    		}
//        	i++;
//    	}

    	
    	int zarID = diceNodeList.size()-1;
    	int konum = 24;
    	int sayac = 0;
    	
    	while(konum>0 && sayac!=2) {
    		if (diceNodeList.get(zarID).getDice1() != diceNodeList.get(zarID).getDice2()) {
    			System.out.println();
    			System.out.println(rakip_pullar[konum]);
    			System.out.println(diceNodeList.get(zarID).getDice1());
    			System.out.println(buHamleOlurmu(konum, diceNodeList.get(zarID).getDice1()));
    			System.out.println(buHamleOlurmu(konum, diceNodeList.get(zarID).getDice2()));
        		 if (sayac != 1 && rakip_pullar[konum] >= 2) {
        			System.out.println(1);
         			diceID = zarID;
         			dice1 = diceNodeList.get(zarID).getDice1();
         			dice2 = diceNodeList.get(zarID).getDice2();
        			if (buHamleOlurmu(konum, diceNodeList.get(zarID).getDice1()) && buHamleOlurmu(konum, diceNodeList.get(zarID).getDice2())) {	
        				Play1(konum, diceNodeList.get(zarID).getDice1());
        				Play2(konum, diceNodeList.get(zarID).getDice2());
        				sayac += 2;
        				break;
					}	
        		}
        		else if (rakip_pullar[konum] >= 2 && buHamleOlurmu(konum, diceNodeList.get(zarID).getDice1())) {
        			System.out.println(2);
        			if (sayac != 1) {
        				diceID = zarID;
        				dice1 = diceNodeList.get(zarID).getDice1();
        				Play1(konum, diceNodeList.get(zarID).getDice1());
        				sayac += 1;
					}
        			else if(buHamleOlurmu(konum, diceNodeList.get(zarID).getDice2())){
        				diceID = zarID;
        				dice2 = diceNodeList.get(zarID).getDice2();
        				Play2(konum, diceNodeList.get(zarID).getDice2());
        				sayac += 1;
        			}
        		}
        		else if (rakip_pullar[konum] >= 2 && buHamleOlurmu(konum, diceNodeList.get(zarID).getDice2())) {
        			System.out.println(3);
        			if (sayac != 1) {
        				diceID = zarID;
        				dice1 = diceNodeList.get(zarID).getDice2();
        				Play1(konum, diceNodeList.get(zarID).getDice2());
        				sayac += 1;
					}
        			else if(buHamleOlurmu(konum, diceNodeList.get(zarID).getDice1())){
        				diceID = zarID;
        				dice2 = diceNodeList.get(zarID).getDice1();
        				Play2(konum, diceNodeList.get(zarID).getDice1());
        				sayac += 1;
        			}
        		}
        		
        		if (sayac == 1) {
					konum --;
				}
        		else {
        			zarID --;
            		if (zarID == 0) {
    					zarID = diceNodeList.size()-1;
    					konum --;
    				}
        		}	
        	}
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
