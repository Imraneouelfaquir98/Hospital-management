import java.sql. * ;

class Test {
    public static void main(String args[]) {
        try {
            String dbUrl = "jdbc:mysql://localhost:3306/ok";
            String username = "user";
            String password = "P@ssW0rd";
            Class.forName("com.mysql.jdbc.Driver");


            Connection myconnection = DriverManager.getConnection(dbUrl, username, password);
            System.out.println("Connected");

            // ADD ROW

            // PreparedStatement pstmt = myconnection.prepareStatement(
            //    "INSERT INTO patients (id,name,email) VALUES(1,?,?)");
            // pstmt.setString(1, "user1080390"); // set parameter 1 (FIRST_NAME)
            // pstmt.setString(2, "u80390"); // set parameter 1 (FIRST_NAME)
            // // pstmt.setInt(2, 101); // set parameter 2 (ID)
            // int rows = pstmt.executeUpdate(); // "rows" save the affected rows

            // READ ALL CONTENT OF TABLE

            // Statement mystatement = myconnection.createStatement();
            // ResultSet myresultset = mystatement.executeQuery("select * from patients");

            // while (myresultset.next()) {
            //     System.out.println(" ID : " + myresultset.getString("id"));
            //     System.out.println(" name : " + myresultset.getString("name"));
            //     System.out.println(" email : " + myresultset.getString("email") );
            // }

            // CHECH WARD

            // Statement mystatement = myconnection.createStatement();

            // ResultSet rs = mystatement.executeQuery("SELECT room FROM ward WHERE id=2");
            // if (rs.next()) {
            //     String room = rs.getString("room");
            //     System.out.println("ROOM="+room);
            // }

            // GET ID FROM patients

            String sql = "SELECT id,name FROM patients WHERE name = ?";
            PreparedStatement st = myconnection.prepareStatement(sql);
            st.setString(1, "3afya");
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                String idpatient = rs.getString("id");
                 System.out.println("IDP="+idpatient);
            }


            } catch(Exception e) {
                System.out.println(e);
            }
    }
}