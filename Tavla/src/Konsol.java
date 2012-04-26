import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.RepaintManager;


public class Konsol extends JFrame{
	
	public void AI(final Graphics g){
		ilkSure = System.currentTimeMillis();
		
		listedenSecilenZarinSiraNumarasi = 0;
		secilenZarIkilisi = listedenZarSec(listedenSecilenZarinSiraNumarasi);
		oyuncuSirasi = 1;
		int zar1 = secilenZarIkilisi/10;
		int zar2 = secilenZarIkilisi%10;
		System.out.println(uygunTaslar(oyuncuSirasi,zar1,zar2));
		
	
		birinciTasNumarasi = 14;
		ikinciTasNumarasi = 13;
		ucuncuTasNumarasi = 7;
		dorduncuTasNumarasi = 6;	
	}
	
	public static int secilenZarIkilisi = 0;
	public static int listedenSecilenZarinSiraNumarasi = 0; // zar listesindeki ikiliyi belirleyen sira numarasi
	public static int oyuncuSirasi = 1; // 1. oyuncu, 2. oyuncu
	public static int birinciTasNumarasi; // oyuncunun hamle yapacagi birinci tas
	public static int ikinciTasNumarasi; // oyuncunun hamle yapacagi ikinci tas
	public static int ucuncuTasNumarasi; // oyuncunun hamle yapacagi birinci tas
	public static int dorduncuTasNumarasi; // oyuncunun hamle yapacagi ikinci tas
	
	public static int zarSetiBoyutu = 10;
	public static int zarSetiSayisi = 11;
	public static int zarListesi[][][] = new int[zarSetiSayisi][zarSetiBoyutu][2];//zarListesi[0][][] diye erisilecek
	public static int taslarinKonumu1[] = new int[15];
	public static int taslarinKonumu2[] = new int[15];
	public static ArrayList<Integer> oynayaBilirTaslar1 = new ArrayList<Integer>();
	public static ArrayList<Integer> oynayaBilirTaslar2 = new ArrayList<Integer>();
	//////////////////
	//NORMAL DIZILIM//
	//////////////////
//	public static int taslarX1[] = {420,420,420,420,420,270,270,270,30, 30, 30, 30, 30,720,720};
//	public static int taslarY1[] = {630,570,510,450,390,630,570,510,45,105,165,225,285, 45,105};
//	public static int taslarX2[] = {420,420,420,420,420,270,270,270, 30, 30, 30, 30, 30,720,720};
//	public static int taslarY2[] = { 45,105,165,225,285, 45,105,165,630,570,510,450,390,630,570};

	public static int taslarX1[] = {420,420,540,540,420,210,150,330,30, 30, 30,90,270,720,720};
	public static int taslarY1[] = {630,570,630,570,510,630,630,630,45,105,165,45, 45, 45,105};
	public static int taslarX2[] = {600,540,540,540,540,330,150,150, 30, 90, 90,270, 90,480,720};
	public static int taslarY2[] = { 45,225,165,105, 45, 45, 45,105,630,630,570,630,510,630,630 };

	public static int kirikSayisi1 = 0;
	public static int kirikSayisi2 = 0;
	
	public static int cikanSayisi1 = 0;
	public static int cikanSayisi2 = 0;
	public JLabel cikan1 = new JLabel("0");
	public JLabel cikan2 = new JLabel("0");
	
	public int sonOynananTasNumarasi = 0;
	public int sonOynananHangiTas = 0;
	
	public JTextField durumlar;
	public static String ts1;
	public static String ts2;

	
	public JTextField sureoync1;
	public JTextField sureoync2;
	public JTextField toplamsureoync1;
	public JTextField toplamsureoync2;
	public double oync1ToplamSure = 0;
	public double oync2ToplamSure = 0;
	public double ilkSure;
	
	public static int kontrol = 0;
	
	public static int hamle = 0;
	
	public Konsol() {
		setLayout(null);
		setBounds(-7,0,1450,720);
		setVisible(true);
		setBackground(Color.black);
	}
	
	public void randomZar() {
		for (int set = 0; set < zarListesi.length; set++) {
			for (int j = 0; j < zarListesi[set].length; j++) {
				for (int i = 0; i < 2; i++) {
					zarListesi[set][j][i] = (int)(Math.random()*6)+1;			
				}
			}
		}
		
	}
	
	public int listedenZarSec(int sira) {
		
		int secilenZar = zarListesi[0][sira][0]*10+zarListesi[0][sira][1];

		int liste[][] = new int[zarListesi[0].length - 1][2];
		for (int j = 0; j < zarListesi[0].length -1; j++) {
			for (int i = 0; i < 2; i++) {
				liste[j][i] = zarListesi[0][j][i];
			}
		}

		for (int j = sira; j < zarListesi[0].length -1; j++) {
			for (int i = 0; i < 2; i++) {
				liste[j][i] = zarListesi[0][j+1][i];
			}
		}
		zarListesi[0] = new int[zarListesi[0].length -1][2];
		for (int j = 0; j < zarListesi[0].length; j++) {
			for (int i = 0; i < 2; i++) {
				zarListesi[0][j][i] = liste[j][i];
			}
		}	
		hamle++;
		if(hamle==zarSetiBoyutu) {
			hamle=0;
			zarListesi[0] = new int[zarSetiBoyutu][2];
			for (int i = 0; i < zarListesi.length-1; i++) {
				zarListesi[i] = zarListesi[i+1];
			}		
			for (int i = 0; i < zarSetiBoyutu; i++) {
				zarListesi[zarListesi.length-1][i][0] = 0;
				zarListesi[zarListesi.length-1][i][1] = 0;
			}	
		}
		return secilenZar;
	}
	
	public void paint(final Graphics g) {
		super.paint(g);
		//////////////////
		//  TAHTA CIZ   //
		//////////////////
		g.setColor(new Color(130,60,20));
		g.fillRect(15, 30, 780, 15);
		g.setColor(new Color(130,60,20));
		g.fillRect(15, 690, 780, 15);
		g.setColor(new Color(130,60,20));
		g.fillRect(15, 30, 15, 660);
		g.setColor(new Color(130,60,20));
		g.fillRect(390, 30, 30, 660);
		g.setColor(new Color(130,60,20));
		g.fillRect(780, 30, 15, 660);
		
		int[][] xPoints = {{30,90,60},{90,150,120},{150,210,180},{210,270,240},{270,330,300},{330,390,360},{420,480,450},{480,540,510},{540,600,570},{600,660,630},{660,720,690},{720,780,750}};
		int[][] yPoints = {{45,45,330},{690,690,390}};
		for (int i = 0; i < xPoints.length; i++) {
			if (i%2==0) {
				g.setColor(Color.GRAY);
				g.fillPolygon(xPoints[i], yPoints[0], 3);
			}
			else {
				g.setColor(Color.RED);
				g.fillPolygon(xPoints[i], yPoints[0], 3);
			}
		}
		for (int i = 0; i < xPoints.length; i++) {
			if (i%2==1) {
				g.setColor(Color.GRAY);
				g.fillPolygon(xPoints[i], yPoints[1], 3);
			}
			else {
				g.setColor(Color.RED);
				g.fillPolygon(xPoints[i], yPoints[1], 3);
			}
		}
		//////////////////
		// TASLARI CIZ  //
		//////////////////
		for (int i = 0; i < 15; i++) {
			g.setColor(new Color(222,188,153));
			g.fillOval(taslarX1[i], taslarY1[i], 60, 60);
			g.setColor(Color.black);
			g.drawString(i + "", taslarX1[i]+25, taslarY1[i]+30);
		}
		for (int i = 0; i < 15; i++) {
			g.setColor(new Color(107,77,57));
			g.fillOval(taslarX2[i], taslarY2[i], 60, 60);
			g.setColor(Color.white);
			g.drawString(i + "", taslarX2[i]+25, taslarY2[i]+30);
		}
		
		//////////////////
		//  NUMARALAR   //
		//////////////////
		if (kirikSayisi1!=0) {
			g.setColor(Color.white);
			g.drawString(""+kirikSayisi1, 401, 290);
		}
		if (kirikSayisi2!=0) {
			g.setColor(Color.white);
			g.drawString(""+kirikSayisi2, 401, 440);
		}
		
		for (int i = 0; i < 6; i++) {
			g.setColor(Color.white);
			g.drawString(""+(i+1), 747-i*60, 703);
		}
		for (int i = 0; i < 3; i++) {
			g.setColor(Color.white);
			g.drawString(""+(i+7), 357-i*60, 703);
		}
		for (int i = 0; i < 3; i++) {
			g.setColor(Color.white);
			g.drawString(""+(i+10), 173-i*60, 703);
		}
		for (int i = 0; i < 6; i++) {
			g.setColor(Color.white);
			g.drawString(""+(i+13), 53+i*60, 42);
		}
		for (int i = 0; i < 6; i++) {
			g.setColor(Color.white);
			g.drawString(""+(i+19), 443+i*60, 42);
		}
		
		//////////////////
		// KOORDINAT    //
		//////////////////
		final JTextField xx = new JTextField();
		xx.setBounds(1300,633,50,25);
		add(xx);
		final JTextField yy = new JTextField();
		yy.setBounds(1300,658,50,25);
		add(yy);
		
		addMouseMotionListener(new MouseMotionListener() {
			@Override
			public void mouseMoved(MouseEvent a) {
				xx.setText(a.getX() + "");
				yy.setText(a.getY() + "");
			}
			@Override
			public void mouseDragged(MouseEvent arg0) {}
		});

		///////////////////
		//TASLARIN KONUMU//
		///////////////////
		JLabel o = new JLabel("Taþlarýn Konumu");
		o.setBounds(850,15,180,25);
		add(o);
		JLabel t = new JLabel("konum1 konum2");
		t.setBounds(850,45,180,25);
		add(t);

		String s1[] = konumHesapla(taslarX1, taslarY1, 1);
		String s2[] = konumHesapla(taslarX2, taslarY2, 2);
		
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 15; j++) {
				if (i==1) {
					JTextField tt = new JTextField(s2[j]);
					tt.setBounds(860+i*40,75+j*18,40,18);
					add(tt);
				}else {
					JTextField tt = new JTextField(s1[j]);
					tt.setBounds(860+i*40,75+j*18,40,18);
					add(tt);
				}
			}
		}
		JLabel o1 = new JLabel("Uygun Taþlar");
		o1.setBounds(970,15,180,25);
		add(o1);
		JLabel t1 = new JLabel("  tas1    tas2");
		t1.setBounds(970,45,180,25);
		add(t1);

		//////////////////
		// ZAR LISTESI  //
		//////////////////
		JLabel o2 = new JLabel("Zar Listesi");
		o2.setBounds(1185,45,180,25);
		add(o2);
		
		for (int set = 0; set < zarSetiSayisi; set++) {
			for (int j = 0; j < zarListesi[set].length; j++) {
				JTextField tt = new JTextField(zarListesi[set][j][0] + " - " + zarListesi[set][j][1]);
				tt.setBounds(1085+set*30,75+j*15,30,15);
				add(tt);
			}
		}
		if(kontrol==0){
			randomZar();
			kontrol ++;
		}
		//////////////////
		// UYARI MESAJI //
		//////////////////
		durumlar = new JTextField();
		durumlar.setBounds(850,630,212,25);
		add(durumlar);
		//////////////////
		//    SURE      //
		//////////////////
		JLabel sure = new JLabel("Son hamle süre");
		sure.setBounds(1070, 520, 100, 20);
		add(sure);
		JLabel toplamsure = new JLabel("Toplam süre");
		toplamsure.setBounds(1180, 520, 100, 20);
		add(toplamsure);
		sureoync1 = new JTextField();
		sureoync1.setBounds(1080, 550, 80, 25);
		add(sureoync1);
		sureoync2 = new JTextField();
		sureoync2.setBounds(1080, 575, 80, 25);
		add(sureoync2);
		toplamsureoync1 = new JTextField();
		toplamsureoync1.setBounds(1180, 550, 160, 25);
		add(toplamsureoync1);
		toplamsureoync2 = new JTextField();
		toplamsureoync2.setBounds(1180, 575, 160, 25);
		add(toplamsureoync2);
		
		//////////////////
		//     ACTION   //
		//////////////////
		JLabel oync1 = new JLabel("oyuncu1");
		oync1.setBounds(800, 550, 50, 20);
		add(oync1);
		JLabel oync2 = new JLabel("oyuncu2");
		oync2.setBounds(800, 575, 50, 20);
		add(oync2);
		JLabel l1 = new JLabel("tas1 no");
		l1.setBounds(860, 520, 50, 20);
		add(l1);
		JLabel l2 = new JLabel("tas2 no");
		l2.setBounds(920, 520, 50, 20);
		add(l2);
		
		final JTextField tas1 = new JTextField();
		tas1.setBounds(870,550,35,25);
		add(tas1);
		final JTextField zar1 = new JTextField();
		zar1.setBounds(920,550,35,25);
		add(zar1);
		final JTextField tas2 = new JTextField();
		tas2.setBounds(870,575,35,25);
		add(tas2);
		final JTextField zar2 = new JTextField();
		zar2.setBounds(920,575,35,25);
		add(zar2);
		
		final JButton AI = new JButton("oynat1");
		AI.setBounds(975,525,80,25);
		add(AI);
		AI.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				AI(g);
			}
		});
	
		if (oyuncuSirasi == 1) {
			final JButton oynat1 = new JButton("oynat1");
			oynat1.setBounds(975,550,80,25);
			add(oynat1);
			double l = System.currentTimeMillis()-ilkSure;
			l /= 1000;
			sureoync1.setText(l + " sn");
			oync1ToplamSure += l;
			toplamsureoync1.setText(oync1ToplamSure + " sn");
			oynat1.addActionListener(new ActionListener() {		
				public void actionPerformed(ActionEvent arg0) {		
					oynat(g,birinciTasNumarasi,secilenZarIkilisi/10,1);
					oynat(g,ikinciTasNumarasi,secilenZarIkilisi%10,1);
					if (secilenZarIkilisi/10 == secilenZarIkilisi%10) {
						oynat(g,ucuncuTasNumarasi,secilenZarIkilisi/10,1);
						oynat(g,dorduncuTasNumarasi,secilenZarIkilisi%10,1);
					}	
				}
			});
		}
		else if (oyuncuSirasi == 2) {
			final JButton oynat2 = new JButton("oynat2");
			oynat2.setBounds(975,575,80,25);
			add(oynat2);
			double l = System.currentTimeMillis()-ilkSure;
			l /= 1000;
			sureoync2.setText(l + " sn");
			oync2ToplamSure += l;
			toplamsureoync2.setText(oync2ToplamSure + " sn");	
			oynat2.addActionListener(new ActionListener() {		
				public void actionPerformed(ActionEvent arg0) {	
					oynat(g,birinciTasNumarasi,secilenZarIkilisi/10,2);
					oynat(g,ikinciTasNumarasi,secilenZarIkilisi%10,2);
					if (secilenZarIkilisi/10 == secilenZarIkilisi%10) {
						oynat(g,ucuncuTasNumarasi,secilenZarIkilisi/10,2);
						oynat(g,dorduncuTasNumarasi,secilenZarIkilisi%10,2);
					}	
				}
			});
		}
		
		
		
		
//		final JButton oynat1 = new JButton("oynat1");
//		oynat1.setBounds(975,550,80,25);
//		add(oynat1);
//		oynat1.addActionListener(new ActionListener() {		
//			@Override
//			public void actionPerformed(ActionEvent arg0) {
//				secilenZarIkilisi = listedenZarSec(listedenSecilenZarinSiraNumarasi);
//				oynat(g,Integer.parseInt(tas1.getText()),secilenZarIkilisi/10,1);
//				oynat(g,Integer.parseInt(zar1.getText()),secilenZarIkilisi%10,1);//zar1 den ikinci tas bilgisi aliniyor
//				double l = System.currentTimeMillis()-ilkSure;
//				l /= 1000;
//				sureoync1.setText(l + " sn");
//				oync1ToplamSure += l;
//				toplamsureoync1.setText(oync1ToplamSure + " sn");
//				ts1 = tas1.getText();
//			}
//		});
//		final JButton oynat2 = new JButton("oynat2");
//		oynat2.setBounds(975,575,80,25);
//		add(oynat2);
//		oynat2.addActionListener(new ActionListener() {		
//			@Override
//			public void actionPerformed(ActionEvent arg0) {
//				secilenZarIkilisi = listedenZarSec(listedenSecilenZarinSiraNumarasi);
//				oynat(g,Integer.parseInt(tas2.getText()),secilenZarIkilisi/10,2);
//				oynat(g,Integer.parseInt(zar2.getText()),secilenZarIkilisi%10,2);//zar1 den ikinci tas bilgisi aliniyorts2 = tas2.getText();
//				double l = System.currentTimeMillis()-ilkSure;
//				l /= 1000;
//				sureoync2.setText(l + " sn");
//				oync2ToplamSure += l;
//				toplamsureoync2.setText(oync2ToplamSure + " sn");	
//			}
//		});

		
	}

	public void oynat(Graphics g, int tasNumarasi, int zar, int hangiTas) {
		if (hangiTas==1) {
			if(taslarX1[tasNumarasi]==375 && taslarY1[tasNumarasi]==300 && kontrol(45, taslarX1[tasNumarasi]+405-zar*60, 1)) {
				System.out.println("kiriktan giris 1");
		        sonOynananTasNumarasi = tasNumarasi;
		        sonOynananHangiTas = hangiTas;
				kirikSayisi1--;
				taslarX1[tasNumarasi]=taslarX1[tasNumarasi]+405-zar*60;
				taslarY1[tasNumarasi]=-15;
				for (int i = 0; i < 15; i++) {
					if (Math.abs(taslarY1[i]%30)==15 && taslarX1[i]==taslarX1[tasNumarasi]) {
						taslarY1[tasNumarasi]+=60;
					}
				}
				repaint();
			}
			else if(taslarX1[tasNumarasi]<=720 && taslarX1[tasNumarasi]>=420 && taslarX1[tasNumarasi]-zar*60>=420 && Math.abs(taslarY1[tasNumarasi]%30)==15 && kontrol(45, taslarX1[tasNumarasi]-zar*60, 1)) {
				System.out.println("sag ustten sag uste 1");
				int gidecegiYer = taslarX1[tasNumarasi]-zar*60;
				haraket11(tasNumarasi, gidecegiYer);
			}
			else if(taslarX1[tasNumarasi]<=720 && taslarX1[tasNumarasi]>=420 && taslarX1[tasNumarasi]-zar*60<420 && Math.abs(taslarY1[tasNumarasi]%30)==15 && kontrol(45, taslarX1[tasNumarasi]-zar*60-30, 1)) {
				System.out.println("sag ustten sol uste 1");
				int gidecegiYer = taslarX1[tasNumarasi]-zar*60-30;
				haraket11(tasNumarasi, gidecegiYer);
			}
			else if(taslarX1[tasNumarasi]<=390 && taslarX1[tasNumarasi]>=30 && taslarX1[tasNumarasi]-zar*60>=30 && Math.abs(taslarY1[tasNumarasi]%30)==15 && kontrol(45, taslarX1[tasNumarasi]-zar*60, 1)) {
				System.out.println("sol ustten sol uste 1");
				int gidecegiYer = taslarX1[tasNumarasi]-zar*60;
				haraket11(tasNumarasi, gidecegiYer);
			}
			else if(taslarX1[tasNumarasi]<=390 && taslarX1[tasNumarasi]>=30 && taslarX1[tasNumarasi]-zar*60<=30 && Math.abs(taslarY1[tasNumarasi]%30)==15 && kontrol(60, -(taslarX1[tasNumarasi]-zar*60), 1)) {
				System.out.println("sol ustten sol alta 1");
		        sonOynananTasNumarasi = tasNumarasi;
		        sonOynananHangiTas = hangiTas;
				int gidecegiYer =  -(taslarX1[tasNumarasi]-zar*60);
				taslarX1[tasNumarasi]=gidecegiYer;
				taslarY1[tasNumarasi]=690;
				for (int i = 0; i < 15; i++) {
					if (taslarY1[i]%30==0 && taslarX1[i]==taslarX1[tasNumarasi]) {
						taslarY1[tasNumarasi]-=60;
					}
				}
				repaint();
			}
			else if(taslarX1[tasNumarasi]<=390 && taslarX1[tasNumarasi]>=30 && taslarX1[tasNumarasi]+zar*60<390 && Math.abs(taslarY1[tasNumarasi]%30)==0 && kontrol(60, taslarX1[tasNumarasi]+zar*60, 1)) {
				System.out.println("sol alttan sol alta 1");
				int gidecegiYer = taslarX1[tasNumarasi]+zar*60;
				haraket12(tasNumarasi, gidecegiYer);
			}
			else if(taslarX1[tasNumarasi]<=390 && taslarX1[tasNumarasi]>=30 && taslarX1[tasNumarasi]+zar*60+30<=780 && taslarX1[tasNumarasi]+zar*60+30>=390 && Math.abs(taslarY1[tasNumarasi]%30)==0 && kontrol(60, taslarX1[tasNumarasi]+zar*60+30, 1) && taslarX1[tasNumarasi]!=375 && taslarY1[tasNumarasi]!=300) {
				System.out.println("sol alttan sag alta 1");
				int gidecegiYer = taslarX1[tasNumarasi]+zar*60+30;
				haraket12(tasNumarasi, gidecegiYer);
			}
			else if(taslarX1[tasNumarasi]>=390 && taslarX1[tasNumarasi]<=780 && taslarX1[tasNumarasi]+zar*60<780 && Math.abs(taslarY1[tasNumarasi]%30)==0 && kontrol(60, taslarX1[tasNumarasi]+zar*60, 1)) {
				System.out.println("sag alttan sag alta 1");
				int gidecegiYer = taslarX1[tasNumarasi]+zar*60;
				haraket12(tasNumarasi, gidecegiYer);
			}
			else if(taslarX1[tasNumarasi]>=390 && taslarX1[tasNumarasi]<=780 && taslarX1[tasNumarasi]+zar*60>=780 && Math.abs(taslarY1[tasNumarasi]%30)==0) {
				System.out.println("sag alttan cikis 1");
		        sonOynananTasNumarasi = tasNumarasi;
		        sonOynananHangiTas = hangiTas;
				cikanSayisi1++;
				remove(cikan1);
				taslarX1[tasNumarasi]=795;
				taslarY1[tasNumarasi]=630;
				cikan1 = new JLabel(""+cikanSayisi1);
				cikan1.setBounds(815,640,60,60);
				add(cikan1);
				repaint();
			}
		}
		else if (hangiTas==2) {
			if(taslarX2[tasNumarasi]==375 && taslarY2[tasNumarasi]==360 && kontrol(60, taslarX2[tasNumarasi]+405-zar*60, 2)) {
				System.out.println("kiriktan giris 2");
		        sonOynananTasNumarasi = tasNumarasi;
		        sonOynananHangiTas = hangiTas;
				kirikSayisi2--;
				taslarX2[tasNumarasi]=taslarX2[tasNumarasi]+405-zar*60;
				taslarY2[tasNumarasi]=690;
				for (int i = 0; i < 15; i++) {
					if (Math.abs(taslarY2[i]%30)==0 && taslarX2[i]==taslarX2[tasNumarasi]) {
						taslarY2[tasNumarasi]-=60;
					}
				}
				repaint();
			}
			else if(taslarX2[tasNumarasi]<=720 && taslarX2[tasNumarasi]>=420 && taslarX2[tasNumarasi]-zar*60>=420 && taslarY2[tasNumarasi]%30==0 && kontrol(60, taslarX2[tasNumarasi]-zar*60, 2)) {
				System.out.println("sag alttan sag alta 2");
				int gidecegiYer = taslarX2[tasNumarasi]-zar*60;
				haraket21(tasNumarasi, gidecegiYer);
			}
			else if(taslarX2[tasNumarasi]<=720 && taslarX2[tasNumarasi]>=420 && taslarX2[tasNumarasi]-zar*60-30<390 && taslarX2[tasNumarasi]-zar*60-30>=30 && taslarY2[tasNumarasi]%30==0 && kontrol(60, taslarX2[tasNumarasi]-zar*60-30, 2)) {
				System.out.println("sag alttan sol alta 2");
				int gidecegiYer = taslarX2[tasNumarasi]-zar*60-30;
				haraket21(tasNumarasi, gidecegiYer);
			}
			else if(taslarX2[tasNumarasi]<=390 && taslarX2[tasNumarasi]>=30 && taslarX2[tasNumarasi]-zar*60>=30 && taslarY2[tasNumarasi]%30==0 && kontrol(60, taslarX2[tasNumarasi]-zar*60, 2) && taslarX2[tasNumarasi]!=375 && taslarY2[tasNumarasi]!=360) {
				System.out.println("sol alttan sol alta 2");
				int gidecegiYer = taslarX2[tasNumarasi]-zar*60;
				haraket21(tasNumarasi, gidecegiYer);
			}
			else if(taslarX2[tasNumarasi]<=390 && taslarX2[tasNumarasi]>=30 && taslarX2[tasNumarasi]-zar*60<30 && taslarY2[tasNumarasi]%30==0 && kontrol(45, -(taslarX2[tasNumarasi]-zar*60), 2)) {
				System.out.println("sol alttan sol uste 2");
		        sonOynananTasNumarasi = tasNumarasi;
		        sonOynananHangiTas = hangiTas;
				int gidecegiYer =  -(taslarX2[tasNumarasi]-zar*60);
				taslarX2[tasNumarasi]=gidecegiYer;
				taslarY2[tasNumarasi]=-15;
				for (int i = 0; i < 15; i++) {
					if (Math.abs(taslarY2[i]%30)==15 && taslarX2[i]==taslarX2[tasNumarasi]) {
						taslarY2[tasNumarasi]+=60;
					}
				}
				repaint();
			}
			else if(taslarX2[tasNumarasi]<=390 && taslarX2[tasNumarasi]>=30 && taslarX2[tasNumarasi]+zar*60<390 && Math.abs(taslarY2[tasNumarasi]%30)==15 && kontrol(45, taslarX2[tasNumarasi]+zar*60, 2)) {
				System.out.println("sol ustten sol uste 2");
				int gidecegiYer = taslarX2[tasNumarasi]+zar*60;
				haraket22(tasNumarasi, gidecegiYer);
			}
			else if(taslarX2[tasNumarasi]<=390 && taslarX2[tasNumarasi]>=30 && taslarX2[tasNumarasi]+zar*60+30<=780 && taslarX2[tasNumarasi]+zar*60+30>=390 && Math.abs(taslarY2[tasNumarasi]%30)==15 && kontrol(45, taslarX2[tasNumarasi]+zar*60+30, 2)) {
				System.out.println("sol ustten sag uste 2");
				int gidecegiYer = taslarX2[tasNumarasi]+zar*60+30;
				haraket22(tasNumarasi, gidecegiYer);
			}
			else if(taslarX2[tasNumarasi]>=420 && taslarX2[tasNumarasi]<780 && taslarX2[tasNumarasi]+zar*60<780 && taslarX2[tasNumarasi]+zar*60>=390 && Math.abs(taslarY2[tasNumarasi]%30)==15 && kontrol(45, taslarX2[tasNumarasi]+zar*60, 2)) {
				System.out.println("sag ustten sag uste 2");
				int gidecegiYer = taslarX2[tasNumarasi]+zar*60;
				haraket22(tasNumarasi, gidecegiYer);
			}
			else if(taslarX2[tasNumarasi]>=420 && taslarX2[tasNumarasi]<780 && taslarX2[tasNumarasi]+zar*60>=780 && Math.abs(taslarY2[tasNumarasi]%30)==15 && kontrol(45, taslarX2[tasNumarasi]+zar*60, 2)) {
				System.out.println("sag ustten cikis 2");
		        sonOynananTasNumarasi = tasNumarasi;
		        sonOynananHangiTas = hangiTas;
				cikanSayisi2++;
				remove(cikan2);
				taslarX2[tasNumarasi]=795;
				taslarY2[tasNumarasi]=45;
				cikan2 = new JLabel(""+cikanSayisi2);
				cikan2.setBounds(815,55,60,60);
				add(cikan2);
				repaint();
			}
		}

	}

	private void haraket11(int tasNumarasi, int gidecegiYer) {
        sonOynananTasNumarasi = tasNumarasi;
        sonOynananHangiTas = 1;
		for (int i = 0; i < 15; i++) {
			if (Math.abs(taslarY1[i]%30)==15 && taslarX1[i]==taslarX1[tasNumarasi]) {
				taslarY1[tasNumarasi]-=60;
			}
		}
		taslarX1[tasNumarasi] = gidecegiYer;
		for (int i = 0; i < 15; i++) {
			if (Math.abs(taslarY1[i]%30)==15 && taslarX1[i]==taslarX1[tasNumarasi]) {
				taslarY1[tasNumarasi]+=60;
			}
		}
		repaint();
	}
	
	private void haraket12(int tasNumarasi, int gidecegiYer) {
        sonOynananTasNumarasi = tasNumarasi;
        sonOynananHangiTas = 1;
		for (int i = 0; i < 15; i++) {
			if (taslarY1[i]%30==0 && taslarX1[i]==taslarX1[tasNumarasi]) {
				taslarY1[tasNumarasi]+=60;
			}
		}
		taslarX1[tasNumarasi] = gidecegiYer;
		for (int i = 0; i < 15; i++) {
			if (taslarY1[i]%30==0 && taslarX1[i]==taslarX1[tasNumarasi]) {
				taslarY1[tasNumarasi]-=60;
			}
		}
		repaint();
	}
	
	private void haraket21(int tasNumarasi, int gidecegiYer) {
        sonOynananTasNumarasi = tasNumarasi;
        sonOynananHangiTas = 2;
		for (int i = 0; i < 15; i++) {
			if (taslarY2[i]%30==0 && taslarX2[i]==taslarX2[tasNumarasi]) {
				taslarY2[tasNumarasi]+=60;
			}
		}
		taslarX2[tasNumarasi] = gidecegiYer;
		for (int i = 0; i < 15; i++) {
			if (taslarY2[i]%30==0 && taslarX2[i]==taslarX2[tasNumarasi]) {
				taslarY2[tasNumarasi]-=60;
			}
		}
		repaint();
	}
	
	private void haraket22(int tasNumarasi, int gidecegiYer) {
        sonOynananTasNumarasi = tasNumarasi;
        sonOynananHangiTas = 2;
		for (int i = 0; i < 15; i++) {
			if (Math.abs(taslarY2[i]%30)==15 && taslarX2[i]==taslarX2[tasNumarasi]) {
				taslarY2[tasNumarasi]-=60;
			}
		}
		taslarX2[tasNumarasi] = gidecegiYer;
		for (int i = 0; i < 15; i++) {
			if (Math.abs(taslarY2[i]%30)==15 && taslarX2[i]==taslarX2[tasNumarasi]) {
				taslarY2[tasNumarasi]+=60;
			}
		}
		repaint();
	}
	
	public boolean kontrol(int bolge,int gidecegiYer,int hangiTas) {
		if (hangiTas==1) {
			int sayac = 0;
			for (int i = 0; i < 15; i++) {
				if (taslarX2[i]==gidecegiYer && taslarY2[i]%30==bolge%30) {
					sayac++;
				}
			}
			if (sayac>1) {
				System.out.println("oynamaz");
				durumlar = new JTextField(ts1 + " numaralý taþ oynamaz");
				durumlar.setBounds(850,630,212,25);
				add(durumlar);
				return false;
			}
			else if (sayac==1) {
				kirikSayisi2++;
				for (int i = 0; i < 15; i++) {
					if (taslarX2[i]==gidecegiYer) {
						taslarX2[i]=375;
						taslarY2[i]=360;
						repaint();
					}
				}
				return true;
			}
		}
		else if (hangiTas==2) {
			int sayac = 0;
			for (int i = 0; i < 15; i++) {
				if (taslarX1[i]==gidecegiYer && taslarY1[i]%30==bolge%30) {
					sayac++;
				}
			}
			if (sayac>1) {
				System.out.println("oynamaz");
				durumlar = new JTextField(ts2 + " numaralý taþ oynamaz");
				durumlar.setBounds(850,630,212,25);
				add(durumlar);
				return false;
			}
			else if (sayac==1) {
				kirikSayisi1++;
				for (int i = 0; i < 15; i++) {
					if (taslarX1[i]==gidecegiYer) {
						taslarX1[i]=375;
						taslarY1[i]=300;
						repaint();
					}
				}
				return true;
			}
		}
		return true;
	}
	
	public String[] konumHesapla(int[] x, int[] y, int h) {
		int knm[] = new int[15];
		String dizi[] = new String[15];
		String s = "";
		for (int i = 0; i < x.length; i++) {
			int konum = 0;
			if (y[i]%30==15) {
				konum = (x[i]+30)/60+12;
			}else {
				konum = 13-(x[i]+30)/60;
			}
			if (x[i]==375 && y[i]==300) {
				konum = 25;
			}
			if (x[i]==375 && y[i]==360) {
				konum = 0;
			}
			
			if (i/10>=1) {
				s += i + " -" + konum;
			}else {
				s += i + "  - " + konum;;
			}
			dizi[i] = s;
			s = "";
			knm[i] = konum;
			if (h == 1) {
				taslarinKonumu1[i] = konum;
			}
			else if (h ==2) {
				taslarinKonumu2[i] = konum;
			}
		}
		return dizi;
	}
	
	public ArrayList<Integer> uygunTaslar(int oyuncu, int zar1, int zar2){
		ArrayList<Integer> A = new ArrayList<Integer>(); 
		ArrayList<Integer> liste1 = new ArrayList<Integer>(); 
		ArrayList<Integer> liste2 = new ArrayList<Integer>(); 
		if (zar1 != zar2) {
			if (oyuncu == 1) {
				int sayac = 0;
				for (int i1 = 0; i1 < 15; i1++) {
					for (int i2 = 0; i2 < 15; i2++) {
						if(taslarinKonumu1[i1]-zar1 == taslarinKonumu2[i2]){
							sayac ++;
						}
					}
					if (sayac < 2) {
						int kontrol = 0;
						for (int i = 0; i < 15; i++) {
							if (taslarinKonumu1[i] > 6) {
								kontrol = 1;
							}
						}
						if (kontrol == 1) {
							if(taslarinKonumu1[i1]-zar1 > 0)
								liste1.add(i1);
						}
						else if (kontrol == 0) {
							liste1.add(i1);
						}	
					}
					sayac = 0;
				}
				sayac = 0;
				for (int i1 = 0; i1 < 15; i1++) {
					for (int i2 = 0; i2 < 15; i2++) {
						if(taslarinKonumu1[i1]-zar2 == taslarinKonumu2[i2]){
							sayac ++;
						}
					}
					if (sayac < 2) {
						int kontrol = 0;
						for (int i = 0; i < 15; i++) {
							if (taslarinKonumu1[i] > 6) {
								kontrol = 1;
							}
						}
						if (kontrol == 1) {
							if(taslarinKonumu1[i1]-zar2 > 0)
								liste2.add(i1);
						}
						else if (kontrol == 0) {
							liste2.add(i1);
						}	
					}
					sayac = 0;
				}
				System.out.println("liste1 " + liste1);
				System.out.println("liste2 " + liste2);
				
				for (int i = 0; i < 15; i++) {
					if (liste1.contains(i) && liste2.contains(i)) {
						ArrayList<Integer> ayniKonumda = new ArrayList<Integer>();
						for (int k = 0; k < 15; k++) {
							if (taslarinKonumu1[k]==taslarinKonumu1[i]) {
								ayniKonumda.add(k);
							}
						}
						System.out.println(ayniKonumda);
						
						int kucuk = 1000;
						int buyuk = 0;
						if (taslarY1[ayniKonumda.get(0)]%30==0) {
							for (int k = 0; k < ayniKonumda.size(); k++) {
								if (taslarY1[ayniKonumda.get(k)] < kucuk) {
									kucuk = taslarY1[ayniKonumda.get(k)];
								}
							}
							if (taslarY1[i]-kucuk <= 60) {
								A.add(i);
							}
						}
						else if (taslarY1[ayniKonumda.get(0)]%30==15) {
							for (int k = 0; k < ayniKonumda.size(); k++) {
								if (taslarY1[ayniKonumda.get(k)] > buyuk) {
									buyuk = taslarY1[ayniKonumda.get(k)];
								}
							}
							if (buyuk-taslarY1[i] <= 60) {
								A.add(i);
							}						
						}
					}
					else if (liste1.contains(i) || liste2.contains(i)) {
						ArrayList<Integer> ayniKonumda = new ArrayList<Integer>();
						for (int k = 0; k < 15; k++) {
							if (taslarinKonumu1[k]==taslarinKonumu1[i]) {
								ayniKonumda.add(k);
							}
						}
						System.out.println(ayniKonumda);
						
						int kucuk = 1000;
						int buyuk = 0;
						if (taslarY1[ayniKonumda.get(0)]%30==0) {
							for (int k = 0; k < ayniKonumda.size(); k++) {
								if (taslarY1[ayniKonumda.get(k)] < kucuk) {
									kucuk = taslarY1[ayniKonumda.get(k)];
								}
							}
							if (taslarY1[i]-kucuk <= 0) {
								A.add(i);
							}
						}
						else if (taslarY1[ayniKonumda.get(0)]%30==15) {
							for (int k = 0; k < ayniKonumda.size(); k++) {
								if (taslarY1[ayniKonumda.get(k)] > buyuk) {
									buyuk = taslarY1[ayniKonumda.get(k)];
								}
							}
							if (buyuk-taslarY1[i] <= 0) {
								A.add(i);
							}						
						}
					}
				}
			}
			else if (oyuncu == 2) {
				int sayac = 0;
				for (int i1 = 0; i1 < 15; i1++) {
					for (int i2 = 0; i2 < 15; i2++) {
						if(taslarinKonumu2[i1]+zar1 == taslarinKonumu1[i2]){
							sayac ++;
						}
					}
					if (sayac < 2) {
						int kontrol = 0;
						for (int i = 0; i < 15; i++) {
							if (taslarinKonumu2[i] < 19) {
								kontrol = 1;
							}
						}
						if (kontrol == 1) {
							if(taslarinKonumu2[i1]+zar1 < 25)
								liste1.add(i1);
						}
						else if (kontrol == 0) {
							liste1.add(i1);
						}	
					}
					sayac = 0;
				}
				sayac = 0;
				for (int i1 = 0; i1 < 15; i1++) {
					for (int i2 = 0; i2 < 15; i2++) {
						if(taslarinKonumu2[i1]+zar2 == taslarinKonumu1[i2]){
							sayac ++;
						}
					}
					if (sayac < 2) {
						int kontrol = 0;
						for (int i = 0; i < 15; i++) {
							if (taslarinKonumu2[i] < 19) {
								kontrol = 1;
							}
						}
						if (kontrol == 1) {
							if(taslarinKonumu2[i1]+zar2 < 25)
								liste2.add(i1);
						}
						else if (kontrol == 0) {
							liste2.add(i1);
						}	
					}
					sayac = 0;
				}
				for (int i = 0; i < 15; i++) {
					if (liste1.contains(i) && liste2.contains(i)) {
						ArrayList<Integer> ayniKonumda = new ArrayList<Integer>();
						for (int k = 0; k < 15; k++) {
							if (taslarinKonumu2[k]==taslarinKonumu2[i]) {
								ayniKonumda.add(k);
							}
						}
						System.out.println(ayniKonumda);
						
						int kucuk = 1000;
						int buyuk = 0;
						if (taslarY2[ayniKonumda.get(0)]%30==0) {
							for (int k = 0; k < ayniKonumda.size(); k++) {
								if (taslarY2[ayniKonumda.get(k)] < kucuk) {
									kucuk = taslarY2[ayniKonumda.get(k)];
								}
							}
							if (taslarY2[i]-kucuk <= 60) {
								A.add(i);
							}
						}
						else if (taslarY2[ayniKonumda.get(0)]%30==15) {
							for (int k = 0; k < ayniKonumda.size(); k++) {
								if (taslarY2[ayniKonumda.get(k)] > buyuk) {
									buyuk = taslarY2[ayniKonumda.get(k)];
								}
							}
							if (buyuk-taslarY2[i] <= 60) {
								A.add(i);
							}						
						}
					}
					else if (liste1.contains(i) || liste2.contains(i)) {
						ArrayList<Integer> ayniKonumda = new ArrayList<Integer>();
						for (int k = 0; k < 15; k++) {
							if (taslarinKonumu2[k]==taslarinKonumu2[i]) {
								ayniKonumda.add(k);
							}
						}						
						int kucuk = 1000;
						int buyuk = 0;
						if (taslarY2[ayniKonumda.get(0)]%30==0) {
							for (int k = 0; k < ayniKonumda.size(); k++) {
								if (taslarY2[ayniKonumda.get(k)] < kucuk) {
									kucuk = taslarY2[ayniKonumda.get(k)];
								}
							}
							if (taslarY2[i]-kucuk <= 0) {
								A.add(i);
							}
						}
						else if (taslarY2[ayniKonumda.get(0)]%30==15) {
							for (int k = 0; k < ayniKonumda.size(); k++) {
								if (taslarY2[ayniKonumda.get(k)] > buyuk) {
									buyuk = taslarY2[ayniKonumda.get(k)];
								}
							}
							if (buyuk-taslarY2[i] <= 0) {
								A.add(i);
							}						
						}
					}
				}
			}
		}
		else if (zar1 == zar2) {
			if (oyuncu == 1) {
				int sayac = 0;
				for (int i1 = 0; i1 < 15; i1++) {
					for (int i2 = 0; i2 < 15; i2++) {
						if(taslarinKonumu1[i1]-zar1 == taslarinKonumu2[i2]){
							sayac ++;
						}
					}
					if (sayac < 2) {
						int kontrol = 0;
						for (int i = 0; i < 15; i++) {
							if (taslarinKonumu1[i] > 6) {
								kontrol = 1;
							}
						}
						if (kontrol == 1) {
							if(taslarinKonumu1[i1]-zar1 > 0)
								liste1.add(i1);
						}
						else if (kontrol == 0) {
							liste1.add(i1);
						}	
					}
					sayac = 0;
				}
				sayac = 0;
				for (int i1 = 0; i1 < 15; i1++) {
					for (int i2 = 0; i2 < 15; i2++) {
						if(taslarinKonumu1[i1]-zar2 == taslarinKonumu2[i2]){
							sayac ++;
						}
					}
					if (sayac < 2) {
						int kontrol = 0;
						for (int i = 0; i < 15; i++) {
							if (taslarinKonumu1[i] > 6) {
								kontrol = 1;
							}
						}
						if (kontrol == 1) {
							if(taslarinKonumu1[i1]-zar2 > 0)
								liste2.add(i1);
						}
						else if (kontrol == 0) {
							liste2.add(i1);
						}	
					}
					sayac = 0;
				}
				System.out.println("liste1 " + liste1);
				System.out.println("liste2 " + liste2);
				
				for (int i = 0; i < 15; i++) {
					if (liste1.contains(i) && liste2.contains(i)) {
						ArrayList<Integer> ayniKonumda = new ArrayList<Integer>();
						for (int k = 0; k < 15; k++) {
							if (taslarinKonumu1[k]==taslarinKonumu1[i]) {
								ayniKonumda.add(k);
							}
						}
						int kucuk = 1000;
						int buyuk = 0;
						if (taslarY1[ayniKonumda.get(0)]%30==0) {
							for (int k = 0; k < ayniKonumda.size(); k++) {
								if (taslarY1[ayniKonumda.get(k)] < kucuk) {
									kucuk = taslarY1[ayniKonumda.get(k)];
								}
							}
							if (taslarY1[i]-kucuk <= 180) {
								A.add(i);
							}
						}
						else if (taslarY1[ayniKonumda.get(0)]%30==15) {
							for (int k = 0; k < ayniKonumda.size(); k++) {
								if (taslarY1[ayniKonumda.get(k)] > buyuk) {
									buyuk = taslarY1[ayniKonumda.get(k)];
								}
							}
							if (buyuk-taslarY1[i] <= 180) {
								A.add(i);
							}						
						}
					}
					else if (liste1.contains(i) || liste2.contains(i)) {
						ArrayList<Integer> ayniKonumda = new ArrayList<Integer>();
						for (int k = 0; k < 15; k++) {
							if (taslarinKonumu1[k]==taslarinKonumu1[i]) {
								ayniKonumda.add(k);
							}
						}
						System.out.println(ayniKonumda);
						
						int kucuk = 1000;
						int buyuk = 0;
						if (taslarY1[ayniKonumda.get(0)]%30==0) {
							for (int k = 0; k < ayniKonumda.size(); k++) {
								if (taslarY1[ayniKonumda.get(k)] < kucuk) {
									kucuk = taslarY1[ayniKonumda.get(k)];
								}
							}
							if (taslarY1[i]-kucuk <= 180) {
								A.add(i);
							}
						}
						else if (taslarY1[ayniKonumda.get(0)]%30==15) {
							for (int k = 0; k < ayniKonumda.size(); k++) {
								if (taslarY1[ayniKonumda.get(k)] > buyuk) {
									buyuk = taslarY1[ayniKonumda.get(k)];
								}
							}
							if (buyuk-taslarY1[i] <= 180) {
								A.add(i);
							}						
						}
					}
				}
			}
			else if (oyuncu == 2) {
				int sayac = 0;
				for (int i1 = 0; i1 < 15; i1++) {
					for (int i2 = 0; i2 < 15; i2++) {
						if(taslarinKonumu2[i1]+zar1 == taslarinKonumu1[i2]){
							sayac ++;
						}
					}
					if (sayac < 2) {
						int kontrol = 0;
						for (int i = 0; i < 15; i++) {
							if (taslarinKonumu2[i] < 19) {
								kontrol = 1;
							}
						}
						if (kontrol == 1) {
							if(taslarinKonumu2[i1]+zar1 < 25)
								liste1.add(i1);
						}
						else if (kontrol == 0) {
							liste1.add(i1);
						}	
					}
					sayac = 0;
				}
				sayac = 0;
				for (int i1 = 0; i1 < 15; i1++) {
					for (int i2 = 0; i2 < 15; i2++) {
						if(taslarinKonumu2[i1]+zar2 == taslarinKonumu1[i2]){
							sayac ++;
						}
					}
					if (sayac < 2) {
						int kontrol = 0;
						for (int i = 0; i < 15; i++) {
							if (taslarinKonumu2[i] < 19) {
								kontrol = 1;
							}
						}
						if (kontrol == 1) {
							if(taslarinKonumu2[i1]+zar2 < 25)
								liste2.add(i1);
						}
						else if (kontrol == 0) {
							liste2.add(i1);
						}	
					}
					sayac = 0;
				}
				for (int i = 0; i < 15; i++) {
					if (liste1.contains(i) && liste2.contains(i)) {
						ArrayList<Integer> ayniKonumda = new ArrayList<Integer>();
						for (int k = 0; k < 15; k++) {
							if (taslarinKonumu2[k]==taslarinKonumu2[i]) {
								ayniKonumda.add(k);
							}
						}
						System.out.println(ayniKonumda);
						
						int kucuk = 1000;
						int buyuk = 0;
						if (taslarY2[ayniKonumda.get(0)]%30==0) {
							for (int k = 0; k < ayniKonumda.size(); k++) {
								if (taslarY2[ayniKonumda.get(k)] < kucuk) {
									kucuk = taslarY2[ayniKonumda.get(k)];
								}
							}
							if (taslarY2[i]-kucuk <= 180) {
								A.add(i);
							}
						}
						else if (taslarY2[ayniKonumda.get(0)]%30==15) {
							for (int k = 0; k < ayniKonumda.size(); k++) {
								if (taslarY2[ayniKonumda.get(k)] > buyuk) {
									buyuk = taslarY2[ayniKonumda.get(k)];
								}
							}
							if (buyuk-taslarY2[i] <= 180) {
								A.add(i);
							}						
						}
					}
					else if (liste1.contains(i) || liste2.contains(i)) {
						ArrayList<Integer> ayniKonumda = new ArrayList<Integer>();
						for (int k = 0; k < 15; k++) {
							if (taslarinKonumu2[k]==taslarinKonumu2[i]) {
								ayniKonumda.add(k);
							}
						}
						System.out.println(ayniKonumda);
						
						int kucuk = 1000;
						int buyuk = 0;
						if (taslarY2[ayniKonumda.get(0)]%30==0) {
							for (int k = 0; k < ayniKonumda.size(); k++) {
								if (taslarY2[ayniKonumda.get(k)] < kucuk) {
									kucuk = taslarY2[ayniKonumda.get(k)];
								}
							}
							if (taslarY2[i]-kucuk <= 180) {
								A.add(i);
							}
						}
						else if (taslarY2[ayniKonumda.get(0)]%30==15) {
							for (int k = 0; k < ayniKonumda.size(); k++) {
								if (taslarY2[ayniKonumda.get(k)] > buyuk) {
									buyuk = taslarY2[ayniKonumda.get(k)];
								}
							}
							if (buyuk-taslarY2[i] <= 180) {
								A.add(i);
							}						
						}
					}
				}
			}
		}
		return A; 	
	}

	public static void main(String[] args) {
		new Konsol();
	}
}

	