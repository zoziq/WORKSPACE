package tiryaki.behaviour;

import links.DisinheritanceLink;
import task.Behaviour;
import task.Outcome;
import tiryaki.action.MshnAction;

public class MshnBehaviour extends Behaviour{
	
	Outcome Ok, Fail;
	
	public MshnBehaviour() {
		
//		System.out.println("--- MshnBehaviour'a girildi. ---");
		
		this.setName("Mesaj");
		this.setId("MshnBehaviour");
		
		MshnAction action = new MshnAction();
		
		this.setSubTask(action);
		
		(Ok = new Outcome()).setName("Ok");
		(Fail = new Outcome()).setName("Fail");
		
		addOutcome(Ok);
		addOutcome(Fail);
		
		DisinheritanceLink dl1 = new DisinheritanceLink();
		dl1.setReceiverTask(this);
		dl1.setReceiverOutcome(Ok);
		dl1.setSenderOutcome(action.Ok);
		dl1.setSenderTask(action);
		dl1.setOutcomeName("Ok");
		
		DisinheritanceLink dl2 = new DisinheritanceLink();
		dl2.setReceiverTask(this);
		dl2.setReceiverOutcome(Fail);
		dl2.setSenderOutcome(action.Fail);
		dl2.setSenderTask(action);
		dl2.setOutcomeName("Fail");
		
		links.add(dl1);
		links.add(dl2);
		
//		System.out.println("--- MshnBehaviour'dan çýkýlýyor. ---");
	
	}

}
