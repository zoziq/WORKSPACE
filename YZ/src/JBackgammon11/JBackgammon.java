package JBackgammon11;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.image.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Iterator;
import java.util.Random;

public class JBackgammon extends JFrame implements ActionListener {

    //DegerAl A;
    //private static final String VERSION = "1.3";
    private static final int neutral = 0;
    private static final int white = 1;
    private static final int black = 2;
    private static int PanelIndex = 0;
    private static boolean stuckFlag = false;
    private boolean diskalifiye = false;
    private static int rolledDiceCounter = 0;
    private static int hamleSayici = 0;
    private static int old_dice = 0;
    private Random rdice;
    private static int diceID;
    private static int dice1;
    private static int dice2;
    private static boolean used_move_sifirla = false;
    private static int used_move_sifirla_sayac = 0;
    //10 zar durumlarını saklar eğer zar oynanmışsa false olur
    private static boolean diceSelectionState[];
    //oynanan hamlelerin ne olduğu ile ilgili bilginin tutulduğu alan
    private JTextArea area;
    //Color to be used when drawing a white checker
    private static final Color clr_white = new Color(105, 105, 105);
    //Color to be used when drawing a black checker
    private static final Color clr_black = new Color(0, 0, 0);
    //Color to be used when drawing a white spike
    private static final Color spike_black = new Color(128, 0, 0);
    //Color to be used when drawing a black spike
    private static final Color spike_white = new Color(255, 255, 255);
    //Buffers used for double buffering
    private BufferedImage b_bimage;//arkaplana bir resim ekliyor
    private Graphics2D g_buffer;
    private static final int x_offset = 20;
    private static final int y_offset = 60;
    private board b;
    private Arayuz arayuz;
    //When moving, the original position of the checker
    private int old_spike;//ta�� oynatan ki�inin eski pozisyonunu tutar
    // int a=oyuncubelirleme.nextInt(2)+1;
    //The current player
    public int current_player;
    //current_player=oyuncubelirleme.nextInt(2)+1;
    private int used_move = 0;//zarlar�n kullan�m durumlar�n� belirler
    /* used_move == 1 means first dice has been used
     * used_move == 2 means second dice has been used
     * used_move == 0 means no die have been used yet
     */
    //The move possible with each dice
    //Positions:
    // 1 - 24 = spikes, 1 being on the beginning of the black quarter
    //spikes: ��gen s�tunlar
    //-1 = bar
    // 0 = black bear off //siyah i�in k�r�klar�n koyuldu�u yer
    // 25 = white bear off
    private int potmove1, potmove2;
    //If there are doublets, how many doublet moves remain
    private int doublet_moves; //e�er �ift atarsa ka� hareket yapaca��n� tutuyor
    //This contains some booleans about the status of the game
    private Status status;
    //Textfield used for typing messages
    private FixedDicePanel FPanel[] = new FixedDicePanel[10];
    private FixedButton FButton[] = new FixedButton[8];
    private FixedDiceButton DiceButton[][] = new FixedDiceButton[10][10];
    private FixedDiceButton yedekDiceButton[][] = new FixedDiceButton[10][10];
    //Integer zarDegeri[][] = new Integer[10][10];
    /* FButton Number             Purpose
     * ------------------------------------------------------------
     * 0                          Cancel Move
     * 1                          Roll Dice
     * 2                          Bear Off
     * 3                          Potential Move 1
     * 4                          Potential Move 2
     * 5                          Call Artifical intelligence 1
     * 6                          Call Artifical intelligence 2
     * 7                          New Game */
    //Button labels
    private static final String CANCEL = "Seçimi Sil";
    private static final String ROLL_DICE = "Roll Dice";
    private static final String BEAR_OFF = "Pul Çıkar";//tahtadan ��km�� ta�lar� belirten buton
    private static final String MOVE1 = "M1";
    private static final String MOVE2 = "M2";
    private static final String NEW_GAME = "Yeni Oyun";
    private static final String FUNC_1 = "1. Yapay Zeka";
    private static final String FUNC_2 = "2. Yapay Zeka";
    private static final String MakeMove = "Hamle Yap";

    /*=================================================
     * Game-related Methods 
     * ================================================*/
    //Selects a spike and shows the possible moves
    //handle bar kullanıldı ise buna göre yapılması gereknleri yapacağız belki 
    //boolean bir genel değişken ile bunu tesbit edip ona göre hande spike çalıştırılmaz ya da 
    //bazı özellikleri çalıştırılmaz.
    private void HandleSpike(int spike) {
        //int xpos;
        //int ypos;
        //int y_comp;
        //The player cannot move the other's pieces //oyuncunun di�er oyuncunun ta�lar�n� se�ememesini sa�l�yor.
        if (b.getColor(spike) == current_player && !status.spike_selected) //spike default false olur. (seçilmişse bu işlemler yapılacak)
        //spike içerisindeki taşalrın rengi ile oyuncunun taşlarının rengi aynı var ise
        //spike false olduğu zaman seçilmiş olacak
        {
            //Get the possible moves from that spike
            if (current_player == white) {
                potmove1 = spike + dice1; //spike ta��n zar at�ld��� zamanki yeri
                potmove2 = spike + dice2; //potmove sonraki gidece�i yeri g�sterir
                //If the player can make no other moves, allow him
                //to bear off with rolls larger than what is needed to bear off
                if (NeedsInexactRolls()) {  //herhangi bir hamle yapıp yamayacağına bkıyor 
                    if (potmove1 > 25) {// hamle yapılabiliyorsa taşma olan yerleri hesaplıyor taşma olmayacak şekilde set ediyor.
                        potmove1 = 25;
                    }
                    if (potmove2 > 25) {
                        potmove2 = 25;
                    }
                }
            } else if (current_player == black) {
                potmove1 = spike - dice1;
                potmove2 = spike - dice2;

                //If the player can make no other moves, allow him
                //to bear off with rolls larger than what is needed to bear off
                if (NeedsInexactRolls()) {  //eğer gidilecek olan yerlerden bir tanesi 0 dan küçük ise onu taşma olmaması için 0 yapıyor
                    if (potmove1 < 0) {
                        potmove1 = 0;
                    }
                    if (potmove2 < 0) {
                        potmove2 = 0;
                    }
                }
            }

            //If a move is valid, enable the button to move to it
            if (CheckFair(potmove1) && used_move != 1) {
                if ((potmove1 < 25) && (potmove1 > 0)) {
//                    buttonState[3] = true;
//                    FButton[0].setEnabled(true);
                    //FButton[3].drawOnSpike(potmove1);
                    status.spike_selected = true;
                } //The possible move leads to bearing off
                else {
//                    buttonState[2] = true;
//                    FButton[0].setEnabled(true);
                    //FButton[2].setEnabled(true);
                    status.spike_selected = true;
                }
            }
            if (CheckFair(potmove2) && used_move != 2) {
                if ((potmove2 < 25) && (potmove2 > 0)) {
//                    buttonState[4] = true;
//                    FButton[0].setEnabled(true);
                    //FButton[4].drawOnSpike(potmove2);
                    status.spike_selected = true;
                } //The possible move leads to bearing off
                else {
//                    buttonState[2] = true;
//                    FButton[0].setEnabled(true);
                    //FButton[2].setEnabled(true);
                    status.spike_selected = true;
                }
            }
            if (spike == 25) {
                System.out.println("Spike 25 geldi hata burada olabilir. Handle Spike içerisinde spike ==25 geldi!");
                spike = -1;
            }
            old_spike = spike;
        }
    }
    //Handle moving from one spike to another
    //new_move - the new position to move to
    //move - which dice is being used, the first one or the second one

    private void SuperMove(int new_move, int move) {

        //gidilecek konum //oynanacak olan hamle birincimi ikincimi

        boolean switchedplayers = true;
        //If the new space is empty, make the move
        //Else send the opponent on the bar first

        if (old_spike != -1) {
            if ((b.getColor(new_move) == current_player) || (b.getColor(new_move) == neutral) && (b.getColor(old_spike) == current_player)) { //gidilecek olan yerin rengi uygunmu

                move(current_player, old_spike, new_move); //send move çalışıyordu.
                EkleInfArea("" + move + ". Zar ile ");
                EkleInfArea("" + old_spike + "-->" + new_move + "\n");
            } else if (b.getCount(new_move) == 1) {
                b.moveToBar(new_move);
                move(current_player, old_spike, new_move);
                EkleInfArea("" + move + ". Zar ile " + old_spike + "-->" + new_move + " kırdı.\n");

                //send on bar send move gönderiliyordu.
            }
        } else {
            if ((b.getColor(new_move) == current_player) || (b.getColor(new_move) == neutral)) { //gidilecek olan yerin rengi uygunmu

                move(current_player, old_spike, new_move); //send move çalışıyordu.
                EkleInfArea("" + move + ". Zar ile ");
                EkleInfArea("" + old_spike + "-->" + new_move + "\n");
            } else if (b.getCount(new_move) == 1) {
                b.moveToBar(new_move);
                move(current_player, old_spike, new_move);
                EkleInfArea("" + move + ". Zar ile " + old_spike + "-->" + new_move + " kırdı.\n");

                //send on bar send move gönderiliyordu.
            }
        }
        if (used_move_sifirla == true && used_move_sifirla_sayac < 2) {
            used_move_sifirla_sayac++;
        }
        if (used_move_sifirla == true && used_move_sifirla_sayac == 2) {
            used_move = 0;
            used_move_sifirla_sayac = 0;
            used_move_sifirla = false;
        }
        int sonrakiZar = 0;
        if (move == 1) {
            sonrakiZar = dice2;
        } else {
            sonrakiZar = dice1;
        }
        if (!sonrakiHamlesiOynanirMi(sonrakiZar)) {
            String msg = "Hamle yok1";
            JOptionPane.showMessageDialog(this, msg);
            hamleSayici = 4;
            EndTurn();
        } else if (!status.doublets) {
            //If a move has been made previously,
            //this is the second move, end the player's turn
            if (hamleSayici == 2) {
                hamleSayici = 4;
                EndTurn();
            } else {
                switchedplayers = false;
                used_move = move;
            }
        } else if (status.doublets) {
            doublet_moves--;
            if (doublet_moves == 0) {
                EndTurn();
            } else {
                switchedplayers = false;
            }
        }

        //Turn off focus on this spike
        EndMove();
        repaint();
        //If this wasn't the player's last move,
        //check if he is still on the bar or if he can make more moves
        if (!switchedplayers) {
//            if (b.onBar(current_player)) {
//                HandleBar();
//            }

            if (!CanMove()) {
                Forfeit();
            }
        }

    }
    //Bear off a checker from the current spike

    private void BearOff(int kullanilan) {
        //Remove a checker from the old spike
        b.setColumn(old_spike, b.getCount(old_spike) - 1, current_player);
        EkleInfArea("" + kullanilan + ". Zar ile " + old_spike + "çıkarıldı\n");
        if (current_player == white) {
            b.white_bear++;
        } else {
            b.black_bear++;
        }

        //send move gönderiliyordu.
        FButton[2].setEnabled(false);
        boolean won = CheckWin(current_player);
        if (won) {
            EndMove();//Disable buttons
            repaint();
            return;//Do nothing if there's a winner
        }
        //Remove the dice we used
        if (used_move_sifirla == true && used_move_sifirla_sayac < 2) {
            used_move_sifirla_sayac++;
        }
        if (used_move_sifirla == true && used_move_sifirla_sayac == 2) {
            used_move = 0;
            used_move_sifirla_sayac = 0;
            used_move_sifirla = false;
        }

        int sonrakiZar = 0;
        if (kullanilan == 1) {
            sonrakiZar = dice2;
        } else {
            sonrakiZar = dice1;
        }
        if (!sonrakiHamlesiOynanirMi(sonrakiZar)) {
            String msg = "Hamle yok2";
            JOptionPane.showMessageDialog(this, msg);
            hamleSayici = 4;
            EndTurn();
        }
        if (!(dice1 == dice2)) {
            //Since a previous move has already occured, we are done

            if (hamleSayici == 2) {
                hamleSayici = 4;
                EndTurn();
            } else {
                //if you can bear off with both, use smaller dice
                if (((potmove1 == 25) || (potmove1 == 0)) && ((potmove2 == 25) || (potmove2 == 0))) {
                    if (b.getDice1() > b.getDice2()) {
                        used_move = 2;
                    } else {
                        used_move = 1;
                    }
                } else if ((potmove1 == 25) || (potmove1 == 0)) {
                    used_move = 1;
                } else if ((potmove2 == 25) || (potmove2 == 0)) {
                    used_move = 2;
                }
            }
        } else if (dice1 == dice2) {
            doublet_moves--;
            if (doublet_moves == 0) {
                EndTurn();
            }
        }
        //Turn off focus on this spike
        EndMove();
        repaint();

        if (!CanMove()) {
            Forfeit();
        }
    }
    //Handle someone being on the bar
    //Mark possible escapes and forfeit if there are none

    private void HandleBar() {
        int escape1;
        int escape2;

        if (current_player == white) {
            escape1 = b.getDice1();
            escape2 = b.getDice2();
        } else {
            escape1 = 25 - b.getDice1();
            escape2 = 25 - b.getDice2();  //zar değerlerini alıyor.
        }
        //Can they escape?
        //taş kırık durumunda iken tekrar oyuna alınabilir mi olduğunu kontrol ediyor.
        if ((used_move != 1) && CheckFair(escape1)) {
            //FButton[3].drawOnSpike(escape1);
            //FButton[3].setVisible(true);
//            buttonState[3] = true;

            potmove1 = escape1;
            old_spike = -1;
            status.spike_selected = true;
        }
        if ((used_move != 2) && CheckFair(escape2)) {
            //FButton[4].drawOnSpike(escape2);
            //FButton[4].setVisible(true);
//            buttonState[4] = true;
            potmove2 = escape2;
            old_spike = -1;
            status.spike_selected = true;
        }

        //Nope? Then they forfeit
        if (used_move == 0) {
            if (!CheckFair(escape1) && !CheckFair(escape2)) {
                Forfeit();
            }
        } else if (used_move == 1) {
            if (!CheckFair(escape2)) {
                Forfeit();
            }
        } else if (used_move == 2) {
            if (!CheckFair(escape1)) {
                Forfeit();
            }
        }
    }

    //Forfeit the current player's turn
    private void Forfeit() {
        String msg = "Hamle yok3";
        JOptionPane.showMessageDialog(this, msg);
        stuckFlag = true;
        EndTurn();
        repaint();
    }
    //Checks if there is a winner
    //If there is one, displays appropriate message
    //Return true if there was a winner, false otherwise

    private boolean CheckWin(int color) {
        String msg;
        if (color == white) {
            msg = "Beyaz kazandı!";
        } else if (color == black) {
            msg = "Siyah kazandı!";
        } else {
            msg = "Sen kazandın!";
        }
        if (color == white) {
            if (b.white_bear == 15) {
                //send lose gönderiliyordu.
                repaint();
                JOptionPane.showMessageDialog(this, msg);
                return true;
            }
        }
        if (color == black) {
            if (b.black_bear == 15) {
                //send lose gönderiliyordu.
                repaint();
                JOptionPane.showMessageDialog(this, msg);
                return true;
            }
        }
        return false;
    }

    //Roll the dice for the current player
    private void DoRoll(int dice1, int dice2) {
        b.rollDice(dice1, dice2);
        //sendroll gönderiliyordu.
        if (b.getDice1() == b.getDice2()) {
            status.doublets = true;
            doublet_moves = 4;
        } else {
            status.doublets = false;
        }
        //Turn off roll dice button
        repaint();
        //Check if the player is on the bar
//        if (b.onBar(current_player)) {
//            HandleBar();
//        } else 
        if (!CanMove()) {
            Forfeit();
        }
    }
    //End the current player's turn and start the turn
    //of the other player

    private void EndTurn() {
        String msg;
        //Change player
        if (current_player == white) {
            current_player = white;
        } else {
            current_player = white;
        }
        //Reset vars, turn off new game button
        used_move = 0;
        b.resetDice();
        b.rolled = false;
        //FButton[7].setEnabled(false);
//Burada zarlar tıklanabilir olacak
        repaint();
//        msg = "Lütfen oyuncu değiştirin.";
//        //send end turn gönderiliyordu.
//        JOptionPane.showMessageDialog(this, msg);
        StartTurn();
        repaint();
    }

    //Begins a player's turn
    private void StartTurn() {

        onluAc();
        for (int i = 0; i < 5; i++) {
//            buttonState[i] = false;
        }

        for (int i = 0; i < 10; i++) {
            DiceButton[i][rolledDiceCounter / 10].setEnabled(diceSelectionState[i]);
        }
        if (current_player == white && !diskalifiye) {
            FButton[5].setEnabled(true);
        } else if (current_player == black && !diskalifiye) {
            FButton[6].setEnabled(true);
        }
        FButton[1].setEnabled(false);
        FPanel[PanelIndex].repaint();
    }
    //Remove focus from a sertain spike which has been selected
    //This allows the player to select a new spike

    private void EndMove() {
        status.spike_selected = false;
        //Disable potential move buttons
        FButton[1].setEnabled(false);

        //FButton[0].setEnabled(false);
    }
    //Return wether the current player can place a checker
    //at a certain position

    private boolean CheckFair(int new_pos) {
        //Only positions 0 through 25 are valid moves
        if (new_pos > 25 || new_pos < 0) {
            return false;
        }

        //Positions 0 and 25 are bearing off
        if ((new_pos == 25) || (new_pos == 0)) {
            if (b.canBearOff(current_player)) {
                return true;
            } else {
                return false;
            }
        } else {
            //If there is only one checker, the move is legal
            if (b.getCount(new_pos) == 1) {
                return true;
            }

            //If the spike is empty or has the user's own checkers, the move is legal
            if ((b.getColor(new_pos) == neutral) || (b.getColor(new_pos) == current_player)) {
                return true;
            }
        }
        return false;
    }
    //With the current rolls, can the user move anywhere?

    private boolean CanMove() {
        int move1, move2;
        //Cycle through all the spikes
        for (int spike = 1; spike <= 24; spike++) {
            //Only check spikes which contain the player's pieces

            if (b.getColor(spike) == current_player || b.getColor(spike) == neutral || (b.getColor(spike) != current_player && b.getCount(spike) == 1)) {
                if (current_player == white) {
                    move1 = spike + b.getDice1();
                    move2 = spike + b.getDice2();
                } else {
                    move1 = spike - b.getDice1();
                    move2 = spike - b.getDice2();
                }
                if ((CheckFair(move1) && used_move != 1) || (CheckFair(move2) && used_move != 2)) {
                    return true;
                } //CheckFair() only allows bearing off with exact rolls.
                //If the player has no other option, moving with a roll greater than needed to bear off is legal
                else if (NeedsInexactRolls() && (move1 > 25 || move1 < 0 || move2 > 25 || move2 < 0)) {
                    return true;
                }
            }
        }
        return false;
    }
    //Returns wether the current player can't move anywhere else
    //and needs to be able to bear off with an inexact roll

    private boolean NeedsInexactRolls() {  //taşların gideceği yerlerdeki renklere bakılıyor gitmek için uygun ise ya da true oluyor.
        //taşlar tabladan çıkarılabiliyorsada ture
        boolean canmove = false;  //oynayamaz olarak set ediliyor
        int move1, move2;
        //Cycle through all the spikes
        for (int spike = 1; spike <= 24; spike++) {
            //Only check spikes which contain the player's pieces

            if (b.getColor(spike) == current_player) {
                if (current_player == white) {
                    move1 = spike + b.getDice1();
                    move2 = spike + b.getDice2();
                } else {
                    move1 = spike - b.getDice1();
                    move2 = spike - b.getDice2();
                }
                if ((CheckFair(move1) && used_move != 1) || (CheckFair(move2) && used_move != 2)) {
                    canmove = true;
                }
            }
        }
        if (!canmove && b.canBearOff(current_player)) {
            return true;
        } else {
            return false;
        }
    }
    //Moves checker from one position to another,
    //modifying the board object

    private void move(int color, int old_pos, int new_pos) {
        //If the move is coming from a bar, remove it from the bar
        //and add it to the spike

        //eğer kırık taş geri oyuna geliyorsa 
        if (old_pos == -1) {
            if (color == white) {
                b.white_bar--;
            } else {
                b.black_bar--;
            }
            b.setColumn(new_pos, b.getCount(new_pos) + 1, color);
        } //Move is coming from another spike
        else {

            //Decrease the checkers in the old spike

            b.setColumn(old_pos, b.getCount(old_pos) - 1, color);
            if (b.getCount(old_pos) == 0) {
                b.setColumn(old_pos, 0, neutral);
            }
            //Increase the checkers on the new spike
            b.setColumn(new_pos, b.getCount(new_pos) + 1, color);

        }
    }
    //Initialize the GUI
    //Sets up all the buttons

    private void setupGUI() {

        FButton[5].setBounds(460, 260, 135, 25);
        FButton[5].setVisible(true);
        FButton[5].setText(FUNC_1);
        FButton[5].addActionListener(this);
        FButton[5].setEnabled(false);

        FButton[6].setBounds(460, 295, 135, 25);
        FButton[6].setVisible(true);
        FButton[6].setText(FUNC_2);
        FButton[6].addActionListener(this);
        FButton[6].setEnabled(false);

        FButton[1].setBounds(460, 330, 135, 25);
        FButton[1].setVisible(true);
        FButton[1].setText(MakeMove);
        FButton[1].addActionListener(this);
        FButton[1].setEnabled(false);

        area.setBounds(460, 365, 135, 110);//400
        area.setVisible(true);
        getContentPane().add(area);

        for (int i = 0; i < 10; i++) {
            if (i < 5) {
                FPanel[i].setBounds(550 + ((i + 1) * 57), 30, 55, 210); // daha sonra burada bütün zar sütunlarını aynı yerde göstereceğiz.
            } else {
                FPanel[i].setBounds(550 + ((i - 4) * 57), 255, 55, 210);
            }

            FPanel[i].setVisible(false);
        }
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                DiceButton[i][j].setSize(55, 20);
            }
        }
    }
    /*=================================================
     * Overridden Methods 
     * ================================================*/
    //JBackgammon class constructor
    //Sets title bar, size, shows the window, and does the GUI

    private JBackgammon() {
        //A = new DegerAl();
        area = new JTextArea();
        rdice = new Random();
        setTitle("Tavla");
        setResizable(false);
        status = new Status();
        b = new board(this);
        arayuz = new Arayuz(this);
        diceSelectionState = new boolean[10];
        for (int i = 0; i < 10; i++) {
            diceSelectionState[i] = true;
        }
        //Call pack() since otherwise getItsets() does not work until the frame is shown
        pack();

        for (int i = 0; i < FButton.length; i++) {
            FButton[i] = new FixedButton(getContentPane(), this);
        }
        for (int i = 0; i < 10; i++) {
            FPanel[i] = new FixedDicePanel(getContentPane(), this);
            FPanel[i].setLayout(new GridLayout(10, 1));
        }
        for (int j = 0; j < 10; j++) {
            for (int i = 0; i < 10; i++) {
                DiceButton[i][j] = new FixedDiceButton(this, this);
                DiceButton[i][j].row = i;
                DiceButton[i][j].col = j;
                FPanel[j].add(DiceButton[i][j]);
                DiceButton[i][j].diceID = i + (j * 10);
            }
        }
        setSize(910, 518);
        setResizable(false);
        //Set up double buffering
        b_bimage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        g_buffer = b_bimage.createGraphics();//grafik olarak b_imagi arkaplan resmi yapt�k

        setupGUI();
        show();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        DiceNode nodeTemp;
        if (e.getActionCommand().equals(MakeMove)) {
            if (hamlesiVarmi()) {
                int veri[] = new int[8];

                long start = System.nanoTime();

                veri = arayuz.araZeka.kos();
                diceID = arayuz.araZeka.getDice().getDiceID();
                dice1 = arayuz.araZeka.getDice().getDice1();
                dice2 = arayuz.araZeka.getDice().getDice2();
//                if (current_player == black) {
//                    if (!(veri[0] == 25)) {
//                        veri[0] = 25 - veri[0];
//                    }
//                    if (!(veri[2] == 25)) {
//                        veri[2] = 25 - veri[2];
//                    }
//                    if (!(veri[4] == 25)) {
//                        veri[4] = 25 - veri[4];
//                    }
//                    if (!(veri[6] == 25)) {
//                        veri[6] = 25 - veri[6];
//                    }
//                    System.out.println("Siyah verisi");
//                    for (int i = 0; i < 8; i++) {
//                        System.out.println("veri " + i + "i:" + veri[i]);
//                    }
//                }else{
//                    System.out.println("Beyaz verisi");
//                    for (int i = 0; i < 8; i++) {
//                        System.out.println("veri " + i + "i:" + veri[i]);
//                    }
//                }
                
                long elapsed = System.nanoTime() - start;

                String msg = "Oynanacak hamleler:\n"
                        + "1. Hamle: " + veri[0] + ". Pul " + veri[1] + " ilerleyecek.\n"
                        + "1. Hamle: " + veri[2] + ". Pul " + veri[3] + " ilerleyecek.\n"
                        + "1. Hamle: " + veri[4] + ". Pul " + veri[5] + " ilerleyecek.\n"
                        + "1. Hamle: " + veri[6] + ". Pul " + veri[7] + " ilerleyecek.\n";
                JOptionPane.showMessageDialog(this, msg);

                EkleInfArea("Süre:" + elapsed + " ns\n");
//                secilenzaribul(veri);


                EkleInfArea("1. Zar:" + dice1 + "\n" + "2. Zar:" + dice2 + "\n");
                DoRoll(dice1, dice2);

                openCloseDice();

                //static diceID ye göre kullanılan zarı kapatıyor.
                if (rolledDiceCounter == 100) {
                    yenizarlar(true);
                    rolledDiceCounter = 0;
                }
                if (rolledDiceCounter == 90) {
                    ResetDiceSet();
                    yenizarlar(false);
                }

                int spike = 0;
                int kullanilan = 0;
                boolean kirikOynandimi = false;
                while ((hamleSayici < 4 && status.doublets && !diskalifiye) || (hamleSayici < 2 && !status.doublets && !diskalifiye)) {
                    hamleSayici++;


                    try {
                        if (hamleSayici == 1) {
                            old_dice = veri[1];
                            spike = veri[0];
                            kullanilan = zariSec(veri[1]);
                            if (dice1 != veri[1] && dice2 != veri[1]) {
                                throw new moveException("Hatalı zar:" + veri[1]);
                            }
                        } else if (hamleSayici == 2) {
                            spike = veri[2];
                            kullanilan = zariSec(veri[3]);
                            if (zarHatalimi(veri[3])) {
                                throw new moveException("Hatalı zar:" + veri[3]);
                            }
                        } else if (hamleSayici == 3) {
                            spike = veri[4];
                            kullanilan = zariSec(veri[5]);
                            if (old_dice != veri[5]) {
                                throw new moveException("Hatalı zar:" + veri[5]);
                            }
                        } else if (hamleSayici == 4) {
                            spike = veri[6];
                            kullanilan = zariSec(veri[7]);
                            if (old_dice != veri[7]) {
                                throw new moveException("Hatalı zar:" + veri[7]);
                            }
                        }

                        if (b.onBar(current_player)) {
                            if ((hamleSayici == 1 && veri[0] == 25) || (hamleSayici == 2 && veri[2] == 25) || (hamleSayici == 3 && veri[4] == 25) || (hamleSayici == 4 && veri[6] == 25)) {
                                if (current_player == white) {
                                    if (kullanilan == 1) {
                                        if ((b.getColor(dice1) != current_player) && (b.getCount(dice1) > 1)) {
                                            if (hamleSayici > 0 && kiriksonrakihamlesivarmi(dice1)) {
                                                throw new moveException("Hedef hatalı seçilmiş:" + (dice1));
                                            } else {
                                                used_move_sifirla = true;
                                            }
                                        }
                                    } else {
                                        if ((b.getColor(dice2) != current_player) && (b.getCount(dice2) > 1)) {
                                            if (hamleSayici > 0 && kiriksonrakihamlesivarmi(dice2)) {
                                                throw new moveException("Hedef hatalı seçilmiş:" + (dice2));
                                            } else {
                                                used_move_sifirla = true;
                                            }
                                        }
                                    }
                                } else {
                                    if (kullanilan == 1) {
                                        if ((b.getColor(25 - dice1) != current_player) && (b.getCount(25 - dice1) > 1)) {
                                            if (hamleSayici > 0 && kiriksonrakihamlesivarmi(dice1)) {
                                                throw new moveException("Hedef hatalı seçilmiş:" + (dice1));
                                            } else {
                                                used_move_sifirla = true;
                                            }
                                        }
                                    } else {
                                        if ((b.getColor(25 - dice2) != current_player) && (b.getCount(25 - dice2) > 1)) {
                                            if (hamleSayici > 0 && kiriksonrakihamlesivarmi(dice2)) {
                                                throw new moveException("Hedef hatalı seçilmiş:" + (dice2));
                                            } else {
                                                used_move_sifirla = true;
                                            }
                                        }
                                    }
                                }


                                HandleBar();
                                if (kullanilan == 1 && stuckFlag == false) {
                                    kirikOynandimi = true;
                                    SuperMove(potmove1, kullanilan);
                                } else if (kullanilan == 2 && stuckFlag == false) {
                                    kirikOynandimi = true;
                                    SuperMove(potmove2, kullanilan);
                                } else {
                                    if (kullanilan == 1 && stuckFlag == true) {
                                        //eğer kırık açıldı sonraki hamle oynanamıyorsa burada 
                                        //
                                        kiriksonrakihamlesivarmi(dice1);
                                    } else if (kullanilan == 2 && stuckFlag == true) {
                                        kiriksonrakihamlesivarmi(dice2);
                                    }
                                }
                            } else {
                                int yer = 0;
                                if (hamleSayici == 1) {
                                    yer = veri[0];
                                }
                                if (hamleSayici == 2) {
                                    yer = veri[2];
                                }
                                if (hamleSayici == 3) {
                                    yer = veri[4];
                                }
                                if (hamleSayici == 4) {
                                    yer = veri[6];
                                }
                                throw new moveException("Kırık taş var " + yer + " seçilemez.");
                            }


                        } else if (b.getColor(spike) != current_player) {

                            throw new moveException("Geçersiz taş alanı:" + spike);

                        } //else if (stuckFlag == false && !kirikOynandimi) {
                        else if (spike != 25) {

                            if (hamleSayici < 5 && moveisBearOff(spike, kullanilan)) {
                                System.out.println("Bearoff için");
                                old_spike = spike;
                                BearOff(kullanilan);
                            } else if (hamleSayici == 1) {
                                HamleyiYapan(spike, kullanilan);

                            } else if (hamleSayici == 2) {
                                //if ((b.white_bear < 12 && current_player == 1) || (b.black_bear < 12 && current_player == 2)) {
                                HamleyiYapan(spike, kullanilan);
                                //}

                            } else if (hamleSayici == 3 && status.doublets) {
                                //if ((b.white_bear < 11 && current_player == 1) || (b.black_bear < 11 && current_player == 2)) {
                                HamleyiYapan(spike, kullanilan);
                                //}

                            } else if (hamleSayici == 4 && status.doublets) {
                                //if ((b.white_bear < 10 && current_player == 1) || (b.black_bear < 10 && current_player == 2)) {
                                HamleyiYapan(spike, kullanilan);
                                //}
                            }
                        }
                    } catch (moveException e1) {
                        String msj = e1.getMessage() + (current_player == 1 ? "\nBeyaz Oyuncu diskalifiye" : "\nSiyah Oyuncu diskalifiye");
                        JOptionPane.showMessageDialog(this, msj);
                        FButton[5].setVisible(false);
                        FButton[6].setVisible(false);
                        FButton[1].setVisible(false);
                        repaint();
                    }
                    kirikOynandimi = false;
                }
            } else {
                String msg = "Hamle yok4";
                JOptionPane.showMessageDialog(this, msg);
                EndTurn();
            }
        } else if (e.getActionCommand().equals(FUNC_1)) {

            area.setText(null);
            hamleSayici = 0;

            FButton[5].setEnabled(false);
            sendDiceNodeList();
            repaint();
            arayuz.zekaSec(1);
            FButton[1].setEnabled(true);



        } else if (e.getActionCommand().equals(FUNC_2)) {
            area.setText(null);
            hamleSayici = 0;
            repaint();
            FButton[6].setEnabled(false);
            sendDiceNodeList();
            arayuz.zekaSec(2);

            FButton[1].setEnabled(true);
        }


    }

    @Override
    public void paint(Graphics g) {
        //Cast the Graphics to a Graphics2D so actual drawing methods
        //are available
        Graphics2D screen = (Graphics2D) g;
        g_buffer.clearRect(0, 0, getWidth(), getHeight());//
        drawBoard();
        drawBar();
        drawMen();//ta�lar�n ilk konumunu belirleyen fonksiyon
        drawBearStats();
        drawTurnWiever();
        drawDice();
        screen.drawImage(b_bimage, null, 0, 0);
        //Blit the buffer onto the screen

        FButton[0].repaint();
        FButton[1].repaint();
        FButton[2].repaint();
        //FButton[3].repaint();
        //FButton[4].repaint();
        FButton[5].repaint();
        FButton[6].repaint();
        FButton[7].repaint();
        area.repaint();
        for (int i = 0; i < 10; i++) {
            FPanel[i].repaint();
        }

    }

    public static void main(String args[]) {
        JBackgammon app = new JBackgammon();

        app.addWindowListener(
                new WindowAdapter() {

                    @Override
                    public void windowClosing(WindowEvent e) {
                        System.exit(0);
                    }
                });
        app.resetGame();
    }
    /*=================================================
     * Drawing Methods 
     * ================================================*/

    public int findX(int spike) //ta�lar dizilirken x ekseni koordinat�n� hesapl�yor.
    {
        if (spike <= 6) {
            return x_offset + 401 - (32 * (spike - 1)); //4 b�l�mden hangisi oldu�u belirlendikten sonra 32 ile �arparak son koordinat� belirliyor.
        }
        if (spike <= 12) {
            return x_offset + 161 - (32 * (spike - 7));
        }
        if (spike <= 18) {
            return x_offset + 1 + (32 * (spike - 13));
        }
        if (spike <= 24) {
            return x_offset + 241 + (32 * (spike - 19));
        }
        return -1;
    }

    public int findY(int spike)//y koordinat�n� hesaplar
    {
        if (spike <= 12) //�st taraftakiler 12 den k���k olarak hesaplan�r
        {
            return y_offset;
        }
        if (spike <= 24) {
            return y_offset + 361;  //361 ile toplanarak alt taraftakilerin y koordinat� hesaplanmaktad�r.
        }
        return -1;
    }

    private void drawBearStats() {
        String m1, m2;
        m1 = "Çıkarılan Beyaz Pullar: " + b.white_bear;
        m2 = "Çıkarılan Siyah Pullar: " + b.black_bear;

        g_buffer.setColor(Color.BLACK);   //new game buttonu �st�ndeki yaz�lar�n arkas�na siyah dikd�rtgen koymu�
        //g_buffer.fill(new Rectangle2D.Double(45, 450, 150, 30));  //Rectangle2D.Double(x,y,width,heigth)
        if (b.white_bear != 0) {
            putString(m1, 30, 470, Color.WHITE, 12);
        }
        if (b.black_bear != 0) {
            putString(m2, 30, 487, Color.WHITE, 12);
        }
    }

    private void putString(String message, int x, int y, Color c, int size) {
        g_buffer.setFont(new Font("Arial", Font.BOLD, size));
        g_buffer.setColor(c);
        g_buffer.drawString(message, x, y); //bear offdan sonra gelen say�lar�n s�rekli g�ncel halde yaz�lmas� sa�l�yo.
    }

    private void drawDice(int roll, int x, int y, Color dicecolor, Color dotcolor) {
        g_buffer.setColor(Color.gray);
        g_buffer.fill(new Rectangle2D.Double(x - 2, y - 2, 29, 29));
        g_buffer.setColor(Color.LIGHT_GRAY);
        g_buffer.fill(new Rectangle2D.Double(x, y, 29, 29));
        g_buffer.setColor(dicecolor);  //ilk hmlede dice color white 255,255,255
        g_buffer.fill(new Rectangle2D.Double(x, y, 27, 27)); //zar �izdiriyor.

        g_buffer.setColor(dotcolor); //zar i�indeki noktalar�n rengi

        switch (roll) //daha �nceden random belirlenmi� olan zar�n de�eri
        {
            case 1: //zar�n de�erine g�re noktalar yerle�tiriliyor.
                g_buffer.fill(new Rectangle2D.Double(x + 11, y + 11, 4, 4));
                break;
            case 2:
                g_buffer.fill(new Rectangle2D.Double(x + 2, y + 2, 4, 4));
                g_buffer.fill(new Rectangle2D.Double(x + 19, y + 19, 4, 4));
                break;
            case 3:
                g_buffer.fill(new Rectangle2D.Double(x + 2, y + 2, 4, 4));
                g_buffer.fill(new Rectangle2D.Double(x + 11, y + 11, 4, 4));
                g_buffer.fill(new Rectangle2D.Double(x + 19, y + 19, 4, 4));
                break;
            case 4:
                g_buffer.fill(new Rectangle2D.Double(x + 2, y + 2, 4, 4));
                g_buffer.fill(new Rectangle2D.Double(x + 19, y + 19, 4, 4));
                g_buffer.fill(new Rectangle2D.Double(x + 19, y + 2, 4, 4));
                g_buffer.fill(new Rectangle2D.Double(x + 2, y + 19, 4, 4));
                break;
            case 5:
                g_buffer.fill(new Rectangle2D.Double(x + 2, y + 2, 4, 4));
                g_buffer.fill(new Rectangle2D.Double(x + 19, y + 19, 4, 4));
                g_buffer.fill(new Rectangle2D.Double(x + 19, y + 2, 4, 4));
                g_buffer.fill(new Rectangle2D.Double(x + 2, y + 19, 4, 4));
                g_buffer.fill(new Rectangle2D.Double(x + 11, y + 11, 4, 4));
                break;
            case 6:
                g_buffer.fill(new Rectangle2D.Double(x + 2, y + 2, 4, 4));
                g_buffer.fill(new Rectangle2D.Double(x + 19, y + 19, 4, 4));
                g_buffer.fill(new Rectangle2D.Double(x + 19, y + 2, 4, 4));
                g_buffer.fill(new Rectangle2D.Double(x + 2, y + 19, 4, 4));
                g_buffer.fill(new Rectangle2D.Double(x + 2, y + 11, 4, 4));
                g_buffer.fill(new Rectangle2D.Double(x + 19, y + 11, 4, 4));
                break;
        }
    }
    /* drawTriangle: Draws a triangle with the point facing downward, takes in
    left corner coordinates and a number for color 
    hooks: status, g_buffer, old_spike */

    private void drawTriangle(int x, int y, int spike_color) {
        if (spike_color == 1) {
            g_buffer.setColor(spike_white);
        } else {
            g_buffer.setColor(spike_black);
        }

        Polygon tri = new Polygon(new int[]{x, x + 15, x + 30}, new int[]{y, y + 160, y}, 3);
        g_buffer.fillPolygon(tri);
        if (status.spike_selected) {
            if (old_spike == getSpikeNum(x, y)) {
                g_buffer.setColor(Color.RED);
            }
        }
        g_buffer.drawPolygon(tri);
    }
    /* drawTriangleRev: Draws a triangle with the point facing downward,
    takes in left corner coordinates and a number for color
    hooks: status, g_buffer, old_spike */

    private void drawTriangleRev(int x, int y, int spike_color) {
        if (spike_color == 0) {
            g_buffer.setColor(spike_white);
        } else {
            g_buffer.setColor(spike_black);
        }

        Polygon tri = new Polygon(new int[]{x, x + 15, x + 30}, new int[]{y, y - 160, y}, 3);
        g_buffer.fillPolygon(tri);
        if (status.spike_selected) {
            if (old_spike == getSpikeNum(x, y)) {
                g_buffer.setColor(Color.RED);
            }
        }
        g_buffer.drawPolygon(tri);
    }

    //Draws the JBackgammon board onto the buffer
    private void drawTurnWiever() {
        String m1;
        g_buffer.setColor(Color.gray);
        g_buffer.fill(new Rectangle2D.Double(483, 128, 60, 20));
        g_buffer.setColor(Color.LIGHT_GRAY);
        g_buffer.fill(new Rectangle2D.Double(487, 132, 60, 20));
        if (current_player == black) {
            m1 = "Oyuncu: Siyah";
            putString(m1, 485, 120, Color.WHITE, 12);
            g_buffer.setColor(Color.BLACK);
            g_buffer.fill(new Rectangle2D.Double(485, 130, 60, 20));
        }
        if (current_player == white) {
            m1 = "Oyuncu: Beyaz";
            putString(m1, 485, 120, Color.WHITE, 12);
            g_buffer.setColor(Color.WHITE);
            g_buffer.fill(new Rectangle2D.Double(485, 130, 60, 20));
        }
    }

    private void drawBoard() {
        //Set the green color
        g_buffer.setColor(new Color(205, 133, 63));
        //Draw the two halves of the board
        Rectangle2D.Double halfBoardA = new Rectangle2D.Double(x_offset, y_offset, 192, 360);
        Rectangle2D.Double halfBoardB = new Rectangle2D.Double(x_offset + 238, y_offset, 192, 360);
        g_buffer.draw(halfBoardA);
        g_buffer.fill(halfBoardA);
        g_buffer.draw(halfBoardB);
        g_buffer.fill(halfBoardB);
        //Draw the bar
        g_buffer.setColor(new Color(160, 82, 45));
        Rectangle2D.Double bar = new Rectangle2D.Double(x_offset + 192, y_offset, 46, 360);
        g_buffer.draw(bar);
        g_buffer.fill(bar);
        g_buffer.setColor(Color.WHITE);

        Integer j = 7;
        for (int i = 10; i <= 180; i += 32) {
            j--;
            String m1 = j.toString();
            g_buffer.setFont(new Font("Arial", Font.BOLD, 12));
            g_buffer.drawString(m1, x_offset + i + 240, y_offset - 5);

        }

        j = 13;
        for (int i = 10; i <= 180; i += 32) {
            j--;
            String m1 = j.toString();
            g_buffer.setFont(new Font("Arial", Font.BOLD, 12));
            g_buffer.drawString(m1, x_offset + i, y_offset - 5);

        }

        j = 12;
        for (int i = 10; i <= 180; i += 32) {
            j++;
            String m1 = j.toString();
            g_buffer.setFont(new Font("Arial", Font.BOLD, 12));
            g_buffer.drawString(m1, x_offset + i, y_offset + 375);

        }

        j = 18;
        for (int i = 10; i <= 180; i += 32) {
            j++;
            String m1 = j.toString();
            g_buffer.setFont(new Font("Arial", Font.BOLD, 12));
            g_buffer.drawString(m1, x_offset + i + 240, y_offset + 375);

        }
        int spike_color = 0;
        //Draw the spikes
        for (Integer i = 0; i <= 180; i += 32) {
            if (spike_color == 1) {
                spike_color = 0;
            } else {
                spike_color = 1;
            }
            drawTriangle(x_offset + i, y_offset, spike_color);
            drawTriangleRev(x_offset + i, y_offset + 360, spike_color);
            drawTriangle(x_offset + 240 + i, y_offset, spike_color);
            drawTriangleRev(x_offset + 240 + i, y_offset + 360, spike_color);
        }
    }

    private void drawBar() {
        g_buffer.setColor(new Color(110, 05, 30));
        g_buffer.drawRect(x_offset + 192, y_offset + 120, 46, 40);
        g_buffer.fill(new Rectangle2D.Double(x_offset + 192, y_offset + 120, 46, 40));
        g_buffer.fill(new Rectangle2D.Double(x_offset + 192, y_offset + 200, 46, 40));
        g_buffer.setColor(Color.WHITE);
        g_buffer.fill(new Rectangle2D.Double(x_offset + 192, y_offset + 160, 47, 40));

        if (b.onBar(white)) {
            g_buffer.setColor(clr_white);
            g_buffer.fill(new Ellipse2D.Double(x_offset + 201, y_offset + 205, 29, 29));
            if (b.white_bar > 1) {
                putString(String.valueOf(b.white_bar), 232, 285, Color.RED, 15);
            }
        }
        if (b.onBar(black)) {
            g_buffer.setColor(clr_black);
            g_buffer.fill(new Ellipse2D.Double(x_offset + 201, y_offset + 125, 29, 29));
            if (b.black_bar > 1) {
                putString(String.valueOf(b.black_bar), 232, 205, Color.RED, 15);
            }
        }
    }

    private void drawMen() {

        for (int spike = 1; spike <= 12; spike++) {
            if ((b.getCount(spike) > 0) && (b.getCount(spike) < 6)) {
                for (int i = 0; i < b.getCount(spike); i++) {

                    if (b.getColor(spike) == white) {
                        g_buffer.setColor(clr_white);
                    } else {
                        g_buffer.setColor(clr_black);
                    }
                    g_buffer.fill(new Ellipse2D.Double(findX(spike), findY(spike) + i * 30, 29, 29));
                }
            }
            if (b.getCount(spike) > 5) {
                for (int i = 0; i < 5; i++) {

                    if (b.getColor(spike) == white) {
                        g_buffer.setColor(clr_white);
                    } else {
                        g_buffer.setColor(clr_black);
                    }
                    g_buffer.fill(new Ellipse2D.Double(findX(spike), findY(spike) + i * 30, 29, 29));
                }
                putString(String.valueOf(b.getCount(spike)), findX(spike) + 10, 235, Color.RED, 15);
            }
        }

        for (int spike = 13; spike <= 24; spike++) {
            if ((b.getCount(spike) > 0) && (b.getCount(spike) < 6)) {
                for (int i = 0; i < b.getCount(spike); i++) {

                    if (b.getColor(spike) == white) {
                        g_buffer.setColor(clr_white);
                    } else {
                        g_buffer.setColor(clr_black);
                    }
                    g_buffer.fill(new Ellipse2D.Double(findX(spike), findY(spike) - 30 - i * 30, 29, 29));
                }
            }
            if (b.getCount(spike) > 5) {
                for (int i = 0; i < 5; i++) {

                    if (b.getColor(spike) == white) {
                        g_buffer.setColor(clr_white);
                    } else {
                        g_buffer.setColor(clr_black);
                    }
                    g_buffer.fill(new Ellipse2D.Double(findX(spike), findY(spike) - 30 - i * 30, 29, 29));
                }
                putString(String.valueOf(b.getCount(spike)), findX(spike) + 10, 255, Color.RED, 15);
            }
        }
    }

    private int getSpikeNum(int spike_x, int spike_y) {
        int quad = 0;
        int half = 0;
        int i = 1;
        //Find which portion of the board the click occured in
        if (spike_y >= 200) {
            half = 1;
        }

        if (spike_x >= 238) {
            spike_x -= 238;
            quad = 1;
        }
        //Find how many times we can subtract 32 from the position
        //while remaining positive
        for (i = 1; spike_x >= 32; spike_x -= 32) {
            i++;
        }

        //Compensate for top/bottom and left/right
        if (half == 0) {
            if (quad == 0) {
                i = (6 - i) + 7;
            } else {
                i = (6 - i) + 1;
            }
        } else {
            if (quad == 0) {
                i += 12;
            } else {
                i += 18;
            }
        }
        return i;
    }

    private void sendDiceNodeList() {
        LinkedList<DiceNode> diceNodeList = new LinkedList<DiceNode>();
        LinkedList<DiceNode> secondDiceList = new LinkedList<DiceNode>();

        if (rolledDiceCounter % 10 == 0) {
            for (int i = 0; i < 10; i++) {
                DiceNode node = new DiceNode(DiceButton[i][rolledDiceCounter / 10].dice1, DiceButton[i][rolledDiceCounter / 10].dice2, DiceButton[i][rolledDiceCounter / 10].diceID);
                diceNodeList.add(node);
            }
        } else {
            for (int i = 0; i < 10; i++) {
                if (diceSelectionState[i]) {
                    DiceNode node = new DiceNode(DiceButton[i][rolledDiceCounter / 10].dice1, DiceButton[i][rolledDiceCounter / 10].dice2, DiceButton[i][rolledDiceCounter / 10].diceID);
                    diceNodeList.add(node);
                }
            }
        }
        for (int i = 0; i < 10; i++) {
            if ((rolledDiceCounter / 10) + 1 < 10) {
                DiceNode node = new DiceNode(DiceButton[i][(rolledDiceCounter / 10) + 1].dice1, DiceButton[i][(rolledDiceCounter / 10) + 1].dice2, DiceButton[i][(rolledDiceCounter / 10) + 1].diceID);
                secondDiceList.add(node);
            } else {
                DiceNode node = new DiceNode(yedekDiceButton[i][0].dice1, yedekDiceButton[i][0].dice2, yedekDiceButton[i][0].diceID);
                secondDiceList.add(node);
            }
        }
        arayuz.setDiceNodeList(diceNodeList);
        arayuz.setSecondDiceList(secondDiceList);
        arayuz.setCount(b.count);
        arayuz.setType(b.type);
        arayuz.setCurrent_player(current_player);
        arayuz.setWhite_bar_bearoff(b.white_bar, b.white_bear);
        arayuz.setBlack_bar_bearoff(b.black_bar, b.black_bear);
    }

    private void resetGame() { //yeni oyun başlatılacağı zaman componentleri reset eder.
        // Reset JBackgammon data/
        used_move = 0;
        old_spike = 0;
        current_player = rdice.nextInt(2) + 1;  //youna ilk başlayacak tarafı seçer.
        current_player = white;
        doublet_moves = 0;

        FButton[2].setEnabled(false);
        FButton[7].setEnabled(true);
        FButton[7].setVisible(true);
        FButton[1].setEnabled(false);
        FButton[5].setVisible(true);
        FButton[6].setVisible(true);
        if (current_player == white) {
            FButton[5].setEnabled(true);
            FButton[6].setEnabled(false);
        } else if (current_player == black) {
            FButton[5].setEnabled(false);
            FButton[6].setEnabled(true);
        }

        // Re-create the board
        b = new board(this);
        int dicea;
        int diceb;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                dicea = rdice.nextInt(6) + 1;
                diceb = rdice.nextInt(6) + 1;
                DiceButton[i][j].setText("" + dicea + "," + diceb);
                DiceButton[i][j].dice1 = dicea;
                DiceButton[i][j].dice2 = diceb;
                DiceButton[i][j].setEnabled(false);
            }
            FPanel[i].setVisible(true);
        }
        for (int i = 0; i < 10; i++) {
            DiceButton[i][0].setEnabled(true);
        }
        // Have the Status object reset game values, keep network value
        area.setText(null);
        status.newGame();
        repaint();
    }

    private void ResetDiceSet() {
        int dicea;
        int diceb;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                yedekDiceButton[i][j] = new FixedDiceButton(this, this);
                dicea = rdice.nextInt(6) + 1;
                diceb = rdice.nextInt(6) + 1;
                yedekDiceButton[i][j].diceID = j + (i * 10);
                yedekDiceButton[i][j].dice1 = dicea;
                yedekDiceButton[i][j].dice2 = diceb;
                yedekDiceButton[i][j].setText("" + dicea + "," + diceb);
                yedekDiceButton[i][j].setEnabled(false);
            }
        }
        //PanelIndex = 0;
    }

    private void onluAc() {
        if (rolledDiceCounter % 10 == 0) {
            for (int i = 0; i < 10; i++) {
                diceSelectionState[i] = true;
            }
        }
    }

    private void EkleInfArea(String gelen) {
        String bilgi = area.getText();
        bilgi = bilgi + gelen;
        area.setText(bilgi);
    }

    private void openCloseDice() {
        for (int j = 0; j < 10; j++) {
            if ((DiceButton[diceID % 10][diceID / 10].isEnabled())) {
                if (stuckFlag) {

                    for (int i = 0; i < 10; i++) {
                        DiceButton[i][PanelIndex].setEnabled(diceSelectionState[i]);
                    }
                    stuckFlag = false;
                } //        else JBack.FPanel[j].setVisible(false);
                else {
                    for (int i = 0; i < 10; i++) {
                        DiceButton[i][PanelIndex].setEnabled(false);
                    }
                }
            }
        }
        DiceButton[diceID % 10][diceID / 10].setEnabled(false);  //zarı oynandı olarak işaretle
        diceSelectionState[DiceButton[diceID % 10][diceID / 10].row] = false;  //zarın oynandığını işaretle
        PanelIndex = (rolledDiceCounter / 10);
        rolledDiceCounter++;
    }

    private void drawDice() {
        if (b.rolled) {
            if (current_player == white) {
                drawDice(b.getDice1(), 479, 200, Color.WHITE, Color.BLACK);
                drawDice(b.getDice2(), 529, 200, Color.WHITE, Color.BLACK);
            } else {
                drawDice(b.getDice1(), 479, 200, clr_black, Color.WHITE);
                drawDice(b.getDice2(), 529, 200, clr_black, Color.WHITE);
            }
        }
    }

    private int KullanicidanDegerOku() {  //klavyerden int değer almak için tanımalnmış oyunun işleyişini etkileyen bir metod değil
        Integer okunan = 0;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String line;
            System.out.println("Sayi gir:");
            line = reader.readLine();
            okunan = Integer.parseInt(line);
            System.out.println("Girilen değer:" + okunan);




        } catch (IOException ex) {
            Logger.getLogger(JBackgammon.class.getName()).log(Level.SEVERE, null, ex);
        }
        return okunan;
    }

    private boolean moveisBearOff(int spike, int diceNumber) {
        if (diceNumber == 1) {
            return hamleBearOffMu(spike, dice1);
        } else if (diceNumber == 2) {
            return hamleBearOffMu(spike, dice2);
        }
        return false;
    }

    private boolean zarHatalimi(int veriDice) {
        if (status.doublets && old_dice != veriDice) {
            return true;
        } else if (!status.doublets && (old_dice == veriDice)) {
            return true;
        } else if (!status.doublets) {
            if (old_dice == dice1 && dice1 == veriDice) {
                return true;
            } else if (old_dice == dice2 && dice2 == veriDice) {
                return true;
            }
        }
        return false;
    }

    private void HamleyiYapan(int spike, int kullanilan) throws moveException {
        if (kullanilan == 1) {
            if (current_player == white) {
                if (spike + dice1 < 25) {
                    if ((b.getColor(spike + dice1) != current_player) && (b.getCount(spike + dice1) > 1)) {
                        throw new moveException("Hedef hatalı seçilmiş:" + (spike + dice1));
                    }
                    HandleSpike(spike);
                    SuperMove(potmove1, 1);
                } else {
                    throw new moveException("Hedef hatalı seçilmiş:" + (spike + dice1));
                }
            } else if (current_player == black) {
                if (spike - dice1 > 0) {
                    if ((b.getColor(spike - dice1) != current_player) && (b.getCount(spike - dice1) > 1)) {
                        throw new moveException("Hedef hatalı seçilmiş:" + (spike - dice1));
                    }
                    HandleSpike(spike);
                    SuperMove(potmove1, 1);
                } else {
                    throw new moveException("Hedef hatalı seçilmiş:" + (spike - dice1));
                }
            }
        } else {
            if (current_player == white) {
                if (spike + dice2 < 25) {
                    if ((b.getColor(spike + dice2) != current_player) && (b.getCount(spike + dice2) > 1)) {
                        throw new moveException("Hedef hatalı seçilmiş:" + (spike + dice2));
                    }
                    HandleSpike(spike);
                    SuperMove(potmove2, 2);
                } else {
                    throw new moveException("Hedef hatalı seçilmiş:" + (spike + dice2));
                }
            } else if (current_player == black) {
                if (spike - dice2 > 0) {
                    if ((b.getColor(spike - dice2) != current_player) && (b.getCount(spike - dice2) > 1)) {
                        throw new moveException("Hedef hatalı seçilmiş:" + (spike - dice2));
                    }
                    HandleSpike(spike);
                    SuperMove(potmove2, 2);
                } else {
                    throw new moveException("Hedef hatalı seçilmiş:" + (spike - dice2));
                }
            }
        }
    }

    private int zariSec(int veri) {
        int kullanilan = 0;
        if (doublet_moves != 0) {
            if (doublet_moves == 4 || doublet_moves == 2) {
                kullanilan = 1;
            } else {
                kullanilan = 2;
            }
        } else if (dice1 == veri) {
            kullanilan = 1;
        } else {
            kullanilan = 2;
        }
        return kullanilan;
    }

    private void yenizarlar(boolean tumzarlar) {
        if (tumzarlar) { //100 zarın tamamı yeniden set edilir.
            for (int j = 0; j < 10; j++) {
                for (int i = 0; i < 10; i++) {
                    DiceButton[i][j].setText(yedekDiceButton[i][j].getText());
                    DiceButton[i][0].dice1 = yedekDiceButton[i][0].dice1;
                    DiceButton[i][0].dice2 = yedekDiceButton[i][0].dice2;
                }
            }
            repaint();
        } else {  //ilk 10 zar set edilir.
            for (int i = 0; i < 10; i++) {
                DiceButton[i][0].setText(yedekDiceButton[i][0].getText());
                DiceButton[i][0].dice1 = yedekDiceButton[i][0].dice1;
                DiceButton[i][0].dice2 = yedekDiceButton[i][0].dice2;
            }
        }
    }

    private boolean hamlesiVarmi() {
        LinkedList<DiceNode> diceNodeList = new LinkedList<DiceNode>();
        for (int i = 0; i < 10; i++) {
            if (DiceButton[i][rolledDiceCounter / 10].isEnabled()) {
                DiceNode node = new DiceNode(DiceButton[i][rolledDiceCounter / 10].dice1, DiceButton[i][rolledDiceCounter / 10].dice2, DiceButton[i][rolledDiceCounter / 10].diceID);
                diceNodeList.add(node);
            }
        }
        DiceNode diceNode;
        for (Iterator it = diceNodeList.iterator(); it.hasNext();) {
            diceNode = (DiceNode) it.next();
            int birincizar = diceNode.getDice1();
            int ikincizar = diceNode.getDice2();


            if (b.onBar(current_player)) {
                if (current_player == white) {
                    if ((b.getColor(birincizar) == current_player) || (b.getCount(birincizar) < 2)) {
                        return true;
                    }
                    if ((b.getColor(ikincizar) == current_player) || (b.getCount(ikincizar) < 2)) {
                        return true;
                    }
                } else if (current_player == black) {
                    if ((b.getColor(25 - birincizar) == current_player) || (b.getCount(25 - birincizar) < 2)) {
                        return true;
                    }
                    if ((b.getColor(25 - ikincizar) == current_player) || (b.getCount(25 - ikincizar) < 2)) {
                        return true;
                    }
                }

            } else if (current_player == white) {  //oyuncu beyaz //bulunduuğu yer beyaz
                for (int spike = 1; spike < 25; spike++) {
                    if (b.canBearOff(white) && (hamleBearOffMu(spike, birincizar) || hamleBearOffMu(spike, ikincizar))) {
                        return true;
                    }
                    if ((spike + birincizar < 25) && b.getColor(spike) == white) {            //hedef tahtanın içerisinde
                        if ((b.getColor(spike + birincizar) == current_player) || (b.getCount(spike + birincizar) < 2)) {
                            return true;
                        }

                    } else if ((spike + ikincizar < 25) && b.getColor(spike) == white) {
                        if ((b.getColor(spike + ikincizar) == current_player) || (b.getCount(spike + ikincizar) < 2)) {
                            return true;
                        }

                    }
                }

            } else if (current_player == black) {  //oyuncu beyaz //bulunduuğu yer beyaz

                for (int spike = 1; spike < 25; spike++) {
                    if (b.canBearOff(black) && (hamleBearOffMu(spike, birincizar) || hamleBearOffMu(spike, ikincizar))) {
                        return true;
                    }
                    if ((spike - birincizar > 0 && b.getColor(spike) == black)) {            //hedef tahtanın içerisinde
                        if ((b.getColor(spike - birincizar) == current_player) || (b.getCount(spike - birincizar) < 2)) {
                            return true;
                        }

                    } else if (spike - ikincizar > 0 && b.getColor(spike) == black) {
                        if ((b.getColor(spike - ikincizar) == current_player) || (b.getCount(spike - ikincizar) < 2)) {
                            return true;
                        }

                    }

                }


            }
        }
        return false;
    }

    private boolean hamleBearOffMu(int spike, int dice) {
        int toplam = 0;
        if (b.canBearOff(current_player)) {// TA�?IN YERİ VE ZARA GÖRE ÇIKARILIP ÇIKARILMADI�?NA BAKIYOR.
            if (current_player == white) {

                if (dice + spike == 25) {
                    return true;
                } else if (dice + spike > 24) {
                    for (int i = 19; i < spike; i++) {
                        if (b.getColor(i) == white) {
                            toplam += b.getCount(i);
                        }
                    }
                    if (toplam > 0) {
                        return false;
                    } else {
                        return true;
                    }
                }
            } else {
                if (spike - dice == 0) {
                    return true;
                } else if (spike - dice < 0) {
                    for (int i = 6; i > spike; i--) {
                        if (b.getColor(i) == black) {
                            toplam += b.getCount(i);
                        }
                    }
                    if (toplam > 0) {
                        return false;
                    } else {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean buHamleOlurmu(int spike, int birincizar) {

        if (b.onBar(current_player)) {
            if (current_player == white) {
                if ((b.getColor(birincizar) == current_player) || (b.getCount(birincizar) < 2)) {
                    return true;
                }
            } else if (current_player == black) {
                if ((b.getColor(25 - birincizar) == current_player) || (b.getCount(25 - birincizar) < 2)) {
                    return true;
                }
            }
        } else if (current_player == white) {  //oyuncu beyaz //bulunduuğu yer beyaz
            if (b.canBearOff(white)) {
                return (hamleBearOffMu(spike, birincizar));
            }
            if ((spike + birincizar < 25) && b.getColor(spike) == white) {            //hedef tahtanın içerisinde
                if ((b.getColor(spike + birincizar) == current_player) || (b.getCount(spike + birincizar) < 2)) {
                    return true;
                }
            }
        } else if (current_player == black) {  //oyuncu beyaz //bulunduuğu yer beyaz
            if (b.canBearOff(black)) {
                return (hamleBearOffMu(spike, birincizar));
            }
            if ((spike - birincizar > 0 && b.getColor(spike) == black)) {            //hedef tahtanın içerisinde
                if ((b.getColor(spike - birincizar) == current_player) || (b.getCount(spike - birincizar) < 2)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean kiriksonrakihamlesivarmi(int dice) {
        LinkedList<DiceNode> diceNodeList = new LinkedList<DiceNode>();
        for (int i = 0; i < 10; i++) {
            if (DiceButton[i][rolledDiceCounter / 10].isEnabled()) {
                DiceNode node = new DiceNode(DiceButton[i][rolledDiceCounter / 10].dice1, DiceButton[i][rolledDiceCounter / 10].dice2, DiceButton[i][rolledDiceCounter / 10].diceID);
                if (node.getDice1() != dice && node.getDice2() != dice) {
                    diceNodeList.add(node);
                }
            }
        }
        DiceNode diceNode;
        for (Iterator it = diceNodeList.iterator(); it.hasNext();) {
            diceNode = (DiceNode) it.next();
            int birincizar = diceNode.getDice1();
            int ikincizar = diceNode.getDice2();


            if (b.onBar(current_player)) {
                if (current_player == white) {
                    if ((b.getColor(birincizar) == current_player) || (b.getCount(birincizar) < 2)) {
                        return true;
                    }
                    if ((b.getColor(ikincizar) == current_player) || (b.getCount(ikincizar) < 2)) {
                        return true;
                    }
                } else if (current_player == black) {
                    if ((b.getColor(25 - birincizar) == current_player) || (b.getCount(25 - birincizar) < 2)) {
                        return true;
                    }
                    if ((b.getColor(25 - ikincizar) == current_player) || (b.getCount(25 - ikincizar) < 2)) {
                        return true;
                    }
                }
            }
//                else if (current_player == white) {  //oyuncu beyaz //bulunduuğu yer beyaz
//                for (int spike = 1; spike < 25; spike++) {
//                    if (b.canBearOff(white) && (hamleBearOffMu(spike, birincizar) || hamleBearOffMu(spike, ikincizar))) {
//                        return true;
//                    }
//                    if ((spike + birincizar < 25) && b.getColor(spike) == white) {            //hedef tahtanın içerisinde
//                        if ((b.getColor(spike + birincizar) == current_player) || (b.getCount(spike + birincizar) < 2)) {
//                            return true;
//                        }
//
//                    } else if ((spike + ikincizar < 25) && b.getColor(spike) == white) {
//                        if ((b.getColor(spike + ikincizar) == current_player) || (b.getCount(spike + ikincizar) < 2)) {
//                            return true;
//                        }
//
//                    }
//                }
//
//            } else if (current_player == black) {  //oyuncu beyaz //bulunduuğu yer beyaz
//
//                for (int spike = 1; spike < 25; spike++) {
//                    if (b.canBearOff(black) && (hamleBearOffMu(spike, birincizar) || hamleBearOffMu(spike, ikincizar))) {
//                        return true;
//                    }
//                    if ((spike + birincizar < 25 && b.getColor(spike) == black)) {            //hedef tahtanın içerisinde
//                        if ((b.getColor(spike - birincizar) == current_player) || (b.getCount(spike - birincizar) < 2)) {
//                            return true;
//                        }
//
//                    } else if (spike + ikincizar < 25 && b.getColor(spike) == black) {
//                        if ((b.getColor(spike - ikincizar) == current_player) || (b.getCount(spike - ikincizar) < 2)) {
//                            return true;
//                        }
//
//                    }
//
//                }
//
//
//            }
        }
        return false;
    }

    private boolean sonrakiHamlesiOynanirMi(int birincizar) {
        if (b.onBar(current_player)) {
            if (current_player == white) {
                if ((b.getColor(birincizar) == current_player) || (b.getCount(birincizar) < 2)) {
                    return true;
                }
            } else if (current_player == black) {
                if ((b.getColor(25 - birincizar) == current_player) || (b.getCount(25 - birincizar) < 2)) {
                    return true;
                }
            }
        } else if (current_player == white) {  //oyuncu beyaz //bulunduuğu yer beyaz
            for (int spike = 1; spike < 25; spike++) {
                if (b.canBearOff(white) && (hamleBearOffMu(spike, birincizar))) {
                    return true;
                }
                if ((spike + birincizar < 25) && b.getColor(spike) == white) {            //hedef tahtanın içerisinde
                    if ((b.getColor(spike + birincizar) == current_player) || (b.getCount(spike + birincizar) < 2)) {
                        return true;
                    }

                }
            }

        } else if (current_player == black) {  //oyuncu beyaz //bulunduuğu yer beyaz

            for (int spike = 1; spike < 25; spike++) {
                if (b.canBearOff(black) && (hamleBearOffMu(spike, birincizar))) {
                    return true;
                }
                if ((spike - birincizar > 0 && b.getColor(spike) == black)) {            //hedef tahtanın içerisinde
                    if ((b.getColor(spike - birincizar) == current_player) || (b.getCount(spike - birincizar) < 2)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
