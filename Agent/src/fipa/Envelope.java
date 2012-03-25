package fipa;

import java.io.Serializable;

import agent.AgentIdentifier;
import agent.AgentIdentifierList;

/**
 * @author Aegeant-S Group
 * 
 */
public class Envelope implements Cloneable, Serializable {

	//
	public final static String FIPA_ACL_REP_BITEFFICIENT = "fipa.acl.rep.bitefficient.std";

	public final static String FIPA_ACL_REP_STRING = "fipa.acl.rep.string.std";

	public final static String FIPA_ACL_REP_XML = "fipa.acl.rep.xml.std";

	private String aclRepresentation = null; // String

	private String comments = null; // String

	private DateTime date = null;

	private AgentIdentifier from = null; // AgentIdentifier

	// creation date, can only be assigned at creation and cannot be changed
	// String[] encrypted; --- depreciated
	private AgentIdentifierList intendedReceiver = null; // AgentIdentifierList

	private String payloadEncoding = null; // cannot be changed

	private String payloadLength = null; // cannot be changed

	private ReceivedObject received = null; // ReceivedObject

	private AgentIdentifierList to = null; // AgentIdentifierList

	private String transportBehavior = null; // reserved for future use

	public Envelope() {
	}

	public void addIntendedReceiver(AgentIdentifier aid) {
		intendedReceiver.add(aid);
	}

	public void addTo(AgentIdentifier aid) {
		if (to == null) {
			to = new AgentIdentifierList(AgentIdentifierList.SEQUENCE);
		}
		to.add(aid);
	}

	public String getAclRepresentation() {
		return aclRepresentation;
	}

	public String getComments() {
		return comments;
	}

	public DateTime getDate() {
		return date;
	}

	public AgentIdentifier getFrom() {
		return from;
	}

	public AgentIdentifierList getIntendedReceiver() {
		return intendedReceiver;
	}

	public String getPayloadEncoding() {
		return payloadEncoding;
	}

	public String getPayloadLength() {
		return payloadLength;
	}

	public ReceivedObject getReceived() {
		return received;
	}

	public AgentIdentifierList getTo() {
		return to;
	}

	public String getTransportBehavior() {
		return transportBehavior;
	}

	public void setAclRepresentation(String string) {
		aclRepresentation = string;
	}

	public void setComments(String string) {
		comments = string;
	}

	public void setDate(DateTime date) {
		this.date = date;
	}

	public void setFrom(AgentIdentifier identifier) {
		from = identifier;
	}

	public void setIntendedReceiver(AgentIdentifierList list) {
		intendedReceiver = list;
	}

	public void setPayloadEncoding(String string) {
		payloadEncoding = string;
	}

	public void setPayloadLength(String string) {
		payloadLength = string;
	}

	public void setReceived(ReceivedObject object) {
		received = object;
	}

	public void setTo(AgentIdentifierList list) {
		to = list;
	}

	public void setTransportBehavior(String string) {
		transportBehavior = string;
	}

	protected Object clone() {
		// later .... my boy, later.
		return this;
	}

}