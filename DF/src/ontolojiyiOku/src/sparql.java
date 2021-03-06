package ontolojiyiOku.src;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.swing.JPopupMenu.Separator;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;

public class sparql {

	public static void main(String[] args) throws Exception {
		
		InputStream in = new FileInputStream(new File("Service1.owl"));

		Model model = ModelFactory.createOntologyModel();
		model.read(in,null); // null base URI, since model URIs are absolute
		in.close();

		String queryString = 
			"PREFIX service1: <http://www.semanticweb.org/ontologies/2011/11/Agent1.owl#> " +
			"SELECT ?x  " +
			"WHERE { " +
			"      ?x service1:serviceName ?y. " +
			"      }";

		
		
		Query query = QueryFactory.create(queryString);

		// Execute the query and obtain results
		QueryExecution qe = QueryExecutionFactory.create(query, model);
		ResultSet results = qe.execSelect();

		// Output query results	
		ResultSetFormatter.out(System.out, results, query);

		// Important - free up resources used running the query
		qe.close();
		
	}

		
}
