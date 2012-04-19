package userinterface;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import fipa.FIPARDFContent0;
import fipa.FipaMessage;

public class Action extends JFrame implements ActionListener{

	private JLabel lblAct;
	private JLabel lblActor;
	private JLabel lblArgument;
	
	private JTextField txtAct;
	private JTextField txtActor;
	private JTextField txtArgument;
	
	private JButton  btnEkle;
	private JButton  btnArgumentEkle;
	ArrayList ar=new ArrayList();
	ArrayList actions=new ArrayList();
	FipaMesaj f=new FipaMesaj();
	public Action(FipaMesaj fipaMesaj){
		
		lblAct=new JLabel("Act");
		lblActor=new JLabel("Actor");
		lblArgument=new JLabel("Argument");
		
		txtAct=new JTextField();
		txtActor=new JTextField();
		txtArgument=new JTextField();
		
		btnEkle=new JButton("Ekle");
		btnArgumentEkle=new JButton("Argument Ekle");
		f=fipaMesaj;
		setSize(500,200);
		setLocation(800,200);
		setLayout(null);
		setTitle("FÝPA RDF0 Content");
		
		lblAct.setBounds(30,30,80,25);
		lblActor.setBounds(30,60,80,25);
		lblArgument.setBounds(30,90,80,25);
		
		txtAct.setBounds(120,30,200,25);
		txtActor.setBounds(120,60,200,25);
		txtArgument.setBounds(120,90,200,25);
		
		
		btnEkle.setBounds(120,120,200,25);
		btnArgumentEkle.setBounds(330,90,130,25);
		
		Container con=this.getContentPane();
		con.add(lblAct);
		con.add(lblActor);
		con.add(lblArgument);
		
		con.add(txtAct);
		con.add(txtActor);
		con.add(txtArgument);
		
		con.add(btnEkle);
		con.add(btnArgumentEkle);
		
		btnArgumentEkle.addActionListener(this);
		btnEkle.addActionListener(this);
		
	
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnArgumentEkle)
		{
			ar.add(txtArgument.getText());
			txtArgument.setText("");
		}
		
		if(e.getSource()==btnEkle)
		{
			FIPARDFContent0 rdf=new FIPARDFContent0();
			rdf.setAct(txtAct.getText());
			rdf.setActor(txtActor.getText());
			rdf.setArguments(actions);
			actions.add(rdf);
			f.actions=actions;
			txtAct.setText("");
			txtActor.setText("");
			txtArgument.setText("");
		
			
		}
		
	}
}
