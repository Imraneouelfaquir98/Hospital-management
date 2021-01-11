package Agents;

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
import java.util.HashMap;
import java.util.List;


public class Pharmacy extends Agent {
    class Requests{
        private String medicine;
        private int quantity;
        private Actor from, to;
    }

    enum Actor{
        Patient,Pharmacy,Supplier;
    }

    private String FILE_NAME;
    List<Requests> logs=new ArrayList<>();
    HashMap<String,Integer> inventory=new HashMap<>();

    @Override
    protected void setup() {
        super.setup();
        // Load inventory from database;

        // Register my service;
        DFAgentDescription dfd= new DFAgentDescription();
        dfd.setName(getAID());
        ServiceDescription service=new ServiceDescription();
        service.setName("Pharmacy");
        dfd.addServices(service);
        try {
            DFService.register(this,dfd);
        } catch (FIPAException e) {
            e.printStackTrace();
        }

        // Wait for requests to register new patient in queue
        addBehaviour(new WaitForConsultingRoom());
    }

    @Override
    protected void takeDown() {
        super.takeDown();
        // append Logs and save Inventory: filename must contain AID;

        // delist from DF;
        try {
            DFService.deregister(this);
        }
        catch (FIPAException fe) {
            fe.printStackTrace();
        }
    }





    /////////////////////////////////    BEHAVIORS

    // Triggered by another agent
    class WaitForConsultingRoom extends CyclicBehaviour {
        MessageTemplate template;
        //        MessageTemplate template = MessageTemplate.and(
//                MessageTemplate.MatchPerformative(ACLMessage.INFORM),
//                MessageTemplate.not(MessageTemplate.MatchSender(new AID("brahim", AID.ISLOCALNAME))));
        @Override
        public void action() {
            ACLMessage msg = receive(template);
            if (msg != null) {
                // check that its from consulting room and respond accordingly;
                // make sure that you keep track of inventory;


            } else block();
        }
    }

    // Triggered by requestMedicine button;
    class RequestMedicine extends OneShotBehaviour {

        @Override
        public void action() {
            // Send Message to database || show it in a panel

        }
    }

    // Triggered by giveMedicine button;
    class GiveMedicine extends OneShotBehaviour{

        @Override
        public void action() {
            // Send Message to database || show it in a panel

        }
    }

    // Triggered by addToStock button;
    class AddMedicineToInventory extends OneShotBehaviour{

        @Override
        public void action() {
            // Send Message to database || show it in a panel

        }
    }
}
