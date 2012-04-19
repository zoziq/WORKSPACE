package sayiPlan;

import links.DisinheritanceLink;
import links.InheritanceLink;
import links.ProvisionOutcomeLink;
import task.Behaviour;
import task.Outcome;
import task.Provision;

public class beh4000 extends Behaviour {
	Provision sayi;
	Outcome Ok;
	Outcome Fail;

	public beh4000() {
		this.setId("beh4000");
		this.setName("En ust Beh");
		beh4001 beh4001 = new beh4001();
		beh4002 beh4002 = new beh4002();
		this.setSubTask(beh4001);
		this.setSubTask(beh4002);
		sayi = new Provision();
		sayi.setprovName("sayi");
		(Ok = new Outcome()).setName("Ok");
		(Fail = new Outcome()).setName("Fail");

		addProvision(sayi);
		addOutcome(Ok);
		addOutcome(Fail);

		System.out.println("beh4000 burasi");

		InheritanceLink il1 = new InheritanceLink();
		il1.setReceiverProvision(beh4001.sayi);
		il1.setReceiverTask(beh4001);
		il1.setSenderProvision(this.sayi);

		ProvisionOutcomeLink proOutLink1 = new ProvisionOutcomeLink();
		proOutLink1.setReceiverProvision(beh4002.Numara);
		proOutLink1.setReceiverTask(beh4002);
		proOutLink1.setSenderOutcome(beh4001.Ok);
		proOutLink1.setSenderOutcomeName(beh4001.Ok.getName());
		proOutLink1.setSenderTask(beh4001);

		DisinheritanceLink dl1 = new DisinheritanceLink();
		dl1.setReceiverTask(this);
		dl1.setReceiverOutcome(Fail);
		dl1.setSenderOutcome(beh4001.Fail);
		dl1.setSenderTask(beh4001);
		dl1.setOutcomeName("Fail");

		DisinheritanceLink dl2 = new DisinheritanceLink();
		dl2.setReceiverTask(this);
		dl2.setReceiverOutcome(Ok);
		dl2.setSenderOutcome(beh4002.Ok);
		dl2.setSenderTask(beh4002);
		dl2.setOutcomeName("Ok");

		DisinheritanceLink dl3 = new DisinheritanceLink();
		dl3.setReceiverTask(this);
		dl3.setReceiverOutcome(Fail);
		dl3.setSenderOutcome(beh4002.Fail);
		dl3.setSenderTask(beh4002);
		dl3.setOutcomeName("Fail");

		links.add(il1);
		links.add(proOutLink1);
		links.add(dl1);
		links.add(dl2);
		links.add(dl3);

	}
}
