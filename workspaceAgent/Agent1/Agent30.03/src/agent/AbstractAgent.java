package agent;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.StringTokenizer;

import fipa.FipaMessage;

/**
 * @author Aegeant-S Group Sep 3, 2003
 */
public abstract class AbstractAgent {

	/* public constructors */

	/**
	 * Constructor.
	 * 
	 * @param aid
	 * @param accAddress
	 */
	public AbstractAgent(AgentIdentifier aid, String accAddress) {
		this.AID = aid;
		this.accAddress = accAddress;
		this.agentName = parseAgentName();
		this.platformName = parsePlatformName();

		conversationID = 0;
		startServer();
		//
		registerToAMS();
	}

	/* public accessors */

	/**
	 * Gets the current conversation id.
	 * 
	 * @return
	 */
	public long getConversationId() {
		return conversationID;
	}

	/**
	 * Returns the full name, addresses and resolvers of this agent.
	 * 
	 * @return
	 */
	public AgentIdentifier getAID() {
		return AID;
	}

	/**
	 * Getter of name.
	 * 
	 * @return
	 */
	public String getAgentName() {
		return agentName;
	}

	/**
	 * Getter of full name.
	 * 
	 * @return agentName@platformName
	 */
	public String getFullAgentName() {
		return AID.getName().toString();
	}

	/**
	 * Getter of platform name.
	 * 
	 * @return
	 */
	public String getPlatformName() {
		return platformName;
	}

	/**
	 * Getter of address of ACC.
	 * 
	 * @return
	 */
	public String getACCAddress() {
		return accAddress;
	}

	/**
	 * Returns the next conversation id.
	 * 
	 * @return
	 */
	public long getNextConversationID() {
		return ++conversationID;
	}

	/* public mutators */

	/**
	 * Sets the conversation id.
	 * 
	 * @param id
	 */
	public void setConversationId(long id) {
		conversationID = id;
	}

	/* public auxiliary methods */

	/**
	 * Logs a message. For now it only prompts the messages, future purpose is
	 * to save these messages in a file.
	 * 
	 * @param msg
	 *            the message to be logged.
	 */
	public void logMessage(String msg) {
		Date today;
		String dateOut;
		DateFormat formatter;
		formatter = DateFormat.getDateTimeInstance(DateFormat.LONG,
				DateFormat.LONG, Locale.getDefault());

		today = new Date();
		dateOut = formatter.format(today);

		StringBuffer logMsg = new StringBuffer();
		logMsg.append(dateOut);
		logMsg.append(" [" + agentName + "]");
		logMsg.append(" : ");
		logMsg.append(msg);
		System.out.println(logMsg.toString());
	}

	/**
	 * Receives the incoming message and processes it.
	 * 
	 * @param msg
	 *            incoming message.
	 */
	public abstract void message(FipaMessage msg);

	/* protected auxiliary methods */

	/**
	 * Registers this agent to the AMS of the platform it belongs.
	 */
	protected abstract void registerToAMS();

	/**
	 * Deregisters this agent to the AMS of the platform it belongs.
	 */
	protected abstract void deregisterFromAMS();

	/**
	 * Starts the servers that is used to communicate.
	 */
	protected abstract void startServer();

	/* private auxiliary methods */

	/**
	 * Parses the name of the platform from full agent name.
	 * 
	 * @return
	 */
	private String parsePlatformName() {
		String name = AID.getName().getValue();
		StringTokenizer st = new StringTokenizer(name);
		st.nextToken("@");
		return st.nextToken();
	}

	/**
	 * Parses the name of this agent from full agent name.
	 * 
	 * @return
	 */
	private String parseAgentName() {
		String name = AID.getName().getValue();
		StringTokenizer st = new StringTokenizer(name);
		return st.nextToken("@");
	}

	/**
	 * Sends a message.
	 * 
	 * @param msg
	 *            message to be sent.
	 */
	protected abstract void sendMessage(FipaMessage msg);

	/**
	 * Finds the hostname of the computer that this agent works on.
	 * 
	 * @return
	 */
	protected String findHostName() {
		try {
			InetAddress myself = InetAddress.getLocalHost();
			return myself.getHostName();
		} catch (UnknownHostException e) {
			return null;
		}
	}

	/* private fields */

	// name of this agent.
	private String agentName;
	// name of the platform.
	private String platformName;
	// identifier of this agent. it contains full name(agent@platform),
	// addresses and resolvers.
	private AgentIdentifier AID;
	// the address of the ACC that this agent uses while communicating.
	private String accAddress;
	// unique identifier of the each conversation that this agent does.1
	private long conversationID;

}
