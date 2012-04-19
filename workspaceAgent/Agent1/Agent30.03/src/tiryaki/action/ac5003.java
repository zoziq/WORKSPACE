package tiryaki.action;

import fipa.FipaMessage;
import task.Action;
import task.Outcome;
import task.Provision;

public class ac5003 extends Action {

	int PlanNum;
	public Provision Cevap;
	public Outcome Ok;
	public Outcome Fail;

	public ac5003() {
		this.setId("ac2007");
		this.setName("Kullanýcý Bilgilendir");
		(Cevap = new Provision()).setprovName("Cevap");
		(Ok = new Outcome()).setName("Ok");
		(Fail = new Outcome()).setName("Fail");
		addOutcome(Ok);
		addOutcome(Fail);
		addProvision(Cevap);
	}

	@Override
	public void doAction() {
		System.out.println("burasý ac5003");
		if (Cevap.getValue().toString().equalsIgnoreCase("Ok")) {
			Ok.addOutput(null);
			Fail.setOutputList(null);
		} else if (Cevap.getValue().toString().equalsIgnoreCase("Fail")) {
			Fail.addOutput(null);
			Ok.setOutputList(null);
		}

	}

	@Override
	public void sendMessage(FipaMessage fm) {

		getSendingMessages().add(fm);

	}

}
