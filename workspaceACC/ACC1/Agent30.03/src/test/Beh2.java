package test;

import links.DisinheritanceLink;
import links.ExternalLink;
import task.Behaviour;
import task.Outcome;

public class Beh2 extends Behaviour {

	public Outcome Ok;
	public Outcome Fail;

	public Beh2() throws InstantiationException, IllegalAccessException {

		this.setPlanNum(1);
		this.setId("1");
		this.setName("Beh2");

		Act2 action = new Act2();
		this.setSubTask(action);

		Ok = new Outcome();
		Ok.setName("Ok");
		Fail = new Outcome();
		Fail.setName("Fail");

		addOutcome(Ok);
		addOutcome(Fail);

		ExternalLink exLink = new ExternalLink();
		exLink.setReceiverProvision(action.girdi);
		exLink.setReceiverTask(action);
		exLink.setsourceTask(Act1.class.newInstance());
		
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
