package agents;

import jade.core.AID;
import jade.core.Agent;

import jade.core.behaviours.*;
import jade.lang.acl.ACLMessage;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import frames.VaccinationRoomFrame;

public class VaccinationRoomAgent extends Agent {
	
	public VaccinationRoomFrame frame;
	public int totalVaccinated =  0;
	public int WaitingVaccin   =  0;
	public int vaccinAvailable = 20;
	
	public DefaultTableModel model1, model2;

	protected void setup() {
		
		frame = new VaccinationRoomFrame();
		frame.VaccinAvailable.setText(Integer.toString(vaccinAvailable));
		frame.setVisible(true);
		
		ParallelBehaviour parallelBehaviour = new ParallelBehaviour();
		addBehaviour(parallelBehaviour);
		
		model1 = (DefaultTableModel) frame.WaitingTable.getModel();
		model2 = (DefaultTableModel) frame.VaccinatedTabel.getModel();
		
		parallelBehaviour.addSubBehaviour(new CyclicBehaviour() { 
			public void action() {
				vaccinAvailable = Integer.parseInt(frame.VaccinAvailable.getText());
				ACLMessage msg = receive();
				if( msg != null) {
					if(msg.getSender().getName().equals("QuarantineAgent@192.168.43.96:1099/JADE")) {
						
						Vector<String> row = new Vector<String>();
						row.add(msg.getContent());
						row.add(new SimpleDateFormat("dd/MM/yy").format(Calendar.getInstance().getTime()));
						model1.addRow(row);
						
						WaitingVaccin++;
						vaccinAvailable--;
						frame.WaitingVaccin.setText(Integer.toString(WaitingVaccin));
						frame.VaccinAvailable.setText(Integer.toString(vaccinAvailable));
						
					}
				
					if(frame.AddVaccinButton.getModel().isPressed()) {
						frame.AddVaccinButton.getModel().setPressed(false);
						vaccinAvailable += Integer.parseInt(frame.NumberVaccinAdd.getText());
						frame.NumberVaccinAdd.setText("");
						frame.VaccinAvailable.setText(Integer.toString(vaccinAvailable));
						System.out.println(vaccinAvailable);
					}
				}
				else
					block();
				
			}
		});
		
		parallelBehaviour.addSubBehaviour(new TickerBehaviour(this,1000) { 
			public void onTick() {
				if(WaitingVaccin > 0 && vaccinAvailable > 0) {
					ACLMessage msg = new ACLMessage( ACLMessage.INFORM );
				    msg.addReceiver(new AID("QuarantineAgent", AID.ISLOCALNAME));
				    msg.setContent((String)model1.getValueAt(0, 0));
				    Vector<String> row = new Vector<String>();
					row.add((String)model1.getValueAt(0, 0));
					row.add(new SimpleDateFormat("dd/MM/yy").format(Calendar.getInstance().getTime()));
					model2.addRow(row);
				    model1.removeRow(0);
				    send(msg);
				    WaitingVaccin--;
				    totalVaccinated++;
				    frame.WaitingVaccin.setText(Integer.toString(WaitingVaccin));
					frame.TotalVaccinatedLabel.setText(Integer.toString(totalVaccinated));
				}
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
