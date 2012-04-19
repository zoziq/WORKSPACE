package rmi;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import javax.swing.JOptionPane;

import fipa.FipaMessage;

import agent.AID;
import agent.AgentIdentifier;
import agent.URLSequence;
import ams.AgentManagementSystem;
import ams.Ontology;

public class ACC extends UnicastRemoteObject implements ACCFacade, Serializable {
	private static final long serialVersionUID = 1L;
	Ontology onto = new Ontology();
	private static AgentManagementSystem ams;
	String ad;

	public ACC() throws RemoteException {
		ams = new AgentManagementSystem();
	}

	public FipaMessage received(AID aid) throws RemoteException {
		return null;
	}

	@Override
	public void send(FipaMessage fm) throws RemoteException {

		// Etmen kaydý isteniyorsa...
		if (fm.getAcl().getContent().getActions().get(0).getAct().equals("register")) {
			System.out.println("Agentin kaydedilmesi icin mesaj ACC'den AMS'ye gonderiliyor....");
			if (ams.registerAgent(fm)) {
				AgentRMI rm;
				try {
					rm = (AgentRMI) Naming.lookup(fm.getAcl().getContent().getActions().get(0).getArguments().get(1).toString());
					rm.receivedMessage(fm);
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NotBoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}

		else if (fm.getAcl().getContent().getActions().get(0).getAct().equals("mesaj")){
			System.out.println("Uygun if koþulu ile (mesaj) eþleme yapýldý...");
			System.out.println("String: " + fm.getAcl().getContent().getActions().get(0).getArguments().get(1).toString());
			AgentRMI rm;
			try {
				rm = (AgentRMI) Naming.lookup(fm.getAcl().getContent().getActions().get(0).getArguments().get(1).toString());
				rm.receivedMessage(fm);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (NotBoundException e) {
				e.printStackTrace();
			}
			
		}
		
		else {

			System.out.println("Mesaj, su an ACC'de...");

			try {

				for (int i = 0; i < fm.getEnvelope().getTo().size(); i++) {

					String adres = ams.search(fm.getEnvelope().getTo().get(i)
							.getName().getValue());
					if (adres.equalsIgnoreCase("")) {
						JOptionPane.showMessageDialog(null,
								"Etmen su an aktif deðil!");
					} else {
						URLSequence addrReceiver = new URLSequence();
						addrReceiver.add(adres);
						fm.getEnvelope().getTo().get(i).setAddresses(
								addrReceiver);
					}

				}
				for (int i = 0; i < fm.getEnvelope().getTo().size(); i++) {
					AgentIdentifier list = fm.getEnvelope().getTo().get(i);
					URLSequence url = list.getAddresses();
					for (int j = 0; j < url.getUrls().size(); j++) {

						System.out.println("UrlSequence " + url.getUrls().get(j));
						AgentRMI rm = (AgentRMI) Naming.lookup(url.getUrls()
								.get(j).toString());
						rm.receivedMessage(fm);
						System.out.println("Mesaj, su an ACC'de...");
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

			/*
			 * AgentRMI uyari; try { uyari = (AgentRMI)
			 * Naming.lookup(fm.getAcl()
			 * .getSender().getAddress().get(0).toString());
			 * System.out.println("mesaj suan acc de");
			 * 
			 * } catch (MalformedURLException e) { // TODO Auto-generated catch
			 * block e.printStackTrace(); } catch (NotBoundException e) { //
			 * TODO Auto-generated catch block e.printStackTrace(); }
			 */
		}

	}

	public static void sendMessage(FipaMessage fm) throws RemoteException {

		System.out.println(fm.getAcl().getContent().getActions().get(0).getAct());

		if (fm.getAcl().getContent().getActions().get(0).getAct().equals("register")) {
			System.out.println("Agentin kaydedilmesi icin mesaj ACC'den AMS'ye gonderiliyor....");
			if (ams.registerAgent(fm)) {
				AgentRMI rm;
				try {
					rm = (AgentRMI) Naming.lookup(fm.getAcl().getContent()
							.getActions().get(0).getArguments().get(1)
							.toString());
					rm.receivedMessage(fm);
				} catch (MalformedURLException e) {
					e.printStackTrace();
				} catch (NotBoundException e) {
					e.printStackTrace();
				}

			}
		}

		else if (fm.getAcl().getContent().getActions().get(0).getAct().equals("mesaj")) {
			System.out.println("Mesaj, görüntülenmek üzere ACC'den AMS'ye gönderiliyor.");
			if (ams.registerAgent(fm)) {
				AgentRMI rm;

				try {
					rm = (AgentRMI) Naming.lookup(fm.getAcl().getContent()
							.getActions().get(0).getArguments().get(1)
							.toString());
					rm.receivedMessage(fm);
				} catch (MalformedURLException e) {
					e.printStackTrace();
				} catch (NotBoundException e) {
					e.printStackTrace();
				}

			}

		}

		else {

			System.out.println("Mesaj, su an ACC'de");

			try {

				for (int i = 0; i < fm.getEnvelope().getTo().size(); i++) {

					String adres = ams.search(fm.getEnvelope().getTo().get(i)
							.getName().getValue());
					if (adres.equalsIgnoreCase("")) {
						JOptionPane.showMessageDialog(null,
								"Etmen su an aktif deðil!");
					} else {
						URLSequence addrReceiver = new URLSequence();
						addrReceiver.add(adres);
						fm.getEnvelope().getTo().get(i).setAddresses(
								addrReceiver);
					}

				}
				for (int i = 0; i < fm.getEnvelope().getTo().size(); i++) {
					AgentIdentifier list = fm.getEnvelope().getTo().get(i);
					URLSequence url = list.getAddresses();
					for (int j = 0; j < url.getUrls().size(); j++) {

						System.out.println("UrlSequence " + url.getUrls().get(j));
						AgentRMI rm = (AgentRMI) Naming.lookup(url.getUrls()
								.get(j).toString());
						rm.receivedMessage(fm);
						System.out.println("Mesaj, su an ACC'de...");
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

			/*
			 * AgentRMI uyari; try { uyari = (AgentRMI)
			 * Naming.lookup(fm.getAcl()
			 * .getSender().getAddress().get(0).toString());
			 * System.out.println("mesaj suan acc de");
			 * 
			 * } catch (MalformedURLException e) { // TODO Auto-generated catch
			 * block e.printStackTrace(); } catch (NotBoundException e) { //
			 * TODO Auto-generated catch block e.printStackTrace(); }
			 */
		}

	}

}