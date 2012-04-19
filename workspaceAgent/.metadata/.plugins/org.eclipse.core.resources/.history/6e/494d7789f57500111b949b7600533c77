package fipa;

import java.util.ArrayList;

/**
 * This class is a container for other expression types. It holds expressions in
 * an ArrayList structure. An expression might contain one or more Number,
 * DateTime, StringLiteral, ByteLengthEncodedString, Word or Expressions.
 * Expression structure is defined in <a
 * href="http://www.fipa.org/specs/fipa00070/">FIPA ACL Message Representation
 * in String Specification</a>
 * 
 * @author Aegeant-S Group
 * 
 */
public class Expression implements IExpressionElement {
	/**
	 * The buffer area that is used while encode/decode process
	 */
	private StringBuffer buffer = new StringBuffer();

	/**
	 * Holds expressions that is in this Expression
	 */
	private ArrayList expressionElements;

	/**
	 * Constructor.
	 * 
	 */
	public Expression() {
		expressionElements = new ArrayList();
	}

	/**
	 * One parameter constructor. You may give expressions while creating this
	 * expression using this constructor, or, instead, you may use default
	 * constructor and add expressions using
	 * {@link #addExpression(IExpressionElement) addExpression()} or
	 * {@link #addExpressionList(ArrayList) addExpressionList()}
	 * 
	 * @param expressionElements
	 * @see #addExpression(IExpressionElement)
	 * @see #addExpressionList(java.util.ArrayList)
	 */
	public Expression(ArrayList expressionElements) {
		this.expressionElements = expressionElements;
	}

	/**
	 * One parameter constructor.
	 * 
	 * @param expression
	 *            value of Expression.
	 */
	public Expression(IExpressionElement expression) {
		expressionElements = new ArrayList();
		expressionElements.add(expression);
	}

	/**
	 * Adds an expression to this Expression.
	 * 
	 * @param e
	 *            the expression to be added.
	 * @see #addExpressionList(java.util.ArrayList)
	 */
	public void addExpression(IExpressionElement e) {
		expressionElements.add(e);
	}

	/**
	 * Adds a list of expressions to this Expression.
	 * 
	 * @param expElementList
	 *            the list to be added.
	 * @see #addExpression(IExpressionElement)
	 */
	public void addExpressionList(ArrayList expElementList) {
		for (int i = 0; i < expElementList.size(); i++)
			addExpression((IExpressionElement) expElementList.get(i));
	}

	/**
	 * Clears this expression
	 * 
	 */
	public void clear() {
		expressionElements.clear();
	}

	/**
	 * Returns the number of expressions in this expression
	 * 
	 * @return the number of expressions in this expression
	 */
	public int elementCount() {
		return expressionElements.size();
	}

	/**
	 * Indicates whether some other object is "equal to" this one
	 */
	public boolean equals(Object o) {
		if (o instanceof Expression) {
			Expression exp = (Expression) o;
			return exp.getExpressionElements().equals(getExpressionElements());
		} else {
			return false;
		}
	}

	/**
	 * Returns the ExpressionElement at the specified position in this
	 * Expression.
	 * 
	 * @param index
	 *            index of ExpressionElement to return.
	 * @return the ExpressionElement at the specified position in this
	 *         Expression.
	 */
	public IExpressionElement getElement(int index) {
		return (IExpressionElement) expressionElements.get(index);
	}

	/**
	 * Returns the encoded string of this class. This method must be invoked
	 * after this object is given to an IExpressionEncoder instance
	 * 
	 * @return encoded string of the Expression
	 * @see acl.encoding.IExpressionEncoder
	 */
	public String getEncodedStr() {
		return buffer.toString();
	}

	/**
	 * Returns the expressions inside this expression
	 * 
	 * @return the expressions inside this expression
	 */
	public ArrayList getExpressionElements() {
		return expressionElements;
	}

	/**
	 * Sets the encoded string of this class. This method is used to decode an
	 * encoded Expression. To decode this object using an IExpressionEncoder
	 * instance, this method must be invoked first.
	 * 
	 * @param encodedStr
	 *            encoded string of the Expression
	 */
	public void setEncodedStr(String encodedStr) {
		buffer = new StringBuffer(encodedStr);
	}

	/**
	 * Returns the number of expressions in this Expression.
	 * 
	 * @return the number of expressions in this Expression.
	 */
	public int size() {
		return expressionElements.size();
	}

	/**
	 * Returns a string representation of this class
	 */
	public String toString() {
		return getExpressionElements().toString();
	}

}