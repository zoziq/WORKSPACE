package tiryaki.action;

import java.util.StringTokenizer;
import java.util.Vector;

import fipa.FipaMessage;
import task.Action;
import task.Outcome;
import task.Output;
import task.Provision;

public class ac2003 extends Action {
	
	int PlanNum;
	public Provision AIDs;
	public Outcome Ok;
	public Outcome Fail;
	
	  
	public ac2003(){
		this.setId("ac2003");
		this.setName("Otel Etmen Degerlendir");
		(AIDs = new Provision()).setprovName("AIDs");
		(Ok = new Outcome()).setName("Ok");
		(Fail = new Outcome()).setName("Fail");
		addOutcome(Ok);
		addOutcome(Fail);
		addProvision(AIDs);
	}
	

	@Override
	public void doAction() {
		System.out.println("AID deger "+AIDs.getValue().toString());
		StringTokenizer st=new StringTokenizer(AIDs.getValue().toString());
		String ad = null;
		while(st.hasMoreTokens())
		{
		System.out.println("1"+st.nextToken());	
		System.out.println("2"+st.nextToken());	
		System.out.println("3"+st.nextToken());	
		System.out.println("4"+st.nextToken());	
		ad=st.nextToken();	
		System.out.println("5"+st.nextToken());	
		
		}
		
		Output out=new Output();
		out.setName("AIDs");
		out.setValue(ad);
		Ok.addOutput(out);
		Fail.setOutputList(null);
		
	}

	@Override
	public void sendMessage(FipaMessage fm) {
		// TODO Auto-generated method stub
		
	}
}
