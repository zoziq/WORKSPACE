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

public class PlanDB extends JFrame implements ActionListener {
	private JTextField txtLanguage;
	private JTextField txtAction;
	private JTextField txtPlan;
	private JLabel lblLanguage;
	private JLabel lblAction;
	private JLabel lblPlan;
	private JButton btnEkle;

	public PlanDB() {
		txtLanguage = new JTextField();
		txtAction = new JTextField();
		txtPlan = new JTextField();
		lblLanguage = new JLabel("Language");
		lblAction = new JLabel("Action");
		lblPlan = new JLabel("Plan Name");
		btnEkle = new JButton("Kaydet");
		setSize(400, 400);
		setLocation(250, 200);
		showWindow();
	}

	private void showWindow() {
		Container con = this.getContentPane();
		con.setLayout(null);
		lblLanguage.setBounds(50, 50, 100, 25);
		lblAction.setBounds(50, 80, 100, 25);
		lblPlan.setBounds(50, 110, 100, 25);
		txtLanguage.setBounds(170, 50, 150, 25);
		txtAction.setBounds(170, 80, 150, 25);
		txtPlan.setBounds(170, 110, 150, 25);
		btnEkle.setBounds(150, 160, 100, 25);
		con.add(lblLanguage);
		con.add(lblAction);
		con.add(lblPlan);
		con.add(txtLanguage);
		con.add(txtAction);
		con.add(txtPlan);
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

		PlanDB pl = new PlanDB();
		pl.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		MatcherOntology mo = new MatcherOntology();
		mo.addPlan(txtPlan.getText(), txtAction.getText(), txtLanguage
				.getText());

	}

}
