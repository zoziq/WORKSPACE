package test;

import java.io.File;
import java.io.IOException;
import java.rmi.RemoteException;

import com.sun.accessibility.internal.resources.accessibility;

import df.DirectoryFacilitator;

import agent.AgentIdentifier;
import agent.URLSequence;
import agent.Word;
import ams.AgentManagementSystem;

import rmi.ACC;
import rmi.AgentRMIImpl01;
import rmi.RemoteMessageServer;
import tiryaki.behaviour.MshnBehaviour;
import tiryaki.planner.Agent;

public class PlatformLauncher {

	private String platformName = "mshn.com";
	private String amsAddress = "rmi://localhost/ams";
	AgentManagementSystem ams;
	ACC acc;
	
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		
		// required policy file for RMI
		System.setProperty("java.security.policy", "policy");
		String javaHome = System.getProperty("java.home");
		
		File f = new File(javaHome);
		String javaBin = f.getParent() + "/bin";
		String rmic = javaBin + "/rmic";
		String rmiRegistry = javaBin + "/rmiregistry";
		
		PlatformLauncher launcher = new PlatformLauncher();
//		launcher.rmic(rmic);
//		launcher.rmiRegistry(rmiRegistry);
		launcher.startAMS();
		launcher.startACC();
		launcher.startDF();
		
		AgentIdentifier aid = new AgentIdentifier();
		aid.setName(new Word("platformLauncher@mshn.com"));

		URLSequence addrSender = new URLSequence();
		addrSender.add("rmi://192.168.2.183/Agent");
		aid.setAddresses(addrSender);
		
		Agent agent = new Agent(aid, "rmi://192.168.2.183/ACC");
		
		new RemoteMessageServer(agent);
		
		System.out.println("ACC Address: " + agent.getACCAddress());
		System.out.println("Agent Name: " + agent.getAgentName());
		System.out.println("Full Agent Name: " + agent.getFullAgentName());
	
	}
	
	public void rmic(String rmic) {
		try {
			Runtime.getRuntime().exec(rmic + " " + AgentRMIImpl01.class.getName());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void rmiRegistry(String rmiRegistry) {
		try {
			Runtime.getRuntime().exec(rmiRegistry);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void startAMS() {
//		// AMS AID
//		agent.AgentIdentifier amsAID = new agent.AgentIdentifier();
//		amsAID.setName(new Word("ams@" + platformName));
//		URLSequence url = new URLSequence();
//		url.add(amsAddress);
//		amsAID.setAddresses(url);
		// Start AMS
		ams = new AgentManagementSystem();
//		// AMS AMSAgentDescription
//		AMSAgentDescription amsInfo = new AMSAgentDescription();
//		amsInfo.setName(amsAID);
//		// Start AMS
//		AgentManagementSystem ams = new AgentManagementSystem(amsInfo, amsAddr,	new AMSOntologyFacade());
	}
	
	public void startACC() {
		
		try {
			acc = new ACC();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void startDF(){
		
		DirectoryFacilitator df = new DirectoryFacilitator();
		
	}
	
}
