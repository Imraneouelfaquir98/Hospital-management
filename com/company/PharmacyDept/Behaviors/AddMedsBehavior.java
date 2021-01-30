package com.company.PharmacyDept.Behaviors;

import jade.core.behaviours.OneShotBehaviour;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.company.PharmacyDept.PharmacyAgent.INVENTORY_TABLE;
import static com.company.Utils.individualExists;

public class AddMedsBehavior extends OneShotBehaviour {

    private String medicine;
    private int qty;
    private Connection connection;

    public AddMedsBehavior(Connection connection, String medicine, int qty) {
        this.medicine = medicine;
        this.qty = qty;
        this.connection = connection;
    }

    @Override
    public void action() {
        try {
            PreparedStatement statement;
            if (individualExists(connection, INVENTORY_TABLE, "medicine", medicine)) {
                statement = connection.prepareStatement("UPDATE " + INVENTORY_TABLE +
                        " SET quantity=quantity+? WHERE medicine=?");
                statement.setInt(1, qty);
                statement.setString(2, medicine);
            } else {
                statement = connection.prepareStatement("INSERT INTO " + INVENTORY_TABLE + "(medicine,quantity) VALUES(?,?); ");
                statement.setString(1, medicine);
                statement.setInt(2, qty);
            }
            int rows = statement.executeUpdate();
            System.out.println(rows + " Updated.");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


}
