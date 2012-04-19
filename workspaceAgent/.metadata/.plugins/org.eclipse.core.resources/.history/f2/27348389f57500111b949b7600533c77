package tiryaki.action;

import fipa.FipaMessage;
import task.Action;
import task.Outcome;
import task.Output;
import task.Provision;

public class ac2001 extends Action {

	int PlanNum;
	public Provision location;
	public Outcome Ok;
	public Outcome Fail;

	public ac2001() {
		System.out.println("ac2001 burasi");
		this.setId("ac2001");
		this.setName("Sorgu Olustur");

		(location = new Provision()).setprovName("Location");
		Ok = new Outcome();
		Ok.setName("Ok");
		(Fail = new Outcome()).setName("Fail");
		addOutcome(Ok);
		addOutcome(Fail);
		addProvision(location);

	}

	@Override
	public void doAction() {

		Output out = new Output();
		out.setName("Sorgu");
		out.setValue(location.getValue().toString());
		Ok.addOutput(out);
		Fail.setOutputList(null);
	}

	@Override
	public void sendMessage(FipaMessage fm) {
		// TODO Auto-generated method stub

	}

}
