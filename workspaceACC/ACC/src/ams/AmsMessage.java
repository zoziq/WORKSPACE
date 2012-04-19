package ams;

import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntDocumentManager;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.ontology.ProfileRegistry;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.util.FileManager;

import agent.AID;
import agent.AgentDescription;
import agent.AgentState;
import fipa.FIPARDFContent0;
import fipa.Content;

public class AmsMessage implements Serializable{

	private Content con;
	private AgentDescription agentD;
	private AgentState as;
	String NS = "c://Plan//Agent.owl/";
	String xmlbase = NS+"#";
	OntModel m;
	InputStream in;
	OntClass onto;
	OntClass onto1;
	Individual ad1;
	int indNumber;
	private static int counter=1;
	
	
	public static int getCounter() {
		return counter;
	}
	
	public int getMsgNumber() {
		return indNumber;
	}
	
	public AmsMessage(){
		agentD=new AgentDescription();
		con=new Content();
		as=new AgentState(1);
		this.indNumber = ++counter;
		
	}

	
	
	public Content register(AID aid,String owner){
		
		String agd=Integer.toString(indNumber);
		
		System.out.println(aid.getName()+" "+aid.getAddress().get(0));
		m = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);
		OntDocumentManager dm = m.getDocumentManager();
		dm.addAltEntry(NS, "file:\\C:\\Plan\\Agent.owl");
		m.read(NS);
		m.prepare();
		m.setNsPrefix("Agent", "http://www.semanticweb.org/ontologies/2010/11/Agent.owl#");
		
		
		onto=m.getOntClass(xmlbase+"agentdescription");
		Individual	ad= m.createIndividual(NS + "agentdesc"+agd,onto);
	//	ad.addProperty(m.getDatatypeProperty(xmlbase+"id"),Integer.toString(indNumber));
		ad.addProperty(m.getDatatypeProperty(xmlbase+"name"),aid.getName());
		ad.addProperty(m.getDatatypeProperty(xmlbase+"ownership"),owner);
		ad.addProperty(m.getDatatypeProperty(xmlbase+"state"), "active");
	
		
		 onto1=m.getOntClass(xmlbase+"agentidentifier");
		ad1 = m.createIndividual(NS +"agentidentifier"+agd,onto1);
//		ad1.addProperty(m.getDatatypeProperty(xmlbase+"id"),Integer.toString(indNumber));
		ad1.addProperty(m.getDatatypeProperty(xmlbase+"name"),aid.getName());
		ad1.addProperty(m.getDatatypeProperty(xmlbase+"address"),aid.getAddress().get(0).toString());
	
		// write it to standard out
	//	m.write(System.out);
		ArrayList ar=new ArrayList();
		ar.add(seriAgentDesc(ad));
		ar.add(seriAgentIdentifier(ad1));
		FIPARDFContent0 conA=new FIPARDFContent0( "ams","register",ar);
		con.addList(conA);
		return con;
		
	}
	
	
	public String seriAgentDesc(Individual ind)
	{
		StringBuffer sb=new StringBuffer();
		sb.append(ind.getURI()+" ");
	//	System.out.println("amsMessage ind id "+ind.getProperty(m.getDatatypeProperty(xmlbase+"id")).getString());
	//	System.out.println("amsMessage ind name "+ind.getProperty(m.getDatatypeProperty(xmlbase+"name")).getString());
	//	System.out.println("amsMessage ind ownership "+ind.getProperty(m.getDatatypeProperty(xmlbase+"ownership")).getString());
	//	System.out.println("amsMessage ind state "+ind.getProperty(m.getDatatypeProperty(xmlbase+"state")).getString());
		sb.append(ind.getProperty(m.getDatatypeProperty(xmlbase+"id")).getString()+" ");
		sb.append(ind.getProperty(m.getDatatypeProperty(xmlbase+"name")).getString()+" ");
		sb.append(ind.getProperty(m.getDatatypeProperty(xmlbase+"ownership")).getString()+" ");
		sb.append(ind.getProperty(m.getDatatypeProperty(xmlbase+"state")).getString());
	//	System.out.println("agentdescription serileştirme "+sb.toString());
		return sb.toString();
		
	}


	public String seriAgentIdentifier(Individual ind)
	{
		StringBuffer sb=new StringBuffer();
		sb.append(ind.getURI()+" ");
		
	//	System.out.println("amsMessage ind id "+ind.getProperty(m.getDatatypeProperty(xmlbase+"id")).getString());
	//	System.out.println("amsMessage ind name "+ind.getProperty(m.getDatatypeProperty(xmlbase+"name")).getString());
	//	System.out.println("amsMessage ind address "+ind.getProperty(m.getDatatypeProperty(xmlbase+"address")).getString());
		
		sb.append(ind.getProperty(m.getDatatypeProperty(xmlbase+"id")).getString()+" ");
		sb.append(ind.getProperty(m.getDatatypeProperty(xmlbase+"name")).getString()+" ");
		sb.append(ind.getProperty(m.getDatatypeProperty(xmlbase+"address")).getString()+" ");
		
	//	System.out.println("agentidentifier serileştirme "+sb.toString());
		return sb.toString();
		
	}
}
