package ams;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Iterator;

import agent.AID;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.ProfileRegistry;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.util.FileManager;

public class test {
	String NS = "http://localhost/owl/ontologies/agent/";
	String xmlbase = NS + "#";
	OntModel m;
	InputStream in;
	OntClass onto;
	OntClass onto1;
	Individual ad1;
	int indNumber;
	private static int counter = 0;

	public test() {
		this.indNumber = ++counter;
	}

	public static int getCounter() {
		return counter;
	}

	public int getMsgNumber() {
		return indNumber;
	}

	FileOutputStream camera_File;

	public static void main(String[] args) {
		AID aid = new AID();
		aid.setName("agent01");
		aid.addAddress("1234560");
		test t = new test();
		t.mesaj(aid, "emrah");
		// t.search();
		Ontology o = new Ontology();
		// o.createOntology();
	}

	public void mesaj(AID aid, String owner) {
		String agd = "ad" + Integer.toString(indNumber);
		m = ModelFactory.createOntologyModel(ProfileRegistry.OWL_LANG);
		in = FileManager.get().open("agentschema.owl");
		// read the RDF/XML file
		m.read(in, xmlbase);
		onto = m.getOntClass("agentdescription");
		Individual ad = m.createIndividual(NS + "agd", onto);
		ad.addProperty(m.getDatatypeProperty(xmlbase + "id"), Integer
				.toString(indNumber));
		ad.addProperty(m.getDatatypeProperty(xmlbase + "name"), aid.getName());
		ad.addProperty(m.getDatatypeProperty(xmlbase + "ownership"), owner);
		ad.addProperty(m.getDatatypeProperty(xmlbase + "state"), "active");

		OntClass onto1 = m.getOntClass("agentidentifier");
		ad1 = m.createIndividual(NS + agd + "i", onto1);
		ad1.addProperty(m.getDatatypeProperty(xmlbase + "id"), Integer
				.toString(indNumber));
		ad1.addProperty(m.getDatatypeProperty(xmlbase + "name"), aid.getName());
		ad1.addProperty(m.getDatatypeProperty(xmlbase + "address"), aid
				.getAddress().get(0).toString());
		indNumber++;
		agd = "ad" + Integer.toString(indNumber);

		// write it to standard out
		m.write(System.out);

		try {
			camera_File = new FileOutputStream("agentdata.owl");
			m.write(camera_File, "RDF/XML-ABBREV", xmlbase);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	public void search() {
		for (Iterator bs = onto1.listInstances(); bs.hasNext();) {
			System.out.println("instance " + bs.next().toString());
		}
	}
}
