package agent;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntDocumentManager;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.ontology.ProfileRegistry;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.util.FileManager;

import fipa.ACLMessage;
import fipa.FipaMessage;

public class MatcherOntology {
	OntClass onto;
	Individual ad;
	OntModel m;
	String NS = "http://www.semanticweb.org/ontologies/2011/3/Plan.owl";
	String xmlbase = NS + "#";
	int counter;
	InputStream in;

	public MatcherOntology() {

		m = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);
		OntDocumentManager dm = m.getDocumentManager();
		dm.addAltEntry(NS, "file:\\C:\\Plan\\Plan.owl");
		m.read(NS);
		m.prepare();
		m.setNsPrefix("Plan", "http://www.semanticweb.org/ontologies/2011/3/Plan.owl#");
	}

	public ArrayList getPlan(FipaMessage fm) {
		ArrayList ar = new ArrayList();
		String plan = null;
		String language = fm.getAcl().getLanguage();
		StringBuilder result = null;
		String act = fm.getAcl().getContent().getActions().get(0).getAct();
		
		
		System.out.println("Language "+language+" act "+act);
		String sorgu = "PREFIX rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
				+ "PREFIX rdfs:<http://www.w3.org/2000/01/rdf-schema#> "
				+ "PREFIX Plan:<http://www.semanticweb.org/ontologies/2011/3/Plan.owl#> "
				+ "SELECT ?name "
				+ " WHERE {"
				+ "?x Plan:language '"+ language+ "'.?x Plan:action '"+ act+ "'.?x Plan:name ?name}";

		Query query = QueryFactory.create(sorgu);

		// Execute the query and obtain results
		QueryExecution qe = QueryExecutionFactory.create(query, m);
		ResultSet results = qe.execSelect();

		while (results.hasNext()) {
			QuerySolution soln = results.nextSolution();
			RDFNode address = soln.get("name");
			result = new StringBuilder();
			result.append(address.toString());
			System.out.println("result " + result.toString());
		
			ar.add(result.toString());
		}
		// Output query results
	// ResultSetFormatter.out(System.out, results, query);

		qe.close();
		return ar;
	}

	public void addPlan(String name, String act, String language) {
		counter++;
		String id = null;
		String address = null;
		onto = m.getOntClass(xmlbase + "Plan");

		ad = m.createIndividual("Plan" + counter, onto);
		ad.addProperty(m.getDatatypeProperty(xmlbase + "name"), name);
		ad.addProperty(m.getDatatypeProperty(xmlbase + "language"), language);
		ad.addProperty(m.getDatatypeProperty(xmlbase + "action"), act);
		ad.addOntClass(onto);

		FileOutputStream camera_File;
		try {
			camera_File = new FileOutputStream("C:\\Plan\\Plan.owl");
			m.write(camera_File, "RDF/XML-ABBREV", xmlbase);
			// String adres=Search("barca");
			// write it to standard out
			// m.write(System.out);
		}

		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
