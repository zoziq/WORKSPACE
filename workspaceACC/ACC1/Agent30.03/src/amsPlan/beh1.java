package amsPlan;

import links.DisinheritanceLink;
import task.Behaviour;
import task.Outcome;

public class beh1 extends Behaviour{

	public Outcome Ok;
	public Outcome Fail;
	
	public beh1() {
	
		this.setPlanNum(1);
		this.setId("1");
		this.setName("beh1");
		
		act1 action = new act1();
		this.setSubTask(action);
		
		Ok = new Outcome();
		Ok.setName("Ok");
		Fail = new Outcome();
		Fail.setName("Fail");

		addOutcome(Ok);
		addOutcome(Fail);
	
		// Disinteritance ile de alt taskin outcome'larini behaviour'in outcome'ina bagladim.
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

		// Linkleri link kuyruguna ekliyoruz.
		links.add(dl1);
		links.add(dl2);
		
	}
	
}
