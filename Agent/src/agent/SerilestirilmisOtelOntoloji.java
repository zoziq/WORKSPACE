package agent;

import java.io.InputStream;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntDocumentManager;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.ModelFactory;

public class SerilestirilmisOtelOntoloji {
	OntClass onto;
	OntClass Aid;
	Individual otel;
	OntModel m;
	String NS = "http://www.semanticweb.org/ontologies/2011/3/Otel.owl";
	String xmlbase = NS + "#";
	static int counter;
	InputStream in;

	public SerilestirilmisOtelOntoloji() {

		m = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);
		OntDocumentManager dm = m.getDocumentManager();
		dm.addAltEntry(NS, "file:\\C:\\Plan\\Otel.owl");
		m.read(NS);
		m.prepare();
		m.setNsPrefix("Oda",
				"http://www.semanticweb.org/ontologies/2011/3/Otel.owl#");
	}

	public String seriAgentDesc(Individual ind) {
		StringBuffer sb = new StringBuffer();
		sb.append(ind.getURI() + " ");
		// System.out.println("amsMessage ind id "+ind.getProperty(m.getDatatypeProperty(xmlbase+"id")).getString());
		// System.out.println("amsMessage ind name "+ind.getProperty(m.getDatatypeProperty(xmlbase+"name")).getString());
		// System.out.println("amsMessage ind ownership "+ind.getProperty(m.getDatatypeProperty(xmlbase+"ownership")).getString());
		// System.out.println("amsMessage ind state "+ind.getProperty(m.getDatatypeProperty(xmlbase+"state")).getString());
		sb.append(ind.getProperty(m.getDatatypeProperty(xmlbase + "id"))
				.getString()
				+ " ");
		sb.append(ind.getProperty(m.getDatatypeProperty(xmlbase + "name"))
				.getString()
				+ " ");
		sb.append(ind.getProperty(m.getDatatypeProperty(xmlbase + "ownership"))
				.getString()
				+ " ");
		sb.append(ind.getProperty(m.getDatatypeProperty(xmlbase + "state"))
				.getString());
		// System.out.println("agentdescription serileştirme "+sb.toString());
		return sb.toString();

	}

}
