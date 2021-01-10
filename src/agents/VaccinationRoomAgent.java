package agents;

import jade.core.Agent;

import jade.core.behaviours.*;
import jade.lang.acl.ACLMessage;
import java.util.ArrayList;
import frames.VaccinationRoomFrame;
public class VaccinationRoomAgent extends Agent {
	
	ArrayList<String> vaccinatedPerson = new ArrayList<String>();
	
	public void addVaccinatedPerson(String id_Person) {
		vaccinatedPerson.add(id_Person);
	}
	
	public void VaccinatedPerson(String id_P) {
		for(String id : vaccinatedPerson) {
			if(id.equals(id_P)) {
				vaccinatedPerson.remove(id);
			}
		}
	}
	
	protected void setup() {
		
		VaccinationRoomFrame frame = new VaccinationRoomFrame();
		System.out.println("********************************************************");
		System.out.println("  Vaccination Room Agent Initialisaiton ...  "+this.getAID().getName());
		System.out.println("********************************************************");
		ParallelBehaviour parallelBehaviour = new ParallelBehaviour();
		
		addBehaviour(parallelBehaviour);
		
		parallelBehaviour.addSubBehaviour(new Behaviour() { 
			public boolean done() {
				return true;
			}
			public void action() {
				System.out.println("Hello I am Vaccination Room Agent");
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
