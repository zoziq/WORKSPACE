package task;

import java.util.ArrayList;

public class Outcome {

	private String name;
	private ArrayList<Output> outputList = new ArrayList<Output>();

	public Outcome() {
		this.name = null;
	}

	public Outcome(String name) {
		this.name = name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void addOutput(Output out) {
		outputList.add(out);
	}

	public ArrayList<Output> getOutputList() {
		return outputList;
	}

	public void setOutputList(ArrayList<Output> outputList) {
		this.outputList = outputList;
	}

}