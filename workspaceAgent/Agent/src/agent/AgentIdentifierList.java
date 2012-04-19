package agent;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * This class is a container for AgentIdentifier. It holds expressions in an
 * ArrayList structure. AgentIdentifierList is used for both
 * AgentIdentifierSequence and AgentIdentifierSet as described in <a
 * href="http://www.fipa.org/specs/fipa00070/">FIPA ACL Message Representation
 * in String Specification </a>
 * 
 * @author Aegeant-S Group Aug 13, 2003
 */
public class AgentIdentifierList implements Serializable {

	public final static int SEQUENCE = 0;

	public final static int SET = 1;

	/**
	 * The list that contains AgentIdentifiers
	 */
	private ArrayList _ais;

	/**
	 * The buffer area that is used while encode/decode process
	 */
	private StringBuffer buffer;

	/**
	 * Type of this list. It could be either SET or SEQUENCE
	 * 
	 * @see AgentIdentifierList#SET
	 * @see AgentIdentifierList#SEQUENCE
	 */
	private int type;

	/*
	 * (non-Javadoc)
	 * 
	 * @see acl.content.encoding.ontology.IOWLCodable#getClassOntology()
	 */

	public AgentIdentifierList() {
		setType(SEQUENCE);
		_ais = new ArrayList();
		buffer = new StringBuffer();
	}

	/**
	 * Constructs an AgentIdentifierList with a given type:
	 * AgentIdentifierSequence or AgentIdentfiferSet
	 * 
	 * @param type
	 *            a constant integer which can be AgentIdentifierList.SET or
	 *            AgentIdentifierList.SEQUENCE. Default is
	 *            AgentIdentifierList.SEQUENCE when an invalid integer is given.
	 */
	public AgentIdentifierList(int type) {
		setType(type);
		_ais = new ArrayList();
		buffer = new StringBuffer();
	}

	/**
	 * Constructs an AgentIdentifierList with a given type and a list of
	 * AgentIdentifier objects.
	 * 
	 * @param type
	 *            a constant integer which can be AgentIdentifierList.SET or
	 *            AgentIdentifierList.SEQUENCE.
	 * @param aiList
	 *            ArrayList containing AgentIdentifier objects.
	 */
	public AgentIdentifierList(int type, ArrayList aiList) {
		setType(type);
		_ais = aiList;
		buffer = new StringBuffer();
	}

	/**
	 * Appends the specified AgentIdentifier to the end of the list.
	 * 
	 * @param ai
	 *            AgentIdentifier to be appended to this list.
	 * @return true if the append is successful
	 */
	public boolean add(AgentIdentifier ai) {
		return _ais.add(ai);
	}

	/**
	 * Indicates whether some other object is "equal to" this one
	 */
	public boolean equals(Object o) {
		if (o instanceof AgentIdentifierList) {
			AgentIdentifierList aidList = (AgentIdentifierList) o;
			return (aidList.getTypeString().equals(getTypeString()) && (aidList
					.getAis().equals(getAis())));
		} else {
			return false;
		}
	}

	/**
	 * Returns the AgentIdentifier at the specified position in this list
	 * 
	 * @param i
	 *            index of the element ot return
	 * @return the AgentIdentifier at the specified position in this list
	 */
	public AgentIdentifier get(int i) {
		return (AgentIdentifier) _ais.get(i);
	}

	/**
	 * Returns the AgentIdentifier in an ArrayList
	 * 
	 * @return an ArrayList containing AgentIdentifiers
	 */
	public ArrayList getAis() {
		return _ais;
	}

	/**
	 * Sets the AgentIdentifier in an ArrayList
	 */
	public void setAis(ArrayList a) {
		_ais = a;
	}

	/**
	 * Returns the encoded form of this class in a String object. This method
	 * does not call the encode method by default
	 * 
	 * @return a string which contains the encoded form of this class
	 */
	public String getEncodedStr() {
		return buffer.toString();
	}

	/**
	 * Returns the type of AgentIdentifierList: set or sequence.
	 * 
	 * @return the type of AgentIdentifierList as integer.
	 */
	public int getType() {
		return type;
	}

	/**
	 * Returns the type of AgentIdentifierList: set or sequence.
	 * 
	 * @return the type of AgentIdentifierList as string.
	 */
	public String getTypeString() {
		if (type == SET) {
			return "set";
		} else {
			return "sequence";
		}
	}

	/**
	 * Removes the AgentIdentifier at the specified position in this list.
	 * 
	 * @param i
	 *            the index of the element to be removed.
	 * @return the instance of the element removed as Object.
	 */
	public AgentIdentifier remove(int i) {
		return (AgentIdentifier) _ais.remove(i);
	}

	/**
	 * Setter of AIDListBuffer
	 * 
	 * @param buffer
	 *            new AIDListBuffer
	 */
	public void setEncodedStr(String buffer) {
		this.buffer = new StringBuffer(buffer);
	}

	/**
	 * Setter for AgentIdentifierList type.
	 * 
	 * @param type
	 *            the type of AgentIdentifierList as an integer constant. The
	 *            type can either be AgentIdentifierList.SET or
	 *            AgentIdentifierList.SEQUENCE. The default is SEQUENCE when an
	 *            invalid integer is given.
	 */
	public void setType(int type) {
		this.type = type;
	}

	/**
	 * Returns the number of AgentIdentifiers in this list.
	 * 
	 * @return the number of AgentIdentifiers in this list.
	 */
	public int size() {
		return _ais.size();
	}

	/**
	 * @see Object#toString()
	 */
	public String toString() {
		return getTypeString() + " " + getAis().toString();
	}
}