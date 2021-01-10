package agents;

import jade.core.Agent;
import jade.core.behaviours.*;
import jade.lang.acl.ACLMessage;
import java.util.ArrayList;
import frames.StockFrame;

public class StockAgent extends Agent {
	
	ArrayList<Equipment> equipments = new ArrayList<Equipment>();
	
	public void addEquipment(String id_Equipment, String type) {
		Equipment newEquipm = new Equipment(id_Equipment, type);
		equipments.add(newEquipm);
	}
	
	public void removeEquipment(String id_Equipment) {
		for(Equipment equipment : equipments) {
			if(equipment.getId_Equipment().equals(id_Equipment)) {
				equipments.remove(equipment);
			}
		}
	}
	
	protected void setup() {
		
		StockFrame frame = new StockFrame();
		
		System.out.println("********************************************************");
		System.out.println("  StockAgent Initialisaiton ...  "+this.getAID().getName());
		System.out.println("********************************************************");
		ParallelBehaviour parallelBehaviour = new ParallelBehaviour();
		
		addBehaviour(parallelBehaviour);
		parallelBehaviour.addSubBehaviour(new Behaviour() { 
			public boolean done() {
				return true;
			}
			public void action() {
				System.out.println("Hello I am Stock Agent");
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
