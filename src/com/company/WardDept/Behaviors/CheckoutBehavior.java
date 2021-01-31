package com.company.WardDept.Behaviors;

import jade.core.behaviours.OneShotBehaviour;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.company.WardDept.WardAgent.NIGHTS_TABLE;

public class CheckoutBehavior extends OneShotBehaviour {

    private Connection connection;
    private String patientId;
    private int bed;

    public CheckoutBehavior(Connection connection, String patientId, int bed) {
        this.connection = connection;
        this.patientId = patientId;
        this.bed = bed;
    }

    @Override
    public void action() {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE " + NIGHTS_TABLE +
                    " SET too=NOW() WHERE patient=? and bed=? and too IS NULL;");
            statement.setString(1, patientId);
            statement.setInt(2, bed);
            int row = statement.executeUpdate();
            System.out.println(row + " row has been updated!");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
