package ams;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Iterator;

import javax.swing.Icon;
import javax.swing.JOptionPane;

import agent.AgentDescription;

import com.hp.hpl.jena.ontology.DatatypeProperty;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.ProfileRegistry;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.RDFWriter;
import com.hp.hpl.jena.rdf.model.ResIterator;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.util.FileManager;
import com.hp.hpl.jena.vocabulary.XSD;

public class Ontology implements Serializable{
	boolean result = false;
	String NS = "http://localhost/owl/ontologies/agent/#";
	String xmlbase = NS;
	OntModel m;
	RDFWriter rdfw;
	OntClass agentdescription;
	OntClass agentidentifier;
	DatatypeProperty id;
	DatatypeProperty name;
	DatatypeProperty ownership;
	DatatypeProperty state;
	DatatypeProperty address;
	FileOutputStream camera_File;
	String dosya = "agent.owl";
	Individual ad;
	InputStream in;
	int i=0;
	AgentDescription agd;

	public void createOntology() {


		// ontologi dilleri ile onların temsil ettiği uri ler arasında eşlemeyi
		// anlamızı sağlar
		OntModel m = ModelFactory.createOntologyModel(ProfileRegistry.OWL_LANG);

		RDFWriter rdfw = m.getWriter("RDF/XML-ABBREV");
		rdfw.setProperty("xmlbase", xmlbase);
		rdfw.setProperty("relativeURIs", "");

		 agentdescription = m.createClass(NS + "agentdescription");

		agentidentifier = m.createClass(NS + "agentidentifier");

		DatatypeProperty id = m.createDatatypeProperty(NS + "id");
		id.addDomain(m.getOntClass(NS + "agentdescription"));
		id.addRange(XSD.nonNegativeInteger);

		/*
		 * Resource vcard = m.getResource(NS + "id"); vcard.addProperty(id,"1");
		 * vcard.addProperty(id,"2");
		 */
		DatatypeProperty name = m.createDatatypeProperty(NS + "name");
		name.addDomain(m.getOntClass(NS + "agentdescription"));
		name.addDomain(m.getOntClass(NS + "agentidentifier"));
		name.addRange(XSD.xstring);

		DatatypeProperty ownership = m.createDatatypeProperty(NS + "ownership");
		ownership.addDomain(m.getOntClass(NS + "agentdescription"));
		ownership.addRange(XSD.xstring);

		DatatypeProperty state = m.createDatatypeProperty(NS + "state");
		state.addDomain(m.getOntClass(NS + "agentdescription"));
		state.addRange(XSD.xstring);

		DatatypeProperty address = m.createDatatypeProperty(NS + "address");
		address.addDomain(m.getOntClass(NS + "agentidentifier"));
		address.addRange(XSD.xstring);
		
		
		
		FileOutputStream camera_File;
		String dosya = "agent.owl";
		OntClass onto=m.getOntClass("agentdescription");
		Individual	ad= m.createIndividual(NS + "agentdesc"+agd,onto);
		ad.addProperty(m.getDatatypeProperty(xmlbase+"id"),"1");
		ad.addProperty(m.getDatatypeProperty(xmlbase+"name"),"sadsadsad");
		ad.addProperty(m.getDatatypeProperty(xmlbase+"ownership"),"sadsad");
		ad.addProperty(m.getDatatypeProperty(xmlbase+"state"), "active");
	
		
		OntClass onto1=m.getOntClass("agentidentifier");
		Individual ad1 = m.createIndividual(NS +"agentdesc"+agd+"1",onto);
		ad1.addProperty(m.getDatatypeProperty(xmlbase+"id"),"1");
		ad1.addProperty(m.getDatatypeProperty(xmlbase+"name"),"sadsadsad");
		ad1.addProperty(m.getDatatypeProperty(xmlbase+"ownership"),"sadsad");
		ad1.addProperty(m.getDatatypeProperty(xmlbase+"state"), "active");
		
		
		
		 Iterator<Individual> individuals = m.listIndividuals(onto);
		// Vector<Individual> hits = new Vector<Individual>();
		 while(individuals.hasNext()){
		 Individual entity = individuals.next();
		 
		 System.out.println(entity.getURI());
		 }
		
		try {
			
			/*
			 * StmtIterator iter = ad.listProperties(name); while
			 * (iter.hasNext()) { if(
			 * iter.nextStatement().getObject().toString().equals("mehmet")) {
			 * JOptionPane.showMessageDialog(null, "başarılı"); } }
			 */
			camera_File = new FileOutputStream("agentschema.owl");
			m.write(camera_File, "RDF/XML-ABBREV", xmlbase);
			InputStream in = FileManager.get().open(dosya);
			if (in == null) {
				throw new IllegalArgumentException(dosya + " bulunamadı");
			}

			
	
		} catch (Exception e) {
			e.printStackTrace();
		}
		// OutputStream out = (OutputStream) camera_File;

	}

	public void register() {
		ad = m.createIndividual(NS + "ad33", agentdescription);
		ad.addProperty(id,"1");
		ad.addProperty(name,"emrah");
		ad.addProperty(ownership,"emrah");
		ad.addProperty(state, "active");
		try {
			camera_File = new FileOutputStream("agentdata.owl");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		m.write(camera_File, "RDF/XML-ABBREV", xmlbase);

		// read the RDF/XML file
		m.read(in, xmlbase);

		// write it to standard out
		m.write(System.out);


	}

	public static void main(String[] args) {
		Ontology ont=new Ontology();
		ont.createOntology();
	}

}
