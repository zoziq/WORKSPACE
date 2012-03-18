import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.RepaintManager;


public class Konsol extends JFrame{
	
	public static int taslarX1[] = {420,480,540,540,600,210,210,330,30, 30, 30,90,270,720,420};
	public static int taslarY1[] = {630,630,630,570,630,630,570,630,45,105,165,45, 45, 45,45};
	public static int taslarX2[] = {600,540,540,540,540,330,150,150, 30, 90, 90,270,90,660,720};
	public static int taslarY2[] = {45,225,165,105, 45, 45, 45,105,630,630,570,630,510,630,630 };
	
	public static int kirikSayisi1 = 0;
	public static int kirikSayisi2 = 0;
	public JLabel kirik1 = new JLabel("0");
	public JLabel kirik2 = new JLabel("0");
	
	public static int cikanSayisi1 = 0;
	public static int cikanSayisi2 = 0;
	public JLabel cikan1 = new JLabel("0");
	public JLabel cikan2 = new JLabel("0");
	
	public Konsol() {
		setLayout(null);
		setBounds(0,0,1100,720);
		setVisible(true);
		setBackground(Color.black);
	}
	
	public void paint(final Graphics g) {
		super.paint(g);
		//////////////////
		//  TAHTA ��Z   //
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
		// TA�LARI ��Z  //
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
		//    GE��C�    //
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
		
		final JButton oynat = new JButton("oynat");
		oynat.setBounds(950,575,111,25);
		add(oynat);
		oynat.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent arg0) {
				oynat(g,1,1,1);
				oynat(g,4,1,1);
			}
		});
		
		//////////////////
		//  B�LE�ENLER  //
		//////////////////
		
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
				return false;
			}
			else if (sayac==1) {
				kirikSayisi2++;
				remove(kirik2);
				for (int i = 0; i < 15; i++) {
					if (taslarX2[i]==gidecegiYer) {
						kirik2 = new JLabel(""+kirikSayisi2);
						kirik2.setBounds(800,640,60,60);
						add(kirik2);
						taslarX2[i]=780;
						taslarY2[i]=630;
					}
				}
				return true;
			}
		}
		return true;
	}
	
	public void oynat(Graphics g, int tasNumarasi, int zar, int hangiTas) {
		if (hangiTas==1) {
			if(taslarX1[tasNumarasi]<=720 && taslarX1[tasNumarasi]>=420 && taslarX1[tasNumarasi]-zar*60>=420 && Math.abs(taslarY1[tasNumarasi]%30)==15 && kontrol(45, taslarX1[tasNumarasi]-zar*60, 1)) {
				System.out.println("sag ustten sag uste 1");
				int gidecegiYer = taslarX1[tasNumarasi]-zar*60;
				haraket1(tasNumarasi, gidecegiYer);
			}
			else if(taslarX1[tasNumarasi]<=720 && taslarX1[tasNumarasi]>=420 && taslarX1[tasNumarasi]-zar*60<=420 && Math.abs(taslarY1[tasNumarasi]%30)==15 && kontrol(45, taslarX1[tasNumarasi]-zar*60-30, 1)) {
				System.out.println("sag ustten sol uste 1");
				int gidecegiYer = taslarX1[tasNumarasi]-zar*60-30;
				haraket1(tasNumarasi, gidecegiYer);
			}
			else if(taslarX1[tasNumarasi]<=390 && taslarX1[tasNumarasi]>=30 && taslarX1[tasNumarasi]-zar*60>=30 && Math.abs(taslarY1[tasNumarasi]%30)==15 && kontrol(45, taslarX1[tasNumarasi]-zar*60, 1)) {
				System.out.println("sol ustten sol uste 1");
				int gidecegiYer = taslarX1[tasNumarasi]-zar*60;
				haraket1(tasNumarasi, gidecegiYer);
			}
			else if(taslarX1[tasNumarasi]<=390 && taslarX1[tasNumarasi]>=30 && taslarX1[tasNumarasi]-zar*60<=30 && Math.abs(taslarY1[tasNumarasi]%30)==15 && kontrol(60, -(taslarX1[tasNumarasi]-zar*60), 1)) {
				System.out.println("sol ustten sol alta 1");
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
				haraket2(tasNumarasi, gidecegiYer);
			}
			else if(taslarX1[tasNumarasi]<=390 && taslarX1[tasNumarasi]>=30 && taslarX1[tasNumarasi]+zar*60+30<=780 && taslarX1[tasNumarasi]+zar*60+30>=390 && Math.abs(taslarY1[tasNumarasi]%30)==0 && kontrol(60, taslarX1[tasNumarasi]+zar*60+30, 1)) {
				System.out.println("sol alttan sag alta 1");
				int gidecegiYer = taslarX1[tasNumarasi]+zar*60+30;
				haraket2(tasNumarasi, gidecegiYer);
			}
			else if(taslarX1[tasNumarasi]>=390 && taslarX1[tasNumarasi]<=780 && taslarX1[tasNumarasi]+zar*60<780 && Math.abs(taslarY1[tasNumarasi]%30)==0 && kontrol(60, taslarX1[tasNumarasi]+zar*60, 1)) {
				System.out.println("sag alttan sag alta 1");
				int gidecegiYer = taslarX1[tasNumarasi]+zar*60;
				haraket2(tasNumarasi, gidecegiYer);
			}
			else if(taslarX1[tasNumarasi]>=390 && taslarX1[tasNumarasi]<=780 && taslarX1[tasNumarasi]+zar*60>=780 && Math.abs(taslarY1[tasNumarasi]%30)==0) {
				System.out.println("sag alttan cikis 1");
				cikanSayisi1++;
				remove(cikan1);
				taslarX1[tasNumarasi]=855;
				taslarY1[tasNumarasi]=630;
				cikan1 = new JLabel(""+cikanSayisi1);
				cikan1.setBounds(875,640,60,60);
				add(cikan1);
				repaint();
			}
		}
		
		
		
	}

	private void haraket1(int tasNumarasi, int gidecegiYer) {
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
	
	private void haraket2(int tasNumarasi, int gidecegiYer) {
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
	
	public static void main(String[] args) {
		new Konsol();
	}
}


//Math.abs(taslarY1[i])%30==15 durumuna dikkat et -15 ��kabiliyor
//koseyi donmek icin haraket metotlarini kullanma
//haraket metotlarini kullanmiyorken repaint fonksiyonunu cagirmayi unutma

