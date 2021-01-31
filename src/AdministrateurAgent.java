
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.FSMBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

import java.util.*;

import org.json.*;

import java.sql.*;

/*

- Recieve new patients from reception -> id,ordre
- Recieve new visitor from reception -> id, room number
- Recieve next from consulting -> id of patient by ordre



*/

public class AdministrateurAgent extends Agent {
    Connection myconnection;
    Statement mystatement;
    ResultSet myresultset;

    AdministrateurJFrame jFrame = new AdministrateurJFrame(this);

    private int id = 1;

    protected void setup() {
        jFrame.setVisible(true);

        // add listener
        jFrame.addMessageHandler(new AdministrateurJFrame.MessageHandler() {
            public void handleMessage(ACLMessage message) {
                System.out.println("ADMINISTRATION>>" + message.getContent());
                send(message);
            }
        });


        System.out.println("---------------------------------------");
        System.out.println("-----------------Admin---------------");
        System.out.println("---------------------------------------");

        // TODO : inialise SQL database for all type patients/Visitors/...

        FSMBehaviour agent_beh = new FSMBehaviour();

        agent_beh.registerFirstState(new waitMessage(), "waitMessage");
        agent_beh.registerState(new handleMessage(), "getrequest");

        agent_beh.registerDefaultTransition("waitMessage", "getrequest");
        agent_beh.registerTransition("getrequest", "waitMessage", 1);

        addBehaviour(agent_beh);
    }

    private class waitMessage extends OneShotBehaviour {

        @Override
        public void action() {

            System.out.println("Server:  waitMessage ...");
            block();
        }
    }

    private class handleMessage extends OneShotBehaviour {

        @Override
        public void action() {

			/*

				# INPUT
				@Ontology    connexion
				@keysJson    username,password
				
				# OUPTUT
				@Ontology    connexion
				@keysJson    connexion(ok/error)
			*/


            System.out.println("Server: action start");

            ACLMessage messageRecu = receive();
            System.out.println(messageRecu.getContent());

            String str = messageRecu.getContent();

            String ontology = messageRecu.getOntology();

            JSONObject jo = new JSONObject(str);

            if (ontology.equals("connexion")) {
                String rp = "{'connexion':'ok'}";


                try {
                    String dbUrl = "jdbc:mysql://localhost:3306/ok";
                    String username = "root";
                    String password = "ayoubassis";
                    Class.forName("com.mysql.jdbc.Driver");


                    myconnection = DriverManager.getConnection(dbUrl, username, password);
                    System.out.println("Connected");

                    // SELECT * FROM agents WHERE username = 'Abdo' and password = '0000';
                    PreparedStatement pstmt = myconnection.prepareStatement(
                            "SELECT * FROM agents WHERE username = ? and password = ?");
                    pstmt.setString(1, (String) jo.get("username").toString());
                    pstmt.setString(2, (String) jo.get("password").toString());

                    myresultset = pstmt.executeQuery();

                    jFrame.usernames.add(messageRecu.getSender());

                    if (myresultset.next()) {
                        // Valide

                        jFrame.showRequest(ontology + "  #  USERNAME" + (String) jo.get("username").toString() + " # ACCEPTED");

                    } else {
                        // Not valide
                        jFrame.showRequest(ontology + "  #  USERNAME" + (String) jo.get("username").toString() + " # REFUSED");
                        rp = "{'connexion':'error'}";
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }


                ACLMessage message = new ACLMessage(ACLMessage.INFORM);
                message.setOntology("connexion");
                message.addReceiver(messageRecu.getSender());
                message.setContent(rp);
                send(message);
            } else if (ontology.equals("new")) {
                jFrame.showRequest("NEW   #  ID" + (String) jo.get("cin").toString());

                try {
                    String dbUrl = "jdbc:mysql://localhost:3306/ok";
                    String username = "user";
                    String password = "P@ssW0rd";
                    Class.forName("com.mysql.jdbc.Driver");


                    myconnection = DriverManager.getConnection(dbUrl, username, password);
                    // System.out.println("Connected");

                    if (jo.get("service").toString().equals("Visitor")) {
                        PreparedStatement pstmt = myconnection.prepareStatement(
                                "INSERT INTO visitors (id,name,cin,mobile,gender,dob,service,email,address,name2) VALUES(?,?,?,?,?,?,?,?,?,?)");
                        pstmt.setInt(1, id);
                        pstmt.setString(2, (String) jo.get("name"));
                        pstmt.setString(3, (String) jo.get("cin"));
                        pstmt.setString(4, (String) jo.get("mobile"));
                        pstmt.setString(5, (String) jo.get("gender"));
                        pstmt.setString(6, (String) jo.get("dob"));
                        pstmt.setString(7, (String) jo.get("service"));
                        pstmt.setString(8, (String) jo.get("email"));
                        pstmt.setString(9, (String) jo.get("address"));
                        pstmt.setString(10, (String) jo.get("name2"));

                        int rows = pstmt.executeUpdate();
                    }
                    if (jo.get("service").toString().equals("Consulting")) {
                        PreparedStatement pstmt = myconnection.prepareStatement(
                                "INSERT INTO patients (id,name,cin,mobile,gender,dob,service,email,address) VALUES(?,?,?,?,?,?,?,?,?)");
                        pstmt.setInt(1, id);
                        pstmt.setString(2, (String) jo.get("name"));
                        pstmt.setString(3, (String) jo.get("cin"));
                        pstmt.setString(4, (String) jo.get("mobile"));
                        pstmt.setString(5, (String) jo.get("gender"));
                        pstmt.setString(6, (String) jo.get("dob"));
                        pstmt.setString(7, (String) jo.get("service"));
                        pstmt.setString(8, (String) jo.get("email"));
                        pstmt.setString(9, (String) jo.get("address"));

                        int rows = pstmt.executeUpdate();

                    }

						/*
						ontology : new
						JsonKeys : service(.../emergency)
								   cin
								   name

						*/
                    if (jo.getString("service").equals("emergency")) {

                        // SELECT * FROM patients WHERE cin = ?

                        // IF NO RESULT
                        PreparedStatement pstmt = myconnection.prepareStatement(
                                "INSERT INTO patients (id,name,cin,service) VALUES(?,?,?,?)");
                        pstmt.setString(1, (String) jo.get("cin"));
                        pstmt.setString(2, (String) jo.get("name"));
                        pstmt.setString(3, (String) jo.get("cin"));
                        pstmt.setString(4, "emergency");
                        int rows = pstmt.executeUpdate();


                    }


                } catch (Exception e) {
                    System.out.println(e);
                }
            }


            // // Responce the reception & the doctor/nuercery by the id of the patient and the doctor

            // ACLMessage message = new ACLMessage(ACLMessage.INFORM);


            // message.addReceiver(messageRecu.getSender());

            // if((String)jo.get("service").equals("Consulting"))
            // {
            // 	message.addReceiver(new AID("consulting", AID.ISLOCALNAME));
            // 	message.setContent("id:" + String.valueOf(id));
            // }

            // if((String)jo.get("service").equals("Nursery"))
            // {
            // 	message.addReceiver(new AID("nursery", AID.ISLOCALNAME));
            // 	message.setContent("id:" + String.valueOf(id));
            // }

            // if((String)jo.get("service").equals("Visitor"))
            // {

            // 	String sql = "SELECT room FROM ward WHERE id IN ( SELECT id FROM patients WHERE name = ?)";
            // 	try {
            // 		String room = "NO";
            //            PreparedStatement st = myconnection.prepareStatement(sql);
            //            st.setString(1, (String)jo.get("name2"));
            //            myresultset = st.executeQuery();
            //            if (myresultset.next()) {
            //                 room = myresultset.getString("room");
            //                System.out.println("Server>> ROOM="+room);
            //            }

            // 		message.setContent("id:"+id+",room:"+room);
            // 		// WHEN WART IS READY
            // 		// message.addReceiver(new AID("ward", AID.ISLOCALNAME));
            //        }
            //        catch(Exception e) {
            //             System.out.println(e);
            //         }

            // }


            // send(message);

            // id+=1;

        }

        public int onEnd() {
            return 1;
        }
    }
}
