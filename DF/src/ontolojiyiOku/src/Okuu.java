package ontolojiyiOku.src;


import org.apache.log4j.lf5.util.Resource;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntDocumentManager;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;


public class Okuu {
	public static void main(String[] args) {
		
/*		String NS = "http://www.semanticweb.org/ontologies/2011/11/Agent1.owl";
		String xmlbase = NS + "#";
		
		OntModel model = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);
		OntDocumentManager dm = model.getDocumentManager();
		dm.addAltEntry(NS, "file:\\C:\\Yeni Klasör\\DFOntolojiModel\\Agent1.owl");
		model.read(NS);
	
		model.setNsPrefix("Agent", NS);
		
		Individual ind = model.getIndividual(xmlbase + "agent1");
		Property prop = model.getProperty(xmlbase + "matchService");
		
		System.out.println(ind.getPropertyValue(prop));
*/
		String NS = "http://www.semanticweb.org/ontologies/2011/11/Service1.owl";
		String xmlbase = NS + "#";
		
		OntModel model = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);
		OntDocumentManager dm = model.getDocumentManager();
		dm.addAltEntry(NS, "file:\\C:\\Users\\tr1\\Document\\Mustafa\\service ontolojisi\\Service1.owl");
		model.read(NS);
		
		model.setNsPrefix("Service", NS);
		
		
		System.out.println(model.getResource("AtomicProcess"));
		
		OntClass a = model.getOntClass(xmlbase + "Service");
		System.out.println(a);
		
		System.out.println(a.getProperty(model.getProperty(xmlbase + "service1")));
		
		

		Individual ind = model.getIndividual(xmlbase + "atomicProcess1");
		Property prop = model.getProperty(xmlbase + "hasOutput");
		
		System.out.println((ind.getPropertyValue(prop)));
		
	}
	

}
