package ontolojiyiOku.src;




import java.io.InputStream;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntDocumentManager;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.util.FileManager;


public class Oku {
	public static void main(String[] args) {
		

		String NS = "http://www.semanticweb.org/ontologies/2011/11/Service1.owl";
		String xmlbase = NS + "#";
		
		OntModel model = ModelFactory.createOntologyModel();

		//Read the ontology file

		InputStream in = FileManager.get().open("Service1.owl");
	       
		model.read(in,"");
		
		System.out.println(	model.getResource("Service"));


		Individual ind = model.getIndividual(xmlbase + "atomicProcess1");
		Property prop = model.getProperty(xmlbase + "hasOutput");
		
		System.out.println((ind.getPropertyValue(prop)));
	}
	

}
