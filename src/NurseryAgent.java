
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.*;
import jade.lang.acl.ACLMessage;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.*;
import jade.domain.FIPAException;
import jade.core.*;
import java.io.IOException;
import java.util.Vector;

import org.json.*;


import javax.swing.*; 
import java.awt.*; 
import java.awt.event.*; 



public class NurseryAgent extends Agent {

	NurseryJFrame jFrame = new NurseryJFrame(this);

	protected void setup() 
	{
		/*

		 // Register the book-selling service in the yellow pages
		 DFAgentDescription dfd = new DFAgentDescription();
		 dfd.setName(getAID());
		 ServiceDescription sd = new ServiceDescription();
		 // sd.setType("");
		 sd.setName("Nursery");
		 dfd.addServices(sd);
		 try {
		 DFService.register(this, dfd);
		 }
		 catch (FIPAException fe) {
		 fe.printStackTrace();
		 }

*/
		jFrame.setVisible(true);
		// add listener
	    jFrame.addMessageHandler(new NurseryJFrame.MessageHandler() {
	        public void handleMessage(ACLMessage message) {
	            System.out.println("Nursery>>"+message.getContent());
				
			
				send(message);
	        }
	    });

	   



		System.out.println("---------------------------------------");
		System.out.println("-----------------Nursery Room Open---------------");
		System.out.println("---------------------------------------");

		FSMBehaviour agent_beh = new FSMBehaviour();

		agent_beh.registerFirstState(new waitMessage(), "waitMessage");
		agent_beh.registerState(new handleMessage(), "getrequest");

		agent_beh.registerDefaultTransition("waitMessage", "getrequest");
		agent_beh.registerTransition("getrequest", "waitMessage",1);

		addBehaviour(agent_beh);
	}

	private class waitMessage extends OneShotBehaviour {

		@Override
		public void action() {

			System.out.println("Nursery:waitMessage ...");
			block();
		}
	}

	private class handleMessage extends OneShotBehaviour {

		@Override
		public void action() {

			
			ACLMessage messageRecu = receive();
			String ontology = messageRecu.getOntology();
			String s = messageRecu.getContent();
			System.out.println("Nursery: Recieve >>"+s);
			JSONObject jo = new JSONObject(s);


			if(ontology.equals("newPatient"))
			{
				jFrame.patients.add(messageRecu.getContent());

			}
			else if(ontology.equals("connexion"))
			{
				String r = (String)jo.get("connexion");
				if(r.equals("ok"))
				{
					jFrame.connexion(true);
				}else
				{
					jFrame.connexion(false);
				}
			}
			else if (ontology.equals("Admin"))
				{

					jFrame.jLabel7.setText("ADMIN~ " +jo.get("message").toString());

				}
			
		}

		public int onEnd() {
			return 1;
		}
	}
}