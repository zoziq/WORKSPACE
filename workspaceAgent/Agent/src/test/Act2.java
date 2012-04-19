package test;

import fipa.FipaMessage;
import task.Action;
import task.Outcome;
import task.Provision;

public class Act2 extends Action{

	Provision girdi;
	Outcome Ok;
	Outcome Fail;
	
	public Act2() {
	
		girdi = new Provision();
		girdi.setprovName("girdi");
		Ok = new Outcome();
		Ok.setName("Ok");
		Fail = new Outcome();
		Fail.setName("Fail");

		addProvision(girdi);
		addOutcome(Ok);
		addOutcome(Fail);
		
	}
	
	@Override
	public void doAction() {
	
		System.out.println("Act2=> doAction()... OK!!!");
		
	}

	@Override
	public void sendMessage(FipaMessage fm) {
		// TODO Auto-generated method stub
		
	}
	
}
