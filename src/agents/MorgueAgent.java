package agents;

import jade.core.Agent;
import jade.core.behaviours.*;
import jade.lang.acl.ACLMessage;
import java.util.ArrayList;
import frames.MorgueFrame;

public class MorgueAgent extends Agent {
	
	// Corpses in morgue
	ArrayList<String> Id_Corpses = new ArrayList<String>();
	
	public void addVaccinatedPerson(String id_Person) {
		Id_Corpses.add(id_Person);
	}
	
	public void VaccinatedPerson(String id_P) {
		for(String id : Id_Corpses) {
			if(id.equals(id_P)) {
				Id_Corpses.remove(id);
			}
		}
	}
	
	protected void setup() {
		
		MorgueFrame frame = new MorgueFrame();
		
		System.out.println("********************************************************");
		System.out.println("  Morgue Agent Initialisaiton ...  "+this.getAID().getName());
		System.out.println("********************************************************");
		ParallelBehaviour parallelBehaviour = new ParallelBehaviour();
		
		addBehaviour(parallelBehaviour);
		parallelBehaviour.addSubBehaviour(new Behaviour() { 
			public boolean done() {
				return true;
			}
			public void action() {
				System.out.println("Hello I am Morgue Agent");
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
