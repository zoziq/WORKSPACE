package ams;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Statement;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntDocumentManager;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.query.*;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.RDFNode;
import fipa.Content;
import fipa.FipaMessage;

import agent.AID;
import agent.AgentDescription;
import agent.AgentState;

public class AgentManagementSystem implements AmsInterface {

	private AgentDescription agentDesc;
	Statement statement;
	private AgentState agentState;
	private AID aid;
	String NS = "http://www.semanticweb.org/ontologies/2010/11/Agent.owl";
	String xmlbase = NS + "#";
	OntModel model;
	InputStream in;
	OntClass onto;
	OntClass onto1;
	Individual ad;
	Individual ad1;
	Content con;
	int count = 0;

	public AgentManagementSystem() {

		aid = new AID();
		agentDesc = new AgentDescription();
		con = new Content();
		
		/*
		 * m = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM); in =
		 * FileManager.get().open("C:\\Plan\\AgentInd.owl"); // read the RDF/XML
		 * file m.read(in, xmlbase);
		 */

		model = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);
		OntDocumentManager dm = model.getDocumentManager();
		dm.addAltEntry(NS, "file:\\C:\\Plan\\AgentInd.owl");
		model.read(NS);
		model.prepare();
		model.setNsPrefix("Agent", "http://www.semanticweb.org/ontologies/2010/11/Agent.owl#");

	}

	public void execute(FipaMessage fm) {
		
		con = fm.getAcl().getContent();

		if (con.getActions().get(0).getAct().equals("register"))
			register(fm);
	// else if (con.getActions().get(0).getAct().equals("deregister"))
		// deregister(agentdes);
	// else if (con.getActions().get(0).getAct().equals("modify"))
		// modify(agentdes);
		else if (con.getActions().get(0).getAct().equals("search"))
			search(fm.getEnvelope().getTo().getAis().get(0).toString());
	}

	@Override
	public void deregister(String agentName) {
		
		StringBuilder result = null;
		String sorgu = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " +
				       "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> " +
				       "PREFIX Agent: <http://www.semanticweb.org/ontologies/2010/11/Agent.owl#> " +
				       "SELECT ?address " +
				       "WHERE {" +
				       		   "?x Agent:name '" + agentName + "'." + 
				       		   "?x Agent:address ?address" +
				       	"}";

		Query query = QueryFactory.create(sorgu);

		// Execute the query and obtain results
		QueryExecution qe = QueryExecutionFactory.create(query, model);
		com.hp.hpl.jena.query.ResultSet results = qe.execSelect();

		while (results.hasNext()) {
			QuerySolution soln = results.nextSolution();

			RDFNode address = soln.get("address");

			result = new StringBuilder();

			result.append(address.toString());

			System.out.println("result " + result.toString());

		}
		// Output query results
		// ResultSetFormatter.out(System.out, results, query);

		qe.close();

	}

	@Override
	public void modify(AgentDescription agentDesc) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean register(FipaMessage fm) {

		return true;
	}

	public boolean registerAgent(FipaMessage fm) {

		String namespace = "AGD";
		String name = fm.getAcl().getContent().getActions().get(0).getArguments().get(0).toString();
		String ownership = fm.getAcl().getContent().getActions().get(0).getArguments().get(2).toString();
		String state = "active";
		String id = null;
		String address = fm.getAcl().getContent().getActions().get(0).getArguments().get(1).toString();
		String namespace1 = "AID";
		System.out.println("ams icinde " + fm.getAcl().getContent().getActions().get(0).getArguments().get(0).toString());

		/*
		 * StringTokenizer st = new StringTokenizer(fm.getAcl().getContent()
		 * .getActions().get(0).getArguments().get(0).toString());
		 * StringTokenizer st1 = new StringTokenizer(fm.getAcl().getContent()
		 * .getActions().get(0).getArguments().get(1).toString());
		 */
		
		// ad=m.createIndividual(fm.getAcl().getContent().getActions().get(0).getArguments().get(0));
		// ad1=m.createIndividual(fm.getAcl().getContent().getActions().get(0).getArguments().get(1));
		
		onto = model.getOntClass(xmlbase + "AgentDescription");
		onto1 = model.getOntClass(xmlbase + "AgentIdentifier");

		/*
		 * while (st.hasMoreElements()) { namespace =
		 * st.nextElement().toString(); id = st.nextElement().toString(); name =
		 * st.nextElement().toString(); ownership = st.nextElement().toString();
		 * state = st.nextElement().toString(); break;
		 * 
		 * }
		 * 
		 * while (st1.hasMoreElements()) { namespace1 =
		 * st1.nextElement().toString();
		 * 
		 * id = st1.nextElement().toString(); name =
		 * st1.nextElement().toString(); address = st1.nextElement().toString();
		 * break;
		 * 
		 * }
		 */
		
		++count;
		ad = model.createIndividual(namespace + count, onto);
		// ad.addProperty(m.getDatatypeProperty(xmlbase + "id"), id);
		ad.addProperty(model.getDatatypeProperty(xmlbase + "name"), name);
		ad.addProperty(model.getDatatypeProperty(xmlbase + "ownership"), ownership);
		ad.addProperty(model.getDatatypeProperty(xmlbase + "state"), state);
		ad.addOntClass(onto);

		ad1 = model.createIndividual(namespace1 + count, onto1);
		// ad1.addProperty(m.getDatatypeProperty(xmlbase + "id"), id);
		ad1.addProperty(model.getDatatypeProperty(xmlbase + "name"), name);
		ad1.addProperty(model.getDatatypeProperty(xmlbase + "address"), address);
		ad1.addOntClass(onto1);

		FileOutputStream output_File;
		try {
			output_File = new FileOutputStream("C:\\Plan\\agentind.owl");

			model.write(output_File, "RDF/XML-ABBREV", xmlbase);
			model.write(System.out);
			// String adres=Search("barca");

			// write it to standard out
			// m.write(System.out);

		}

		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;

	}

	/*
	 * @Override public AgentDescription search(String name) { String adres =
	 * null;
	 * 
	 * 
	 * for (Iterator bs = m.listIndividuals(); bs.hasNext();) {
	 * System.out.println("instance " + bs.next()); Individual in =
	 * m.getIndividual(bs.next().toString());
	 * 
	 * StmtIterator iter = in.listProperties(); while (iter.hasNext()) { if
	 * (iter.nextStatement().getObject().toString().equals( "barcelona")) {
	 * adres= iter.next().getObject().toString(); System.out.println(adres);
	 * 
	 * }
	 * 
	 * }
	 * 
	 * } return query(name); }
	 */

	public String search(String name) {
		StringBuilder result = null;

		String sorgu = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " +
					   "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> " +
					   "PREFIX Agent: <http://www.semanticweb.org/ontologies/2010/11/Agent.owl#> " +
					   "SELECT ?address " +
					   "WHERE {" +
					   			"?x Agent:name '" + name	+ "'." +
					   			"?x Agent:address ?address." +
					   			"?y Agent:state 'active'." +
					   			"?y Agent:name '" + name + "'" +
					   	"}";

		Query query = QueryFactory.create(sorgu);

		// Execute the query and obtain results
		QueryExecution qe = QueryExecutionFactory.create(query, model);
		com.hp.hpl.jena.query.ResultSet results = qe.execSelect();

		while (results.hasNext()) {
			QuerySolution soln = results.nextSolution();

			RDFNode address = soln.get("address");

			result = new StringBuilder();

			result.append(address.toString());

			System.out.println("result " + result.toString());

		}
		// Output query results
		// ResultSetFormatter.out(System.out, results, query);

		qe.close();
		if (result == null)
			return "";

		return result.toString();

	}

	@Override
	public String sparql(FipaMessage fm) {

		StringBuilder result = null;

		Query query = QueryFactory.create(fm.getAcl().getContent().getActions().get(0).getArguments().get(0).toString());

		// Execute the query and obtain results
		QueryExecution qe = QueryExecutionFactory.create(query, model);
		com.hp.hpl.jena.query.ResultSet results = qe.execSelect();

		while (results.hasNext()) {
			
			QuerySolution soln = results.nextSolution();

			RDFNode address = soln.get(fm.getAcl().getContent().getActions().get(0).getAct());

			result = new StringBuilder();
			result.append(address.toString());

			System.out.println("result " + result.toString());

		}
		// Output query results
		// ResultSetFormatter.out(System.out, results, query);

		qe.close();

		return result.toString();
	}
}