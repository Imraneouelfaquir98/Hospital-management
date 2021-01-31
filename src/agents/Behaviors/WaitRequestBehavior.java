package agents.Behaviors;

import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Queue;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

public class WaitRequestBehavior extends CyclicBehaviour {

    private final MessageTemplate template = MessageTemplate.or(
            MessageTemplate.or(
                    MessageTemplate.MatchOntology("consulting"),
                    MessageTemplate.MatchOntology("emergency")),
            MessageTemplate.or(
                    MessageTemplate.MatchOntology("blood_donation"),
                    MessageTemplate.MatchOntology("vaccination_room")));

    private Queue<String> requests;

    public WaitRequestBehavior(Queue<String> requests) {
        this.requests = requests;
    }

    @Override
    public void action() {
        ACLMessage msg = myAgent.receive();
        if (msg != null) {
        	String ontology = msg.getOntology();
        	if(ontology.equals("BloodDonation")) {
        		
        		System.out.println("Received i");
        		ACLMessage reply = msg.createReply();
        		reply.setOntology("Laboratory");
                Vector<String> result = new Vector<String>();
                result.add(msg.getContent());
                if(Math.random()<=0.5)
                	result.add("Positive");
                else
                	result.add("Negative");
                try {
                	reply.setContentObject(result);
	            }catch(IOException e){}
                try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                myAgent.send(reply);
        	}
        	
        	else {
        		// Read message
                System.out.println("-----------------------------------");
                System.out.println("Lab: new message: " + msg.getContent());
                JSONObject json = new JSONObject(msg.getContent());
                String source = msg.getOntology();
                String patient = json.getString("idPatient");
                requests.add(patient);

                // Respond to the source
                ACLMessage reply = msg.createReply();
                json = new JSONObject();
                json.put("order", String.valueOf(requests.size() + 1));
                reply.setContent(json.toString());
                myAgent.send(reply);
                System.out.println("Message ssent to emergency: "+json.getInt("order"));
        	}
            
            
        } else block();
    }
}
