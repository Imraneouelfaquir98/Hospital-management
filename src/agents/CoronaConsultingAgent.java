package agents;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.*;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import frames.CoronaConsultingFrame;

public class CoronaConsultingAgent extends Agent{

	private static final long serialVersionUID = 1L;
	public CoronaConsultingFrame consultFrame = new CoronaConsultingFrame();
	public int totalConsulting   = 0;
	public int positiveTest      = 0;
	public int negativeTest      = 0;
	public int quarantined       = 0;
	public int notYetQuarantined = 0;
	
	public static int counter = 0;
	public DefaultTableModel model1, model2;
	
	protected void setup() {
		
		model1 = (DefaultTableModel) consultFrame.ConsultingCovidTable.getModel();
		model2 = (DefaultTableModel) consultFrame.QuarantedTable.getModel();
		
		consultFrame.setVisible(true);
		
		ParallelBehaviour parallelBehaviour = new ParallelBehaviour(); 
		addBehaviour(parallelBehaviour);
		
		parallelBehaviour.addSubBehaviour(new CyclicBehaviour() { 
			public void action() {
				ACLMessage msg = receive();
				if( msg != null) {
					// model = (DefaultTableModel) consultFrame.ConsultingCovidTable.getModel();
					if(msg.getSender().getName().equals("Reception Agent@192.168.43.96:1099/JADE")){
						Vector<String> row = new Vector<String>();			
						try {
							row = (Vector<String>)msg.getContentObject();
						}catch(UnreadableException e){}	
						System.out.println(row.toString());
						System.out.println("Sender : "+msg.getSender().getName());
						row.add(new SimpleDateFormat("dd/MM/yy").format(new Date())+" "+ new SimpleDateFormat("h:mm a").format(new Date()));								
						
						if(Math.random()<=0.6) {
				    		row.add("Positive");
				    		positiveTest++;
				    		notYetQuarantined++;
				    		consultFrame.PositiveTestLabel.setText(Integer.toString(positiveTest));
				    		consultFrame.nbrNotQuarantinedLabel.setText(Integer.toString(notYetQuarantined));
				    	}
				    	else {
				    		row.add("Negative");
				    		negativeTest++;
				    		consultFrame.NegativeTestLabel.setText(Integer.toString(negativeTest));
				    	}
				    	totalConsulting++;
				    	model1.addRow(row);
				    	
				    	consultFrame.TotalConsultingLabel.setText(Integer.toString(totalConsulting));
					}
					else if(msg.getSender().getName().equals("QuarantineAgent@192.168.43.96:1099/JADE")) {
						if(msg.getContent().equals("Yes, there is an empty place.")) {
							if(notYetQuarantined>0) {
								notYetQuarantined--;
	
								for(int i=0; i < model1.getRowCount(); i++) {
									if(model1.getValueAt(i,2).equals("Positive")) {
										ACLMessage reply = msg.createReply();
										reply.setContent((String)model1.getValueAt(i,0));

										Vector<String> row = new Vector<String>();
										row.add((String)model1.getValueAt(i,0));
										row.add(new SimpleDateFormat("dd/MM/yy").format(new Date())+" "+ new SimpleDateFormat("h:mm a").format(new Date()));
										
										model2.addRow(row);
										model1.removeRow(0);
										send(reply);
										break;
									}
								}
								
								
							}
						}
					}
				}
				else
					block();
			}
		});
		
		parallelBehaviour.addSubBehaviour(new TickerBehaviour(this,1000) { 
			public void onTick() {
				if(notYetQuarantined>0) {
					ACLMessage msg = new ACLMessage( ACLMessage.INFORM );
				    msg.addReceiver(new AID("QuarantineAgent", AID.ISLOCALNAME));
				    msg.setContent("Request a place for quarantine");
				    send(msg);
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
		System.out.println("           I am going to die        ");
	}
}
