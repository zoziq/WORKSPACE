package ontolojiyiOku.src;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;

public class EtkinlikSorgu {

public static void main(String[] args) throws IOException {
		
		InputStream in = new FileInputStream(new File("Etkinlik.rdf"));

		// Create an empty in-memory model and populate it from the graph
		Model model = ModelFactory.createOntologyModel();
		model.read(in,null); // null base URI, since model URIs are absolute
		in.close();
	
		// tüm müþterilerin adlarý ve soyadlarý
		String queryString = 
			"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " +
			"PREFIX etkinlik: <http://www.etkinlik.org/etkinlik#> " +
			"SELECT ?ad ?soyad " +
			"WHERE {" +
			"      ?a etkinlik:Ad ?ad ." +
			"      ?a  etkinlik:Soyad ?soyad " +
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
