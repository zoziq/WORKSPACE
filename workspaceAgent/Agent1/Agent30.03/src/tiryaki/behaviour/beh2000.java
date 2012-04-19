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

public class beh2000 extends Behaviour {
	Provision location;
	Outcome Ok;
	Outcome Fail;
	public beh2000() {
		this.setId("beh2000");
		this.setName("Otel Odasý Kiralama");
		beh2001 beh2001=new beh2001();
		beh2002 beh2002=new beh2002();
		beh2003 beh2003=new beh2003();
		this.setSubTask(beh2001);
		this.setSubTask(beh2002);
		this.setSubTask(beh2003);
		location=new Provision();
		location.setprovName("location");
		(Ok = new Outcome()).setName("Ok");
		(Fail = new Outcome()).setName("Fail");
		
		addProvision(location);
		addOutcome(Ok);
		addOutcome(Fail);
		
		System.out.println("beh2000 burasi");
		
		InheritanceLink il1 = new InheritanceLink();
		il1.setReceiverProvision(beh2001.location);
		il1.setReceiverTask(beh2001);
		il1.setSenderProvision(this.location);
		
		ProvisionOutcomeLink proOutLink1=new ProvisionOutcomeLink();
		proOutLink1.setReceiverProvision(beh2002.AIDs);
		proOutLink1.setReceiverTask(beh2002);
		proOutLink1.setSenderOutcome(beh2001.Ok);
		proOutLink1.setSenderOutcomeName(beh2001.Ok.getName());
		proOutLink1.setSenderTask(beh2001);
		
		ProvisionOutcomeLink proOutLink2=new ProvisionOutcomeLink();
		proOutLink2.setReceiverProvision(beh2003.Oda);
		proOutLink2.setReceiverTask(beh2003);
		proOutLink2.setSenderOutcome(beh2002.Ok);
		proOutLink2.setSenderOutcomeName(beh2002.Ok.getName());
		proOutLink2.setSenderTask(beh2002);
		
		DisinheritanceLink dl1 = new DisinheritanceLink();
		dl1.setReceiverTask(this);
		dl1.setReceiverOutcome(Fail);
		dl1.setSenderOutcome(beh2001.Fail);
		dl1.setSenderTask(beh2001);
		dl1.setOutcomeName("Fail");
		
		DisinheritanceLink dl2 = new DisinheritanceLink();
		dl2.setReceiverTask(this);
		dl2.setReceiverOutcome(Fail);
		dl2.setSenderOutcome(beh2002.Fail);
		dl2.setSenderTask(beh2002);
		dl2.setOutcomeName("Fail");
		
		DisinheritanceLink dl3 = new DisinheritanceLink();
		dl3.setReceiverTask(this);
		dl3.setReceiverOutcome(Fail);
		dl3.setSenderOutcome(beh2003.Fail);
		dl3.setSenderTask(beh2003);
		dl3.setOutcomeName("Fail");
		
		DisinheritanceLink dl4 = new DisinheritanceLink();
		dl4.setReceiverTask(this);
		dl4.setReceiverOutcome(Ok);
		dl4.setSenderOutcome(beh2003.Ok);
		dl4.setSenderTask(beh2003);
		dl4.setOutcomeName("Ok");
		
		links.add(il1);
		links.add(proOutLink1);
		links.add(proOutLink2);
		links.add(dl1);
		links.add(dl2);
		links.add(dl3);
		links.add(dl4);

	}
}
