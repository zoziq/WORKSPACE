package agent;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;

import com.hp.hpl.jena.ontology.DatatypeProperty;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntDocumentManager;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.vocabulary.XSD;

public class OdaOntoloji {
	OntClass onto;
	OntClass servis;
	Individual ad;
	OntModel m;
	String NS = "http://www.semanticweb.org/ontologies/2011/3/Oda.owl";
	String xmlbase = NS + "#";
	static int counter;
	InputStream in;

	public OdaOntoloji() {

		m = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);
		OntDocumentManager dm = m.getDocumentManager();
		dm.addAltEntry(NS, "file:\\C:\\Plan\\Oda.owl");
		m.read(NS);
		m.prepare();
		m.setNsPrefix("Oda",
				"http://www.semanticweb.org/ontologies/2011/3/Oda.owl#");
	}

	public ArrayList getPlan(String tip) {
		ArrayList ar = new ArrayList();
		String plan = null;
		StringBuilder result = null;

		String sorgu = "PREFIX rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
				+ "PREFIX rdfs:<http://www.w3.org/2000/01/rdf-schema#> "
				+ "PREFIX Oda:<http://www.semanticweb.org/ontologies/2011/3/Oda.owl#> "
				+ "SELECT ?odaNo ?ucret"
				+ " WHERE {"
				+ "?x Oda:tip '"
				+ tip
				+ "'.?x Oda:Durum 'Boþ'. ?x Oda:odaNo ?odaNo. ?x Oda:ucret ?ucret}";

		Query query = QueryFactory.create(sorgu);

		// Execute the query and obtain results
		QueryExecution qe = QueryExecutionFactory.create(query, m);
		ResultSet results = qe.execSelect();
		while (results.hasNext()) {
			QuerySolution soln = results.nextSolution();
			RDFNode address = soln.get("odaNo");
			RDFNode address1 = soln.get("ucret");
			result = new StringBuilder();
			result.append(address.toString());
			System.out.println("result " + address.toString() + " "
					+ address1.toString());
			ar.add(address.toString() + " " + address1.toString());
		}
		// Output query results
		// ResultSetFormatter.out(System.out, results, query);

		qe.close();
		return ar;
	}

	public void addPlan(String Tip, String odaNo, String Durum, String Ucret) {
		++counter;
		onto = m.getOntClass(xmlbase + "Oda");
		ad = m.createIndividual("Oda" + counter, onto);

		DatatypeProperty tip = m.getDatatypeProperty(xmlbase + "tip");
		tip.addDomain(onto);
		tip.addRange(XSD.xstring);

		DatatypeProperty no = m.getDatatypeProperty(xmlbase + "odaNo");

		DatatypeProperty ucret = m.getDatatypeProperty(xmlbase + "ucret");

		no.addDomain(onto);
		no.addRange(XSD.integer);

		DatatypeProperty durum = m.getDatatypeProperty(xmlbase + "Durum");
		durum.addDomain(onto);
		durum.addRange(XSD.xstring);

		ad.addProperty(tip, Tip);
		ad.addProperty(no, odaNo);
		ad.addProperty(durum, Durum);
		ad.addProperty(ucret, Ucret);
		ad.addOntClass(onto);

		FileOutputStream camera_File;
		try {
			camera_File = new FileOutputStream("C:\\Plan\\Oda.owl");
			m.write(camera_File, "RDF/XML-ABBREV", xmlbase);
		
			// write it to standard out
			// m.write(System.out);
		}

		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
