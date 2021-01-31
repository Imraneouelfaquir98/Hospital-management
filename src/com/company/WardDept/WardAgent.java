package com.company.WardDept;

import com.company.Utils;
import com.company.WardDept.Behaviors.CheckoutBehavior;
import com.company.WardDept.Behaviors.HostBehavior;
import com.company.WardDept.UI.CheckoutForm;
import com.company.WardDept.UI.ChoiceForm;
import com.company.WardDept.UI.HostForm;
import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WardAgent extends Agent {

    public enum RoomType {
        Pregnancy, Babies, ICU
    }

    private RoomType type;
    private List<String> places;

    private static final String DB_NAME = "Ward";
    public static String NIGHTS_TABLE = "Nights";
    public static String VISITS_TABLE = "Visits";
    private static final String USERNAME = "root", PASSWORD = "ayoubassis";
    private Connection connection;

    private final WardAgent me = this;

    @Override
    protected void setup() {
        super.setup();

        Object[] args = getArguments();

        if (args.length >= 2) {
            // args must contain first max_places then type;
            places = new ArrayList<>(Collections.nCopies(Integer.parseInt((String) args[0]), "-1"));
            type = (RoomType) args[1];
        } else {
            places = new ArrayList<>(Collections.nCopies(20, "-1"));
            type = RoomType.ICU;
        }

        connectWithDB();
        showFrame();
        registerService();
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
        System.out.println("turning down ward... ");
    }

    /////////////////////////////////////////////////////////  DR & Behaviors
    private void registerService() {
        // Register my service;
        DFAgentDescription dfd = new DFAgentDescription();
        dfd.setName(getAID());
        ServiceDescription service = new ServiceDescription();
        service.setName("Ward");
        service.setType("Ward");
        dfd.addServices(service);
        try {
            DFService.register(this, dfd);
        } catch (FIPAException e) {
            e.printStackTrace();
        }
    }

    public void hostPatient(String patientId, int bed) {
        places.set(bed - 1, patientId);
        addBehaviour(new HostBehavior(connection, type, patientId, bed));
    }

    public void checkoutPatient(String patientId, int bed) {
        places.set(bed - 1, "-1");
        addBehaviour(new CheckoutBehavior(connection, patientId, bed));
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
            if (!Utils.tableExists(connection, NIGHTS_TABLE+type))
                stmt.executeUpdate("CREATE TABLE " + NIGHTS_TABLE + type +
                        "(patient VARCHAR(100)," +
                        " bed INT," +
                        " fromm DATETIME DEFAULT(NOW())," +
                        " too DATETIME ," +
                        " PRIMARY KEY(patient,fromm) ) ;");

            // Create VISITS table if necessary
            if (!Utils.tableExists(connection, VISITS_TABLE+type))
                stmt.executeUpdate("CREATE TABLE " + VISITS_TABLE + type +
                        "(patient VARCHAR(100)," +
                        " visitor VARCHAR(100)," +
                        " whenn DATETIME DEFAULT(NOW())," +
                        " PRIMARY KEY(patient,whenn) ) ;");

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }


    }


    /////////////////////////////////////////////////////////  UI transition methods

    private ChoiceForm choiceForm;
    private CheckoutForm checkoutForm;
    private HostForm hostForm;
    private JFrame frame;


    private void showFrame() {
        //        // show UI
        frame = new JFrame("Ward");

        choiceForm = new ChoiceForm(this);
        hostForm = new HostForm(this);
        checkoutForm = new CheckoutForm(this);

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
        String[][] beds = new String[places.size()][2];
        for (int i = 0; i < beds.length; i++) {
            beds[i] = new String[]{String.valueOf(i + 1), places.get(i).equals("-1") ? "No One" : places.get(i)};
        }

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM " + VISITS_TABLE + type +
                    " WHERE CAST(whenn AS DATE) = CAST(NOW() AS DATE)" +
                    " ORDER BY whenn DESC;");
            List<String[]> visits = new ArrayList<>();
            while (rs.next()) {
                visits.add(new String[]{
                        rs.getString(1),
                        rs.getString(2),
                        String.valueOf(rs.getDate(3))
                });
            }


            switchPanel(choiceForm.getPanel(beds, visits.toArray(new String[][]{})));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void switchToCheckoutForm() {
        switchPanel(checkoutForm.getPanel());
    }

    public void switchToHostForm() {
        switchPanel(hostForm.getPanel());
    }

    private void switchPanel(JPanel panel) {
        frame.setContentPane(panel);
        frame.revalidate();
        frame.pack();
    }
}
