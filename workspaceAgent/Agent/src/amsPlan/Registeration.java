package amsPlan;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Statement;
import java.util.StringTokenizer;

import task.Action;
import task.Outcome;
import task.Provision;

import agent.AID;
import agent.AgentDescription;
import agent.AgentState;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.util.FileManager;

import fipa.Content;
import fipa.FipaMessage;

public class Registeration extends Action {

	Provision Agd;
	Provision Aid;
	Outcome Ok;
	Outcome Fail;

	private AgentDescription agentdes;
	Statement stmt;
	private AgentState state;
	private AID aid;
	// String NS = "http://www.semanticweb.org/ontologies/2010/11/Agent.owl";
	String NS = "http://localhost/owl/ontologies/agent.owl";
	String xmlbase = NS + "#";
	OntModel m;
	InputStream in;
	OntClass onto;
	OntClass onto1;
	Individual ad;
	Individual ad1;
	Content con;

	public Registeration() {

		this.setId("2");
		this.setName("Registeration");

		Agd = new Provision();
		Agd.setprovName("AGD");
		Aid = new Provision();
		Aid.setprovName("AID");
		Ok = new Outcome();
		Ok.setName("Ok");
		Fail = new Outcome();
		Fail.setName("Fail");

		addOutcome(Ok);
		addOutcome(Fail);
		addProvision(Aid);
		addProvision(Agd);

		m = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);
		in = FileManager.get().open("C:/ontologies/AgentInd.owl");
		// read the RDF/XML file
		m.read(in, xmlbase);

	}

	@Override
	public void doAction() {

		String namespace = null;
		String name = null;
		String ownership = null;
		String state = null;
		String id = null;
		String address = null;
		String namespace1 = null;
		StringTokenizer st = new StringTokenizer(Agd.getValue().toString());
		StringTokenizer st1 = new StringTokenizer(Aid.getValue().toString());
		// ad=m.createIndividual(fm.getAcl().getContent().getActions().get(0).getArguments().get(0));
		// ad1=m.createIndividual(fm.getAcl().getContent().getActions().get(0).getArguments().get(1));
		onto = m.getOntClass("AgentDescription");
		onto1 = m.getOntClass("AgentIdentifier");

		while (st.hasMoreElements()) {
			namespace = st.nextElement().toString();
			id = st.nextElement().toString();
			name = st.nextElement().toString();
			ownership = st.nextElement().toString();
			state = st.nextElement().toString();
			break;
		}

		while (st1.hasMoreElements()) {
			namespace1 = st1.nextElement().toString();
			id = st1.nextElement().toString();
			name = st1.nextElement().toString();
			address = st1.nextElement().toString();
			break;
		}

		ad = m.createIndividual(namespace, onto);
		ad.addProperty(m.getDatatypeProperty(xmlbase + "id"), id);
		ad.addProperty(m.getDatatypeProperty(xmlbase + "name"), name);
		ad.addProperty(m.getDatatypeProperty(xmlbase + "ownership"), ownership);
		ad.addProperty(m.getDatatypeProperty(xmlbase + "state"), state);

		ad1 = m.createIndividual(namespace1, onto1);
		ad1.addProperty(m.getDatatypeProperty(xmlbase + "id"), id);
		ad1.addProperty(m.getDatatypeProperty(xmlbase + "name"), name);
		ad1.addProperty(m.getDatatypeProperty(xmlbase + "address"), address);

		FileOutputStream output_File;
		try {
			output_File = new FileOutputStream(
					"C:/ontologies/AgentInd/agentind2.owl");

			m.write(output_File, "RDF/XML-ABBREV", xmlbase);
			m.write(System.out);

			// String adres=Search("barca");

			// write it to standard out
			// m.write(System.out);

		}

		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Hata: " + e.toString());
		}

		return;
	}

	@Override
	public void sendMessage(FipaMessage fm) {
	
	}

}
