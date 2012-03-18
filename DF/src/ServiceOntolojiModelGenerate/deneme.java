package ServiceOntolojiModelGenerate;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.ProfileRegistry;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.RDFWriter;

public class deneme {

	public static void main(String[] args) {
		
		String language = "tr";

		String nameSpace = "http://www.semanticweb.org/ontologies/2011/11/Service1.owl#";
		String xmlBase = nameSpace;

		OntModel model = ModelFactory.createOntologyModel(ProfileRegistry.OWL_LANG);

		model.setNsPrefix("service1", nameSpace);

		RDFWriter rdfWriter = model.getWriter("RDF/XML-ABBREV");
		rdfWriter.setProperty("xmlbase", xmlBase);
		rdfWriter.setProperty("relativeURIs", "");
		
		
	}
	
}
