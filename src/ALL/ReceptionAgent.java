
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


public class ReceptionAgent extends Agent {

	ReceptionJFrame jFrame = new ReceptionJFrame(this);
	public static int counter = 0;
	private AID[] aids;
	
	protected void setup() {
	
		jFrame.setVisible(true);
		// add listener
		// jFrame.addMessageHandler(new ReceptionJFrame.MessageHandler() {
		//     public void handleMessage(ACLMessage message){
		// 		System.out.println("RECEPTION>>"+message.getContent());
				
			
		// 		send(message);



		//     }
		// });
		
		System.out.println("-----------------------------------------");
		System.out.println("----------------Reception----------------");
		System.out.println("-----------------------------------------");
		FSMBehaviour agent_beh = new FSMBehaviour();
		
		agent_beh.registerFirstState(new waitMessage(), "waitMessage");
		agent_beh.registerState(new handleMessage(), "handleMessage");
		
		agent_beh.registerDefaultTransition("waitMessage", "handleMessage");
		agent_beh.registerTransition("handleMessage", "waitMessage",1);
		
		addBehaviour(agent_beh);
		
		
		ParallelBehaviour parallelBehaviour = new ParallelBehaviour();
		addBehaviour(parallelBehaviour);
		
		parallelBehaviour.addSubBehaviour(new CyclicBehaviour() { 
			public void action() {


				if(jFrame.jButton1.getModel().isPressed()) {
					jFrame.jButton1.getModel().setPressed(false);

					System.out.println("Reception: Send Person info to Database ");
					jFrame.toDataBase();
					
					String item = (String) jFrame.jComboBox4.getSelectedItem();
					
					if(item.equals("Blood Donation")) {
						ACLMessage msg = new ACLMessage( ACLMessage.INFORM );
					    msg.addReceiver(new AID("BloodDonation", AID.ISLOCALNAME));
					    Vector row = new Vector();
			            row.add("ID_Person" + counter); 
			            counter++;
			            try {
			            	msg.setContentObject(row);
			            }catch(IOException e){}
					    send(msg);
					}
					else if(item.equals("Consulting")){
						
						JSONObject jo = new JSONObject();

						ACLMessage msg = new ACLMessage( ACLMessage.INFORM );
					    msg.addReceiver(new AID("consulting", AID.ISLOCALNAME));
					    
					    msg.setOntology("reception");
			            
			            jo.put("idPatient",jFrame.jTextField2.getText());
			            jo.put("name",jFrame.jTextField1.getText());

			            msg.setContent(jo.toString());
			            
					    send(msg);

					}
					
					else if(item.equals("Visitor")){

						JSONObject jo = new JSONObject();
            

				        DFAgentDescription template = new DFAgentDescription();
				        ServiceDescription sd = new ServiceDescription();

				        sd.setName("Ward");

				        template.addServices(sd);
				        try {
				        DFAgentDescription[] result = DFService.search(myAgent, template);
				        aids = new AID[result.length];
				        for (int i = 0; i < result.length; ++i) {
				        aids[i] = result[i].getName();


				        ACLMessage msg = new ACLMessage( ACLMessage.INFORM );
				        msg.addReceiver(aids[i]);
				        msg.setOntology("searching");

				        jo.put("idPatient",jFrame.jTextField9.getText());
				        jo.put("idVisitor",jFrame.jTextField2.getText());


				        msg.setContent(jo.toString());
				        send(msg);

				        }
				        }
				        catch (FIPAException fe) {
				        fe.printStackTrace();
				        }


				        }
       
				else if(item.equals("Nursery")){

						JSONObject jo = new JSONObject();
            	
				        ACLMessage msg = new ACLMessage( ACLMessage.INFORM );
				        msg.addReceiver(new AID("nursery", AID.ISLOCALNAME));
				        msg.setOntology("newPatient");

				        jo.put("idPatient",jFrame.jTextField9.getText());
				        jo.put("name",jFrame.jTextField1.getText());

				        msg.setContent(jo.toString());
				        send(msg);

				        // DFAgentDescription template = new DFAgentDescription();
				        // ServiceDescription sd = new ServiceDescription();

				        // sd.setName("Nursery");

				        // template.addServices(sd);
				        // try {
				        // DFAgentDescription[] result = DFService.search(myAgent, template);
				        // aids = new AID[result.length];
				        // for (int i = 0; i < result.length; ++i) {
				        // aids[i] = result[i].getName();


				        // ACLMessage msg = new ACLMessage( ACLMessage.INFORM );
				        // msg.addReceiver(aids[i]);
				        // msg.setOntology("newPatient");

				        // jo.put("idPatient",jFrame.jTextField9.getText());
				        // jo.put("name",jFrame.jTextField1.getText());

				        // msg.setContent(jo.toString());
				        // send(msg);

				        // }
				        // }
				        // catch (FIPAException fe) {
				        // fe.printStackTrace();
				        // }


				        }
        

			
					
				}

			}
		});
	}
	
	
	
	
	
	
 	private class waitMessage extends OneShotBehaviour {

		@Override
		public void action() {
			System.out.println("Reception: Waiting...");
			block();
		}
	}

	private class handleMessage extends OneShotBehaviour {
		@Override
		public void action() {

			ACLMessage messageRecu = receive();	
			try{

			String s = messageRecu.getContent();
			System.out.println("Reception: Recieve "+s);
			JSONObject jo = new JSONObject(s);
			String ontology = messageRecu.getOntology();
			if(ontology.equals("connexion"))
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

					jFrame.jLabel11.setText("ADMIN~ " +jo.get("message").toString());

				}
			else if (ontology.equals("consulting"))
				{

					jFrame.jLabel11.setText("Laboratory~ Ordre " +jo.get("ordre").toString());

				}
			else if (ontology.equals("searching"))
				{

					jFrame.jLabel11.setText("Ward~ Room " +jo.get("room").toString());

				}
			else if (ontology.equals("bloodDonation"))
				{

					jFrame.jLabel11.setText("BloodDonation~ Ordre " +jo.get("ordre").toString());

				}
			// jFrame.res.setText(messageRecu.getContent());
			} catch(Exception e)
			{

			}

		}
		public int onEnd() {
			return 1;
		}
	}
	
	
}
