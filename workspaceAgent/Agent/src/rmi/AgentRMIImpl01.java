package rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import tiryaki.planner.Agent;

import agent.AbstractAgent;
import fipa.FipaMessage;

public class AgentRMIImpl01 extends UnicastRemoteObject implements AgentRMI {

	AbstractAgent agent;

	private static final long serialVersionUID = 1L;

	protected AgentRMIImpl01(Agent agent2) throws RemoteException {
		super();
		this.agent = agent2;

	}

	@Override
	public void receivedMessage(FipaMessage fm) {
		System.out.println("Burasý AgentRMIImpl01...");
		agent.message(fm);
	}
	
	@Override
	public void receivedMessage2(FipaMessage fm){
		System.out.println("AgentRMIImpl => receivedMessage2()");
		System.out.println(fm.getAcl().getLanguage());
	}
	
	@Override
	public void warning(String message) throws RemoteException {

	}

}
