package sayiPlan;

import links.DisinheritanceLink;
import links.InheritanceLink;
import task.Behaviour;
import task.Outcome;
import task.Provision;

public class beh4003 extends Behaviour {

	Provision Rakam;
	Outcome Ok;
	Outcome Fail;

	public beh4003() {
		this.setName("Rakam Beh");
		this.setId("beh4003");

		ac4003 ac4003 = new ac4003();
		this.setSubTask(ac4003);

		Rakam = new Provision();
		Rakam.setprovName("Rakam");

		(Ok = new Outcome()).setName("Ok");
		(Fail = new Outcome()).setName("Fail");

		addProvision(Rakam);
		addOutcome(Ok);
		addOutcome(Fail);

		InheritanceLink il1 = new InheritanceLink();
		il1.setReceiverProvision(ac4003.Rakam);
		il1.setReceiverTask(ac4003);
		il1.setSenderProvision(this.Rakam);

		DisinheritanceLink dl1 = new DisinheritanceLink();
		dl1.setReceiverTask(this);
		dl1.setReceiverOutcome(Fail);
		dl1.setSenderOutcome(ac4003.Fail);
		dl1.setSenderTask(ac4003);
		dl1.setOutcomeName("Fail");

		DisinheritanceLink dl2 = new DisinheritanceLink();
		dl2.setReceiverTask(this);
		dl2.setReceiverOutcome(Ok);
		dl2.setSenderOutcome(ac4003.Ok);
		dl2.setSenderTask(ac4003);
		dl2.setOutcomeName("Ok");

		links.add(il1);
		links.add(dl1);
		links.add(dl2);

		System.out.println("beh 4003 burasi oda kirala");

	}
}
