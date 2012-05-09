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
import javax.swing.JOptionPane;

/**
 *
 * @author Dogru
 */
public class FUNC_1 implements YapayZeka {
    Arayuz arayuz;
    LinkedList<DiceNode> diceNodeList = new LinkedList<DiceNode>();
    LinkedList<DiceNode> secondDiceList = new LinkedList<DiceNode>();
    int benim_pullar[];  //board üzerindeki sütunlarda kaç pul bulunduğu tutulmaktadır.
    int rakip_pullar[];
	private int zar1;
	private int zar2;
    static int dice1;
    static int dice2;
    static int diceID;
    static int[] veri = new int[8];   //bunları verileri göndermek için kullanıyoruz

    public FUNC_1(Arayuz arayuz) {
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
//        System.out.println("Play4 için:");
//        Play4(KullanicidanDegerOku(), KullanicidanDegerOku());
        
//        int buyuk = 0;
//        
//        for (int i = 0; i < diceNodeList.size(); i++) {
//      	  int toplam = diceNodeList.get(i).getDice1() + diceNodeList.get(i).getDice2();
//          
//      	  if (toplam > buyuk) {
//      		buyuk = toplam;
//      		diceID = diceNodeList.get(i).getDiceID();
//      	  }  
//      	  
//        }  
//
//        Play1(1, diceNodeList.get(diceID).getDice1());
//        Play2(12, diceNodeList.get(diceID).getDice2());
//        Play3(1, diceNodeList.get(diceID).getDice1());
//        Play4(12, diceNodeList.get(diceID).getDice2());
    	
    
//    	
    	
    	
    	

//		else if (j-i == diceNodeList.get(k).getDice2() - diceNodeList.get(k).getDice1()) {
//			if (buHamleOlurmu(j, diceNodeList.get(k).getDice2()) && buHamleOlurmu(i, diceNodeList.get(k).getDice1()) && buHamleOlurmu(j+diceNodeList.get(k).getDice1(), diceNodeList.get(k).getDice2()) && buHamleOlurmu(i+diceNodeList.get(k).getDice1(), diceNodeList.get(k).getDice1())) {
//				diceID = k;
//				Play1(j, diceNodeList.get(k).getDice2());
//				Play2(i, diceNodeList.get(k).getDice1());
//				Play3(i, diceNodeList.get(k).getDice2());
//				Play4(j, diceNodeList.get(k).getDice1());
//				return veri;
//			}
//			
//		}
    	
 
    	
    	
//    	for (int i = 1; i < 25; i++) {
//			if (benim_pullar[i] > 0) {
//				for (int j = i+1; j < i+6; j++) {
//					if (j < 25 && benim_pullar[j] > 0) {
//						for (int k = 0; k < diceNodeList.size(); k++) {
//					    	if (j-i == diceNodeList.get(k).getDice1() - diceNodeList.get(k).getDice2()) {
//					    		if (i > 11 && benim_pullar[i] > 2 && benim_pullar[j] > 2) {
//					    			if (buHamleOlurmu(i, diceNodeList.get(k).getDice1()) && buHamleOlurmu(j, diceNodeList.get(k).getDice2())) {
//										diceID = k;
//										System.out.println(diceID);
//										System.out.println(diceNodeList.get(k).getDice1());
//										System.out.println(diceNodeList.get(k).getDice2());
//										Play1(i, diceNodeList.get(k).getDice1());
//										Play2(j, diceNodeList.get(k).getDice2());
//										return veri;
//									}
//								}	
//							}
//						}
//					}
//				}
//			}
//    	}
        
//        int sayac = 0;
//        while(sayac < 4) {
//        	for (int i = 1; i < 25; i++) {
//        		for (int k = 0; k < diceNodeList.size(); k++) {   
//        			if (diceNodeList.get(k).getDice1() != diceNodeList.get(k).getDice2()) {
//        				if (benim_pullar[i] == 2 && buHamleOlurmu(i, diceNodeList.get(k).getDice1()) && buHamleOlurmu(i, diceNodeList.get(k).getDice2())) {
//        					diceID = k;
//        					System.out.println(diceID);
//        					Play1(i, diceNodeList.get(k).getDice1());
//                			Play2(i, diceNodeList.get(k).getDice2());
//                			Play3(i, diceNodeList.get(k).getDice1());
//                			Play4(i, diceNodeList.get(k).getDice1());
//                			sayac += 4;
//                			break;
//                		}
//					}
//            		
//            	}
//			}
//        	
//        }
//    	
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
//    			i++;
//    		}
//    	}
//    	
    	
   
    	
    	
    	
    	int zarID = 0;
    	int konum = 1;
    	int kontrol = 0;
    	
    		
    	while(konum<25) {
    		if (diceNodeList.get(zarID).getDice1() == diceNodeList.get(zarID).getDice2()) {
    			if (benim_pullar[konum] >= 2) {
    				zar1 = diceNodeList.get(zarID).getDice1();
    				zar2 = diceNodeList.get(zarID).getDice2();
    				calculate();
    				if (kontrol == 0 && buHamleOlurmu(konum, diceNodeList.get(zarID).getDice1())) {
    					Play1(konum, diceNodeList.get(zarID).getDice1());
        				Play2(konum, diceNodeList.get(zarID).getDice1());
        				kontrol = 1;
        				konum ++;
     				}
    				if (kontrol == 1 && buHamleOlurmu(konum, diceNodeList.get(zarID).getDice1())) {
    					Play3(konum, diceNodeList.get(zarID).getDice1());
        				Play4(konum, diceNodeList.get(zarID).getDice1());
        				kontrol = 2;
        				break;
					}

				}//2. if
    		}//1. if  
    		if (kontrol == 1) {
				konum ++;
			}
    		else {
    			zarID ++;
        		if (zarID == diceNodeList.size() - 1) {
    				zarID = 0;
    				konum ++;
    			}
			}  		
    	}//while
    	
    	if (kontrol != 2) {
    		
    	 	
        	int hamleTamam = 0;
        	for (int i = 1; i < 20; i++) {
    			if (benim_pullar[i] > 0) {
    				for (int j = i+1; j < i+6 && j < 20; j++) {
    					for (int k = 0; k < diceNodeList.size(); k++) {
    						if (j-i == diceNodeList.get(k).getDice1() - diceNodeList.get(k).getDice2()) {
    							if (buHamleOlurmu(i, diceNodeList.get(k).getDice1()) && buHamleOlurmu(j, diceNodeList.get(k).getDice2())) {
    								zar1 = diceNodeList.get(k).getDice1();
    								zar2 = diceNodeList.get(k).getDice2();
    								calculate();
    								Play1(i, diceNodeList.get(k).getDice1());
    								Play2(j, diceNodeList.get(k).getDice2());
    								hamleTamam = 1;
    							}
    						}
    						else if (j-i == diceNodeList.get(k).getDice2() - diceNodeList.get(k).getDice1()) {
    							if (buHamleOlurmu(i, diceNodeList.get(k).getDice2())  && buHamleOlurmu(j, diceNodeList.get(k).getDice1())) {
    								zar1 = diceNodeList.get(k).getDice1();
    								zar2 = diceNodeList.get(k).getDice2();
    								calculate();
    								Play1(i, diceNodeList.get(k).getDice2());
    								Play2(j, diceNodeList.get(k).getDice1());
    								hamleTamam = 1;
    							}
    						}
    						if (hamleTamam == 1) {
    							kontrol = 3;
    							break;
    						}
    					}
    				}
    			}
    		}
        	
        	
        	if (kontrol != 3) {
				
            	zarID = 0;
            	konum = 1;
            	int tempKonum = 0;
        		while(konum<25) {
        			if (tempKonum == 0 && benim_pullar[konum] == 1) {
        				if(buHamleOlurmu(konum, diceNodeList.get(zarID).getDice1())){
        					tempKonum = konum;
        					konum++;
        				}
        			}
        			if (tempKonum != 0 && benim_pullar[konum] == 1) {
        				if(buHamleOlurmu(konum, diceNodeList.get(zarID).getDice2())){
        					zar1 = diceNodeList.get(zarID).getDice1();
        					zar2 = diceNodeList.get(zarID).getDice2();
        					calculate();
        					Play1(tempKonum, diceNodeList.get(zarID).getDice1());
        					Play2(konum, diceNodeList.get(zarID).getDice1());
        					break;
        				}
        			}
        			zarID ++;
            		if (zarID == diceNodeList.size() - 1) {
        				zarID = 0;
        				konum ++;
        			}
        		}
        		
        		
        		
        		if (kontrol != 4) {
        			zarID = 0;
                	konum = 1;
                	while(konum<25) {
                		if (diceNodeList.get(zarID).getDice1() != diceNodeList.get(zarID).getDice2()) {
                			if (benim_pullar[konum] >= 2) {
                				zar1 = diceNodeList.get(zarID).getDice1();
                				zar2 = diceNodeList.get(zarID).getDice2();
                				calculate();
                				if (buHamleOlurmu(konum, diceNodeList.get(zarID).getDice1()) && buHamleOlurmu(konum, diceNodeList.get(zarID).getDice2())) {
                					Play1(konum, diceNodeList.get(zarID).getDice1());
                    				Play2(konum, diceNodeList.get(zarID).getDice2());
                    				break;
                				}

            				}//2. if
                		}//1. if  
                		zarID ++;
                		if (zarID == diceNodeList.size() - 1) {
            				zarID = 0;
            				konum ++;
            			}
                	}//while
    			}

        		
			}
    		
        	
    		
		}
    	
//    	
    	return veri;
    	
    	
    }
    
    @Override
    public void calculate() {
        DiceNode secilenZar;
        DiceNode avantajliZar = new DiceNode(zar1, zar2, -1);
        int ID=0;
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
