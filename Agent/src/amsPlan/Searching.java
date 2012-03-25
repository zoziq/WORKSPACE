package amsPlan;

import java.io.InputStream;

import task.Action;
import task.Outcome;
import task.Provision;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.util.FileManager;

import fipa.FipaMessage;

public class Searching extends Action {

	Provision name;
	Outcome Ok;
	Outcome Fail;

	String NS = "http://www.semanticweb.org/ontologies/2010/11/Agent.owl";
	String xmlbase = NS + "#";
	OntModel m;
	InputStream in;

	public Searching() {

		(name = new Provision()).setprovName("name");
		(Ok = new Outcome()).setName("Ok");
		(Fail = new Outcome()).setName("Fail");
		// provisions.add(name);
		// outcomes.add(Ok);
		// outcomes.add(Fail);

		m = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);
		in = FileManager.get().open(
				"C:\\Users\\E-Hero\\ontologies\\AgentInd\\AgentInd.owl");
		// read the RDF/XML file
		m.read(in, xmlbase);
	}

	@Override
	public void doAction() {
		StringBuilder result = null;
		System.out.println("name " + name.getValue());
		String sorgu = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
				+ "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> "
				+ "PREFIX Agent: <http://www.semanticweb.org/ontologies/2010/11/Agent.owl#> "
				+ "SELECT ?address "
				+ " WHERE {"
				+ "?x Agent:name '"
				+ name.getValue() + "'.?x Agent:address ?address}";

		Query query = QueryFactory.create(sorgu);

		// Execute the query and obtain results
		QueryExecution qe = QueryExecutionFactory.create(query, m);
		com.hp.hpl.jena.query.ResultSet results = qe.execSelect();

		while (results.hasNext()) {
			QuerySolution soln = results.nextSolution();

			RDFNode address = soln.get("address");

			result = new StringBuilder();

			result.append(address.toString());

			System.out.println("result " + result.toString());

			qe.close();

		}
		return;
	}

	@Override
	public void sendMessage(FipaMessage fm) {
	
	}
}
