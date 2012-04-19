package fipa;

/**
 * This class implements the StringLiteral structure as described in 
 * <a href="http://www.fipa.org/specs/fipa00070/">FIPA ACL Message 
 * Representation in String Specification</a>
 * @author Aegeant-S Group
 * Aug 13, 2003
 */
public class StringLiteral extends AbstractStringElement {
	
    /**
	 * The buffer area that is used while encode/decode process
	 */
    private StringBuffer buffer = new StringBuffer();

	/**
	 * Default empty constructor.
	 *
	 */
	public StringLiteral() {
	}

	/**
	 * One parameter constructor.
	 * @param value value of StringLiteral.
	 */
	public StringLiteral(String value) {
		super(value);
	}
	
	/**
	 * Indicates whether some other object is "equal to" this one
	 */
	public boolean equals(Object o) {
		if (o instanceof StringLiteral) {
			StringLiteral sl = (StringLiteral) o;
			return sl.getValue().equals(this.getValue());
		} else {
			return false;
		}
	}

	/**
	 * Returns the encoded string of this class
	 * This method must be invoked after this object is given
	 * to an IStringLiteralEncoder instance
	 * @return encoded string of this class
	 * @see acl.encoding.IStringLiteralEncoder
	 */
	public String getEncodedStr() {
		return buffer.toString();
	}

	/**
	 * Returns the value of this class
	 * @return the value of this class
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Sets the encoded string of this class. This method
	 * is used to decode an encoded StringLiteral. To decode 
	 * this object using an IStringLiteralEncoder instance, this
	 * method must be invoked first.
	 * @param encodedStr encoded string of the StringLiteral
	 */
	public void setEncodedStr(String encodedStr) {
		buffer = new StringBuffer(encodedStr);
	}

	/**
	 * Sets the value of this class
	 * @param value the value to be set to
	 */
	public void setValue(String value) {
		this.value = value;
	}
	
}