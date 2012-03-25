package fipa;

import java.io.Serializable;

/**
 * This class represents the stamp representing receipt of a message by an ACC.
 * 
 * A new received parameter is added to the envelope by each ACC that the
 * message passes through. Each ACC handling a message must add a completed
 * received parameter. If an ACC receives a message it has already stamped, it
 * is free to discard the message without any need to generate an error message.
 * 
 * @author AegeantS-Group
 * @see FIPA Agent Message Transport Service Specification
 */
public class ReceivedObject implements Serializable {
	/**
	 * the unique identifier of a message.
	 */
	private static int id = 0;

	/**
	 * the url representing the transport address of the receiving ACC.
	 */
	private String by;
	/**
	 * the date when a message was received.
	 */
	private DateTime date;
	/**
	 * the url representing the transport address of the sending ACC.
	 */
	private String from;
	/**
	 * the type of mtp the message was delivered over.
	 */
	private String via;

	/**
	 * Constructor
	 */
	public ReceivedObject() {
		// 
		id++;
	}

	public String getBy() {
		return by;
	}

	public DateTime getDate() {
		return date;
	}

	public String getFrom() {
		return from;
	}

	public int getId() {
		return id;
	}

	public String getVia() {
		return via;
	}

	public void setBy(String by) {
		this.by = by;
	}

	public void setDate(DateTime date) {
		this.date = date;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public void setVia(String via) {
		this.via = via;
	}

}
