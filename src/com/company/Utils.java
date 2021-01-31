package com.company;

import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.StaleProxyException;

import java.sql.*;

public class Utils {

    public static boolean dbExists(Connection connection, String dbName) throws SQLException {
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SHOW DATABASES LIKE '" + dbName + "';");
        return rs.next();
    }

    public static boolean tableExists(Connection connection, String tableName) throws SQLException {
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SHOW TABLES LIKE '" + tableName + "';");
        return rs.next();
    }

    public static boolean individualExists(Connection connection, String tablename, String column, String value) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("Select * FROM " + tablename + " WHERE " + column + " = ?;");
        stmt.setString(1, value);
        ResultSet res = stmt.executeQuery();
        return res.next();
    }

    public static void createAgent(String className, String dept, String username, Object[] params) {
        //Get the JADE runtime interface (singleton)
        jade.core.Runtime runtime = jade.core.Runtime.instance();
//Create a Profile, where the launch arguments are stored
        Profile profile = new ProfileImpl();
        profile.setParameter(Profile.CONTAINER_NAME, dept);
        profile.setParameter(Profile.MAIN_HOST, "localhost");
//create a non-main agent container
        // Todo get the type and capacity of this wardroom;
        ContainerController container = runtime.createAgentContainer(profile);
        try {
            AgentController ag = container.createNewAgent(username + "-" + dept,
                    className,
                    params);//arguments
            ag.start();
        } catch (StaleProxyException e) {
            e.printStackTrace();
        }
    }

    public static void createAgent(String className, String dept, String username) {
        //Get the JADE runtime interface (singleton)
        jade.core.Runtime runtime = jade.core.Runtime.instance();
//Create a Profile, where the launch arguments are stored
        Profile profile = new ProfileImpl();
        profile.setParameter(Profile.CONTAINER_NAME, dept);
        profile.setParameter(Profile.MAIN_HOST, "localhost");
//create a non-main agent container
        // Todo get the type and capacity of this wardroom;
        ContainerController container = runtime.createAgentContainer(profile);
        try {
            AgentController ag = container.createNewAgent(username + "-" + dept,
                    className,
                    new Object[]{});//arguments
            ag.start();
        } catch (StaleProxyException e) {
            e.printStackTrace();
        }
    }

}
