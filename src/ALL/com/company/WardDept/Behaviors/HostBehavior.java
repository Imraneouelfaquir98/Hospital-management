package com.company.WardDept.Behaviors;

import com.company.WardDept.WardAgent;
import jade.core.behaviours.OneShotBehaviour;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.company.WardDept.WardAgent.NIGHTS_TABLE;

public class HostBehavior extends OneShotBehaviour {

    private Connection connection;
    private String patientId;
    private int bed;
    private WardAgent.RoomType type;


    public HostBehavior(Connection connection, WardAgent.RoomType type, String patientId, int bed) {
        this.connection = connection;
        this.patientId = patientId;
        this.bed = bed;
        this.type=type;
    }

    @Override
    public void action() {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO " + NIGHTS_TABLE+type + "(patient,bed) " +
                    "VALUES(?,?);");
            statement.setString(1, patientId);
            statement.setInt(2, bed);
            int row = statement.executeUpdate();
            System.out.println(row + " row has been updated!");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
