package tiryaki.behaviour;

import links.DisinheritanceLink;
import links.ExternalLink;
import links.InheritanceLink;
import links.ProvisionOutcomeLink;
import task.Action;
import task.Behaviour;
import task.Outcome;
import task.Provision;
import task.Task;
import tiryaki.action.ac3001;

public class beh3000 extends Behaviour{

	Provision location;
	Outcome Ok;
	Outcome Fail;
	public beh3000(){
		this.setId("beh3000");
		this.setName("Otel Etmen Bul");
		ac3001 ac3001=new ac3001();
		this.setSubTask(ac3001);
		location=new Provision();
		location.setprovName("location");
		(Ok = new Outcome()).setName("Ok");
		(Fail = new Outcome()).setName("Fail");
		
		addProvision(location);
		addOutcome(Ok);
		addOutcome(Fail);
		
		
		InheritanceLink il1 = new InheritanceLink();
		il1.setReceiverProvision(ac3001.location);
		il1.setReceiverTask(ac3001);
		il1.setSenderProvision(this.location);

		
		
		// Linkleri link kuyru�una ekliyoruz
		links.add(il1);
	

		System.out.println("beh3000 burasi"); 
	}
	
}
