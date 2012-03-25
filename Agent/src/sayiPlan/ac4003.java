package sayiPlan;

import fipa.FipaMessage;
import task.Action;
import task.Outcome;
import task.Output;
import task.Provision;

public class ac4003 extends Action {

	public Provision Rakam;
	public Outcome Ok;
	public Outcome Fail;

	public ac4003() {
		this.setId("ac4003");
		this.setName("Rakam 2 ile carp");
		(Rakam = new Provision()).setprovName("Rakam");
		(Ok = new Outcome()).setName("Ok");
		(Fail = new Outcome()).setName("Fail");
		addProvision(Rakam);

		addOutcome(Ok);
		addOutcome(Fail);
	}

	@Override
	public void doAction() {
		System.out.println("Rakam " + Rakam.getValue().toString());
		String s = Rakam.getValue().toString();
		int s1 = Integer.parseInt(s);
		int sayi = s1 * 2;
		System.out.println("Sayi " + sayi);
		Output out = new Output();
		out.setName("Numara");
		out.setValue(sayi);
		Ok.addOutput(out);
		Fail.setOutputList(null);
		System.out.println("burasi ac4003 " + Ok.getName());

	}

	@Override
	public void sendMessage(FipaMessage fm) {

		getSendingMessages().add(fm);

	}

}
