/*
 * Created on Jul 16, 2004
 *
 */
package fipa;

/**
 * This class represents the ACL communicative acts as described in
 * <a href="http://www.fipa.org/specs/fipa00037/">FIPA Communicative Act Library Specification</a> 
 * @author Aegeant-S Group
 *
 */
public class CommunicativeActLibrary {
    
    /**
     * The action of accepting a previously submitted proposal to perform an action
     */
	public static final String ACCEPT_PROPOSAL = "accept_proposal";
	/**
	 * The action of agreeing to perform some action, possibly in the future
	 */
	public static final String AGREE = "agree";
	/**
	 * The action of one agent informing another agent that the first agent
	 * no longer has the intention that the second agent perform some action
	 */
	public static final String CANCEL = "cancel";
	/**
	 * The action of calling for proposals to perform a given action
	 */
	public static final String CFP = "cfp";
	/**
	 * The sender informs the receiver that a given proposition is true, where
	 * the receiver is known to be uncertain about the proposition
	 */
	public static final String CONFIRM = "confirm";
	/**
	 * The sender informs the receiver that a given proposition is false, where
	 * the receiver is known to believe, believe it likely that, the proposition is true
	 */
	public static final String DISCONFIRM = "disconfirm";
	/**
	 * The action of telling another agent that an action was attempted but
	 * the attempt failed
	 */
	public static final String FAILURE = "failure";
	/**
	 * The sender informs the receiver that a given proposition is true
	 */
	public static final String INFORM = "inform";
	/**
	 * A macro action for the agent of the action to inform the recipient whether or not
	 * a proposition is true
	 */
	public static final String INFORM_IF = "inform-if";
	/**
	 * A macro action for sender to inform the receiver the object which corresponds 
	 * to a descriptor, for example, a name
	 */
	public static final String INFORM_REF = "inform-ref";
	/**
	 * The sender of the act (for example, <I>i</I>) informs the receiver (for example, <I>j</I>) that
	 * it perceived that <I>j</I> performed some action, but that <I>i</I> did not understand what <I>j</I> just did. 
	 * A particular common case is that <I>i</I> tells <I>j</I> that <I>i</I> did not understand the message 
	 * that <I>j</I> has just sent to <I>i</I>.
	 */
	public static final String NOT_UNDERSTOOD = "not-understood";
	/**
	 * The sender intends that the receiver treat the embedded message as sent directly to the
	 * receiver, and wants the receiver to identify the agents denoted by the given descriptor 
	 * and send the received propagate message to them
	 */
	public static final String PROPAGATE = "propagate";
	/**
	 * The action of submitting a proposal to perform a certain action, given certain preconditions 
	 */
	public static final String PROPOSE = "propose";
	/**
	 * The sender wants the receiver to select target agents denoted by a given description
	 * and to send an embedded message to them
	 */
	public static final String PROXY = "proxy";
	/**
	 * The action of asking another agent whether or not a given proposition is true
	 */
	public static final String QUERY_IF = "query-if";
	/**
	 * The action of asking another agent for the object referred to by a referential expression
	 */
	public static final String QUERY_REF = "query-ref";
	/**
	 * The action of refusing to perform a given action, and explaining the reason for the refusal
	 */
	public static final String REFUSE = "refuse";
	/**
	 * The action of rejecting a proposal to perform some action during a negotiation
	 */
	public static final String REJECT_PROPOSAL = "reject-proposal";
	/**
	 * The sender requests the receiver to perform some action. One important class of uses 
	 * of the request act is to request the receiver to perform another communicative act
	 */
	public static final String REQUEST = "request";
	/**
	 * The sender wants the receiver to perform some action when some given proposition becomes true
	 */
	public static final String REQUEST_WHEN = "request-when";
	/**
	 * The sender wants the receiver to perform some action as soon as some proposition becomes true
	 * and thereafter each time the proposition becomes true again
	 */
	public static final String REQUEST_WHENEVER = "request-whenever";
	/**
	 * The act of requesting a persistent intention to notify the sender of the value of a reference,
	 * and to notify again whenever the object identified by the reference changes
	 */
	public static final String SUBSCRIBE = "subscribe";

}
