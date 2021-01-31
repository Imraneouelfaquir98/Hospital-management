package com.company.PharmacyDept;

import com.company.PharmacyDept.Behaviors.AddMedsBehavior;
import com.company.PharmacyDept.Behaviors.GiveMedsBehavior;
import com.company.PharmacyDept.UI.AddMedsForm;
import com.company.PharmacyDept.UI.ChoiceForm;
import com.company.PharmacyDept.UI.GiveMedsForm;
import com.company.PharmacyDept.UI.RequestForm;
import com.company.Utils;
import jade.core.Agent;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PharmacyAgent extends Agent {

    private final PharmacyAgent me = this;
    private static final String DB_NAME = "Pharmacy";
    public static final String INVENTORY_TABLE = "Inventory";
    public static final String TRANSACTIONS_TABLE = "Transactions";
    private static final String USERNAME = "root", PASSWORD = "ayoubassis";
    private Connection connection;

    @Override
    protected void setup() {
        super.setup();

        connectWithDB();
        showFrame();
    }

    @Override
    protected void takeDown() {
        super.takeDown();

        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        frame.dispose();

        System.out.println("turning down Pharmacy... ");
    }

    ////////////////////////////////////////////////////////// Behaviors
    public void addMedsBehavior(String med, int qty) {
        addBehaviour(new AddMedsBehavior(connection, med, qty));
    }

    public int giveMedsBehavior(String patient, String med, int qty) {
        // return the number of available units of 'med';
        PreparedStatement statement;
        int tmp_qty = 0;
        try {
            statement = connection.prepareStatement("SELECT quantity FROM " + INVENTORY_TABLE +
                    " WHERE medicine=?");
            statement.setString(1, med);
            ResultSet rs = statement.executeQuery();
            tmp_qty = rs.next() ? rs.getInt(1) : 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if (tmp_qty >= qty) {
            addBehaviour(new GiveMedsBehavior(med, patient, qty, connection));
            tmp_qty = -1;   // means transaction is possible;
        }
        return tmp_qty;

    }

    ////////////////////////////////////////////////////////// Databases management
    private void connectWithDB() {
        // fields of inventory are: medicine & quantity;
        // fields of transaction are: timestamp & patient & medicine & quantity;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/", USERNAME, PASSWORD);
            Statement stmt = connection.createStatement();
            // Create DB if necessary
            if (!Utils.dbExists(connection, DB_NAME))
                stmt.executeUpdate("CREATE DATABASE " + DB_NAME + " ;");
            stmt.executeUpdate("USE " + DB_NAME + ";");

            // Create Inventory table if necessary
            if (!Utils.tableExists(connection, INVENTORY_TABLE))
                stmt.executeUpdate("CREATE TABLE " + INVENTORY_TABLE +
                        "(medicine VARCHAR(100) NOT NULL, quantity INT NOT NULL , PRIMARY KEY(medicine) ) ;");
            // Create Transaction if necessary
            if (!Utils.tableExists(connection, TRANSACTIONS_TABLE))
                stmt.executeUpdate("CREATE TABLE " + TRANSACTIONS_TABLE +
                        "(timestamp datetime DEFAULT(NOW())," +
                        "patient VARCHAR(100) NOT NULL," +
                        "medicine VARCHAR(100) NOT NULL," +
                        "quantity INT NOT NULL , PRIMARY KEY(timestamp,medicine) ) ;");

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }


    }

    /////////////////////////////////////////////////////////  UI transition methods

    private ChoiceForm choiceForm;
    private AddMedsForm addMedsForm;
    private GiveMedsForm giveMedsForm;
    private RequestForm requestForm;
    private JFrame frame;

    private void showFrame() {
        //        // show UI
        frame = new JFrame("Pharmacy");

        choiceForm = new ChoiceForm(this);
        addMedsForm = new AddMedsForm(this);
        giveMedsForm = new GiveMedsForm(this);
        requestForm = new RequestForm(this);

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
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM " + INVENTORY_TABLE + " ;");

            List<String[]> inv = new ArrayList<>();
            while (rs.next()) {
                inv.add(new String[]{rs.getString(1), String.valueOf(rs.getInt(2))});
            }
            switchPanel(choiceForm.getPanel(inv.toArray(new String[][]{})));
        } catch (
                SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void switchToAddMedsForm() {
        switchPanel(addMedsForm.getPanel());
    }

    public void switchToGiveMedsForm() {
        switchPanel(giveMedsForm.getPanel());
    }

//    public void switchToRequestForm() {
//        switchPanel(requestForm.getPanel());
//    }

    private void switchPanel(JPanel panel) {
        frame.setContentPane(panel);
        frame.revalidate();
        frame.pack();
    }
}
