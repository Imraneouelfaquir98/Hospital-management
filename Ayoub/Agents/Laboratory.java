package Agents;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;


public class Laboratory extends Agent {
    enum Type{
        blood_test,radio
    }

    class Request{
        int patient_id;
        Type type;
    }
    private Type type;
    private Queue<Request> requests=new LinkedList<>();

    @Override
    protected void setup() {
        super.setup();
        Object[] args=getArguments();
        if(args.length!=1)
            takeDown();
        type=(Type) args[0];

        // Register my service;
        DFAgentDescription dfd= new DFAgentDescription();
        dfd.setName(getAID());
        ServiceDescription service=new ServiceDescription();
        service.setName("Laboratory");
        service.setType(String.valueOf(type));
        dfd.addServices(service);
        try {
            DFService.register(this,dfd);
        } catch (FIPAException e) {
            e.printStackTrace();
        }

        // Wait for requests to register new patient in queue
        addBehaviour(new WaitForPatient());

        // Calling next one if button nextPatient was clicked;

        // dispatching results when returnResutls clicked;

    }

    @Override
    protected void takeDown() {
        super.takeDown();
        // append the queue of requests to the database;

        // deregister from DF;
        try {
            DFService.deregister(this);
        }
        catch (FIPAException fe) {
            fe.printStackTrace();
        }
    }



    /////////////////////////////////    BEHAVIORS

    // Triggered by another agent
    class WaitForPatient extends CyclicBehaviour {
        MessageTemplate template;
//        MessageTemplate template = MessageTemplate.and(
//                MessageTemplate.MatchPerformative(ACLMessage.INFORM),
//                MessageTemplate.not(MessageTemplate.MatchSender(new AID("brahim", AID.ISLOCALNAME))));
        @Override
        public void action() {
            ACLMessage msg = receive(template);
            if (msg != null) {
                System.out.println(myAgent.getAID().getName().split("@")[0] + " -- " + msg.getSender() + " said : " + msg.getContent());
                // if its a test from emergency
                // do something quickly

                // elseif from consulting room
                // do something else

                // else from blood donation service
                // take your time
            } else block();
        }
    }

    // Calling next patient when the current one is done;
    // Triggered by nextPatient button;
    class CallNextPatient extends OneShotBehaviour{

        @Override
        public void action() {
            // Send Message to database || show it in a panel

        }
    }

    // Entering the results in the database and notifying the concerned agents;
    // Triggered by returnResults button;
    class DispatchResults extends OneShotBehaviour{

        @Override
        public void action() {
            // Send data to database agent && send Message to the one requesting these results;

        }
    }
}
