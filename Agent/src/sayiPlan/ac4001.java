package sayiPlan;

import fipa.FipaMessage;
import task.Action;
import task.Outcome;
import task.Output;
import task.Provision;

public class ac4001 extends Action {

	public Provision sayi;
	public Outcome Ok;
	public Outcome Fail;

	public ac4001() {
		System.out.println("ac4001 burasi");
		this.setId("ac4001");
		this.setName("Action Kare Al");

		(sayi = new Provision()).setprovName("sayi");
		(Ok = new Outcome()).setName("Ok");
		(Fail = new Outcome()).setName("Fail");
		addOutcome(Ok);
		addOutcome(Fail);
		addProvision(sayi);

	}

	@Override
	public void doAction() {
		String s = sayi.getValue().toString();
		int s1 = Integer.parseInt(s);
		int sayi = s1 * s1;
		Output out = new Output();
		out.setName("Rakam");
		out.setValue(sayi);
		Ok.addOutput(out);
		Fail.setOutputList(null);
	}

	@Override
	public void sendMessage(FipaMessage fm) {

	}

}
