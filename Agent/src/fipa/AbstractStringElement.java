package fipa;

/**
 * This abstract class shows the String structure as described in <a
 * href="http://www.fipa.org/specs/fipa00070/">FIPA ACL Message Representation
 * in String Specification</a>
 * 
 * @author aegeantS Group Aug 13, 2003
 */
public abstract class AbstractStringElement implements IExpressionElement {

	/**
	 * The value of the object
	 */
	protected String value;

	/**
	 * Default constructor
	 */
	public AbstractStringElement() {
	}

	/**
	 * Default constructor with a string parameter
	 * 
	 * @param value
	 */
	public AbstractStringElement(String value) {
		this.value = value;
	}

	/**
	 * Returns the value of the object
	 * 
	 * @return the value of the object
	 */
	public abstract String getValue();

	/**
	 * Sets the value of the object
	 * 
	 * @param value
	 *            the value of the object
	 */
	public abstract void setValue(String value);

	/**
	 * Returns a string representation of this class
	 */
	public String toString() {
		return getValue();
	}
}