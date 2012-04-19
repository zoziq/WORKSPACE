package fipa;

import java.io.Serializable;

public final class FipaMessage implements Serializable{
	
	private Envelope envelope;
	private ACLMessage acl;
	
	public FipaMessage(Envelope envelope,ACLMessage acl){
		this.envelope=envelope;
		this.acl=acl;
	}
	
	public ACLMessage getAcl() {
		return acl;
	}

	public void setAcl(ACLMessage acl) {
		this.acl = acl;
	}
	
	public void setEnvelope(Envelope envelope) {
		this.envelope = envelope;
	}
	public Envelope getEnvelope() {
		return envelope;
	}

}
