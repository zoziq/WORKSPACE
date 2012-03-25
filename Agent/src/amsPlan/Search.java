package amsPlan;

import links.DisinheritanceLink;
import links.InheritanceLink;
import task.Behaviour;
import task.Outcome;
import task.Provision;

public class Search extends Behaviour {

	Provision name;
	Outcome Ok;
	Outcome Fail;

	public Search() {
		Searching k = new Searching();
		this.setSubTask(k);
		name = new Provision();
		name.setprovName("name");
		// provisions.add(name);
		Ok = new Outcome();
		Fail = new Outcome();
		// outcomes.add(Ok);
		// outcomes.add(Fail);

		// Inheritance link ile behaviour provisionu alt task
		// provisionina baglandi.
		InheritanceLink il1 = new InheritanceLink();
		il1.setSenderProvision(this.name);
		il1.setReceiverTask(k);
		il1.setReceiverProvision(k.name);

		// Disinteritance ile de alt taskin outcome'lari
		// behaviourun outcome'ina baglandi.
		DisinheritanceLink dl1 = new DisinheritanceLink();
		dl1.setReceiverOutcome(Ok);
		dl1.setSenderOutcome(k.Ok);
		dl1.setSenderTask(k);
		dl1.setOutcomeName("Ok");

		DisinheritanceLink dl2 = new DisinheritanceLink();
		dl2.setReceiverOutcome(Fail);
		dl2.setSenderOutcome(k.Fail);
		dl2.setSenderTask(k);
		dl2.setOutcomeName("Fail");
		// dl2.update();

		// Linkler, link kuyruguna eklendi.
		links.add(il1);
		links.add(dl1);
		links.add(dl2);
	}
}
