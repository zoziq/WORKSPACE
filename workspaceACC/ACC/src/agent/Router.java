package agent;

import java.io.Serializable;
import java.rmi.Naming;
import java.rmi.RemoteException;

import rmi.ACC;
import rmi.ACCFacade;
import fipa.ACLMessage;
import fipa.Envelope;
import fipa.FipaMessage;

public class Router implements Serializable {

	public Router() {

	}

	public void sendMessage(FipaMessage fm) {
		if (fm.getEnvelope().getTransportBehavior().equals("rmi")) {
			System.out.println("router ");
			
			ACCFacade rm;
			try {
				rm = (ACCFacade)Naming.lookup("rmi://127.0.0.1/ACC");
				System.out.println("router acc ye yolluyor ");
				rm.send(fm);
			} catch (Exception ex) {
				ex.printStackTrace();
			}

		}

		if (fm.getEnvelope().getTransportBehavior().equals("iiop")) {
			System.out.println("protokol iiop");
		}

	}
}
