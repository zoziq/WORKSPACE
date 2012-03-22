package adassparql;

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

public class HTN {

	public static QueryExecution executeQuery(Model model, String queryString) {

		Query query = QueryFactory.create(queryString);

		// Execute the query and obtain results
		QueryExecution qe = QueryExecutionFactory.create(query, model);
		ResultSet results = qe.execSelect();

		// Output query results
		ResultSetFormatter.out(System.out, results, query);
		return qe;
	}

	public static void main(String[] args) throws IOException {

		// Open the RDF graph from the filesystem
		File dosya = new File("htnRdfDeneme.rdf");
		InputStream in = new FileInputStream(dosya);

		// Create an empty in-memory model and populate it from the graph
		Model model = ModelFactory.createMemModelMaker().createDefaultModel();
		model.read(in, null); // null base URI, since model URIs are absolute
		in.close();

		// Plan Numarasý 13 olan Behaviour'un ismi
		String queryString =
			"PREFIX htn: <http://www.htn.org/htn#> " + 
			"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " +
			"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> " + 
			"SELECT ?behName " +
			"WHERE { " +
			"      ?x htn:planNum \"13\". " +
			"      ?x htn:behName ?behName. " +
			" }";

		QueryExecution qe = executeQuery(model, queryString);
		qe.close();
		
		// Güzergah Provision'ýna sahip Resource'lar (çýktý kaynak tipli)
		String queryString2 =
			"PREFIX htn: <http://www.htn.org/htn#> " + 
			"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " +
			"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> " + 
			"SELECT ?x " +
			"WHERE { " +
			"      ?x htn:provision \"Guzergah\". " +
			" }";

		QueryExecution qe2 = executeQuery(model, queryString2);
		qe2.close();
		
		// Herhangi bir giren linki olan tüm Task'lar
		String queryString3 =
			"PREFIX htn: <http://www.htn.org/htn#> " + 
			"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " +
			"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> " + 
			"SELECT ?kimden " +
			"WHERE { " +
			"      ?x htn:kimden ?kimden. " +
			" }";

		QueryExecution qe3 = executeQuery(model, queryString3);
		qe3.close();
		
		// Þart olarak 'Guzergah', seçimli olarak 'Sefer' 
		// Provision'ýna sahip Action'larýn
		// ID ve isim bilgileri
		String queryString4 =
			"PREFIX htn: <http://www.htn.org/htn#> " + 
			"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " +
			"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> " + 
			"SELECT ?taskID ?actName " +
			"WHERE { " +
			"      ?x htn:taskID ?taskID. " +
			"      ?x htn:actName ?actName. " +
			"      ?x htn:provision \"Guzergah\". " +
			"OPTIONAL {" +
			"      ?x htn:provision \"Sefer\". }" +
			" }";

		QueryExecution qe4 = executeQuery(model, queryString4);
		qe4.close();
		
		// Plan numarasý 14'ten büyük olan kaynaklar
		String queryString5 =
			"PREFIX htn: <http://www.htn.org/htn#> " + 
			"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " +
			"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> " + 
			"SELECT ?resource " +
			"WHERE { " +
			"      ?resource htn:planNum ?planNum . " +
			"      FILTER (?planNum > 14) " +
			" }";

		QueryExecution qe5 = executeQuery(model, queryString5);
		qe5.close();
		
	}

}
