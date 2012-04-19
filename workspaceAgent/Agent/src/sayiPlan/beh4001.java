package sayiPlan;

import links.DisinheritanceLink;
import links.InheritanceLink;
import links.ProvisionOutcomeLink;
import task.Behaviour;
import task.Outcome;
import task.Provision;

public class beh4001 extends Behaviour {

	Provision sayi;
	Outcome Ok;
	Outcome Fail;

	public beh4001() {
		this.setId("beh4001");
		this.setName("Otel Etmenlerini Bul");
		ac4001 ac4001 = new ac4001();
		beh4003 beh4003 = new beh4003();
		this.setSubTask(ac4001);
		this.setSubTask(beh4003);
		sayi = new Provision();
		sayi.setprovName("sayi");
		(Ok = new Outcome()).setName("Ok");
		(Fail = new Outcome()).setName("Fail");

		addProvision(sayi);
		addOutcome(Ok);
		addOutcome(Fail);

		InheritanceLink il1 = new InheritanceLink();
		il1.setReceiverProvision(ac4001.sayi);
		il1.setReceiverTask(ac4001);
		il1.setSenderProvision(this.sayi);

		DisinheritanceLink dl1 = new DisinheritanceLink();
		dl1.setReceiverTask(this);
		dl1.setReceiverOutcome(Fail);
		dl1.setSenderOutcome(ac4001.Fail);
		dl1.setSenderTask(ac4001);
		dl1.setOutcomeName("Fail");

		DisinheritanceLink dl2 = new DisinheritanceLink();
		dl2.setReceiverTask(this);
		dl2.setReceiverOutcome(Ok);
		dl2.setSenderOutcome(beh4003.Ok);
		dl2.setSenderTask(beh4003);
		dl2.setOutcomeName("Ok");

		DisinheritanceLink dl3 = new DisinheritanceLink();
		dl3.setReceiverTask(this);
		dl3.setReceiverOutcome(Fail);
		dl3.setSenderOutcome(beh4003.Fail);
		dl3.setSenderTask(beh4003);
		dl3.setOutcomeName("Fail");

		ProvisionOutcomeLink proOutLink1 = new ProvisionOutcomeLink();
		proOutLink1.setReceiverProvision(beh4003.Rakam);
		proOutLink1.setReceiverTask(beh4003);
		proOutLink1.setSenderOutcome(ac4001.Ok);
		proOutLink1.setSenderOutcomeName(ac4001.Ok.getName());
		proOutLink1.setSenderTask(ac4001);

		// Linkleri link kuyruguna ekliyoruz
		links.add(il1);
		links.add(dl1);
		links.add(dl2);
		links.add(dl3);
		links.add(proOutLink1);

		System.out.println("beh2001 burasi");
	}

}
