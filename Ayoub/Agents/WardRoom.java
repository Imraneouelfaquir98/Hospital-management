package Agents;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

import java.util.ArrayList;
import java.util.List;

public class WardRoom extends Agent {
    enum Type{
        Pregnancy,Babies, ICU
    }
    Type type;
    private List<Integer> places;       // -1 if that place is empty

    @Override
    protected void setup() {
        super.setup();
        Object[] args = getArguments();
        if (args.length<2){
            takeDown();
        }
        // args must contain first max_places then type;
        places=new ArrayList<>(Integer.parseInt((String)args[0]));
        type= (Type) args[1];

        // Register my service;
        DFAgentDescription dfd= new DFAgentDescription();
        dfd.setName(getAID());
        ServiceDescription service=new ServiceDescription();
        service.setName("Ward");
        service.setType(String.valueOf(type));
        dfd.addServices(service);
        try {
            DFService.register(this,dfd);
        } catch (FIPAException e) {
            e.printStackTrace();
        }

        // Wait for requests


    }

    @Override
    protected void takeDown() {
        super.takeDown();

        // Deregister from DF;
        try {
            DFService.deregister(this);
        }
        catch (FIPAException fe) {
            fe.printStackTrace();
        }
    }

    private void host(int patient_id){
        //TODO
    }

    private void checkout(int patient_id){
        //TODO : + cleaning
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
                // check that it's from surgery room ;
                // TODO;
            } else block();
        }
    }



}
