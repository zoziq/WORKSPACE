package fipa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import agent.AID;
import agent.AgentIdentifier;


// AClMessage sinifi Fipa mesajimizin payload kismindaki bilgieri tutan siniftir...
public class ACLMessage implements Serializable{
	
	private AgentIdentifier sender;
	private ArrayList<AgentIdentifier> receiver=new ArrayList<AgentIdentifier>();
	private ArrayList<AgentIdentifier> reply_to=new ArrayList<AgentIdentifier>();
	private Content content;
	private String language;
	private String encoding;
	private String ontology;
	private String protocol;
	private String conversation_id;
	private String reply_with;
	private String in_reply_to;
	private String reply_by;
	private String performative;
	
	private ArrayList performativeList;
	
	public ACLMessage(){
		performativeList = new ArrayList();
		// load the performative to this performative list
		loadPerformativeList();
		// set performative to UNKNOWN
		setPerformative("");
	}
	public ACLMessage(String performative)
	{
		this();
		// set the performative to the incoming performative
		setPerformative(performative);
	}
	public void setSender(AgentIdentifier sender) {
		this.sender = sender;
	}
	public void addReceiver(AgentIdentifier receiver){
		this.receiver.add(receiver);
	}
	public void addReplyTo(AgentIdentifier reply_to){
		this.reply_to.add(reply_to);
	}
	public AgentIdentifier getSender() {
		return sender;
	}
	public void setReciever(ArrayList<AgentIdentifier> reciever) {
		this.receiver = reciever;
	}
	public ArrayList<AgentIdentifier> getReciever() {
		return receiver;
	}
	public void setReply_to(ArrayList<AgentIdentifier> reply_to) {
		if(reply_to!=null)
			this.reply_to = reply_to;
		else 
			this.reply_to=null;
		
	}

	public Iterator<AgentIdentifier> getAllReceiver(){
		return receiver.iterator();
	}
	public ArrayList<AgentIdentifier> getReply_to() {
		return reply_to;
	}
	public Iterator<AgentIdentifier> getAllReplyTo(){
		return reply_to.iterator();
	}

	public void setContent(Content content) {
			this.content = content;
	}

	public Content getContent() {
		return content;
	}

	public void setLanguage(String language) {
		if(language!=null)
			this.language = language;
		else
			this.language=null;
	}
	
	public String getLanguage() {
		return language;
	}

	public void setEncoding(String encoding) {
		if(encoding!=null)
			this.encoding = encoding;
		else
			this.encoding=null;
	}

	public String getEncoding() {
		return encoding;
	}

	public void setOntology(String ontology) {
		if(ontology!=null)
			this.ontology = ontology;
		else
			this.ontology=null;
	}

	public String getOntology() {
		return ontology;
	}

	public void setProtocol(String protocol) {
		if(protocol!=null)
			this.protocol = protocol;
		else
			this.protocol=null;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setConversation_id(String conversation_id) {
		if(conversation_id != null)
			this.conversation_id = conversation_id;
		else
			this.conversation_id=null;
	}

	public String getConversation_id() {
		return conversation_id;
	}

	public void setReply_with(String reply_with) {
		if(reply_with!=null)
			this.reply_with = reply_with;
		else
			this.reply_with=reply_with;
	}

	public String getReply_with() {
		return reply_with;
	}

	public void setIn_reply_to(String in_reply_to) {
		if(in_reply_to!=null)
			this.in_reply_to = in_reply_to;
		else
			this.in_reply_to=in_reply_to;
	}

	public String getIn_reply_to() {
		return in_reply_to;
	}

	public void setReply_by(String reply_by) {
		if(reply_by!=null)
			this.reply_by = reply_by;
		else
			this.reply_by=null;
	}

	public String getReply_by() {
		return reply_by;
	}

	public void setPerformative(String performative) {
		if (performativeList.contains(performative))
			this.performative = performative;
		else
			this.performative = "UNKNOWN";
	}

	public String getPerformative() {
		return performative;
	}

	private void loadPerformativeList() {
		performativeList.add(CommunicativeActLibrary.ACCEPT_PROPOSAL);
		performativeList.add(CommunicativeActLibrary.AGREE);
		performativeList.add(CommunicativeActLibrary.CANCEL);
		performativeList.add(CommunicativeActLibrary.CFP);
		performativeList.add(CommunicativeActLibrary.CONFIRM);
		performativeList.add(CommunicativeActLibrary.DISCONFIRM);
		performativeList.add(CommunicativeActLibrary.FAILURE);
		performativeList.add(CommunicativeActLibrary.INFORM);
		performativeList.add(CommunicativeActLibrary.INFORM_IF);
		performativeList.add(CommunicativeActLibrary.INFORM_REF);
		performativeList.add(CommunicativeActLibrary.NOT_UNDERSTOOD);
		performativeList.add(CommunicativeActLibrary.PROPOSE);
		performativeList.add(CommunicativeActLibrary.QUERY_IF);
		performativeList.add(CommunicativeActLibrary.QUERY_REF);
		performativeList.add(CommunicativeActLibrary.REFUSE);
		performativeList.add(CommunicativeActLibrary.REJECT_PROPOSAL);
		performativeList.add(CommunicativeActLibrary.REQUEST);
		performativeList.add(CommunicativeActLibrary.REQUEST_WHEN);
		performativeList.add(CommunicativeActLibrary.REQUEST_WHENEVER);
		performativeList.add(CommunicativeActLibrary.SUBSCRIBE);
		performativeList.add(CommunicativeActLibrary.PROXY);
		performativeList.add(CommunicativeActLibrary.PROPAGATE);
	}
}
