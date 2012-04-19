package userinterface;

import java.awt.Color;
import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import rmi.RemoteMessageServer;
import tiryaki.planner.MMDepo_Agent;
import agent.AgentIdentifier;
import agent.URLSequence;
import agent.Word;

import fipa.ACLMessage;
import fipa.CommunicativeActLibrary;
import fipa.Content;
import fipa.Envelope;
import fipa.FIPARDFContent0;
import fipa.FipaMessage;

public class FipaMesaj extends JFrame implements ActionListener{

	private JLabel lblSender;
	private JLabel lblReceivers;
	private JLabel lblReplyTo;
	private JLabel lblCommunicativeAct;
	private JLabel lblContent;
	private JLabel lblLanguage;
	private JLabel lblEncoding;
	private JLabel lblOntology;
	private JLabel lblProtocol;
	private JLabel lblConversationID;
	private JLabel lblInReplyTo;
	private JLabel lblReplyWith;
	private JLabel lblReplyBy;
	private JLabel lblUserProperties;
	
	
	private JLabel lblFrom;
	private JLabel lblTo;
	private JLabel lblAclRepresentation;
	private JLabel lblPayloadLenght;
	private JLabel lblPayloadEncoding;
	private JLabel lblDate;
	private JLabel lblIntendedReceiver;
	private JLabel lblReceived;
	private JLabel lblTransportBehaviour;
	
	private JTextField txtFrom;
	private JTextField txtTo;
	private JTextField txtAclRepresentation;
	private JTextField txtPayloadLenght;
	private JTextField txtPayloadEncoding;
	private JTextField txtDate;
	private JTextField txtIntendedReceiver;
	private JTextField txtReceived;
	private JComboBox cmbTransportBehaviour;
	
	private JTextField txtSender;
	private JTextField txtReceivers;
	private JTextField txtReplyTo;
	private JComboBox cmbCommunicativeAct;
	private JTextArea txtContent;
	private JTextField txtLanguage;
	private JTextField txtEncoding;
	private JTextField txtOntology;
	private JTextField txtProtocol;
	private JTextField txtConversationID;
	private JTextField txtInReplyTo;
	private JTextField txtReplyWith;
	private JTextField txtReplyBy;
	private JTextField txtUserProperties;
	private JButton btnGonder;
	private JButton btnAction;
	private JButton btnReceiver;
	
	private String performs[] = { "achieve", "advertise", "ask-all", "ask-if",
			"ask-one", "broadcast", "broker-all", "broker-one", "delete-all",
			"delete-one", "deny", "discard", "error", "eos", "forward",
			"insert", "next", "ready", "recommend-all", "recommend-one",
			"recruit-all", "recruit-one", "register", "rest", "sorry",
			"subscribe", "standby", "stream-all", "tell", "transport-address",
			"uninsert", "untell", "undelete", "unachieve", "unadvertise",
			"unregister", "unsubscribe", "accept-proposal", "agree", "cancel",
			"cfp", "confirm", "disconfirm", "failure", "inform", "inform-if",
			"inform-ref", "not-understood", "propagate", "propose", "proxy",
			"query-if", "query-ref", "refuse", "reject-proposal", "request",
			"request-when", "request-whenever", "subscribe", "other",
			"query ref" };
	
	ArrayList receiver=new ArrayList();
	public ArrayList actions=new ArrayList();
	
	
	
	public FipaMesaj()
	{
		setResizable(false);
		setSize(530,680);
		setLocation(250,0);
		setTitle("Fipa Mesaj");
		
		 JTabbedPane jtp = new JTabbedPane();
		 Container con=this.getContentPane();
	     JPanel jp1 = new JPanel();
	     JPanel jp2 = new JPanel();
	     jp1.setLayout(null);
	     jp2.setLayout(null);
	     jtp.setBounds(10,10,490,580);
	     setLayout(null);
	     
	     con.add(jtp);
	    
	     btnGonder=new JButton("Gönder");
	     btnGonder.setBounds(200,600,100,25);
	     
	     btnAction=new JButton("Action Ekle");
	     btnReceiver=new JButton("Ekle");
	     con.add(btnGonder);
	     getContentPane().add(btnGonder);
	     
		lblSender=new JLabel("Sender");
		lblReceivers=new JLabel("Receivers");
		lblReplyTo=new JLabel("Reply To");
		lblCommunicativeAct=new JLabel("Communicative Act");
		lblContent=new JLabel("Content");
		lblLanguage=new JLabel("Language");
		lblEncoding=new JLabel("Encoding");
		lblOntology=new JLabel("Ontology");
		lblProtocol=new JLabel("Protocol");
		lblConversationID=new JLabel("Conversation ID");
		lblInReplyTo=new JLabel("In Reply To");
		lblReplyWith=new JLabel("Reply With");
		lblReplyBy=new JLabel("Reply By");
		lblUserProperties=new JLabel("User Properties");
		
		
		lblFrom=new JLabel("From");
		lblTo=new JLabel("To");
		lblAclRepresentation=new JLabel("Acl Representation");
		lblPayloadLenght=new JLabel("Payload Lenght");
		lblPayloadEncoding=new JLabel("Payload Encoding");
		lblDate=new JLabel("Date");
		lblIntendedReceiver=new JLabel("Intended Receiver");
		lblReceived=new JLabel("Received");
		lblTransportBehaviour=new JLabel("Transport Behaviour");
		
		
		txtFrom=new JTextField();
		txtTo=new JTextField();
		txtAclRepresentation=new JTextField();
		txtPayloadLenght=new JTextField();
		txtPayloadEncoding=new JTextField();
		txtDate=new JTextField();
		txtIntendedReceiver=new JTextField();
		txtReceived=new JTextField();
		cmbTransportBehaviour=new JComboBox();
		cmbTransportBehaviour.addItem("rmi");	
		cmbTransportBehaviour.addItem("iiop");	
		
		txtSender=new JTextField("MMDepo_Agent@aegeants.com");
		txtReceivers=new JTextField();
		txtReplyTo=new JTextField();
		cmbCommunicativeAct=new JComboBox();
		for (int i = 0; i < performs.length; i++) {
			cmbCommunicativeAct.addItem(performs[i]);	
		}
		cmbCommunicativeAct.setSelectedItem("request");
		txtContent=new JTextArea();
		txtLanguage=new JTextField("fipa-sl0");
		txtEncoding=new JTextField();
		txtOntology=new JTextField("multimedia");
		txtProtocol=new JTextField();
		txtConversationID=new JTextField();
		txtInReplyTo=new JTextField();
		txtReplyWith=new JTextField();
		txtReplyBy=new JTextField();
		txtUserProperties=new JTextField();
		
		lblSender.setBounds(30,30,120,25);
		lblReceivers.setBounds(30,60,120,25);
		lblReplyTo.setBounds(30,90,120,25);
		lblCommunicativeAct.setBounds(30,120,120,25);
		lblContent.setBounds(30,145,120,25);
		lblLanguage.setBounds(30,280,120,25);
		lblEncoding.setBounds(30,310,120,25);
		lblOntology.setBounds(30,340,120,25);
		lblProtocol.setBounds(30,370,120,25);
		lblConversationID.setBounds(30,400,120,25);
		lblInReplyTo.setBounds(30,430,120,25);
		lblReplyWith.setBounds(30,460,120,25);
		lblReplyBy.setBounds(30,490,120,25);
		
		lblFrom.setBounds(30,30,120,25);
		lblTo.setBounds(30,60,120,25);
		lblAclRepresentation.setBounds(30,90,120,25);
		lblPayloadEncoding.setBounds(30,120,120,25);
		lblPayloadLenght.setBounds(30,150,120,25);
		lblDate.setBounds(30,180,120,25);
		lblIntendedReceiver.setBounds(30,210,120,25);
		lblReceived.setBounds(30,240,120,25);
		lblTransportBehaviour.setBounds(30,270,120,25);

		txtFrom.setBounds(170,30,200,25);
		txtTo.setBounds(170,60,200,25);
		txtAclRepresentation.setBounds(170,90,200,25);
		txtPayloadEncoding.setBounds(170,120,200,25);
		txtPayloadLenght.setBounds(170,150,200,25);
		txtDate.setBounds(170,180,200,25);
		txtIntendedReceiver.setBounds(170,210,200,25);
		txtReceived.setBounds(170,240,200,25);
		cmbTransportBehaviour.setBounds(170,270,200,25);
		
		txtSender.setBounds(170,30,200,25);
		txtReceivers.setBounds(170,60,200,25);
		btnReceiver.setBounds(380,60,100,25);
		txtReplyTo.setBounds(170,90,200,25);
		cmbCommunicativeAct.setBounds(170,120,200,25);
		txtContent.setBounds(30,175,340,100);
		txtContent.setAutoscrolls(true);
		txtContent.setLineWrap(true);
		btnAction.setBounds(380,175,100,30);
		txtLanguage.setBounds(170,280,200,25);
		txtEncoding.setBounds(170,310,200,25);
		txtOntology.setBounds(170,340,200,25);
		txtProtocol.setBounds(170,370,200,25);
		txtConversationID.setBounds(170,400,200,25);
		txtInReplyTo.setBounds(170,430,200,25);
		txtReplyWith.setBounds(170,460,200,25);
		txtReplyBy.setBounds(170,490,200,25);
		
		
		
		jp1.add(lblSender);
		jp1.add(lblReceivers);
		jp1.add(lblReplyTo);
		jp1.add(lblCommunicativeAct);
		jp1.add(lblContent);
		jp1.add(lblLanguage);
		jp1.add(lblEncoding);
		jp1.add(lblOntology);
		jp1.add(lblProtocol);
		jp1.add(lblConversationID);
		jp1.add(lblInReplyTo);
		jp1.add(lblReplyWith);
		jp1.add(lblReplyBy);
		
		jp1.add(txtSender);
		jp1.add(txtReceivers);
		jp1.add(btnReceiver);
		jp1.add(txtReplyTo);
		jp1.add(cmbCommunicativeAct);
		jp1.add(txtContent);
		jp1.add(btnAction);
		jp1.add(txtLanguage);
		jp1.add(txtEncoding);
		jp1.add(txtOntology);
		jp1.add(txtProtocol);
		jp1.add(txtConversationID);
		jp1.add(txtInReplyTo);
		jp1.add(txtReplyWith);
		jp1.add(txtReplyBy);
		
		jp2.add(lblFrom);
		jp2.add(lblTo);
		jp2.add(lblAclRepresentation);
		jp2.add(lblPayloadLenght);
		jp2.add(lblPayloadEncoding);
		jp2.add(lblDate);
		jp2.add(lblIntendedReceiver);
		jp2.add(lblReceived);
		jp2.add(lblTransportBehaviour);
		
		jp2.add(txtFrom);
		jp2.add(txtTo);
		jp2.add(txtAclRepresentation);
		jp2.add(txtPayloadLenght);
		jp2.add(txtPayloadEncoding);
		jp2.add(txtDate);
		jp2.add(txtIntendedReceiver);
		jp2.add(txtReceived);
		jp2.add(cmbTransportBehaviour);
		

		
		
		jtp.addTab("ACL Message", jp1);
	    jtp.addTab("Envelope", jp2);
	    
	    btnAction.addActionListener(this);
	    btnGonder.addActionListener(this);
	    btnReceiver.addActionListener(this);
	}
	

public static void main(String[] args) {
	try {
	    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
	        if ("Nimbus".equals(info.getName())) {
	            UIManager.setLookAndFeel(info.getClassName());
	            
	            break;
	        }
	    }
	} catch (Exception e) {}
	


	FipaMesaj fm=new FipaMesaj();
	fm.setVisible(true);
}


@Override
public void actionPerformed(ActionEvent e) {
	if(e.getSource()==btnGonder)
	{
		ACLMessage message = new ACLMessage();
		Content con=new Content();
		for (int i = 0; i < actions.size(); i++) {
			FIPARDFContent0 rdf=new FIPARDFContent0();
			rdf=(FIPARDFContent0)actions.get(i);
			JOptionPane.showMessageDialog(null,rdf.getAct());
		
			con.addList(rdf);
		}
	 	AgentIdentifier aidSender = new AgentIdentifier();
		aidSender.setName(new Word(txtSender.getText()));
		message.setSender(aidSender);
		
		
		URLSequence addrSender = new URLSequence();
		addrSender.add("rmi://192.168.2.21:1099/Agent01");
		aidSender.setAddresses(addrSender);
		MMDepo_Agent mmDepo_Agent=new MMDepo_Agent(aidSender, "rmi://192.168.2.21:1099/Agent01");
		new RemoteMessageServer(mmDepo_Agent);
		AgentIdentifier receiver = new AgentIdentifier();
		receiver.setName(new Word(txtReceivers.getName()));
		message.setPerformative(cmbCommunicativeAct.getSelectedItem().toString());
		message.setOntology(txtOntology.getText());
		message.setLanguage(txtLanguage.getText());
		message.setConversation_id(txtConversationID.getText());
		message.addReceiver(receiver);
		
		Envelope env=new Envelope();
		env.setFrom(aidSender);
		env.addTo(receiver);
		FipaMessage fm=new FipaMessage(env,message);
		mmDepo_Agent.message(fm);
	}
	
	if(e.getSource()==btnAction)
	{
		Action ac=new Action(this);
		ac.setVisible(true);
	}
	
	if(e.getSource()==btnReceiver)
	{
		receiver.add(txtReceivers.getText());
	}
	
}


}
