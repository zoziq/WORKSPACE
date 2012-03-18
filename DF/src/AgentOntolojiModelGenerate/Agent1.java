package AgentOntolojiModelGenerate;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.InverseFunctionalProperty;
import com.hp.hpl.jena.ontology.ObjectProperty;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.Ontology;
import com.hp.hpl.jena.ontology.ProfileRegistry;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.RDFWriter;
import com.hp.hpl.jena.vocabulary.XSD;

public class Agent1 {

	public static void main(String[] args) throws FileNotFoundException {
		
		String language = "tr";
		
		String nameSpace = "http://www.semanticweb.org/ontologies/2011/11/Agent1.owl#";
		String xmlBase = nameSpace;

		// Bir ontoloji modeli olusturuyoruz
		OntModel model = ModelFactory.createOntologyModel(ProfileRegistry.OWL_LANG);
			
//		Ontology ont = model.createOntology(nameSpace);
//		ont.addImport(model.createResource("http://www.semanticweb.org/ontologies/2011/11/AgentMetaModel.owl#"));
//		ont.addImport(model.createResource("http://www.semanticweb.org/ontologies/2011/11/Service1.owl#"));

		////////////////////////////////////////////////////////////////////
		//                                                                 /
		//                    Class tanimlamalari                          /
		//                                                                 /
		////////////////////////////////////////////////////////////////////
		OntClass AgentDirectoryService = model.createClass(nameSpace + "AgentDirectoryService");
		
		OntClass Agent = model.createClass(nameSpace + "Agent");
			
		////////////////////////////////////////////////////////////////////
		//                                                                 /
		//              ObjectProperty tanimlamalari                       /
		//                                                                 /
		////////////////////////////////////////////////////////////////////

		ObjectProperty matchService = model.createObjectProperty(nameSpace + "matchService");
		matchService.setDomain(Agent);
		
		////////////////////////////////////////////////////////////////////
		//                                                                 /
		//             DatatypeProperty tanimlamalari                      /
		//                                                                 /
		////////////////////////////////////////////////////////////////////
		
		/* Asagidaki agentName ve agentAddress tanimlamalarinin birlesimi
		* FIPA standarlarindaki Agent-directory-entry'e karsilik gelir.
		* Asagidaki yapýlar Class olmadiklari icin unionOf kullanilmadi.
		*/ 
		
		InverseFunctionalProperty agentName = model.createInverseFunctionalProperty(nameSpace + "agentName", true);
		agentName.setDomain(Agent);
		agentName.setRange(XSD.xstring);

		InverseFunctionalProperty agentAddress = model.createInverseFunctionalProperty(nameSpace + "agentAddress", true);
		agentAddress.setDomain(Agent);
		agentAddress.setRange(XSD.anyURI);
		/*
		 * Namespace'imizi istedigimiz sekilde adlandirabilmek icin bu komutu
		 * kullaniyoruz; aksi takdirde Jena varsayilan olarak j.0, j.1, ...
		 * seklinde isimler verecektir.
		 */
		
		model.setNsPrefix("agent1", nameSpace);
		
		Individual agent1 = model.createIndividual(nameSpace + "agent1", Agent);
		agent1.addProperty(agentName, model.createResource("Agent1"));
		agent1.addProperty(agentAddress, model.createResource("rmi://192.168.2.23/Service1"));
		agent1.addProperty(matchService, model.createResource("http://www.semanticweb.org/ontologies/2011/11/Service1.owl#"));
		
		model.setNsPrefix("agent1", nameSpace);
		RDFWriter rdfWriter = model.getWriter("RDF/XML-ABBREV");
		rdfWriter.setProperty("xmlbase", xmlBase);
		rdfWriter.setProperty("relativeURIs", "");
		
		
		// Olusturdugumuz dokumani XML formatinda bir dosyaya yaziyoruz.
		FileOutputStream comu_File = new FileOutputStream("Agent1.owl");
		model.write(comu_File, "RDF/XML-ABBREV", xmlBase);
		
		System.out.println("Veri, dosyaya yazildi...");		
	}
}
