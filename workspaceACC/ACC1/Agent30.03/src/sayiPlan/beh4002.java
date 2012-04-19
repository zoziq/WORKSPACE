package sayiPlan;

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

public class beh4002 extends Behaviour{

	public Provision Numara;
	public Outcome Ok;
	public Outcome Fail;
	
	
	public beh4002(){
		this.setId("beh4002");
		this.setName("Numara beh");
		ac4002 ac4002=new ac4002();
		this.setSubTask(ac4002);
		Numara=new Provision();
		Numara.setprovName("Numara");

		(Ok = new Outcome()).setName("Ok");
		(Fail = new Outcome()).setName("Fail");
		
		addProvision(Numara);
		addOutcome(Ok);
		addOutcome(Fail);
		
		
		InheritanceLink il1 = new InheritanceLink();
		il1.setReceiverProvision(ac4002.Numara);
		il1.setReceiverTask(ac4002);
		il1.setSenderProvision(this.Numara);
		
		DisinheritanceLink dl1 = new DisinheritanceLink();
		dl1.setReceiverTask(this);
		dl1.setReceiverOutcome(Ok);
		dl1.setSenderOutcome(ac4002.Ok);
		dl1.setSenderTask(ac4002);
		dl1.setOutcomeName("Ok");

	
		DisinheritanceLink dl2 = new DisinheritanceLink();
		dl2.setReceiverTask(this);
		dl2.setReceiverOutcome(Fail);
		dl2.setSenderOutcome(ac4002.Fail);
		dl2.setSenderTask(ac4002);
		dl2.setOutcomeName("Fail");
		
		// Linkleri link kuyru�una ekliyoruz
		links.add(il1);
		links.add(dl1);
		links.add(dl2);

		System.out.println("beh4002 burasi"); 
	}
	
}
