package tiryaki.action;

import fipa.FipaMessage;
import task.Action;
import task.Outcome;
import task.Output;
import task.Provision;

public class MshnAction extends Action {

	int planNum;
	public Provision Cevap;
	public Outcome Ok, Fail;

	public MshnAction() {

//		System.out.println("--- MshnAction'a girildi. ---");

		this.setId("MshnAction");
		this.setName("EkranaYaz");

		(Cevap = new Provision()).setprovName("Cevap");
		(Ok = new Outcome()).setName("Ok");
		(Fail = new Outcome()).setName("Fail");
		addOutcome(Ok);
		addOutcome(Fail);
		addProvision(Cevap);

//		System.out.println("--- MshnAction yapýcýsýndan çýkýlýyor. ---");

	}

	@Override
	public void doAction() {

		Output out = new Output();
		out.setName("Cevap");
		out.setValue("Ok");
		Ok.addOutput(out);
		Fail.setOutputList(null);
		
		System.out.println("--- MshnAction doAction() metoduna girildi. ---");
		System.out.println("--- Ünlem Ýþareti (!) ---");

	}

	@Override
	public void sendMessage(FipaMessage fm) {

	}

}
