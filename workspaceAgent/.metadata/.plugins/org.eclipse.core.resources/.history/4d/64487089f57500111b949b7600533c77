package agent;

import java.io.Serializable;

/**
 * This class implements the AgentIdentifier structure as described in <a
 * href="http://www.fipa.org/specs/fipa00070/">FIPA ACL Message Representation
 * in String Specification</a>
 * 
 * @author Aegeant-S Group Aug 13, 2003
 */
public class AgentIdentifier implements Serializable {
	/**
	 * Addresses of the agent
	 */
	private URLSequence _addresses;
	/**
	 * The buffer area that is used while encode/decode process
	 */
	private StringBuffer buffer;
	/**
	 * Name of the agent
	 */
	private Word _name;
	/**
	 * Resolvers of the agent
	 */
	private AgentIdentifierList _resolvers;

	/**
	 * User defined slots of the AgentIdentifier
	 */

	/**
	 * Default Constructor
	 */
	public AgentIdentifier() {
		_name = new Word();
		_addresses = new URLSequence();
		_resolvers = new AgentIdentifierList(AgentIdentifierList.SEQUENCE);
		buffer = new StringBuffer();
	}

	 /**
	 * Indicates whether some other object is "equal to" this one
	 */
	public boolean equals(Object o) {
		if (o instanceof AgentIdentifier) {
			AgentIdentifier aid = (AgentIdentifier) o;
			return ((aid.getName().equals(getName()))
					&& (aid.getAddresses().equals(getAddresses())) && (aid
					.getResolvers().equals(getResolvers())));
		} else {
			return false;
		}
	}

	/**
	 * Returns the _addresses of the agent
	 * 
	 * @return the _addresses of the agent
	 */
	public URLSequence getAddresses() {
		return _addresses;
	}

	/**
	 * Returns the encoded string of the AgentIdentifier. This method must be
	 * invoked after this object is given to an IAgentIdentifierEncoder instance
	 * 
	 * @return encoded string of the AgentIdentifier
	 * @see acl.encoding.IAgentIdentifierEncoder
	 */
	public String getEncodedStr() {
		return buffer.toString();
	}

	/**
	 * Returns the _name of the agent
	 * 
	 * @return the _name of the agent
	 */
	public Word getName() {
		return _name;
	}

	/**
	 * Returns the _resolvers of the agent
	 * 
	 * @return the _resolvers of the agent
	 */
	public AgentIdentifierList getResolvers() {
		return _resolvers;
	}

	/**
	 * Sets the _addresses of the agent
	 * 
	 * @param _addresses
	 */
	public void setAddresses(URLSequence addresses) {
		this._addresses = addresses;
	}

	/**
	 * Sets the encoded string of the AgentIdentifier. This method is used to
	 * decode an encoded AgentIdentifier. To decode this object using an
	 * IAgentIdentifierEncoder instance, this method must be invoked first.
	 * 
	 * @param encodedStr
	 *            encoded string of the AgentIdentifier
	 */
	public void setEncodedStr(String encodedStr) {
		buffer = new StringBuffer(encodedStr);
	}

	/**
	 * Sets the _name of the agent
	 * 
	 * @param _name
	 *            the _name of the agent
	 */
	public void setName(Word name) {
		this._name = name;
	}

	/**
	 * Sets the _resolvers of the agent
	 * 
	 * @param _resolvers
	 *            the _resolvers of the agent
	 */
	public void setResolvers(AgentIdentifierList resolvers) {
		this._resolvers = resolvers;
	}

	/**
	 * Returns a string representation of this class
	 */
	public String toString() {
		return getName().toString() + " " + getAddresses().toString() + " "
				+ getResolvers().toString();

	}

}