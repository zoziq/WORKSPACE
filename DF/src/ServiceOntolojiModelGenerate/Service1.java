package ServiceOntolojiModelGenerate;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.hp.hpl.jena.ontology.DatatypeProperty;
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

public class Service1 {

	public static void main(String[] args) throws FileNotFoundException {

		String language = "tr";

		String nameSpace = "http://www.semanticweb.org/ontologies/2011/11/Service1.owl#";
		String xmlBase = nameSpace;

		// Bir ontoloji modeli olusturuyoruz
		OntModel model = ModelFactory
				.createOntologyModel(ProfileRegistry.OWL_LANG);
		System.out.println("Model olusturuldu...");

		/*
		 * Namespace'imizi istedigimiz sekilde adlandirabilmek icin bu komutu
		 * kullaniyoruz; aksi takdirde Jena varsayilan olarak j.0, j.1, ...
		 * seklinde isimler verecektir.
		 */
		model.setNsPrefix("service1", nameSpace);

		RDFWriter rdfWriter = model.getWriter("RDF/XML-ABBREV");
		rdfWriter.setProperty("xmlbase", xmlBase);
		rdfWriter.setProperty("relativeURIs", "");

		// Service ontolojisi tanimlamalari
		
		// //////////////////////////////////////////////////////////////////
		// /
		// Class tanimlamalari /
		// /
		// //////////////////////////////////////////////////////////////////
		OntClass ServiceDirectoryService = model.createClass(nameSpace	+ "ServiceDirectoryService");
		

		OntClass Service = model.createClass(nameSpace + "Service");
		
		/*
		 * Kitap satisi yapan bir veb servis icin dusunulurse; ISBN numarasi
		 * bilgisi input parametresi olarak servise gonderilir, servisten kitap
		 * fiyatýný gosteren output parametresi geri dondurulur.
		 * 
		 * Bu sýnýflar owl-s'in Process yapisindan gelir.
		 */
		
		OntClass Parameter = model.createClass(nameSpace + "Parameter");

		OntClass Input = model.createClass(nameSpace + "Input");

		OntClass Output = model.createClass(nameSpace + "Output");
		

		// subClass tanimlamalari
		Input.setSuperClass(Parameter);
		Output.setSuperClass(Parameter);

		// owl-s yapisindaki Process'in subClass'lari
		OntClass CompositProcess = model.createClass(nameSpace
				+ "CompositProcess");

		OntClass AtomicProcess = model.createClass(nameSpace + "AtomicProcess");

		// CompositProcess ve Service esit dusunulebilir
		CompositProcess.setEquivalentClass(Service);

		// //////////////////////////////////////////////////////////////////
		// /
		// ObjectProperty tanimlamalari /
		// /
		// //////////////////////////////////////////////////////////////////

		// registeringService ve discoveringService icin plan yazilacak.
		ObjectProperty registeringService = model
				.createObjectProperty(nameSpace + "registeringService");
		registeringService.setDomain(ServiceDirectoryService);
		registeringService.setRange(Service);


		ObjectProperty discoveringService = model
				.createObjectProperty(nameSpace + "discoveringService");
		discoveringService.setDomain(ServiceDirectoryService);
		discoveringService.setRange(Service);
		
		// Servisin input ve output parametreleri
		ObjectProperty hasParameter = model.createObjectProperty(nameSpace
				+ "hasParameter");
		hasParameter.setDomain(Service);
		hasParameter.setRange(Parameter);
		
		ObjectProperty hasInput = model.createObjectProperty(nameSpace
				+ "hasInput");
		hasInput.setDomain(Service);
		hasInput.setRange(Input);

		ObjectProperty hasOutput = model.createObjectProperty(nameSpace
				+ "hasOutput");
		hasOutput.setDomain(Service);
		hasOutput.setRange(Output);

		// subProperty tanimlamari
		hasInput.setSuperProperty(hasParameter);
		hasOutput.setSuperProperty(hasParameter);

		// CompositProcess, AtomicProcess ve CompositProcess'lerden olusabilir.
		ObjectProperty composedOf = model.createObjectProperty(nameSpace
				+ "composedOf");
		composedOf.setDomain(CompositProcess);
		composedOf.setRange(CompositProcess);
		composedOf.setRange(AtomicProcess);
		// //////////////////////////////////////////////////////////////////
		// /
		// DatatypeProperty tanimlamalari /
		// /
		// //////////////////////////////////////////////////////////////////

		/*
		 * Asagidaki serviceID, serviceAddress ve serviceTextDescription
		 * tanimlamalarinin birlesimi FIPA standarlarindaki
		 * Service-directory-entry'e karsilik gelir. Asagidaki yapýlar Class
		 * olmadiklari icin unionOf kullanilmadi.
		 * 
		 * Asagidaki bes tanimlama owl-s yapisindaki Profile kismina denk gelir.
		 * Profile, servisin ne yaptigini, servisin yapisini ve hangi hizmetleri
		 * verecegi bilgisini tutar.
		 */

		// serviceID kullaninca serviceName'e gerek kalmayabilir.
		DatatypeProperty serviceName = model.createDatatypeProperty(nameSpace+ "serviceName", true);
		
		serviceName.setDomain(Service);
		serviceName.setRange(XSD.xstring);

		InverseFunctionalProperty serviceId = model.createInverseFunctionalProperty(nameSpace + "serviceId", true);
		
		serviceId.setDomain(Service);
		serviceId.setRange(XSD.xstring);

		InverseFunctionalProperty serviceAddress = model.createInverseFunctionalProperty(nameSpace + "serviceAddress",true);
		
		serviceAddress.setDomain(Service);
		serviceAddress.setRange(XSD.anyURI);

		/*
		 * serviceTextDescription ve serviceCategory FIPA standartlarindaki
		 * Service-type'a karsilik gelir. Bu iki DatatypeProperty, owl-s
		 * tanimlamalarindan alinmisitir.
		 */
		DatatypeProperty serviceTextDescription = model.createDatatypeProperty(
				nameSpace + "serviceTextDescription", true);
		
		serviceTextDescription.setDomain(Service);
		serviceTextDescription.setRange(XSD.xstring);

		DatatypeProperty serviceCategory = model.createDatatypeProperty(
				nameSpace + "serviceCategory", true);
		
		serviceCategory.setDomain(Service);

		// Parametreler icin tip ve deger bilgileri tutulur.
		DatatypeProperty parametreType = model.createDatatypeProperty(nameSpace	+ "parametreType", true);
		parametreType.setDomain(Parameter);
		parametreType.setRange(XSD.anyURI);

		DatatypeProperty parametreValue = model.createDatatypeProperty(nameSpace + "parametreValue", true);
		parametreValue.setDomain(Parameter);
		parametreValue.setRange(XSD.xstring);
		
		
		// //////////////////////////////////////////////////////////////////
		// /
		// Individiual tanimlamalari /
		// /
		// //////////////////////////////////////////////////////////////////
		
		Individual service1 = model.createIndividual(nameSpace + "service1", Service);
		service1.addProperty(serviceName, model.createResource("Toplama_Islemi"));
		service1.addProperty(serviceAddress, model.createResource("rmi://192.168.2.23/Service1"));
		service1.addProperty(serviceTextDescription, model.createResource("Toplama_islemi_yapan_servis"));
		
		Individual input1 = model.createIndividual(nameSpace + "input1", Input);
		input1.addProperty(parametreType, model.createResource("integer"));
		
		Individual input2 = model.createIndividual(nameSpace + "input2", Input);
		input2.addProperty(parametreType, model.createResource("integer"));
		
		Individual output1 = model.createIndividual(nameSpace + "output1", Output);	
		output1.addProperty(parametreType, model.createResource("integer"));
		
		Individual atomicProcess1 = model.createIndividual(nameSpace + "atomicProcess1", AtomicProcess);		
		atomicProcess1.addProperty(hasInput, input1);
		atomicProcess1.addProperty(hasInput, input2);
		atomicProcess1.addProperty(hasOutput, output1);
		
		service1.addProperty(composedOf, atomicProcess1);
		

		// Olusturdugumuz dokumani XML formatinda bir dosyaya yaziyoruz.
		FileOutputStream comu_File = new FileOutputStream("Service1.owl");
		model.write(comu_File, "RDF/XML-ABBREV", xmlBase);

		System.out.println("Veri, dosyaya yazildi...");

		
	}
}
