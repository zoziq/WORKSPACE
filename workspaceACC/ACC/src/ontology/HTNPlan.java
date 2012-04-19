package ontology;

import java.io.FileOutputStream;
import java.io.InputStream;

import com.hp.hpl.jena.ontology.AllValuesFromRestriction;
import com.hp.hpl.jena.ontology.DatatypeProperty;
import com.hp.hpl.jena.ontology.ObjectProperty;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.ProfileRegistry;
import com.hp.hpl.jena.ontology.UnionClass;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.RDFWriter;
import com.hp.hpl.jena.util.FileManager;
import com.hp.hpl.jena.vocabulary.XSD;

public class HTNPlan {

	public static void main(String[] args) {
		String NS = "http://localhost/owl/ontologies/agent/htn/#";
		String xmlbase = NS;

		OntModel m = ModelFactory.createOntologyModel(ProfileRegistry.OWL_LANG);

		RDFWriter rdfw = m.getWriter("RDF/XML-ABBREV");
		rdfw.setProperty("xmlbase", xmlbase);
		rdfw.setProperty("relativeURIs", "");

		OntClass Thing = m.createClass(NS + "Thing");

		OntClass Task = m.createClass(NS + "Task");

		OntClass Primitive_Task = m.createClass(NS + "Primitive_Task");

		OntClass Complex_Task = m.createClass(NS + "Complex_Task");

		OntClass OutputLink = m.createClass(NS + "OutputLink");

		OntClass IO_Component = m.createClass(NS + "IO_Component");

		OntClass Provision = m.createClass(NS + "Provision");

		OntClass Inheritance = m.createClass(NS + "Inheritance");
		OntClass External_Provision = m.createClass(NS + "External_Provision");

		OntClass Internal_Provision = m.createClass(NS + "Internal_Provision");

		OntClass Outcome = m.createClass(NS + "Outcome");
		OntClass Goal = m.createClass(NS + "Goal");
		OntClass ProvisionOutcome_Link = m.createClass(NS
				+ "Provision-Outcome_Link");
		OntClass ParameterLink = m.createClass(NS + "ParameterLink");
		OntClass OutputParameterLink = m
				.createClass(NS + "OutputParameterLink");
		OntClass link = m.createClass(NS + "link");
		OntClass InputLink = m.createClass(NS + "InputLink");

		OntClass Disinheritance = m.createClass(NS + "Disinheritance");

		Thing.addSubClass(IO_Component);
		Thing.addSubClass(link);
		Thing.addSubClass(Task);

		OutputLink.addSubClass(Disinheritance);
		OutputLink.addSubClass(OutputParameterLink);

		Disinheritance.addDisjointWith(Inheritance);
		Disinheritance.addDisjointWith(ParameterLink);
		Disinheritance.addDisjointWith(OutputParameterLink);
		Disinheritance.addDisjointWith(ProvisionOutcome_Link);

		Inheritance.addDisjointWith(Disinheritance);
		Inheritance.addDisjointWith(ProvisionOutcome_Link);
		Inheritance.addDisjointWith(OutputParameterLink);
		Inheritance.addDisjointWith(ParameterLink);

		link.addSubClass(InputLink);
		link.addSubClass(OutputLink);
		link.addSubClass(ProvisionOutcome_Link);

		InputLink.addSubClass(Inheritance);
		InputLink.addSubClass(ParameterLink);

		InputLink.addDisjointWith(OutputLink);
		InputLink.addDisjointWith(ProvisionOutcome_Link);

		IO_Component.addSubClass(Provision);
		IO_Component.addSubClass(Outcome);

		Provision.addDisjointWith(Outcome);
		Provision.addSubClass(External_Provision);
		Provision.addSubClass(Internal_Provision);

		Complex_Task.addDisjointWith(Primitive_Task);

		Task.addSubClass(Primitive_Task);
		Task.addSubClass(Complex_Task);

		External_Provision.addDisjointWith(Internal_Provision);

		Goal.addDisjointWith(Task);
		Goal.addDisjointWith(link);
		Goal.addDisjointWith(IO_Component);

		ObjectProperty HasGoal = m.createObjectProperty(NS + "HasGoal");
		HasGoal.addDomain(Task);
		HasGoal.addRange(Goal);

		ObjectProperty HasInput = m.createObjectProperty(NS + "HasInput");
		HasInput.addDomain(Task);
		HasInput.addRange(Provision);

		ObjectProperty HasOutput = m.createObjectProperty(NS + "HasOutput");
		HasOutput.addDomain(Task);
		HasOutput.addRange(Outcome);

		ObjectProperty HasParentTask = m.createObjectProperty(NS
				+ "HasParentTask");
		HasParentTask.addDomain(Task);
		HasParentTask.addRange(Complex_Task);

		ObjectProperty HasSubGoal = m.createObjectProperty(NS + "HasSubGoal");
		HasSubGoal.addDomain(Goal);
		HasSubGoal.addRange(Goal);

		ObjectProperty HasSubTask = m.createObjectProperty(NS + "HasSubTask");
		HasSubTask.addDomain(Complex_Task);
		HasSubTask.addRange(Task);

		ObjectProperty OwnerofOutcome = m.createObjectProperty(NS
				+ "OwnerofOutcome");
		OwnerofOutcome.addDomain(Outcome);
		OwnerofOutcome.addRange(Task);

		ObjectProperty OwnerofProvision = m.createObjectProperty(NS
				+ "OwnerofProvision");
		OwnerofProvision.addDomain(Provision);
		OwnerofProvision.addRange(Task);
		OwnerofProvision.addInverseOf(HasInput);

		ObjectProperty ReceiveFrom = m.createObjectProperty(NS + "ReceiveFrom");

		ObjectProperty ReceiverIO_of_Link = m.createObjectProperty(NS
				+ "ReceiverIO_of_Link");

		ReceiveFrom.addDomain(IO_Component);
		ReceiveFrom.addRange(link);
		ReceiveFrom.addInverseOf(ReceiverIO_of_Link);

		ReceiverIO_of_Link.addDomain(link);
		ReceiverIO_of_Link.addRange(IO_Component);
		ReceiverIO_of_Link.addInverseOf(ReceiveFrom);

		HasInput.addInverseOf(OwnerofProvision);
		HasSubTask.addInverseOf(HasParentTask);
		HasOutput.addInverseOf(OwnerofOutcome);

		// AllValuesFromRestriction avf = m.createAllValuesFromRestriction(NS,
		// ReceiverIO_of_Link, Outcome);

		UnionClass uni = m.createUnionClass(NS + "uni", null);

		// CardinalityQRestriction cr=(CardinalityQRestriction)
		// m.createCardinalityRestriction(NS, ReceiverIO_of_Link, 0);
		ObjectProperty SenderIO_0f_Link = m.createObjectProperty(NS
				+ "SenderIO_0f_Link");
		ObjectProperty SendTo = m.createObjectProperty(NS + "SendTo");

		SenderIO_0f_Link.addDomain(link);
		SenderIO_0f_Link.addRange(IO_Component);
		SenderIO_0f_Link.addInverseOf(SendTo);

		SendTo.addDomain(IO_Component);
		SendTo.addRange(link);
		SendTo.addInverseOf(SenderIO_0f_Link);

		// Disinheritance.addSubClass(avf);
		DatatypeProperty id = m.createDatatypeProperty(NS + "ID");
		id.addDomain(IO_Component);
		id.addRange(XSD.xstring);

		AllValuesFromRestriction avfInh = m.createAllValuesFromRestriction(NS,
				SenderIO_0f_Link, Provision);

		// Inheritance.addSubClass(avfInh);

		DatatypeProperty name = m.createDatatypeProperty(NS + "name");
		// name.addDomain(uni);
		name.addRange(XSD.xstring);

		DatatypeProperty value = m.createDatatypeProperty(NS + "value");
		value.addDomain(Provision);
		value.addRange(XSD.xstring);

		AllValuesFromRestriction avfLink = m.createAllValuesFromRestriction(NS,
				ReceiverIO_of_Link, Internal_Provision);

		InputLink.addSubClass(avfLink);

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

}
