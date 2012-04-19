package fipa;

import java.io.Serializable;
import java.util.ArrayList;

// Fipa mesaj�m�z�n payload �ndaki content b�l�m�n� bu s�n�fta implement ettik...

public class Content implements Serializable {
	
	//��erisinde act, actor , arg�man tutan Action nesnelerinin listesi
	ArrayList<FIPARDFContent0> actions=new ArrayList<FIPARDFContent0>();
	
	private String predicate;
	
	public Content(String predicate){
		this.predicate=predicate;
	}
	
	public Content(){}
	
	public String getText() {
		return predicate;
	}

	public void setText(String text) {
		this.predicate = text;
	}

	//Listeye Action ekleme metodu
	public void addList(FIPARDFContent0 ac){
		actions.add(ac);
	}
 
	public ArrayList<FIPARDFContent0> getActions() {
		return actions;
	}

	public void setActions(ArrayList<FIPARDFContent0> actions) {
		this.actions = actions;
	}

	
	

	
}
