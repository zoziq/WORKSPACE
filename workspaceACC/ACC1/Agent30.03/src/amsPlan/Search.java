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
	
	public Search()
	{
		Searching k=new Searching();
		this.setSubTask(k);
		name=new Provision();
		name.setprovName("name");
		//provisions.add(name);
		Ok=new Outcome();
		Fail=new Outcome();
	//	outcomes.add(Ok);
	//	outcomes.add(Fail);
		
		//Inher�tance l�nk �le behav�our prov�s�onu alt task prov�s�ona baglad�m
		InheritanceLink il1 = new InheritanceLink();
		il1.setSenderProvision(this.name);
		il1.setReceiverTask(k);
		il1.setReceiverProvision(k.name);
	
		// Dis�nteritance ilede alt task�n outcome lar�n� behav�our�n outcome'�na ba�lad�m
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
		//dl2.update();
		
		// Linkleri link kuyru�una ekliyoruz
		links.add(il1);
		links.add(dl1);
		links.add(dl2);
	}
}
