package fipa;

import java.io.Serializable;
import java.util.ArrayList;

// Fipa mesajimizin payloadundaki content bolumunu bu sinifta implement ettik...

public class Content implements Serializable {

	// Iceerisinde act, actor , arguman tutan Action nesnelerinin listesi
	ArrayList<FIPARDFContent0> actions = new ArrayList<FIPARDFContent0>();

	private String predicate;

	public Content(String predicate) {
		this.predicate = predicate;
	}

	public Content() {
	}

	public String getText() {
		return predicate;
	}

	public void setText(String text) {
		this.predicate = text;
	}

	// Listeye Action ekleme metodu
	public void addList(FIPARDFContent0 ac) {
		actions.add(ac);
	}

	public ArrayList<FIPARDFContent0> getActions() {
		return actions;
	}

	public void setActions(ArrayList<FIPARDFContent0> actions) {
		this.actions = actions;
	}

}
