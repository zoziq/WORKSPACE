package test;

import fipa.FipaMessage;
import task.Action;
import task.Outcome;

public class MerhabaAction extends Action{

	Outcome Ok;
	Outcome Fail;
	
	public MerhabaAction() {
	
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
		
	}
	
}
