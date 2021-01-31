package agents;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.*;
import jade.lang.acl.ACLMessage;
import frames.ReceptionJFrame;
import java.io.IOException;
import java.util.Vector;


public class ReceptionAgent extends Agent {

	ReceptionJFrame jFrame = new ReceptionJFrame();
	public static int counter = 0;
	protected void setup() {
	
		jFrame.setVisible(true);
		// add listener
		jFrame.addMessageHandler(new ReceptionJFrame.MessageHandler() {
		    public void handleMessage(String s){
				System.out.println("RECEPTION"+s);
				
				ACLMessage message = new ACLMessage(ACLMessage.INFORM);
				message.addReceiver(new AID("server", AID.ISLOCALNAME));
				System.out.println("Reception>>Sending to Server ...");
				message.setContent(s);
				send(message);
		    }
		});
		
		System.out.println("-----------------------------------------");
		System.out.println("----------------Reception----------------");
		System.out.println("-----------------------------------------");
		FSMBehaviour agent_beh = new FSMBehaviour();
		
		agent_beh.registerFirstState(new waitMessage(), "waitMessage");
		agent_beh.registerState(new handleMessage(), "handleMessage");
		
		agent_beh.registerDefaultTransition("waitMessage", "handleMessage");
		agent_beh.registerTransition("handleMessage", "waitMessage",1);
		
		addBehaviour(agent_beh);
		
		// Imrane part
		ParallelBehaviour parallelBehaviour = new ParallelBehaviour();
		addBehaviour(parallelBehaviour);
		
		parallelBehaviour.addSubBehaviour(new CyclicBehaviour() { 
			public void action() {
				
				if(jFrame.jButton1.getModel().isPressed()) {
					jFrame.jButton1.getModel().setPressed(false);
					System.out.println("Reception: start action ");
					
					String item = (String) jFrame.jComboBox4.getSelectedItem();
					
					if(item.equals("Blood Donation")) {
						ACLMessage msg = new ACLMessage( ACLMessage.INFORM );
					    msg.addReceiver(new AID("BloodDonation", AID.ISLOCALNAME));
					    Vector<String> row = new Vector<String>();
			            row.add("ID_Person" + counter); 
			            counter++;
			            try {
			            	msg.setContentObject(row);
			            }catch(IOException e){}
					    send(msg);
					}
					
					else if(item.equals("Consulting Coronavirus")){
						ACLMessage msg = new ACLMessage( ACLMessage.INFORM );
					    msg.addReceiver(new AID("CoronaConsultingAgent", AID.ISLOCALNAME));
					    Vector<String> row = new Vector<String>();
			            row.add("ID_Person" + counter); 
			            counter++;
			            try {
			            	msg.setContentObject(row);
			            }catch(IOException e){}
					    send(msg);
					}
					
					else if(item.equals("Consulting")){
						//TODO 
					}
					
					else if(item.equals("Visitor")){
						//TODO 
					}
					
				}

			}
		});
		// End Imrane part
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

			System.out.println("Reception: start action ");
			ACLMessage messageRecu = receive();
			System.out.println(messageRecu.getContent());
			// jFrame.res.setText(messageRecu.getContent());

		}
		public int onEnd() {
			return 1;
		}
	}
	
	
}
