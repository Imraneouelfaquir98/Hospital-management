
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.FSMBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

public class Consulting extends Agent {

	protected void setup() 
	{

		System.out.println("---------------------------------------");
		System.out.println("-----------------Consulting Room Open---------------");
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

			System.out.println("Conslting:waiting ...");
			block();
		}
	}

	private class getRequest extends OneShotBehaviour {

		@Override
		public void action() {

			System.out.println("Consulting: Action Started");
			
			ACLMessage messageRecu = receive();
			System.out.println("Conslting: Recieve >>"+messageRecu.getContent());
			
		}

		public int onEnd() {
			return 1;
		}
	}
}