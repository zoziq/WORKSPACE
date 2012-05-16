/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ali8iterasyon1HAMLE.yedek;

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
	
	private int zar1;
	private int zar2;
	
    Arayuz arayuz;
    LinkedList<DiceNode> diceNodeList = new LinkedList<DiceNode>();
    LinkedList<DiceNode> secondDiceList = new LinkedList<DiceNode>();
    int benim_pullar[];  //board üzerindeki sütunlarda kaç pul bulunduğu tutulmaktadır.
    int rakip_pullar[];
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
        return node;
    }

    @Override
    public void calculate() {
        DiceNode secilenZar;
        System.out.println("dices");
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
    public int[] kos() {System.out.println("swsws");
             
        int taslarToplamayaHazir = 1;//toplanmaya hazir mi
    	for (int i = 0; i < 19; i++) {
    		if (benim_pullar[i] > 0) {
    			taslarToplamayaHazir = 0;
    			break;
    		}
		}
    	System.out.println(taslarToplamayaHazir);
    	int konum = 1;
    	int kontrol = -1;
    	int zarID = 0;
    	
    	if (taslarToplamayaHazir == 1) {
    		while(zarID != diceNodeList.size()-1) {
    			if (diceNodeList.get(zarID).getDice1() == diceNodeList.get(zarID).getDice2()) {
        			if (benim_pullar[25 - diceNodeList.get(zarID).getDice1()] == 1) {
        				zar1 = diceNodeList.get(zarID).getDice1();
        				zar2 = diceNodeList.get(zarID).getDice2();	
        				calculate();        				
    					Play1(25 - diceNodeList.get(zarID).getDice1(), diceNodeList.get(zarID).getDice1());
    					int i = 19;
    					while(true) {
    						if (benim_pullar[i] == 1) {
    							Play2(i, diceNodeList.get(zarID).getDice1());
    							if (benim_pullar[i + 1] == 1) {
    								Play3(i, diceNodeList.get(zarID).getDice1());
    								if (benim_pullar[i + 2] == 1) {
        								Play4(i, diceNodeList.get(zarID).getDice1());
        								kontrol = 0;
        								break;
        							}
    							}
    							else if (benim_pullar[i + 1] == 2) {
    								Play4(i, diceNodeList.get(zarID).getDice1());
    								kontrol = 0;
    								break;
    							}
							}
    						else if (benim_pullar[i] == 2) {
    							Play2(i, diceNodeList.get(zarID).getDice1());
    							Play3(i, diceNodeList.get(zarID).getDice1());
    							if (benim_pullar[i + 1] == 1) {
    								Play4(i, diceNodeList.get(zarID).getDice1());
    								kontrol = 0;
    								break;
    							}
							}
    						else if (benim_pullar[i] == 3) {
    							Play2(i, diceNodeList.get(zarID).getDice1());
    							Play3(i, diceNodeList.get(zarID).getDice1());
    							Play4(i, diceNodeList.get(zarID).getDice1());
    							kontrol = 0;
								break;
							}
    					}
					}
        		}
    			zarID ++;
    		}	
    		    		
		}
    	
    	
    	
    	if (kontrol == -1) {
    		
    		konum = 1;
        	kontrol = 0;
        	zarID = 0;
        	
        	if (benim_pullar[25] == 1) {//tek kirigi sokabiliyor muyum
        		kontrol = 1;
        		while(zarID != diceNodeList.size()-1) {
        			if (diceNodeList.get(zarID).getDice1() != diceNodeList.get(zarID).getDice2()) {
        				if (buHamleOlurmu(25, diceNodeList.get(zarID).getDice1()) && buHamleOlurmu(konum, diceNodeList.get(zarID).getDice2())) {
        					zar1 = diceNodeList.get(zarID).getDice1();
            				zar2 = diceNodeList.get(zarID).getDice2();	
            				calculate();
        					Play1(25, diceNodeList.get(zarID).getDice1());
        					Play2(konum, diceNodeList.get(zarID).getDice2());
        					kontrol = 1;
        					break;
        				}
        				else if(buHamleOlurmu(25, diceNodeList.get(zarID).getDice2()) && buHamleOlurmu(konum, diceNodeList.get(zarID).getDice1())) {
        					zar1 = diceNodeList.get(zarID).getDice2();
            				zar2 = diceNodeList.get(zarID).getDice1();	
            				calculate();
        					Play1(25, diceNodeList.get(zarID).getDice2());
        					Play2(konum, diceNodeList.get(zarID).getDice1());
        					kontrol = 1;
        					break;
        				}
        			}
        			zarID ++;
            		if (zarID == diceNodeList.size() - 1) {
        				zarID = 0;
        				konum ++;
        			}
        		}
        	}
        	
        	if (benim_pullar[25] >= 2) {//cift kirigi sokabiliyor muyum
    			kontrol = 1;
        		while(zarID != diceNodeList.size()-1) {
        			if (diceNodeList.get(zarID).getDice1() != diceNodeList.get(zarID).getDice2()) {
        				if (buHamleOlurmu(25, diceNodeList.get(zarID).getDice1()) && buHamleOlurmu(25, diceNodeList.get(zarID).getDice2())) {
        					zar1 = diceNodeList.get(zarID).getDice1();
            				zar2 = diceNodeList.get(zarID).getDice2();	
            				calculate();
        					Play1(25, diceNodeList.get(zarID).getDice1());
        					Play2(25, diceNodeList.get(zarID).getDice2());
        					kontrol = 1;
        					break;
        				}	
        			}
        			zarID ++;
        		}
        		
        	}
        	
        	if (kontrol == 0) {//kirma
        		
        		int kiranKonum = 0;
        		int kiranZarID = 0;
        		int kiranZar = 0;
        		int kiranBirinciMi = 0;
        		
        		konum = 1;
            	kontrol = 0;
            	zarID = 0;
            	
            	int breakKontrol = 0;
            	for (int i = 1; i < 18; i++) {
        			if (benim_pullar[i] > 0) {
        				for (int j = i+1; j < i+7; j++) {
        					if (rakip_pullar[j] == 1) {
        						if ((diceNodeList.get(zarID).getDice1() == j-i)) {
        							kiranZar = diceNodeList.get(zarID).getDice1();
        							kiranZarID = zarID;
        							kiranKonum = i;
        							breakKontrol = 1;
        							kiranBirinciMi = 1;
        							break;
        						}
        						else if ((diceNodeList.get(zarID).getDice2() == j-i)) {
        							kiranZar = diceNodeList.get(zarID).getDice2();
        							kiranZarID = zarID;
        							kiranKonum = i;
        							breakKontrol = 1;
        							kiranBirinciMi = 0;
        							break;
        						}
        					}
        				}
        				if (breakKontrol == 1) {
        					breakKontrol = 0;
        					break;
        				}
        				zarID ++;
        				i--;
                		if (zarID == diceNodeList.size() - 1) {
            				zarID = 0;
            				i++;
            			}
        			}
        		}
            	
            	if (kiranKonum != 0 && diceNodeList.get(kiranZarID).getDice1() != diceNodeList.get(kiranZarID).getDice2()) {
            		    	
    				konum = 1;
                	while(konum<25) {//rasgele sec
                		if (konum==kiranKonum && benim_pullar[konum] == 1) {
                			konum++;
						}
                		 if(kiranBirinciMi == 1 && buHamleOlurmu(konum, diceNodeList.get(kiranZarID).getDice2())){
                			 zar1 = diceNodeList.get(kiranZarID).getDice1();
             				 zar2 = diceNodeList.get(kiranZarID).getDice2();	
             				 calculate();
                			 Play1(kiranKonum, diceNodeList.get(kiranZarID).getDice1());
                			 Play2(konum, diceNodeList.get(kiranZarID).getDice2());
                			 System.out.println("kirdi1");
                			 kontrol = 1;
                			 break;
                		 }
                		 else if(kiranBirinciMi == 0 && buHamleOlurmu(konum, diceNodeList.get(kiranZarID).getDice1())){
                			 zar2 = diceNodeList.get(kiranZarID).getDice2();
             				 zar1 = diceNodeList.get(kiranZarID).getDice1();	
             				 calculate();
                			 Play1(kiranKonum, diceNodeList.get(kiranZarID).getDice2());
                			 Play2(konum, diceNodeList.get(kiranZarID).getDice1());
                			 System.out.println("kirdi2");
                			 kontrol = 1;
                			 break;
                		 }
                		 
                		 konum ++;
                	}
            	}
            	
        	}
        	
        	
        
        	if (kontrol == 0) {
        		
            	konum = 1;
            	zarID = 0;
            	kontrol = 0;

            	while(konum<25) {//cift 1. oncelik
                	if (diceNodeList.size() == 1) {
        				zarID = 0;
        			}
            		if (diceNodeList.get(zarID).getDice1() == diceNodeList.get(zarID).getDice2()) {
            			if (benim_pullar[konum] >= 2) {
            				zar1 = diceNodeList.get(zarID).getDice1();
            				zar2 = diceNodeList.get(zarID).getDice2();	
            				calculate();
            				if (kontrol == 0 && buHamleOlurmu(konum, diceNodeList.get(zarID).getDice1()) && benim_pullar[konum + diceNodeList.get(zarID).getDice1()] < 2 && benim_pullar[konum] != 3) {            					
            					Play1(konum, diceNodeList.get(zarID).getDice1());
            					Play2(konum, diceNodeList.get(zarID).getDice1());
                				kontrol = 1;
                				konum ++;
             				}
            				if (kontrol == 1 && buHamleOlurmu(konum, diceNodeList.get(zarID).getDice1()) && benim_pullar[konum + diceNodeList.get(zarID).getDice1()] < 2 && benim_pullar[konum] != 3) {
            					Play3(konum, diceNodeList.get(zarID).getDice1());
                				Play4(konum, diceNodeList.get(zarID).getDice1());
                				kontrol = 2;
                				System.out.println("cift zar oynadi");
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
                	for (int i = 1; i < 20; i++) {//kapi ikinci oncelik
            			if (benim_pullar[i] > 0) {
            				for (int j = i+1; j < i+6 && j < 20; j++) {
            					for (int k = 0; k < diceNodeList.size(); k++) {
            						if (j-i == diceNodeList.get(k).getDice1() - diceNodeList.get(k).getDice2() && benim_pullar[i + diceNodeList.get(k).getDice1()] < 2 && benim_pullar[i] != 2 && benim_pullar[j] != 2) {
            							if (buHamleOlurmu(i, diceNodeList.get(k).getDice1()) && buHamleOlurmu(j, diceNodeList.get(k).getDice2())) {
            								zar1 = diceNodeList.get(k).getDice1();
            								zar2 = diceNodeList.get(k).getDice2();
            								calculate();
            								Play1(i, diceNodeList.get(k).getDice1());
            								Play2(j, diceNodeList.get(k).getDice2());
            								hamleTamam = 1;
            							}
            						}
            						else if (j-i == diceNodeList.get(k).getDice2() - diceNodeList.get(k).getDice1()  && benim_pullar[i + diceNodeList.get(k).getDice2()] < 2 && benim_pullar[i] != 2 && benim_pullar[j] != 2) {
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
            							System.out.println("kapi yapti");
            							break;
            						}
            					}
            				}
            			}
            		}
                	
                	if (kontrol != 3) {
                		
                		konum = 1;
                    	zarID = 0;
                		while(konum<25) {//tekten oyna
                			if (benim_pullar[konum] == 1) {
                				if (buHamleOlurmu(konum, diceNodeList.get(zarID).getDice1()) && rakip_pullar[konum + diceNodeList.get(zarID).getDice1() + diceNodeList.get(zarID).getDice2()] < 2) {
                					zar1 = diceNodeList.get(zarID).getDice1();
                					zar2 = diceNodeList.get(zarID).getDice2();
                					calculate();
                					Play1(konum, diceNodeList.get(zarID).getDice1());
                					Play2(konum + diceNodeList.get(zarID).getDice1(), diceNodeList.get(zarID).getDice2());
                					kontrol = 4;
                					System.out.println("tekten oynadi");
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
            				
                			konum = 1;
                	    	zarID = 0;
                        	int tempKonum = 0;
                    		while(konum<25) {//tek taslar
                    			if (benim_pullar[konum] != 3 && tempKonum == 0 && benim_pullar[konum] == 1) {
                    				if(buHamleOlurmu(konum, diceNodeList.get(zarID).getDice1())){
                    					tempKonum = konum;
                    					konum++;
                    				}
                    			}
                    			if (benim_pullar[konum] != 3 && tempKonum != 0 && benim_pullar[konum] == 1) {
                    				if(buHamleOlurmu(konum, diceNodeList.get(zarID).getDice2())){
                    					zar1 = diceNodeList.get(zarID).getDice1();
                    					zar2 = diceNodeList.get(zarID).getDice2();
                    					calculate();
                    					Play1(tempKonum, diceNodeList.get(zarID).getDice1());
                    					Play2(konum, diceNodeList.get(zarID).getDice2());
                    					kontrol = 5;
                    					System.out.println("tek taslardan");
                    					break;
                    				}
                    			}
                    			zarID ++;
                        		if (zarID == diceNodeList.size() - 1) {
                    				zarID = 0;
                    				konum ++;
                    			}
                    		}
                    		
                    		
                    		
                    		if (kontrol != 5) {
                    			konum = 1;
                    	    	zarID = 0;
                            	while(konum<25) {//rasgele sec
                            		if (diceNodeList.get(zarID).getDice1() != diceNodeList.get(zarID).getDice2()) {
                            			if (benim_pullar[konum] >= 2) {
                            				zar1 = diceNodeList.get(zarID).getDice1();
                            				zar2 = diceNodeList.get(zarID).getDice2();
                            				calculate();
                            				if (buHamleOlurmu(konum, diceNodeList.get(zarID).getDice1()) && buHamleOlurmu(konum, diceNodeList.get(zarID).getDice2())) {
                            					Play1(konum, diceNodeList.get(zarID).getDice1());
                                				Play2(konum, diceNodeList.get(zarID).getDice2());
                                				System.out.println("rasgele secti");
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
