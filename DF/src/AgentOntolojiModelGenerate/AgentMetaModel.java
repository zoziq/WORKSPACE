package AgentOntolojiModelGenerate;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.hp.hpl.jena.ontology.InverseFunctionalProperty;
import com.hp.hpl.jena.ontology.ObjectProperty;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntDocumentManager;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.Ontology;
import com.hp.hpl.jena.ontology.ProfileRegistry;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.RDFWriter;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.XSD;

public class AgentMetaModel {

	public static void main(String[] args) throws FileNotFoundException {
		
		String language = "tr";
		
		String nameSpace = "http://www.semanticweb.org/ontologies/2011/11/AgentMetaModel.owl#";
		String xmlBase = nameSpace;

		// Bir ontoloji modeli olusturuyoruz
		OntModel model = ModelFactory.createOntologyModel(ProfileRegistry.OWL_LANG);
		System.out.println("Model olusturuldu...");
		
		/*
		 * Namespace'imizi istedigimiz sekilde adlandirabilmek icin bu komutu
		 * kullaniyoruz; aksi takdirde Jena varsayilan olarak j.0, j.1, ...
		 * seklinde isimler verecektir.
		 */
		model.setNsPrefix("agent", nameSpace);
		
		RDFWriter rdfWriter = model.getWriter("RDF/XML-ABBREV");
		rdfWriter.setProperty("xmlbase", xmlBase);
		rdfWriter.setProperty("relativeURIs", "");
		
		//Service ontolojisi tanimlamalari 
		
		////////////////////////////////////////////////////////////////////
		//                                                                 /
		//                    Class tanimlamalari                          /
		//                                                                 /
		////////////////////////////////////////////////////////////////////
		OntClass AgentDirectoryService = model.createClass(nameSpace + "AgentDirectoryService");
		
		OntClass Agent = model.createClass(nameSpace + "Agent");

		OntClass Service = model.createClass(nameSpace + "Service");

			
		////////////////////////////////////////////////////////////////////
		//                                                                 /
		//              ObjectProperty tanimlamalari                       /
		//                                                                 /
		////////////////////////////////////////////////////////////////////

		ObjectProperty matchService = model.createObjectProperty(nameSpace + "matchService");
		matchService.setDomain(Agent);
		matchService.setRange(Service);
		
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
		
		// Olusturdugumuz dokumani XML formatinda bir dosyaya yaziyoruz.
		FileOutputStream comu_File = new FileOutputStream("AgentMetaModel.owl");
		model.write(comu_File, "RDF/XML-ABBREV", xmlBase);
		
		System.out.println("Veri, dosyaya yazildi...");		
	}
}
