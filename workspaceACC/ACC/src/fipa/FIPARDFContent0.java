package fipa;

import java.io.Serializable;
import java.util.ArrayList;

import agent.AID;
import agent.AgentDescription;

// Mesaj�n Contenti i�erisinde bulunan Action s�n�f�d�r...
public class FIPARDFContent0 implements Serializable {

	private String act;
	private String actor;
	ArrayList arguments;

	public FIPARDFContent0(String act, String actor, ArrayList arguments) {
		super();
		this.act = act;
		this.actor = actor;
		this.arguments = arguments;
	}
	
	
	public FIPARDFContent0() {

	}
	public String getAct() {
		return act;
	}

	public void setAct(String act) {
		this.act = act;
	}

	public String getActor() {
		return actor;
	}

	public void setActor(String agentD) {
		this.actor = agentD;
	}

	public ArrayList getArguments() {
		return arguments;
	}

	public void setArguments(ArrayList arguments) {
		this.arguments = arguments;
	}



}
