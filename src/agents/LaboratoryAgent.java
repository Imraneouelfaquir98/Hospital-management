package agents;

import agents.Behaviors.EnterResultsBehavior;
import agents.Behaviors.WaitRequestBehavior;
import agents.UI.ChoiceForm;
import agents.UI.EnterResultsForm;
import agents.UI.NextPatientForm;
import agents.Utils;
import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LaboratoryAgent extends Agent {

    public enum Specialty {
        urine, blood, covid, x_ray_chest, x_ray_head
    }

    private Queue<String> requests = new LinkedList<>();
    private Specialty specialty;
    private final LaboratoryAgent me = this;

    private static final String DB_NAME = "Laboratory";
    public static final String TESTS_TABLE = "Tests";
    private static final String USERNAME = "root", PASSWORD = "123456789";
    private Connection connection;

    @Override
    protected void setup() {
        super.setup();

        Object[] args = getArguments();
        specialty = Specialty.blood;
        if (args.length == 1)
            specialty = (Specialty) args[0];

        for (int i = 0; i < 2; i++) {
            requests.add(i + "aa");
        }

        connectWithDB();
        registerService();
        showFrame();
        addBehaviour(new WaitRequestBehavior(requests));

    }

    @Override
    protected void takeDown() {
        super.takeDown();
        try {
            DFService.deregister(this);
        } catch (FIPAException e) {
            e.printStackTrace();
        }
        frame.dispose();
        System.out.println("turning off Lab... ");
    }

    /////////////////////////////////////////////////////////  DR & Behaviors
    private void registerService() {
        // Register my service;
        DFAgentDescription dfd = new DFAgentDescription();
        dfd.setName(getAID());
        ServiceDescription service = new ServiceDescription();
        service.setName("Laboratory");
        service.setType(String.valueOf(specialty));
        dfd.addServices(service);
        try {
            DFService.register(this, dfd);
        } catch (FIPAException e) {
            e.printStackTrace();
        }
    }

    public void enterResultsBehavior(String patient, String result) {
        addBehaviour(new EnterResultsBehavior(connection, specialty, patient, result));
    }

    ////////////////////////////////////////////////////////// Databases management
    private void connectWithDB() {
        // fields of NIGHTS_TABLE: patientId & bed & from & to
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/", USERNAME, PASSWORD);
            Statement stmt = connection.createStatement();
            // Create DB if necessary
            if (!Utils.dbExists(connection, DB_NAME))
                stmt.executeUpdate("CREATE DATABASE " + DB_NAME + " ;");
            stmt.executeUpdate("USE " + DB_NAME + ";");

            // Create NIGHTS_TABLE table if necessary
            if (!Utils.tableExists(connection, TESTS_TABLE))
                stmt.executeUpdate("CREATE TABLE " + TESTS_TABLE +
                        "(whenn DATETIME DEFAULT(NOW())," +
                        " patient VARCHAR(100)," +
                        " test_type VARCHAR(100)," +
                        " results VARCHAR(100)," +
                        " PRIMARY KEY(patient,whenn) ) ;");

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }


    }


    /////////////////////////////////////////////////////////  UI transition methods

    private ChoiceForm choiceForm;
    private EnterResultsForm enterResultsForm;
    private NextPatientForm nextPatientForm;
    private JFrame frame;


    private void showFrame() {
        //        // show UI
        frame = new JFrame("Laboratory");

        choiceForm = new ChoiceForm(this);
        enterResultsForm = new EnterResultsForm(this, String.valueOf(specialty));
        nextPatientForm = new NextPatientForm(this);

        switchToChoiceForm();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                me.takeDown();
            }
        });
    }

    public void switchToChoiceForm() {
        List<String> list = ( List<String>) requests;
        String[][] patients = new String[requests.size()][2];
        for (int i = 0; i < patients.length; i++) {
            patients[i] = new String[]{String.valueOf(i + 1), list.get(i)};
        }
        switchPanel(choiceForm.getPanel(patients));
    }

    public void switchToNextPatientForm() {
        switchPanel(nextPatientForm.getPanel(requests.poll()));
    }

    public void switchToEnterResultsForm() {
        switchPanel(enterResultsForm.getPanel());
    }

    private void switchPanel(JPanel panel) {
        frame.setContentPane(panel);
        frame.revalidate();
        frame.pack();
    }
}
