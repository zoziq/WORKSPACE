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
	
	public  static int zarListesi[][] = new int[20][2];
	//////////////////
	//NORMAL DIZILIM//
	//////////////////
	public static int taslarX1[] = {420,420,420,420,420,270,270,270,30, 30, 30, 30, 30,720,720};
	public static int taslarY1[] = {630,570,510,450,390,630,570,510,45,105,165,225,285, 45,105};
	public static int taslarX2[] = {420,420,420,420,420,270,270,270, 30, 30, 30, 30, 30,720,720};
	public static int taslarY2[] = { 45,105,165,225,285, 45,105,165,630,570,510,450,390,630,570};
	/*
	public static int taslarX1[] = {420,660,540,540,600,210,150,330,30, 30, 30,90,270,720,720};
	public static int taslarY1[] = {630,630,630,570,630,630,630,630,45,105,165,45, 45, 45,105};
	public static int taslarX2[] = {600,540,540,540,540,330,150,150, 30, 90, 90,270, 90,480,720};
	public static int taslarY2[] = { 45,225,165,105, 45, 45, 45,105,630,630,570,630,510,630,630 };
		*/
	public static int kirikSayisi1 = 0;
	public static int kirikSayisi2 = 0;
	
	public static int cikanSayisi1 = 0;
	public static int cikanSayisi2 = 0;
	public JLabel cikan1 = new JLabel("0");
	public JLabel cikan2 = new JLabel("0");
	
	public int golgeX = -60; 
	public int golgeY = -60;
	public int sonOynananTasNumarasi = 0;
	public int sonOynananHangiTas = 0;
	
	public JTextField durumlar;
	public static String ts1;
	public static String ts2;
	public int oynamaz1 = 0;
	public int oynamaz2 = 0;
	
	public Konsol() {
		setLayout(null);
		setBounds(0,0,1360,720);
		setVisible(true);
		setBackground(Color.black);
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
		}
		for (int i = 0; i < 15; i++) {
			g.setColor(new Color(107,77,57));
			g.fillOval(taslarX2[i], taslarY2[i], 60, 60);
		}
		
		//////////////////
		//  NUMARALAR   //
		//////////////////
		if (kirikSayisi1!=0) {
			g.setColor(Color.white);
			g.drawString(""+kirikSayisi1, 401, 335);
		}
		if (kirikSayisi2!=0) {
			g.setColor(Color.white);
			g.drawString(""+kirikSayisi2, 401, 395);
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
		//    GOLGE     //
		//////////////////
		g.setColor(Color.black);
		g.drawOval(golgeX, golgeY, 60, 60);
		
		//////////////////
		// KOORDINAT    //
		//////////////////
		final JTextField xx = new JTextField();
		xx.setBounds(950,600,111,25);
		add(xx);
		final JTextField yy = new JTextField();
		yy.setBounds(950,625,111,25);
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
		JLabel o = new JLabel("Tüm Taþlarýn Konumu");
		o.setBounds(855,15,180,25);
		add(o);
		JLabel t = new JLabel("tas1 knm1 tas2 knm2");
		t.setBounds(855,45,180,25);
		add(t);

		String s1[] = konumHesapla(taslarX1, taslarY1);
		String s2[] = konumHesapla(taslarX2, taslarY2);
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 15; j++) {
				if (i==1) {
					JTextField tt = new JTextField(s2[j]);
					tt.setBounds(855+i*65,75+j*18,60,18);
					add(tt);
				}else {
					JTextField tt = new JTextField(s1[j]);
					tt.setBounds(855+i*65,75+j*18,60,18);
					add(tt);
				}
			}
		}
		JLabel o1 = new JLabel("Uygun Taþ Numaralarý");
		o1.setBounds(1000,15,180,25);
		add(o1);
		JLabel t1 = new JLabel("          tas1    tas2");
		t1.setBounds(1000,45,180,25);
		add(t1);
		String s11[] = oynayabilirTaslariHesapla(taslarX1, taslarY1);
		String s22[] = oynayabilirTaslariHesapla(taslarX2, taslarY2);
		
		for (int j = 0; j < s11.length; j++) {
			JTextField tt = new JTextField(s11[j]);
			tt.setBounds(1035,75+j*18,30,18);
			add(tt);
		}
		for (int j = 0; j < s22.length; j++) {
			JTextField tt = new JTextField(s22[j]);
			tt.setBounds(1065,75+j*18,30,18);
			add(tt);
		}
		
		//////////////////
		// ZAR LISTESI  //
		//////////////////
		JLabel o2 = new JLabel("Zar Listesi");
		o2.setBounds(1185,45,180,25);
		add(o2);
		for (int j = 0; j < 20; j++) {
			for (int i = 0; i < 2; i++) {
				zarListesi[j][i] = (int)(Math.random()*6)+1;
			}
			JTextField tt = new JTextField("    " + zarListesi[j][0] + " - " + zarListesi[j][1]);
			tt.setBounds(1185,75+j*18,60,18);
			add(tt);
		}
		//////////////////
		// UYARI MESAJI //
		//////////////////
		durumlar = new JTextField();
		durumlar.setBounds(850,500,212,25);
		add(durumlar);
		//////////////////
		//     ACTION   //
		//////////////////
		final JTextField tas1 = new JTextField();
		tas1.setBounds(850,550,35,25);
		add(tas1);
		final JTextField zar1 = new JTextField();
		zar1.setBounds(900,550,35,25);
		add(zar1);
		final JTextField tas2 = new JTextField();
		tas2.setBounds(850,575,35,25);
		add(tas2);
		final JTextField zar2 = new JTextField();
		zar2.setBounds(900,575,35,25);
		add(zar2);
		
		final JButton oynat1 = new JButton("oynat1");
		oynat1.setBounds(950,550,111,25);
		add(oynat1);
		oynat1.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent arg0) {
				oynat(g,Integer.parseInt(tas1.getText()),Integer.parseInt(zar1.getText()),1);
				ts1 = tas1.getText();
				if (oynamaz1 == 0) {
					durumlar.setText(sonOynananHangiTas + " . oyuncu " + sonOynananTasNumarasi + " numaralý taþýný " + zar1.getText() + " oynadý");
				}
				oynamaz1 = 0;
			}
		});
		final JButton oynat2 = new JButton("oynat2");
		oynat2.setBounds(950,575,111,25);
		add(oynat2);
		oynat2.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent arg0) {
				oynat(g,Integer.parseInt(tas2.getText()),Integer.parseInt(zar2.getText()),2);
				ts2 = tas2.getText();
				if (oynamaz2 == 0) {
					durumlar.setText(sonOynananHangiTas + " . oyuncu " + sonOynananTasNumarasi + " numaralý taþýný " + zar2.getText() + " oynadý");
				}
				oynamaz2 = 0;
			}
		});
			
	}
	
	
	public void oynat(Graphics g, int tasNumarasi, int zar, int hangiTas) {
		if (hangiTas==1) {
			if(taslarX1[tasNumarasi]==375 && taslarY1[tasNumarasi]==300 && kontrol(45, taslarX1[tasNumarasi]+405-zar*60, 1)) {
				System.out.println("kiriktan giris 1");
				golgeX = taslarX1[tasNumarasi];
		        golgeY = taslarY1[tasNumarasi];
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
				golgeX = taslarX1[tasNumarasi];
		        golgeY = taslarY1[tasNumarasi];
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
				golgeX = taslarX1[tasNumarasi];
		        golgeY = taslarY1[tasNumarasi];
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
				golgeX = taslarX2[tasNumarasi];
		        golgeY = taslarY2[tasNumarasi];
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
				golgeX = taslarX2[tasNumarasi];
		        golgeY = taslarY2[tasNumarasi];
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
				golgeX = taslarX2[tasNumarasi];
		        golgeY = taslarY2[tasNumarasi];
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
		golgeX = taslarX1[tasNumarasi];
        golgeY = taslarY1[tasNumarasi];
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
		golgeX = taslarX1[tasNumarasi];
        golgeY = taslarY1[tasNumarasi];
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
		golgeX = taslarX2[tasNumarasi];
        golgeY = taslarY2[tasNumarasi];
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
		golgeX = taslarX2[tasNumarasi];
        golgeY = taslarY2[tasNumarasi];
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
				oynamaz1 = 1;
				System.out.println("oynamaz");
				durumlar = new JTextField(ts1 + " numaralý taþ oynamaz");
				durumlar.setBounds(850,500,212,25);
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
				oynamaz2 = 1;
				System.out.println("oynamaz");
				durumlar = new JTextField(ts2 + " numaralý taþ oynamaz");
				durumlar.setBounds(850,500,212,25);
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
	
	public String[] konumHesapla(int[] x, int[] y) {
		int knm[] = new int[15];
		String dizi[] = new String[15];
		String s = "";
		for (int i = 0; i < x.length; i++) {
			int konum = konumBul(x, y, i);
			if (i/10>=1) {
				s += i + "    -   " + konum + "\n";
			}else {
				s += i + "     -    " + konum + "\n";;
			}
			dizi[i] = s;
			s = "";
			knm[i] = konum;
		}
		return dizi;
	}

	private int konumBul(int[] x, int[] y, int i) {
		int konum = 0;
		if (y[i]%30==15) {
			konum = (x[i]+30)/60+12;
		}else {
			konum = 13-(x[i]+30)/60;
		}
		if (x[i]==375 && y[i]==300) {
			konum = 0;
		}
		if (x[i]==375 && y[i]==360) {
			konum = 25;
		}
		return konum;
	}

	
	
	public String[] oynayabilirTaslariHesapla(int[] x, int[] y) {
		String dizi[] = null;
		ArrayList<Integer> uygunTasX = new ArrayList<Integer>();
		ArrayList<Integer> uygunTasY = new ArrayList<Integer>();
		ArrayList<Integer> indis = new ArrayList<Integer>();	
		for (int i = 0; i < 15; i++) {
			if (y[i]%30==15) {
				int a = x[i];
				ArrayList<Integer> temp = new ArrayList<Integer>();
				for (int j = i+1; j < 15; j++) {
					if (a==x[j]) {
						temp.add(j);
					}
				}
				int uygunTas = 0;
				int uygunIndis = i;
				for (int k = 0; k < temp.size(); k++) {
					if (y[i] > y[temp.get(k)]) {
						uygunTas = y[i];
						uygunIndis = i;
					}
					if(uygunTas < y[temp.get(k)]) {
						uygunTas = y[temp.get(k)];
						uygunIndis = i+k+1;
					}
				}
				i+=temp.size();	
				uygunTasX.add(x[uygunIndis]);
				uygunTasY.add(y[uygunIndis]);
				indis.add(uygunIndis);
			}
			else if (y[i]%30==0) {
				int a = x[i];
				ArrayList<Integer> temp = new ArrayList<Integer>();
				for (int j = i+1; j < 15; j++) {
					if (a==x[j]) {
						temp.add(j);
					}
				}
				int uygunTas = 0;
				int uygunIndis = i;
				for (int k = 0; k < temp.size(); k++) {
					if (y[i] > y[temp.get(k)]) {
						uygunTas = y[i];
						uygunIndis = i;
					}
					if(uygunTas > y[temp.get(k)]) {
						uygunTas = y[temp.get(k)];
						uygunIndis = i+k+1;
					}
				}
				i+=temp.size();	
				uygunTasX.add(x[uygunIndis]);
				uygunTasY.add(y[uygunIndis]);
				indis.add(uygunIndis);
			}	
		}
			
		int xx[] = new int[uygunTasX.size()];
		int yy[] = new int[uygunTasX.size()];
		for (int j = 0; j < uygunTasX.size(); j++) {
			xx[j] = uygunTasX.get(j);
			yy[j] = uygunTasY.get(j);
		}
/*		for (int i = 0; i < yy.length; i++) {
			System.out.println(konumBul(xx, yy, i));
		}
*/
		dizi = new String[indis.size()];
		for (int i = 0; i < indis.size(); i++) {
			dizi[i] = indis.get(i) + "";
			System.out.println(dizi[i]);
		}

		return dizi;
	}
	
	public ArrayList<Integer> listeyeEkle (ArrayList<Integer> a, int sayi){
		int s = 0;
		if (a.isEmpty()) {
			a.add(sayi);
		}
		else {
			for (int i = 0; i < a.size(); i++) {
				if (a.get(i)==sayi) {
					return a;
				}
				else{
					s = 1;
				}
			}
			if (s==1) {
				s=0;
				a.add(sayi);
				return a;
			}
		}
		return null;
	}
	

	
	public static void main(String[] args) {
		Konsol k = new Konsol();
		
	
			
	}
}


//Math.abs(taslarY1[i])%30==15 durumuna dikkat et -15 cikabiliyor
//koseyi donmek icin haraket metotlarini kullanma
//haraket metotlarini kullanmiyorken repaint fonksiyonunu cagirmayi unutma

	