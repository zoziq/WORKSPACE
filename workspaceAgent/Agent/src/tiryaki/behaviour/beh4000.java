package tiryaki.behaviour;

import links.DisinheritanceLink;
import links.InheritanceLink;
import links.ProvisionOutcomeLink;
import task.Action;
import task.Behaviour;
import task.Outcome;
import task.Provision;
import tiryaki.action.ac2002;
import tiryaki.action.ac2003;
import tiryaki.action.ac4001;

public class beh4000 extends Behaviour {
	Provision uygun;
	Outcome Ok;
	Outcome Fail;
	public beh4000() {
		this.setId("beh2000");
		this.setName("Otel Uygunluk");
		ac4001 ac4001=new ac4001();
		this.setSubTask(ac4001);
		uygun=new Provision();
		uygun.setprovName("uygun");
		(Ok = new Outcome()).setName("Ok");
		(Fail = new Outcome()).setName("Fail");
		
		addProvision(uygun);
		addOutcome(Ok);
		addOutcome(Fail);
		
		System.out.println("beh4000 burasi");
		
		InheritanceLink il1 = new InheritanceLink();
		il1.setReceiverProvision(ac4001.location);
		il1.setReceiverTask(ac4001);
		il1.setSenderProvision(this.uygun);
		
		// Linkleri link kuyru�una ekliyoruz
		links.add(il1);

	}
}
