package sayiPlan;

import fipa.FipaMessage;
import task.Action;
import task.Outcome;
import task.Output;
import task.Provision;

public class ac4002 extends Action {

	int PlanNum;
	public Provision Numara;
	public Outcome Ok;
	public Outcome Fail;

	public ac4002() {
		System.out.println("ac4002 burasi");
		this.setId("ac4002");
		this.setName("Numara Action");

		(Numara = new Provision()).setprovName("Numara");
		(Ok = new Outcome()).setName("Ok");
		(Fail = new Outcome()).setName("Fail");
		addOutcome(Ok);
		addOutcome(Fail);
		addProvision(Numara);

	}

	@Override
	public void doAction() {
		String s = Numara.getValue().toString();
		int s1 = Integer.parseInt(s);
		int sayi = s1 + 10;
		System.out.println("burasi ac4002 " + sayi);
		Output out = new Output();
		out.setName("Sonuc");
		out.setValue(sayi);
		Ok.addOutput(out);
		Fail.setOutputList(null);
	}

	@Override
	public void sendMessage(FipaMessage fm) {

	}

}
