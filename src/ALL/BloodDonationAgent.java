
// import com.company.LaboratoryDept.LaboratoryAgent;
// import frames.BloodDonationFrame;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.ParallelBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;

import javax.swing.table.DefaultTableModel;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

public class BloodDonationAgent extends Agent {

    public BloodDonationFrame frame;
    public DefaultTableModel model;

    public static int totalDonation = 0;

    protected void setup() {

        System.out.println("---------------------------------------");
        System.out.println("-------------Blood Donation------------");
        System.out.println("---------------------------------------");

        frame = new BloodDonationFrame();
        frame.setVisible(true);

        ParallelBehaviour parallelBehaviour = new ParallelBehaviour();
        addBehaviour(parallelBehaviour);

        parallelBehaviour.addSubBehaviour(new CyclicBehaviour() {
            @SuppressWarnings("unchecked")
            public void action() {
                ACLMessage msg = receive();
                if (msg != null) {
                    if (msg.getSender().getName().equals("Reception Agent@192.168.43.96:1099/JADE")) {
                        Vector<String> row = new Vector<String>();
                        try {
                            row = (Vector<String>) msg.getContentObject();
                        } catch (UnreadableException e) {
                        }

                        System.out.println(row.toString());
                        System.out.println("Sender : " + msg.getSender().getName());

                        row.add(new SimpleDateFormat("dd/MM/yy").format(new Date()) + " " + new SimpleDateFormat("h:mm a").format(new Date()));
                        DefaultTableModel model = (DefaultTableModel) frame.jTable1.getModel();

                        DFAgentDescription template = new DFAgentDescription();
                        ServiceDescription sd = new ServiceDescription();
                        sd.setName("Laboratory");
                        sd.setType(String.valueOf(LaboratoryAgent.Specialty.blood));
                        template.addServices(sd);

                        try {
                            System.out.println("We found... ");
                            DFAgentDescription[] result = DFService.search(myAgent, template);
                            System.out.println("We found " + result.length);

                            ACLMessage msgToLab = new ACLMessage(ACLMessage.INFORM);
                            msgToLab.setOntology("BloodDonation");
                            msgToLab.addReceiver(result[0].getName());
                            msgToLab.setContent(row.get(0));
                            send(msgToLab);

                        } catch (FIPAException fe) {
                            fe.printStackTrace();
                        }

                        row.add("Waiting result");
                        totalDonation++;
                        model.addRow(row);

                        frame.TotalDonationLabel.setText(Integer.toString(totalDonation));
                    } else if (msg.getOntology().equals("Laboratory")) {
                        Vector row = new Vector();
                        try {
                            row = (Vector) msg.getContentObject();
                        } catch (UnreadableException e) {
                        }
//						System.out.println(row);
                        model = (DefaultTableModel) frame.jTable1.getModel();

                        for (int i = 0; i < model.getRowCount(); i++) {
                            String str = (String) row.get(0);
                            if (model.getValueAt(i, 0).equals(str)) {
                                model.setValueAt((String) row.get(1), i, 2);
                                break;
                            }
                        }

                    }
                } else
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
