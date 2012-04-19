package tiryaki.behaviour;

import links.DisinheritanceLink;
import links.ExternalLink;
import links.InheritanceLink;
import task.Action;
import task.Behaviour;
import task.Outcome;
import task.Provision;
import tiryaki.action.ac2002;
import tiryaki.action.ac2004;
import tiryaki.action.ac2005;

public class beh2002 extends Behaviour {
	Provision AIDs;
	Outcome Ok;
	Outcome Fail;
	
	public beh2002() {
		
		this.setName("Uygunluk_Sor");
		this.setId("beh2002");
		ac2004 ac2004=new ac2004();
		ac2005 ac2005=new ac2005();
		this.setSubTask(ac2004);
		this.setSubTask(ac2005);
		
		AIDs=new Provision();
		AIDs.setprovName("AIDs");
		(Ok = new Outcome()).setName("Ok");
		(Fail = new Outcome()).setName("Fail");
		
		addProvision(AIDs);
		addOutcome(Ok);
		addOutcome(Fail);
		
		InheritanceLink il1 = new InheritanceLink();
		il1.setReceiverProvision(ac2004.AIDs);
		il1.setReceiverTask(ac2004);
		il1.setSenderProvision(this.AIDs);
		
		DisinheritanceLink dl1 = new DisinheritanceLink();
		dl1.setReceiverTask(this);
		dl1.setReceiverOutcome(Ok);
		dl1.setSenderOutcome(ac2005.Ok);
		dl1.setSenderTask(ac2005);
		dl1.setOutcomeName("Ok");

	
		DisinheritanceLink dl2 = new DisinheritanceLink();
		dl2.setReceiverTask(this);
		dl2.setReceiverOutcome(Fail);
		dl2.setSenderOutcome(ac2005.Fail);
		dl2.setSenderTask(ac2005);
		dl2.setOutcomeName("Fail");
		
		
		ExternalLink exlink=new ExternalLink();
		exlink.setReceiverProvision(ac2005.Cevap);
		exlink.setReceiverTask(ac2005);
		exlink.setsourceTask(ac2004);
		
		
		links.add(il1);
		links.add(dl1);
		links.add(dl2);
		links.add(exlink);
		System.out.println("beh 2002 burasi uygunluk sor");
	}
}
