package AgentOntolojiModelGenerate;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;


import ServiceOntolojiModelGenerate.ServiceMetaModel;

import com.hp.hpl.jena.ontology.DatatypeProperty;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.InverseFunctionalProperty;
import com.hp.hpl.jena.ontology.ObjectProperty;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntDocumentManager;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.ontology.Ontology;
import com.hp.hpl.jena.ontology.ProfileRegistry;
import com.hp.hpl.jena.ontology.UnionClass;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.RDFList;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.RDFWriter;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.XSD;

public class deneme {

	public static void main(String[] args) throws FileNotFoundException {
		
		String language = "tr";
		
		String nameSpace = "http://www.semanticweb.org/ontologies/2011/11/Agent1.owl#";
		String xmlBase = nameSpace;

		// Bir ontoloji modeli olusturuyoruz
		OntModel model = ModelFactory.createOntologyModel(ProfileRegistry.OWL_LANG);
		
		// AgenetMetaModel.owl dosyasinin fiziksel adresini gösteriyoruz
		OntDocumentManager dm = model.getDocumentManager();
		dm.addAltEntry("http://www.semanticweb.org/ontologies/2011/11/AgenetMetaModel.owl", "file:\\C:\\Yeni Klasör\\DFOntolojiModel\\AgentMetaModel.owl");
		model.read("http://www.semanticweb.org/ontologies/2011/11/AgenetMetaModel.owl");
		Resource Service = model.getOntClass("http://www.semanticweb.org/ontologies/2011/11/ServiceMetaModel.owl#Service");
		
		/*
		 * Namespace'imizi istedigimiz sekilde adlandirabilmek icin bu komutu
		 * kullaniyoruz; aksi takdirde Jena varsayilan olarak j.0, j.1, ...
		 * seklinde isimler verecektir.
		 */
		model.setNsPrefix("agent", nameSpace);
		
		RDFWriter rdfWriter = model.getWriter("RDF/XML-ABBREV");
		rdfWriter.setProperty("xmlbase", xmlBase);
		rdfWriter.setProperty("relativeURIs", "");
	
		// Kullanacagimiz Agent modelinin namespace'ini her seferinde yazmamak icin degiskene atiyoruz
		String agentNameSpace = "http://www.semanticweb.org/ontologies/2011/11/AgentMetaModel.owl#";
		
		// Kullanacagimiz siniflari modelden  cekip individuallari olusturuyoruz
		Resource r = model.getOntClass(agentNameSpace + "Agent0");

		
		
		
		// Olusturdugumuz dokumani XML formatinda bir dosyaya yaziyoruz.
		FileOutputStream comu_File = new FileOutputStream("C:\\Yeni Klasör\\DFOntolojiModel\\Agent0.owl");
		model.write(comu_File, "RDF/XML-ABBREV", xmlBase);
		
		System.out.println("Veri, dosyaya yazildi...");		
	}
}
