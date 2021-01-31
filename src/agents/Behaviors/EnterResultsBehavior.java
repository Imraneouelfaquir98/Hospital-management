package agents.Behaviors;

import agents.LaboratoryAgent.Specialty;
import jade.core.behaviours.OneShotBehaviour;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import static agents.LaboratoryAgent.TESTS_TABLE;

public class EnterResultsBehavior extends OneShotBehaviour {

    private Connection connection;
    private String patient;
    private Specialty specialty;
    private String result;

    public EnterResultsBehavior(Connection connection, Specialty specialty, String patient, String result) {
        this.connection = connection;
        this.specialty = specialty;
        this.result = result;
        this.patient = patient;
    }

    @Override
    public void action() {
        PreparedStatement statement = null;
        try {
            System.out.println("hhhhhhhhhhhhhhhhhhhhhhh");
            statement = connection.prepareStatement("INSERT INTO " + TESTS_TABLE + "(patient,test_type,results)" +
                    " VALUES(?,?,?); ");
            statement.setString(1, patient);
            statement.setString(2, String.valueOf(specialty));
            statement.setString(3, result);
            int row=statement.executeUpdate();
            System.out.println(row+ " Added.");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
