package agents;

import jade.core.Agent;
import jade.core.behaviours.*;
import jade.lang.acl.ACLMessage;
import frames.BloodDonationFrame;

public class BloodDonationAgent extends Agent{
	protected void setup() {
		BloodDonationFrame frame = new BloodDonationFrame();
		
		System.out.println("********************************************************");
		System.out.println("  Blood Donation Agent Initialisaiton ...  "+this.getAID().getName());
		System.out.println("********************************************************");
		ParallelBehaviour parallelBehaviour = new ParallelBehaviour();
		
		addBehaviour(parallelBehaviour);
		parallelBehaviour.addSubBehaviour(new Behaviour() { 
			public boolean done() {
				return true;
			}
			public void action() {
				System.out.println("Hello I am Blood Donation Agent");
			}
		});
	}
	
	protected void beforeMove() {
		System.out.println("           Befor migration          ");
	}
	
	protected void afterMove()  {
		System.out.println("           After migration          ");
	}
	
	protected void takeDown()   {
		System.out.println("         I am going to die          ");
	}
}
