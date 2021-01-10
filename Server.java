
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.FSMBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import java.util.*; 


import java.time.LocalDateTime; // Import the LocalDateTime class
import java.time.format.DateTimeFormatter; // Import the DateTimeFormatter class

public class Server extends Agent {
	private int id = 1;

	protected void setup() 
	{

		System.out.println("---------------------------------------");
		System.out.println("-----------------Server/DataBase/Planification---------------");
		System.out.println("---------------------------------------");

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


			// Responce the reception & the doctor/nuercery by the id of the patient and the doctor

			ACLMessage message = new ACLMessage(ACLMessage.INFORM);

			message.addReceiver(messageRecu.getSender());
			// if(map.get("Service").equals("Consulting"))
			message.addReceiver(new AID("consulting", AID.ISLOCALNAME)); 
			// if(map.get("Service").equals("Nursery"))
			message.addReceiver(new AID("nursery", AID.ISLOCALNAME)); 


			LocalDateTime myDateObj = LocalDateTime.now();
		    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
			String formattedDate = myDateObj.format(myFormatObj);


			message.setContent("ID@" + String.valueOf(id) + "@@TIMEIN@"+formattedDate);

			send(message);

			id+=1;

		}

		public int onEnd() {
			return 1;
		}
	}
}