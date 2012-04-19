package agent;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;


public class OdaEkle extends JFrame implements ActionListener{
	private JComboBox cmbTip;
	private JTextField txtOdaNo;
	private JComboBox cmbDurum;
	private JTextField txtUcret;
	private JLabel lblTip;
	private JLabel lblAction;
	private JLabel lblPlan;
	private JLabel lblUcret;
	private JButton btnEkle;
	
	public OdaEkle(){
		cmbTip=new JComboBox();
		cmbTip.addItem("suit");
		cmbTip.addItem("kral");
		cmbTip.addItem("tekkisi");
		cmbTip.addItem("ciftkisi");
		txtOdaNo=new JTextField();
		cmbDurum=new JComboBox();
		cmbDurum.addItem("Boþ");
		cmbDurum.addItem("Dolu");
		txtUcret=new JTextField();
		lblTip=new JLabel("Tip");
		lblAction=new JLabel("Oda No");
		lblPlan=new JLabel("Durum");
		lblUcret=new JLabel("Ucret");
		btnEkle=new JButton("Kaydet");
		setSize(400,400);
		setLocation(250,200);
		showWindow();
	}

	private void showWindow() {
		Container con=this.getContentPane();
		con.setLayout(null);
		lblTip.setBounds(50,50,100,25);
		lblAction.setBounds(50,80,100,25);
		lblPlan.setBounds(50,110,100,25);
		lblUcret.setBounds(50,140,100,25);
		cmbTip.setBounds(170,50,150,25);
		txtOdaNo.setBounds(170,80,150,25);
		cmbDurum.setBounds(170,110,150,25);
		txtUcret.setBounds(170,140,150,25);
		btnEkle.setBounds(150,180,100,25);
		con.add(lblTip);
		con.add(lblAction);
		con.add(lblPlan);
		con.add(lblUcret);
		con.add(cmbTip);
		con.add(txtOdaNo);
		con.add(cmbDurum);
		con.add(txtUcret);
		con.add(btnEkle);
		btnEkle.addActionListener(this);
		
		
	}

	public static void main(String[] args) {
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            
		            break;
		        }
		    }
		} catch (Exception e) {}
		
	
	OdaEkle pl=new OdaEkle();
	pl.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		OdaOntoloji on=new OdaOntoloji();
	//	on.addPlan(cmbTip.getSelectedItem().toString() ,txtOdaNo.getText(),cmbDurum.getSelectedItem().toString(),txtUcret.getText());
	//	on.getPlan();
		txtOdaNo.setText("");
		txtUcret.setText("");
	}

}
