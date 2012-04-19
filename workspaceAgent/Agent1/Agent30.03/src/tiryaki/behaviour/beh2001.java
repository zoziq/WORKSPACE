package tiryaki.behaviour;

import links.DisinheritanceLink;
import links.ExternalLink;
import links.InheritanceLink;
import links.ProvisionOutcomeLink;
import task.Action;
import task.Behaviour;
import task.Outcome;
import task.Provision;
import task.Task;
import tiryaki.action.ac2001;
import tiryaki.action.ac2002;
import tiryaki.action.ac2003;

public class beh2001 extends Behaviour{

	Provision location;
	Outcome Ok;
	Outcome Fail;
	public beh2001(){
		this.setId("beh2001");
		this.setName("Otel Etmenlerini Bul");
		ac2001 ac2001=new ac2001();
		ac2002 ac2002=new ac2002();
		ac2003 ac2003=new ac2003();
		this.setSubTask(ac2001);
		this.setSubTask(ac2002);
		this.setSubTask(ac2003);
		location=new Provision();
		location.setprovName("location");
		(Ok = new Outcome()).setName("Ok");
		(Fail = new Outcome()).setName("Fail");
		
		addProvision(location);
		addOutcome(Ok);
		addOutcome(Fail);
		
		
		InheritanceLink il1 = new InheritanceLink();
		il1.setReceiverProvision(ac2001.location);
		il1.setReceiverTask(ac2001);
		il1.setSenderProvision(this.location);
		
		DisinheritanceLink dl1 = new DisinheritanceLink();
		dl1.setReceiverTask(this);
		dl1.setReceiverOutcome(Ok);
		dl1.setSenderOutcome(ac2003.Ok);
		dl1.setSenderTask(ac2003);
		dl1.setOutcomeName("Ok");

	
		DisinheritanceLink dl2 = new DisinheritanceLink();
		dl2.setReceiverTask(this);
		dl2.setReceiverOutcome(Fail);
		dl2.setSenderOutcome(ac2001.Fail);
		dl2.setSenderTask(ac2001);
		dl2.setOutcomeName("Fail");
		
		DisinheritanceLink dl3 = new DisinheritanceLink();
		dl3.setReceiverTask(this);
		dl3.setReceiverOutcome(Fail);
		dl3.setSenderOutcome(ac2003.Fail);
		dl3.setSenderTask(ac2003);
		dl3.setOutcomeName("Fail");
		
		ProvisionOutcomeLink proOutLink=new ProvisionOutcomeLink();
		proOutLink.setReceiverProvision(ac2002.sorgu);
		proOutLink.setReceiverTask(ac2002);
		proOutLink.setSenderOutcome(ac2001.Ok);
		proOutLink.setSenderOutcomeName(ac2001.Ok.getName());
		proOutLink.setSenderTask(ac2001);

		ExternalLink exlink=new ExternalLink();
		exlink.setReceiverProvision(ac2003.AIDs);
		exlink.setReceiverTask(ac2003);
		exlink.setsourceTask(ac2002);
		
		// Linkleri link kuyru�una ekliyoruz
		links.add(il1);
		links.add(dl1);
		links.add(dl2);
		links.add(dl3);
		links.add(proOutLink);
		links.add(exlink);

		System.out.println("beh2001 burasi"); 
	}
	
}
