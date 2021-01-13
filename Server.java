
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.FSMBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import java.util.*; 



import javax.swing.*; 
import java.awt.*; 
import java.awt.event.*; 


import java.time.LocalDateTime; // Import the LocalDateTime class
import java.time.format.DateTimeFormatter; // Import the DateTimeFormatter class


import java.sql. * ;

public class Server extends Agent {
	Connection myconnection;
	Statement mystatement;
	ResultSet myresultset;
	private class MyFrame 
		extends JFrame { 
	    public JLabel res;
	    private Container c; 

	    	public MyFrame() 
			{ 
				setTitle("Server Agent"); 
				setBounds(0, 600, 300, 600); 
				setDefaultCloseOperation(EXIT_ON_CLOSE); 
				setResizable(false); 
		
				c = getContentPane(); 
				c.setLayout(null); 

		        // Frame Title 
		        res = new JLabel("Test"); 
				res.setFont(new Font("Arial", Font.PLAIN, 20)); 
				res.setSize(300, 600); 
				res.setLocation(100, 500); 
				c.add(res); 

				
				setVisible(true); 
			}
	}
	MyFrame f = new MyFrame();

	private int id = 1;

	protected void setup() 
	{

		System.out.println("---------------------------------------");
		System.out.println("-----------------Server/DataBase/Planification---------------");
		System.out.println("---------------------------------------");

		// TODO : inialise SQL database for all type patients/Visitors/...

		FSMBehaviour agent_beh = new FSMBehaviour();

		agent_beh.registerFirstState(new waiting(), "waiting");
		agent_beh.registerState(new getRequest(), "getrequest");

		agent_beh.registerDefaultTransition("waiting", "getrequest");
		agent_beh.registerTransition("getrequest", "waiting",1);

		addBehaviour(agent_beh);
	}

	private class waiting extends OneShotBehaviour {

		@Override
		public void action() {

			System.out.println("Server:  waiting ...");
			block();
		}
	}

	private class getRequest extends OneShotBehaviour {

		@Override
		public void action() {

			System.out.println("Server: action start");
			
			ACLMessage messageRecu = receive();
			System.out.println(messageRecu.getContent());

			String str = messageRecu.getContent(); 
	        String[] arrOfStr = str.split(",", 9); 

	  		Map<String, String> map = new HashMap<String, String>();

	        for (String a : arrOfStr) 
	        {
	            // System.out.println("Server>>"+a); 
	            String[] tmp = a.split(":", 2); 
	            map.put(tmp[0],tmp[1]);
	        }

	        ////// NEW

	         try {
	            String dbUrl = "jdbc:mysql://localhost:3306/ok";
	            String username = "user";
	            String password = "P@ssW0rd";
	            Class.forName("com.mysql.jdbc.Driver");


	            myconnection = DriverManager.getConnection(dbUrl, username, password);
	            System.out.println("Connected");

		        if(map.get("service").equals("Visitor"))
				{
					PreparedStatement pstmt = myconnection.prepareStatement(
		               "INSERT INTO patients (id,name,cin,mobile,gender,dob,service,email,address,name2) VALUES(?,?,?,?,?,?,?,?,?,?)");
		            pstmt.setInt(1, id); // set parameter 1 (FIRST_NAME)
		            pstmt.setString(2, map.get("name"));
		            pstmt.setString(3, map.get("cin"));
		            pstmt.setString(4, map.get("mobile"));
		            pstmt.setString(5, map.get("gender"));
		            pstmt.setString(6, map.get("dob"));
		            pstmt.setString(7, map.get("service"));
		            pstmt.setString(8, map.get("email"));
		            pstmt.setString(9, map.get("address"));
		            pstmt.setString(10, map.get("name2"));

		            int rows = pstmt.executeUpdate(); 
				}
				else
				{
		            PreparedStatement pstmt = myconnection.prepareStatement(
		               "INSERT INTO patients (id,name,cin,mobile,gender,dob,service,email,address) VALUES(?,?,?,?,?,?,?,?,?)");
		            pstmt.setInt(1, id); // set parameter 1 (FIRST_NAME)
		            pstmt.setString(2, map.get("name"));
		            pstmt.setString(3, map.get("cin"));
		            pstmt.setString(4, map.get("mobile"));
		            pstmt.setString(5, map.get("gender"));
		            pstmt.setString(6, map.get("dob"));
		            pstmt.setString(7, map.get("service"));
		            pstmt.setString(8, map.get("email"));
		            pstmt.setString(9, map.get("address"));
		            
	            	int rows = pstmt.executeUpdate(); 

				}




	            mystatement = myconnection.createStatement();
	            myresultset = mystatement.executeQuery("select * from patients");

	            while (myresultset.next()) {
	                System.out.println(" ID : " + myresultset.getString("id"));
	                System.out.println(" name : " + myresultset.getString("name"));
	                System.out.println(" email : " + myresultset.getString("email") );
	            }
	        } catch(Exception e) {
	            System.out.println(e);
	        }



	        ////// 



	        map.forEach((key, value) -> System.out.println("Server>>"+key + ":" + value));
	        // TODO : add the map infos in the database

			// Responce the reception & the doctor/nuercery by the id of the patient and the doctor

			ACLMessage message = new ACLMessage(ACLMessage.INFORM);


			message.addReceiver(messageRecu.getSender());

			if(map.get("service").equals("Consulting"))
			{
				message.addReceiver(new AID("consulting", AID.ISLOCALNAME)); 
				message.setContent("ID:" + String.valueOf(id)+",Service:"+"ConsultingRoom");
			}

			if(map.get("service").equals("Nursery"))
			{
				message.addReceiver(new AID("nursery", AID.ISLOCALNAME));
				message.setContent("ID:" + String.valueOf(id)+",Service:"+"NurseryRoom");
			}

			if(map.get("service").equals("Visitor"))
			{
				// GET ID FROM patients 
				
				int idpatient =-1;
				// SQL>>   SELECT id,name FROM patients WHERE name="?"

				String sql = "SELECT id,name FROM patients WHERE name = ?";
				try {
		            PreparedStatement st = myconnection.prepareStatement(sql);
		            st.setString(1, map.get("name2"));
		            myresultset = st.executeQuery();
		            if (myresultset.next()) {
		                 idpatient = myresultset.getInt("id");
		                 System.out.println("IDP="+idpatient);
		            }


					// GET room FROM ward
					// SQL>> SELECT room FROM ward WHERE id=2
					String room = "#";

					mystatement = myconnection.createStatement();

		            myresultset = mystatement.executeQuery("SELECT room FROM ward WHERE id="+idpatient);
		            if (myresultset.next()) {
		                room = myresultset.getString("room");
		                System.out.println("ROOM="+room);
		            }

					message.setContent("id:"+id+",room:"+room);
					// message.addReceiver(new AID("ward", AID.ISLOCALNAME));
		        } 
		        catch(Exception e) {
			            System.out.println(e);
			        }
				
			}



			send(message);

			id+=1;

		}

		public int onEnd() {
			return 1;
		}
	}
}
