package com.company.PharmacyDept.Behaviors;

import jade.core.behaviours.OneShotBehaviour;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.company.PharmacyDept.PharmacyAgent.TRANSACTIONS_TABLE;

public class GiveMedsBehavior extends OneShotBehaviour {

    private String medicine, patientId;
    private int qty;
    private Connection connection;

    public GiveMedsBehavior(String medicine, String patientId, int qty, Connection connection) {
        this.medicine = medicine;
        this.patientId = patientId;
        this.qty = qty;
        this.connection = connection;
    }

    @Override
    public void action() {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO " + TRANSACTIONS_TABLE + "(patient,medicine,quantity) VALUES(?,?,?); ");
            statement.setString(1, patientId);
            statement.setString(2, medicine);
            statement.setInt(3, qty);
            int rows = statement.executeUpdate();
            System.out.println(rows + " Row Added.");

            // Now update the inventory table
            myAgent.addBehaviour(new AddMedsBehavior(connection, medicine, -qty));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
