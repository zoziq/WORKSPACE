package agent;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.StringTokenizer;

import com.hp.hpl.jena.ontology.DatatypeProperty;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.ObjectProperty;
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

public class OtelOntoloji {
	OntClass onto;
	OntClass Aid;
	OntClass aktivite;
	OntClass havuz1;
	OntClass sporakt;
	OntClass suspor;
	OntClass karaspor;
	OntClass golf;
	OntClass futbol;

	Individual otel;
	OntModel m;
	String NS = "http://www.semanticweb.org/ontologies/2011/3/Otel.owl";
	String xmlbase = NS + "#";
	static int counter;
	InputStream in;
	Individual aid;
	ObjectProperty obj;
	ObjectProperty obj1;
	DatatypeProperty yer1;
	DatatypeProperty adi;
	DatatypeProperty adres1;
	DatatypeProperty yildiz1;;

	public OtelOntoloji() {

		m = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);
		OntDocumentManager dm = m.getDocumentManager();
		dm.addAltEntry(NS, "file:\\C:\\Plan\\Otel.owl");
		m.read(NS);
		m.prepare();
		m.setNsPrefix("Otel",
				"http://www.semanticweb.org/ontologies/2011/3/Otel.owl#");
	}

	public ArrayList getPlan(String deger) {
		ArrayList ar = new ArrayList();
		String plan = null;
		String yildiz = null;
		String yer = null;
		StringTokenizer st = new StringTokenizer(deger);
		while (st.hasMoreTokens()) {
			yer = st.nextToken();
			yildiz = st.nextToken();

		}
		StringBuilder result = null;
		String sorgu = "PREFIX rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
				+ "PREFIX rdfs:<http://www.w3.org/2000/01/rdf-schema#> "
				+ "PREFIX Otel:<http://www.semanticweb.org/ontologies/2011/3/Otel.owl#> "
				+ "SELECT ?s  "
				+ " WHERE {"
				+ "?s Otel:yer '"
				+ yer
				+ "'.?s Otel:yildiz '" + yildiz + "'}";

		String sorgu1 = "PREFIX rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
				+ "PREFIX rdfs:<http://www.w3.org/2000/01/rdf-schema#> "
				+ "PREFIX Otel:<http://www.semanticweb.org/ontologies/2011/3/Otel.owl#> DELETE {?x Otel:sahiptir ?y } WHERE { ?x Otel:yer 'konya'.?x Otel:sahiptir ?y }";

		Query query = QueryFactory.create(sorgu);
		// Execute the query and obtain results
		QueryExecution qe = QueryExecutionFactory.create(query, m);
		ResultSet results = qe.execSelect();
		while (results.hasNext()) {
			QuerySolution soln = results.nextSolution();
			RDFNode address = soln.get("s");

			Individual in = m.getIndividual(address.toString());
			ar.add(seriOtel(in));

			// System.out.println("result " + address.toString());

		}

		qe.close();
		return ar;
	}

	public void addOtel(String yer, String ad, String adres) {
		++counter;
		onto = m.getOntClass(xmlbase + "Otel");
		Aid = m.getOntClass(xmlbase + "AID");
		// aktivite=m.getOntClass(xmlbase+"Aktivite");
		// havuz1=m.getOntClass(xmlbase+"Havuz");
		// sporakt=m.getOntClass(xmlbase+"Spor_Aktiviteleri");
		// suspor=m.getOntClass(xmlbase+"Su_Sporu");
		// karaspor=m.getOntClass(xmlbase+"Kara_Sporu");
		// golf=m.getOntClass(xmlbase+"Golf");
		// futbol=m.getOntClass(xmlbase+"Futbol");
		otel = m.createIndividual("Otel" + counter, onto);

		aid = m.createIndividual("AID" + counter, Aid);

		// Individual aktivit=m.createIndividual("Aktivite"+counter,aktivite);

		// Individual hvz=m.createIndividual("Havuz"+counter,havuz1);
		// Individual
		// spraktv=m.createIndividual("Spor_Aktiviteleri"+counter,sporakt);
		// Individual su_spor=m.createIndividual("Su_Sporu"+counter,suspor);
		// Individual
		// kara_spor1=m.createIndividual("Kara_Sporu"+counter,karaspor);
		// Individual golf1=m.createIndividual("Golf"+counter,golf);
		// Individual futbol1=m.createIndividual("Futbol"+counter,futbol);

		// futbol1.remove();
		adi = m.getDatatypeProperty(xmlbase + "ad");
		// adi.addDomain(aid);
		// adi.addRange(XSD.xstring);

		adres1 = m.getDatatypeProperty(xmlbase + "adres");
		// adres.addDomain(aid);
		// adres.addRange(XSD.xstring);

		yer1 = m.getDatatypeProperty(xmlbase + "yer");
		yer1.addDomain(onto);
		// yer1.addRange(XSD.xstring);

		yildiz1 = m.getDatatypeProperty(xmlbase + "yildiz");
		yildiz1.addDomain(onto);
		// yer1.addRange(XSD.xstring);

		obj = m.getObjectProperty(xmlbase + "sahiptir");
		obj.addDomain(onto);
		obj.addRange(Aid);

		// obj1=m.getObjectProperty(xmlbase+"vardir");
		// obj1.addDomain(onto);
		// obj1.addRange(aktivite);
		// obj1.addRange(hvz);

		aid.addProperty(adi, ad);
		aid.addProperty(adres1, adres);
		aid.addOntClass(Aid);
		otel.addProperty(yer1, yer);
		otel.addProperty(yildiz1, "4");
		otel.addOntClass(onto);
		otel.addProperty(obj, aid);
		// otel.addProperty(obj1,aktivit);
		// otel.addProperty(obj1,hvz);

		// seriOtel(otel);
		FileOutputStream camera_File;
		try {
			camera_File = new FileOutputStream("C:\\Plan\\Otel.owl");
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

	public String seriOtel(Individual ind) {

		StringBuffer sb = new StringBuffer();
		sb.append(ind.getURI() + " ");
		sb.append(ind.getProperty(m.getDatatypeProperty(xmlbase + "yer"))
				.getString()
				+ " ");
		String u = ind.getProperty(m.getObjectProperty(xmlbase + "sahiptir"))
				.getObject().asResource().getURI();
		Individual in = m.getIndividual(u);
		sb.append(in.getURI() + " ");
		sb.append("sahiptir ");
		sb.append(in.getProperty(m.getDatatypeProperty(xmlbase + "ad"))
				.getString()
				+ " ");
		sb.append(in.getProperty(m.getDatatypeProperty(xmlbase + "adres"))
				.getString()
				+ " ");
		System.out.println("ad "
				+ in.getProperty(m.getDatatypeProperty(xmlbase + "ad"))
						.getString());
		System.out.println("adres "
				+ in.getProperty(m.getDatatypeProperty(xmlbase + "adres"))
						.getString());
		System.out.println(" yer "
				+ ind.getProperty(m.getDatatypeProperty(xmlbase + "yer"))
						.getString());

		return sb.toString();

	}
}
