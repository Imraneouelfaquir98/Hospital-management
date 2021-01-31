
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;
// import frames.QuarantineFrame;
// import agents.QuarantineAgent;

public class FolowingQuarantine extends Thread{
	public String ID_Patient;
	public QuarantineFrame frame;
	FolowingQuarantine(String ID_Patient, QuarantineFrame frame){
		this.ID_Patient = ID_Patient;
		this.frame = frame;
	}
	
	public void run() {
		try{
			Thread.sleep(10000);
		}catch(InterruptedException e){}
		System.out.println("Quarantine End for "+ID_Patient);
		
		DefaultTableModel model = (DefaultTableModel) frame.QuarantinedTable.getModel();
		
		for(int i=0; i < model.getRowCount(); i++) {
			if(this.ID_Patient.equals((String)model.getValueAt(i,0))) {
				QuarantineAgent.CurrentQuarantined--;
				QuarantineAgent.EmptyQuarantinePlaces++;
				
				frame.CurrentQuarantinedLabel.setText(Integer.toString(QuarantineAgent.CurrentQuarantined));
				frame.NumberEmptyPlacesLabel.setText(Integer.toString(QuarantineAgent.EmptyQuarantinePlaces));
				model.removeRow(i);
				
				Vector<String> row = new Vector<String>();
				row.add(this.ID_Patient);
				row.add(new SimpleDateFormat("dd/MM/yy").format(Calendar.getInstance().getTime()));
				
				model = (DefaultTableModel) frame.RecoveredTable.getModel();
				model.addRow(row);
				break;
			}
		}
	}
}











