
import jade.core.Agent;
import jade.core.behaviours.*;
import jade.lang.acl.ACLMessage;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
// import frames.QuarantineFrame;

public class QuarantineAgent extends Agent{

	private static final long serialVersionUID = 1L;
	public QuarantineFrame quarantineFrame;
	public int TotalQuarantined      = 0;
	public int RecoveredCases        = 0;
	public int VaccinedCases         = 0;
	public static int CurrentQuarantined    = 0;
	public static int EmptyQuarantinePlaces = 100;
	
	
	
	
	protected void setup() {
		
		quarantineFrame = new QuarantineFrame();
		quarantineFrame.NumberEmptyPlacesLabel.setText(Integer.toString(EmptyQuarantinePlaces));
		quarantineFrame.setVisible(true);
		
		System.out.println("********************************************************");
		System.out.println("  QuarantineAgent Initialisaiton ...  "+this.getAID().getName());
		System.out.println("********************************************************");
		
		ParallelBehaviour parallelBehaviour = new ParallelBehaviour();
		addBehaviour(parallelBehaviour);
		

		parallelBehaviour.addSubBehaviour(new CyclicBehaviour() { 

			private static final long serialVersionUID = 1L;

			public void action() {
//				if(quarantineFrame.SubmitDeathButton.getModel().isPressed()) {
//					quarantineFrame.SubmitDeathButton.getModel().setPressed(false);
//				}
				
				ACLMessage msg = receive();
				if( msg != null) {
					if(msg.getContent().equals("Request a place for quarantine")) {
						ACLMessage reply = msg.createReply();
						if(EmptyQuarantinePlaces > 0)
							reply.setContent("Yes, there is an empty place.");
						else
							reply.setContent("No, there is no empty place.");
						send(reply);
					}
					else {
						
						EmptyQuarantinePlaces--;
						TotalQuarantined++;
						CurrentQuarantined++;
						
						quarantineFrame.TotalQuarantinedLabel.setText(Integer.toString(TotalQuarantined));
						quarantineFrame.CurrentQuarantinedLabel.setText(Integer.toString(CurrentQuarantined));
						quarantineFrame.NumberEmptyPlacesLabel.setText(Integer.toString(EmptyQuarantinePlaces));
						
						Vector<String> row = new Vector<String>();
						row.add(msg.getContent());
						
						Calendar myCal = Calendar.getInstance();
						row.add(new SimpleDateFormat("dd/MM/yy").format(myCal.getTime()));
						myCal.add(Calendar.DAY_OF_MONTH, 15);
						row.add(new SimpleDateFormat("dd/MM/yy").format(myCal.getTime()));
						row.add("Not Yet");
						
						DefaultTableModel model = (DefaultTableModel) quarantineFrame.QuarantinedTable.getModel();
						model.addRow(row);
						
						new FolowingQuarantine(msg.getContent(),quarantineFrame).start();
					}
				}
				else
					block();
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
