package tiryaki.planner;

import java.util.ArrayList;
import java.util.LinkedList;

import links.Link;

import task.Action;
import task.Task;

public class ReadyQCell {

	private Action ready;
	private String conversationId;
	private ArrayList links;

	public ReadyQCell(Action ready, String conversationId, ArrayList<Task> tasks) {
		super();
		this.ready = ready;
		this.conversationId = conversationId;
		this.links = tasks;
	}

	public ReadyQCell(Action ready, String conversationId) {
		super();
		this.ready = ready;
		this.conversationId = conversationId;
	}

	public ArrayList getLink() {
		return links;
	}

	public void addLink(Link link) {
		links.add(link);
	}

	public void setLink(ArrayList links) {
		this.links = links;
	}

	public Action getReady() {
		return ready;
	}

	public String getConversationId() {
		return conversationId;
	}

	public void setReady(Action ready) {
		this.ready = ready;
	}

	public void setConversationId(String conversationId) {
		this.conversationId = conversationId;
	}

}
