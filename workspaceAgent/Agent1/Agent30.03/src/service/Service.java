package service;

import java.util.ArrayList;

public class Service{
	
	private String name;
	private String id;
	private String textDesciption;
	private String address;
	private String matchAgentUri;
	private ArrayList<CompositProcess> compositProcesses = new ArrayList<CompositProcess>();

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTextDesciption() {
		return textDesciption;
	}
	public void setTextDesciption(String textDesciption) {
		this.textDesciption = textDesciption;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMatchAgentUri() {
		return matchAgentUri;
	}
	public void setMatchAgentUri(String matchAgentUri) {
		this.matchAgentUri = matchAgentUri;
	}
	public ArrayList<CompositProcess> getCompositProcesses() {
		return compositProcesses;
	}
	public void setCompositProcesses(ArrayList<CompositProcess> compositProcesses) {
		this.compositProcesses = compositProcesses;
	}
	
	public void printService() {
		System.out.println("Name: " + name);
		System.out.println("ID: " + id);
		System.out.println("Address: " + address);
		System.out.println("Description: " + textDesciption);
	}

}
