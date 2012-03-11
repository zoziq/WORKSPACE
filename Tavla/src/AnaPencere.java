import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Desktop.Action;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class AnaPencere extends JFrame {
	
	
	public int x=0,y=0,xx=50,yy=50;
	public AnaPencere() {
		super("Ana Pencere");
		createGUI();
	}
	
	private void createGUI() {
		getContentPane().setLayout(null);
		setLocation(90, 90);
		setSize(400,365);
		setVisible(true);
		
		JButton b = new JButton();
		b.setBounds(100,100,50,50);
		add(b);
		JButton b1 = new JButton();
		b1.setBounds(200,100,50,50);
		add(b1);
		
		JLabel l = new JLabel("qwqwedqd");
		l.setBounds(x+44,y+44,50,50);
		add(l);
		
		b.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				x+=5;
				y+=5;
				
				repaint();
			}
		});
	}
	
	public void paint(final Graphics g) {
		
		g.setColor(Color.red);
		g.fillOval(x, y, 50, 50);
		
		
		Graphics2D g1 = (Graphics2D) g;
		g1.setColor(Color.black);

		
		System.out.println(-15%30);
	}
		
	public static void main(String[] args) {
		AnaPencere anaPencere = new AnaPencere();
	}

}
