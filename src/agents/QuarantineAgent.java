package agents;

import jade.core.Agent;
import jade.core.behaviours.*;
import jade.lang.acl.ACLMessage;
import frames.QuarantineFrame;

public class QuarantineAgent extends Agent{
	
	protected void setup() {
		
		QuarantineFrame frame = new QuarantineFrame();
		
		System.out.println("********************************************************");
		System.out.println("  QuarantineAgent Initialisaiton ...  "+this.getAID().getName());
		System.out.println("********************************************************");
		ParallelBehaviour parallelBehaviour = new ParallelBehaviour();
		
		addBehaviour(parallelBehaviour);
		parallelBehaviour.addSubBehaviour(new Behaviour() { 
			public boolean done() {
				return true;
			}
			public void action() {
				System.out.println("Hello I am Quarantine Agent");
			}
		});
	}
	
	protected void beforeMove() {
		System.out.println("           Befor migration          ");
	}
	
	protected void afterMove() {
		System.out.println("           After migration          ");
	}
	
	protected void takeDown() {
		System.out.println("         I am going to die          ");
	}
}
