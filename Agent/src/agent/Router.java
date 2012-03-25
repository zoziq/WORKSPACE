package agent;

import java.io.Serializable;
import java.rmi.Naming;

import rmi.ACCFacade;
import fipa.FipaMessage;

public class Router implements Serializable {

	public Router() {

	}

	/**
	 * ACC'ye gonderilecek olan mesajin buraya gelmis olmasi gerekir.
	 * Bu metot ile gelen mesaj, ACC'ye yonlendirelecektir.
	 * @param fm gelen FIPA Message
	 * 
	 */
	public void sendMessage(FipaMessage fm) {
		// rmi davran���na sahip mesajsa kendi platformu
		// i�erisinde y�nlendirilecektir.
		if (fm.getEnvelope().getTransportBehavior().equals("rmi")) {
			System.out.println("router ");

			ACCFacade rm;
			try {
				rm = (ACCFacade) Naming.lookup("rmi://127.0.0.1/ACC");
				System.out.println("Router, mesaj� ACC'ye g�nderiyor...");
				rm.send(fm);
			} catch (Exception ex) {
				ex.printStackTrace();
			}

		}

		// iiop davran���na sahip mesajlarsa farkl� platformlara gideceklerdir.
		if (fm.getEnvelope().getTransportBehavior().equals("iiop")) {
			System.out.println("protokol iiop");
		}

	}
}
