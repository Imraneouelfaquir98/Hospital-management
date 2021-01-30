package com.company;

import java.sql.*;

public class Utils {

    public static boolean dbExists(Connection connection, String dbName) throws SQLException {
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SHOW DATABASES LIKE '"+dbName+"';");
        return rs.next();
    }
    public static boolean tableExists(Connection connection, String tableName) throws SQLException {
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SHOW TABLES LIKE '"+tableName+"';");
        return rs.next();
    }

    public static boolean individualExists(Connection connection,String tablename,String column,String value) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("Select * FROM "+tablename+ " WHERE "+column+" = ?;");
        stmt.setString(1,value);
        ResultSet res=stmt.executeQuery();
        return res.next();
    }

}
