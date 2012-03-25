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
		// rmi davranýþýna sahip mesajsa kendi platformu
		// içerisinde yönlendirilecektir.
		if (fm.getEnvelope().getTransportBehavior().equals("rmi")) {
			System.out.println("router ");

			ACCFacade rm;
			try {
				rm = (ACCFacade) Naming.lookup("rmi://127.0.0.1/ACC");
				System.out.println("Router, mesajý ACC'ye gönderiyor...");
				rm.send(fm);
			} catch (Exception ex) {
				ex.printStackTrace();
			}

		}

		// iiop davranýþýna sahip mesajlarsa farklý platformlara gideceklerdir.
		if (fm.getEnvelope().getTransportBehavior().equals("iiop")) {
			System.out.println("protokol iiop");
		}

	}
}
