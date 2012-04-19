package service;

import java.util.ArrayList;

public class CompositProcess {
	
	private ArrayList<CompositProcess> compositProcesses = new ArrayList<CompositProcess>();
	
	public void addProcess(CompositProcess c){
		compositProcesses.add(c);
	}
	
	public int getProcessCount(){
		return compositProcesses.size();
	}
}
