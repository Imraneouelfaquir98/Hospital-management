
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

public class Server extends Agent {

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
	        String[] arrOfStr = str.split("#", 7); 

	  		Map<String, String> map = new HashMap<String, String>();

	        for (String a : arrOfStr) 
	        {
	            // System.out.println("Server>>"+a); 
	            String[] tmp = a.split("@", 2); 
	            map.put(tmp[0],tmp[1]);
	        }

	        map.forEach((key, value) -> System.out.println("Server>>"+key + ":" + value));
	        // TODO : add the map infos in the database

			// Responce the reception & the doctor/nuercery by the id of the patient and the doctor

			ACLMessage message = new ACLMessage(ACLMessage.INFORM);


			message.addReceiver(messageRecu.getSender());

			if(map.get("Service").equals("Consulting"))
			{
				message.addReceiver(new AID("consulting", AID.ISLOCALNAME)); 
				message.setContent("ID@" + String.valueOf(id)+"#Service@"+"ConsultingRoom");
			}

			if(map.get("Service").equals("Nursery"))
			{
				message.addReceiver(new AID("nursery", AID.ISLOCALNAME));
				message.setContent("ID@" + String.valueOf(id)+"#Service@"+"NurseryRoom");
			}

			if(map.get("Service").equals("Visitor"))
			{
				// TODO
				// get status of patient X from database created bu assis
				// input : name of patient
				// output : status
				String status = "ROOM:12"; // Or "Impossible:Reason"
				message.addReceiver(new AID("ward", AID.ISLOCALNAME));
			}



			send(message);

			id+=1;

		}

		public int onEnd() {
			return 1;
		}
	}
}
