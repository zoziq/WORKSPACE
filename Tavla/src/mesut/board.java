package mesut;

public class board {

    int count[];  //board �zerindeki s�tunlar�n say�s�
    int type[];
    static final int neutral = 0;
    static final int white = 1;
    static final int black = 2;
    boolean rolled = false; //zar att� true hamle yapt� false
    int dice1; //zar
    int dice2;
    int white_bar = 0;
    int black_bar = 0;
    int white_bear = 0;
    int black_bear = 0;
    int current_player;

    public board(JBackgammon game) {

        count = new int[25];
        type = new int[25];
        current_player = game.current_player;

        for (int i = 0; i < 25; i++) {
            count[i] = 0;
            type[i] = neutral;
        }
        //tahtaya ba�lang��ta ta�lar diziliyor.
        setColumn(1, 2, white);
        setColumn(6, 5, black);
        setColumn(8, 3, black);
        setColumn(12, 5, white);
        setColumn(13, 5, black);
        setColumn(17, 3, white);
        setColumn(19, 5, white);
        setColumn(24, 2, black);



//        setColumn(24, 2, white);
//        setColumn(18, 1, black);
//        setColumn(17, 2, white);
//        setColumn(18, 2, white);
//        setColumn(19, 2, white);
//        setColumn(20, 2, white);
//        setColumn(21, 2, white);
//        setColumn(22, 2, white);
//        setColumn(23, 2, white);
//        setColumn(16, 1, white);

//        setColumn(8, 3, black);
//        setColumn(6, 4, black);
//        setColumn(13, 5, black);
//        setColumn(15, 2, black);







//        setColumn(6, 4, white);
//        setColumn(18, 1, white);
//        setColumn(19, 2, white);

//        setColumn(13, 5, black);
//        setColumn(15, 2, black);

//        setColumn(7, 3, black);
//        setColumn(5, 3, black);
//        setColumn(4, 2, black);
//        setColumn(3, 1, black);
//       setColumn(2, 2, black);
//        setColumn(1, 2, black);

//        setColumn(6, 4, black);
//        setColumn(3, 3, black);








//
//        
//        setColumn(7, 1, black);
//        setColumn(2, 7, black);
//        
//        setColumn(18, 1, white);
//        setColumn(19, 1, white);
//        setColumn(20, 2, white);
//        setColumn(21, 1, white);
//        setColumn(22, 5, white);
//        setColumn(23, 5, white);

    }

    public int getColor(int col) {
        if (col == 25) {
            return current_player;
        } else {
            return type[col];
        }
    }

    public int getCount(int col) {
        if (col == 25) {
            System.out.println("Hata varsa eğer board getCount() içerisinde!");
            return 1;
        } else {
            return count[col];
        }
    }

    public void setColumn(int col, int num, int clr) {
        if (col != 25) {
            count[col] = num;
            if (num == 0) {
                clr = neutral;
            }
            type[col] = clr; //gelen ta��n rengi i�lenir
        } else {
            System.out.println("Hata varsa eğer board setColumn içerisinde ");
        }

    }

    public void rollDice(int d1, int d2) {

        dice1 = d1;
        dice2 = d2;
        rolled = true;
    }

    public int getDice1() {
        return dice1;
    }

    public int getDice2() {
        return dice2;
    }

    public void resetDice() {
        dice1 = 0;
        dice2 = 0;
        rolled = false;//zarlar� at�labilir hale getiriyor
    }

    public void moveToBar(int spike) //�u anda bulundu�umuz s�tun
    {
        if (getColor(spike) == white) {
            white_bar++;
        } else {
            black_bar++;
        }
        setColumn(spike, 0, neutral);
    }

    public int getBlack() {
        int sum = 0;

        for (int i = 1; i < 25; i++) {
            if (getColor(i) == black) {
                sum += getCount(i);
            }
        }

        return sum;
    }

    public int getWhite() {
        int sum = 0;

        for (int i = 1; i < 25; i++) {
            if (getColor(i) == white) {
                sum += getCount(i);
            }
        }

        return sum;
    }

    public boolean canBearOff(int color) {
        int sum = 0;

        if (color == white) {
            for (int i = 19; i <= 24; i++) {
                if (getColor(i) == white) {
                    sum += getCount(i);
                }
            }
            sum += white_bear;
        }

        if (color == black) {
            for (int i = 1; i <= 6; i++) {
                if (getColor(i) == black) {
                    sum += getCount(i);
                }
            }
            sum += black_bear;
        }

        if (sum == 15) {
            return true;         //There are 15 pieces in backgammon
        }
        return false;
    }

    public boolean onBar(int color) {
        if (color == white) {
            if (white_bar > 0) {
                return true;
            }
            return false;
        }

        if (color == black) {
            if (black_bar > 0) {
                return true;
            }
            return false;
        }

        return false;
    }

    public void setDice(int roll1, int roll2) {
        dice1 = roll1;
        dice2 = roll2;
        System.out.println("dice1:" + dice1 + "dice2" + dice2);
    }
}
