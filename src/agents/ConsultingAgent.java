package agents;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.FSMBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

import java.util.*;
import org.json.*;



public class ConsultingAgent extends Agent {
	/*


	@Name Laboratory
	@Type  Test1/Test2/Test3/Test4
	

	# INPUT 
	@Ontology    consulting
	@keysJson    idPatient
	
	# OUPTUT
	@Ontology    consulting
	@keysJson  	 ordre(-1)

	*/
	
	String currentPatient ; 

	ConsultingJFrame jFrame = new ConsultingJFrame(this);

	protected void setup() 
	{

		jFrame.setVisible(true);
		// add listener
	    jFrame.addMessageHandler(new ConsultingJFrame.MessageHandler() {
	        public void handleMessage(ACLMessage message) {
	            System.out.println("Consulting>>"+message.getContent());
				
			
				send(message);
	        }
	    });

	   

		jFrame.setVisible(true);

		System.out.println("---------------------------------------");
		System.out.println("-----------------Consulting Room Open---------------");
		System.out.println("---------------------------------------");



		FSMBehaviour agent_beh = new FSMBehaviour();

		agent_beh.registerFirstState(new waitMessage(), "waitMessage");
		agent_beh.registerState(new handleMessage(), "handleMessage");



		agent_beh.registerDefaultTransition("waitMessage", "handleMessage");

		agent_beh.registerTransition("handleMessage", "waitMessage",1);





		addBehaviour(agent_beh);
	}

	private class waitMessage extends OneShotBehaviour {

		@Override
		public void action() {

			System.out.println("Consulting>> waitMessage ...");
			block();
		}
	}

	private class handleMessage extends OneShotBehaviour {

		@Override
		public void action() {

		
			ACLMessage messageRecu = receive();
			String ontology = messageRecu.getOntology();
			String s = messageRecu.getContent();
			System.out.println("Consulting>> Recieve >>"+messageRecu.getContent());

			JSONObject jo = new JSONObject(s);


			if(ontology.equals("reception"))
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

					jFrame.jLabel17.setText("ADMIN~ " +jo.get("message").toString());

				}
			
		}

		public int onEnd() {
			return 1;
		}
	}

}