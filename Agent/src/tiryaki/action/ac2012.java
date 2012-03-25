package tiryaki.action;

import java.util.StringTokenizer;

import javax.swing.JOptionPane;

import fipa.FipaMessage;
import task.Action;
import task.Outcome;
import task.Provision;

public class ac2012 extends Action {

	int PlanNum;
	public Provision Cevap;
	public Outcome Ok;
	public Outcome Fail;

	public ac2012() {
		this.setId("ac2012");
		this.setName("Kullan�c� Bilgilendir");
		(Cevap = new Provision()).setprovName("Cevap");
		(Ok = new Outcome()).setName("Ok");
		(Fail = new Outcome()).setName("Fail");
		addOutcome(Ok);
		addOutcome(Fail);
		addProvision(Cevap);
	}

	@Override
	public void doAction() {
		String odaNo = null;
		String ucret = null;
		StringTokenizer st = new StringTokenizer(Cevap.getValue().toString());
		while (st.hasMoreTokens()) {
			odaNo = st.nextToken();
			ucret = st.nextToken();
		}

		JOptionPane.showMessageDialog(null, odaNo + " Nolu Oda " + ucret
				+ " TL ye Ad�n�za  Kiralanm��t�r.");

	}

	@Override
	public void sendMessage(FipaMessage fm) {

		getSendingMessages().add(fm);

	}

}
