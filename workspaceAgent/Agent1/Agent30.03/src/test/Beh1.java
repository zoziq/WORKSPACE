package test;

import links.DisinheritanceLink;
import links.ExternalLink;
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
		Act2 action2 = new Act2();
		this.setSubTask(action2);
		
		Ok = new Outcome();
		Ok.setName("Ok");
		Fail = new Outcome();
		Fail.setName("Fail");

		addOutcome(Ok);
		addOutcome(Fail);
		
		DisinheritanceLink dis1 = new DisinheritanceLink();
		dis1.setReceiverTask(this);
		dis1.setReceiverOutcome(Ok);
		dis1.setSenderOutcome(action2.Ok);
		dis1.setSenderTask(action2);
		dis1.setOutcomeName("Ok");

		DisinheritanceLink dis2 = new DisinheritanceLink();
		dis2.setReceiverTask(this);
		dis2.setReceiverOutcome(Fail);
		dis2.setSenderOutcome(action2.Fail);
		dis2.setSenderTask(action2);
		dis2.setOutcomeName("Fail");
		
		DisinheritanceLink dis3 = new DisinheritanceLink();
		dis3.setReceiverTask(this);
		dis3.setReceiverOutcome(Fail);
		dis3.setSenderOutcome(action.Fail);
		dis3.setSenderTask(action);
		dis3.setOutcomeName("Fail");

		ExternalLink exLink = new ExternalLink();
		exLink.setReceiverProvision(action2.Agent);
		exLink.setReceiverTask(action2);
		exLink.setsourceTask(action);
		
		links.add(dis1);
		links.add(dis2);
		links.add(dis3);
		links.add(exLink);
		
	}
	
}
