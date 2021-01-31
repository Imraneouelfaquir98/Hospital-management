package containers;

import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.ControllerException;

public class ReceptionContainer {
	public static void main(String[] args) throws ControllerException{
		// TODO Auto-generated method stub
		Runtime runtime = Runtime.instance();
		ProfileImpl profileImpl = new ProfileImpl();
		profileImpl.setParameter(ProfileImpl.MAIN_HOST, "localhost");
		AgentContainer container = runtime.createAgentContainer(profileImpl);
		AgentController agentController = container.createNewAgent("Reception Agent","agents.ReceptionAgent", new Object[] {});
		agentController.start();
	}
}
