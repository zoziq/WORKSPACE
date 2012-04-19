package test;

import links.DisinheritanceLink;
import task.Behaviour;
import task.Outcome;

public class Beh1 extends Behaviour{

	public Outcome Ok;
	public Outcome Fail;
	
	public Beh1() {
	
		this.setPlanNum(1);
		this.setId("1");
		this.setName("Beh1");
		
		Act1 action = new Act1();
		this.setSubTask(action);
		
		Ok = new Outcome();
		Ok.setName("Ok");
		Fail = new Outcome();
		Fail.setName("Fail");

		addOutcome(Ok);
		addOutcome(Fail);
		
		DisinheritanceLink dis1 = new DisinheritanceLink();
		dis1.setReceiverTask(this);
		dis1.setReceiverOutcome(Ok);
		dis1.setSenderOutcome(action.Ok);
		dis1.setSenderTask(action);
		dis1.setOutcomeName("Ok");

		DisinheritanceLink dis2 = new DisinheritanceLink();
		dis2.setReceiverTask(this);
		dis2.setReceiverOutcome(Fail);
		dis2.setSenderOutcome(action.Fail);
		dis2.setSenderTask(action);
		dis2.setOutcomeName("Fail");

		links.add(dis1);
		links.add(dis2);
		
	}
	
}
