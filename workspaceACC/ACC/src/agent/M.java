package agent;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;

import com.hp.hpl.jena.ontology.AllValuesFromRestriction;
import com.hp.hpl.jena.ontology.DatatypeProperty;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.ObjectProperty;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntDocumentManager;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.ontology.ProfileRegistry;
import com.hp.hpl.jena.ontology.UnionClass;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.RDFWriter;
import com.hp.hpl.jena.util.FileManager;
import com.hp.hpl.jena.vocabulary.XSD;

public class M {
	String NS = "http://www.semanticweb.org/ontologies/2011/3/Plan.owl";
	String xmlbase = NS+"#";
	OntModel m;
	OntClass onto;
	public M(){
		
		 m = ModelFactory.createOntologyModel(ProfileRegistry.OWL_LANG);
			
		    OntDocumentManager dm = m.getDocumentManager();
		  dm.addAltEntry("http://www.semanticweb.org/ontologies/2011/3/Plan.owl","file:\\C:\\htn.owl");
		   m.read( NS) ;

		    m.prepare();
		    m.setNsPrefix("Plan","http://www.semanticweb.org/ontologies/2011/3/Plan.owl#");
	}
	public void ekle()
	{
	

	
    
	RDFWriter rdfw = m.getWriter("RDF/XML-ABBREV");

	OntClass Thing = m.createClass(xmlbase + "Thing");

	OntClass Task = m.createClass(xmlbase+ "Plan");


	DatatypeProperty action = m.createDatatypeProperty(xmlbase + "action");
	
	DatatypeProperty name = m.createDatatypeProperty(xmlbase+ "name");


	DatatypeProperty language = m.createDatatypeProperty(xmlbase + "language");

	
	FileOutputStream camera_File;
	String dosya = "C:/htn.owl";
	try {
		camera_File = new FileOutputStream("C:/htn.owl");
		m.write(camera_File, "RDF/XML-ABBREV", xmlbase);
		InputStream in = FileManager.get().open(dosya);
		if (in == null) {
			throw new IllegalArgumentException(dosya + " bulunamadý");
		}

		// read the RDF/XML file
		m.read(in, xmlbase);

		// write it to standard out
		m.write(System.out);

	} catch (Exception e) {
		e.printStackTrace();
	}
	// OutputStream out = (OutputStream) camera_File;

}
	
	
public static void main(String[] args) {
	M m=new M();
	m.ekle();
	m.ekleind();
}


private void ekleind() {
	onto = m.getOntClass(xmlbase+"Plan");
	
	
	Individual ad = m.createIndividual(xmlbase+"Plan1", onto);
	ad.addProperty(m.getDatatypeProperty(xmlbase + "name"), "emrah");
	ad.addProperty(m.getDatatypeProperty(xmlbase + "language"), "language");
	ad.addProperty(m.getDatatypeProperty(xmlbase + "action"), "act");
	ad.addOntClass(onto);
	

	FileOutputStream camera_File;
		try {
			camera_File = new FileOutputStream("C:\\htn.owl");

			m.write(camera_File, "RDF/XML-ABBREV", xmlbase);

		//String adres=Search("barca");

			// write it to standard out
			 m.write(System.out);

		}

		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
}
}
