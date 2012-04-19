package agent;

import java.io.Serializable;

/**
 * This class implements the Word structure as described in <a
 * href="http://www.fipa.org/specs/fipa00070/">FIPA ACL Message Representation
 * in String Specification</a>
 * 
 * @author Aegeant-S Group Aug 13, 2003
 */
public class Word implements Serializable {

	/**
	 * The pattern for this class
	 */
	public final static String PATTERN = "[^\\x00-\\x20\\(\\)#0-9\\-@][^\\x00-\\x20\\(\\)]*";
	/**
	 * The buffer area that is used while encode/decode process
	 */
	private StringBuffer buffer = new StringBuffer();

	/**
	 * The value of this class
	 */
	private String value;

	/**
	 * Default empty constructor.
	 * 
	 */
	public Word() {
	}

	/**
	 * One parameter constructor
	 * 
	 * @param value
	 *            the value to be set to
	 */
	public Word(String value) {
		this.value = value;
	}

	/**
	 * Indicates whether some other object is "equal to" this one
	 */
	public boolean equals(Object o) {
		if (o instanceof Word) {
			Word we = (Word) o;
			return we.getValue().equals(this.getValue());
		} else {
			return false;
		}
	}

	/**
	 * Returns the encoded string of this class. This method must be invoked
	 * after this object is given to an IWordEncoder instance
	 * 
	 * @return encoded string of the Word
	 * @see acl.encoding.IWordEncoder
	 */
	public String getEncodedStr() {
		return buffer.toString();
	}

	/**
	 * Returns the value of this class
	 * 
	 * @return the value of this class
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Sets the encoded string of this class. This method is used to decode an
	 * encoded Word. To decode this object using an IWordEncoder instance, this
	 * method must be invoked first.
	 * 
	 * @param encodedStr
	 *            encoded string of the Word
	 */
	public void setEncodedStr(String encodedStr) {
		buffer = new StringBuffer(encodedStr);
	}

	/**
	 * Sets the value of this class
	 * 
	 * @param value
	 *            the value to be set to
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * Returns a string representation of this class
	 */
	public String toString() {
		return getValue();
	}
}