package containers;

import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.ControllerException;

public class CoronaConsultingContainer {
	
	public static void main(String[] args) throws ControllerException{
		
		Runtime runtime = Runtime.instance();
		ProfileImpl profileImpl = new ProfileImpl();
		profileImpl.setParameter(ProfileImpl.MAIN_HOST, "localhost");
		AgentContainer container = runtime.createAgentContainer(profileImpl);
		AgentController agentController = container.createNewAgent("CoronaConsultingAgent","agents.CoronaConsultingAgent", new Object[] {});
		agentController.start();
		
	}
	
}
