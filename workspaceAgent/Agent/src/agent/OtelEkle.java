package agent;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

public class OtelEkle extends JFrame implements ActionListener {
	private JTextField txtTip;
	private JTextField txtOdaNo;
	private JTextField txtDurum;
	private JLabel lblTip;
	private JLabel lblAction;
	private JLabel lblPlan;
	private JButton btnEkle;

	public OtelEkle() {
		txtTip = new JTextField();
		txtOdaNo = new JTextField();
		txtDurum = new JTextField();
		lblTip = new JLabel("Yer");
		lblAction = new JLabel("AID - Ad");
		lblPlan = new JLabel("AID - Adres");
		btnEkle = new JButton("Kaydet");
		setTitle("Otel Ekleme Formu");
		setSize(400, 400);
		setLocation(250, 200);
		showWindow();
	}

	private void showWindow() {
		Container con = this.getContentPane();
		con.setLayout(null);
		lblTip.setBounds(50, 50, 100, 25);
		lblAction.setBounds(50, 80, 100, 25);
		lblPlan.setBounds(50, 110, 100, 25);
		txtTip.setBounds(170, 50, 150, 25);
		txtOdaNo.setBounds(170, 80, 150, 25);
		txtDurum.setBounds(170, 110, 150, 25);
		btnEkle.setBounds(150, 160, 100, 25);
		con.add(lblTip);
		con.add(lblAction);
		con.add(lblPlan);
		con.add(txtTip);
		con.add(txtOdaNo);
		con.add(txtDurum);
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
		} catch (Exception e) {
		}

		OtelEkle pl = new OtelEkle();
		pl.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		OtelOntoloji on = new OtelOntoloji();
		on.addOtel(txtTip.getText(), txtOdaNo.getText(), txtDurum.getText());
		// on.getPlan("konya");

	}

}
