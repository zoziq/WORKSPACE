package amsPlan;

import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.util.FileManager;

import fipa.FipaMessage;
import task.Action;
import task.Outcome;
import task.Provision;

public class act1 extends Action{

	Outcome Ok;
	Outcome Fail;
	
	public act1() {
	
		this.setId("2");
		this.setName("act1");
		
		Ok = new Outcome();
		Ok.setName("Ok");
		Fail = new Outcome();
		Fail.setName("Fail");

		addOutcome(Ok);
		addOutcome(Fail);
		
	}
	
	@Override
	public void doAction() {
		
		System.out.println("Action1 ==> doAction() metodu çalýþtý.");
		
	}

	@Override
	public void sendMessage(FipaMessage fm) {
		// TODO Auto-generated method stub
		
	}
	
}
