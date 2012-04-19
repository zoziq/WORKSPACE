package amsPlan;

import links.DisinheritanceLink;
import links.InheritanceLink;
import task.Behaviour;
import task.Outcome;
import task.Provision;

public class Register extends Behaviour {
	
	public Provision Agd;
	public Provision Aid;
	public Outcome Ok;
	public Outcome Fail;

	public Register(){
		
		this.setPlanNum(1);
		this.setId("1");
		this.setName("Register");
		
		Registeration regAction = new Registeration();
		this.setSubTask(regAction);
		
		Agd = new Provision();
		Agd.setprovName("AGD");
		Aid = new Provision();
		Aid.setprovName("AID");
		Ok = new Outcome();
		Ok.setName("Ok");
		Fail = new Outcome();
		Fail.setName("Fail");

		addOutcome(Ok);
		addOutcome(Fail);
		addProvision(Aid);
		addProvision(Agd);

		// Inheritance link ile behaviour provisionu alt task provisiona bagladim
		InheritanceLink il1 = new InheritanceLink();
		il1.setSenderProvision(this.Aid);
		il1.setReceiverTask(regAction);
		il1.setReceiverProvision(regAction.Aid);

		InheritanceLink il2 = new InheritanceLink();
		il2.setSenderProvision(this.Agd);
		il2.setReceiverTask(regAction);
		il2.setReceiverProvision(regAction.Agd);

		// Disinteritance ile de alt taskin outcome'larini behaviour'in outcome'ina bagladim.
		DisinheritanceLink dl1 = new DisinheritanceLink();
		dl1.setReceiverOutcome(Ok);
		dl1.setSenderOutcome(regAction.Ok);
		dl1.setSenderTask(regAction);
		dl1.setOutcomeName("Ok");

		DisinheritanceLink dl2 = new DisinheritanceLink();
		dl2.setReceiverOutcome(Fail);
		dl2.setSenderOutcome(regAction.Fail);
		dl2.setSenderTask(regAction);
		dl2.setOutcomeName("Fail");

		// Linkleri link kuyruguna ekliyoruz.
		links.add(il1);
		links.add(il2);
		links.add(dl1);
		links.add(dl2);

	}

}
