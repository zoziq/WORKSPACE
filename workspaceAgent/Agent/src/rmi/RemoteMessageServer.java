package rmi;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import tiryaki.planner.Agent;

public class RemoteMessageServer {
	public RemoteMessageServer(Agent agent) {
		Registry reg = null;

		try {
			reg = LocateRegistry.createRegistry(1100);
			reg = LocateRegistry.getRegistry();
			reg.rebind("Agent", new AgentRMIImpl01(agent));
			System.out.println("RemoteMessageServer calisiyor...");
		} catch (Exception e) {
			System.out.println("RemoteMessageServer: " + e.toString());
		}
	}
}