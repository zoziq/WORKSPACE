package rmi;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

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

		if (fm.getAcl().getContent().getActions().get(0).getAct().equals("register")) {
			System.out.println("agentin kaydedilmesi icin mesaj acc den ams ye gonderiliyor....");
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
		
		else if (fm.getAcl().getContent().getActions().get(0).getAct().equals("icerik")){
			System.out.println("Uygun if koþulu ile (icerik) eþleme yapýldý...");
			System.out.println("Gönderen Etmen: " + fm.getAcl().getContent().getActions().get(0).getArguments().get(1));
			
			System.out.println("Content yazýdýrýlýyor...");
			int size = fm.getAcl().getContent().getActions().size();
			for (int i=0; i<size; i++){
				System.out.println("Action: " + fm.getAcl().getContent().getActions().get(i).getAct());
				System.out.println("Actor: " + fm.getAcl().getContent().getActions().get(i).getActor());
				Object[] array = fm.getAcl().getContent().getActions().get(i).getArguments().toArray();
				System.out.println("Contentin argumanlarý yazdýrýlýyor...");
				for (int j=0; j<array.length; j++){
					System.out.println(array[j]);
				}
			}
			System.out.println("Content yazdýrýldý...");			
		}

		else if (fm.getAcl().getContent().getActions().get(0).getAct().equals("mesaj")){
			
			System.out.println("Uygun if koþulu ile (mesaj) eþleme yapýldý...");
			System.out.println("Gönderen Etmen: " + fm.getAcl().getContent().getActions().get(0).getArguments().get(0));

			AgentRMI rm;
			try {
				rm = (AgentRMI) Naming.lookup(fm.getAcl().getContent().getActions().get(0).getArguments().get(1).toString());
				rm.receivedMessage2(fm);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (NotBoundException e) {
				e.printStackTrace();
			}
			
		}
		
		else {

			System.out.println("mesaj suan acc de");

			try {

				for (int i = 0; i < fm.getEnvelope().getTo().size(); i++) {

					String adres = ams.search(fm.getEnvelope().getTo().get(i).getName().getValue());
					if (adres.equalsIgnoreCase("")) {
						JOptionPane.showMessageDialog(null,	"Etmen Suan Aktif Deðil");
					} else {
						URLSequence addrReceiver = new URLSequence();
						addrReceiver.add(adres);
						fm.getEnvelope().getTo().get(i).setAddresses(addrReceiver);
					}

				}
				for (int i = 0; i < fm.getEnvelope().getTo().size(); i++) {
					AgentIdentifier list = fm.getEnvelope().getTo().get(i);
					URLSequence url = list.getAddresses();
					for (int j = 0; j < url.getUrls().size(); j++) {

						System.out.println("url seq " + url.getUrls().get(j));
						AgentRMI rm = (AgentRMI) Naming.lookup(url.getUrls().get(j).toString());
						rm.receivedMessage(fm);
						System.out.println("mesaj suan acc de");
					}
				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
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
			System.out.println("agentin kaydedilmesi icin mesaj acc den ams ye gonderiliyor....");
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

		else {

			System.out.println("mesaj suan acc de");

			try {

				for (int i = 0; i < fm.getEnvelope().getTo().size(); i++) {

					String adres = ams.search(fm.getEnvelope().getTo().get(i).getName().getValue());
					if (adres.equalsIgnoreCase("")) {
						JOptionPane.showMessageDialog(null,	"Etmen Suan Aktif Deðil");
					} else {
						URLSequence addrReceiver = new URLSequence();
						addrReceiver.add(adres);
						fm.getEnvelope().getTo().get(i).setAddresses(addrReceiver);
					}

				}
				for (int i = 0; i < fm.getEnvelope().getTo().size(); i++) {
					AgentIdentifier list = fm.getEnvelope().getTo().get(i);
					URLSequence url = list.getAddresses();
					for (int j = 0; j < url.getUrls().size(); j++) {

						System.out.println("url seq " + url.getUrls().get(j));
						AgentRMI rm = (AgentRMI) Naming.lookup(url.getUrls().get(j).toString());
						rm.receivedMessage(fm);
						System.out.println("mesaj suan acc de");
					}
				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
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