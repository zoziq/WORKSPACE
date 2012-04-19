package agent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;


/**
 * This class implements the URLSequence structure as described in <a
 * href="http://www.fipa.org/specs/fipa00070/">FIPA ACL Message Representation
 * in String Specification</a>
 * 
 * @author Aegeant-S Group Aug 13, 2003
 */
public class URLSequence implements Serializable {

	/**
	 * The buffer area that is used while encode/decode process
	 */
	private StringBuffer buffer;

	/**
	 * List of _urls
	 */
	private ArrayList _urls;

	public URLSequence() {
		_urls = new ArrayList();
		buffer = new StringBuffer();
	}

	/**
	 * Appends the specified URL to the end of this list.
	 * 
	 * @param URL
	 */
	public void add(String URL) {
		_urls.add(URL);
	}

	/**
	 * Removes all of _urls from this class. The class will be empty after this
	 * call returns
	 * 
	 */
	public void clear() {
		_urls.clear();
	}

	/**
	 * Indicates whether some other object is "equal to" this one
	 */
	public boolean equals(Object o) {
		if (o instanceof URLSequence) {
			URLSequence url = (URLSequence) o;
			return url.getUrls().equals(this.getUrls());
		} else {
			return false;
		}
	}

	/**
	 * Returns the URL at the specified position in this list.
	 * 
	 * @param index
	 *            index of URL to return.
	 * @return URL in String format.
	 */
	public String get(int index) {
		return (String) (_urls.get(index));
	}

	/**
	 * Returns the encoded string of this class. This method must be invoked
	 * after this object is given to an IURLSequenceEncoder instance
	 * 
	 * @return encoded string of the URLSequence
	 * @see acl.encoding.IURLSequenceEncoder
	 */
	public String getEncodedStr() {
		return buffer.toString();
	}

	/**
	 * Returns the _urls of this class
	 * 
	 * @return the _urls of this class
	 */
	public ArrayList getUrls() {
		Collections.sort(_urls);
		return _urls;
	}

	/**
	 * Sets the _urls of this class
	 * 
	 * @return the _urls of this class
	 */
	public void setUrls(ArrayList u) {
		_urls = u;
	}

	/**
	 * Removes the url at the specified position in this class
	 * 
	 * @param i
	 *            index of the url to be removed
	 * @return the url that was removed from the list
	 */
	public Object remove(int i) {
		return _urls.remove(i);
	}

	/**
	 * Removes a single instance of the specified url from this class
	 * 
	 * @param o
	 *            url to be removed from this class, if present
	 * @return true if this class contained the specified url
	 */
	public boolean remove(Object o) {
		return _urls.remove(o);
	}

	/**
	 * Sets the encoded string of this class. This method is used to decode an
	 * encoded URLSequence. To decode this object using an IURLSequenceEncoder
	 * instance, this method must be invoked first.
	 * 
	 * @param encodedStr
	 *            encoded string of the URLSequence
	 */
	public void setEncodedStr(String encodedStr) {
		buffer = new StringBuffer(encodedStr);
	}

	/**
	 * Returns the number of URLs in this list.
	 * 
	 * @return the number of URLs in this list.
	 */
	public int size() {
		return _urls.size();
	}

	/**
	 * Returns a string representation of this class
	 */
	public String toString() {
		return getUrls().toString();
	}

}
