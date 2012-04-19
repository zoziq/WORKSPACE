package tiryaki.behaviour;

import pazarlik.ac2006;
import pazarlik.ac2007;
import pazarlik.ac2008;
import links.DisinheritanceLink;
import links.ExternalLink;
import links.InheritanceLink;
import links.ProvisionOutcomeLink;
import task.Action;
import task.Behaviour;
import task.Outcome;
import task.Provision;
import tiryaki.action.ac2004;
import tiryaki.action.ac2005;
import tiryaki.action.ac2009;
import tiryaki.action.ac2012;
import tiryaki.action.ac5001;
import tiryaki.action.ac5002;
import tiryaki.action.ac5003;

public class beh5000 extends Behaviour {

	Provision Oda;
	Outcome Ok;
	Outcome Fail;

	public beh5000() {
		
		this.setName("Pazarlýk");
		this.setId("beh5000");

		ac5001 ac5001 = new ac5001();

		this.setSubTask(ac5001);

		Oda = new Provision();
		Oda.setprovName("Oda");
		(Ok = new Outcome()).setName("Ok");
		(Fail = new Outcome()).setName("Fail");

		addProvision(Oda);
		addOutcome(Ok);
		addOutcome(Fail);

		InheritanceLink il1 = new InheritanceLink();
		il1.setReceiverProvision(ac5001.Cevap);
		il1.setReceiverTask(ac5001);
		il1.setSenderProvision(this.Oda);

		links.add(il1);

		System.out.println("beh 2003 burasi oda kirala");

	}
}
