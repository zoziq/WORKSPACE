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

public class beh2003 extends Behaviour {

	Provision Oda;
	Outcome Ok;
	Outcome Fail;
	
	public beh2003() {
		this.setName("Oda_Kirala");
		this.setId("beh2003");

		ac2009 ac2009=new ac2009();
		ac2012 ac2012=new ac2012();
		this.setSubTask(ac2009);
		this.setSubTask(ac2012);
		
		Oda=new Provision();
		Oda.setprovName("Oda");
		(Ok = new Outcome()).setName("Ok");
		(Fail = new Outcome()).setName("Fail");
		
		addProvision(Oda);
		addOutcome(Ok);
		addOutcome(Fail);
		
		InheritanceLink il1 = new InheritanceLink();
		il1.setReceiverProvision(ac2009.Oda);
		il1.setReceiverTask(ac2009);
		il1.setSenderProvision(this.Oda);
		
		
		DisinheritanceLink dl5 = new DisinheritanceLink();
		dl5.setReceiverTask(this);
		dl5.setReceiverOutcome(Fail);
		dl5.setSenderOutcome(ac2012.Fail);
		dl5.setSenderTask(ac2012);
		dl5.setOutcomeName("Fail");
		
		DisinheritanceLink dl6 = new DisinheritanceLink();
		dl6.setReceiverTask(this);
		dl6.setReceiverOutcome(Ok);
		dl6.setSenderOutcome(ac2012.Ok);
		dl6.setSenderTask(ac2012);
		dl6.setOutcomeName("Ok");
		
		ExternalLink exlink=new ExternalLink();
		exlink.setReceiverProvision(ac2012.Cevap);
		exlink.setReceiverTask(ac2012);
		exlink.setsourceTask(ac2009);
		
		
		
		
		links.add(il1);
		links.add(dl5);
		links.add(dl6);
		links.add(exlink);
		
		System.out.println("beh 2003 burasi oda kirala");

	}
}
