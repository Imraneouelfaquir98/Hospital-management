package Agents;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

import java.util.LinkedList;
import java.util.Queue;

public class EmergencyRoom extends Agent {
    private Queue<Integer> patients=new LinkedList<>();

    @Override
    protected void setup() {
        super.setup();
        //Treat next patient
        // Simultaneously, wait for the next one and register it the database if his not there yet;

        addBehaviour(new WaitForPatientResults());
    }

    @Override
    protected void takeDown() {
        super.takeDown();
        // append the queue of patients to the database;

    }

    private void registerPatient(String infos){

    }

    private void planPatientTests(int patient_id, String tests){

    }

    private void scheduleSurgery(int patient_id,String surgery){

    }


    /////////////////////////////////    BEHAVIORS

    // Triggered by Laboratory
    class WaitForPatientResults extends CyclicBehaviour{
        MessageTemplate template;

        @Override
        public void action() {
            ACLMessage msg = receive(template);
            if (msg != null) {
                // check whether it's from the emergency room;
                // then notify emergency room;
            } else block();
        }
    }

    // Triggered by addPatient button;
    class RegisterPatient extends OneShotBehaviour{

        @Override
        public void action() {

        }
    }

    // Triggered by testPatient button;
    class PlanPatientTests extends OneShotBehaviour{

        @Override
        public void action() {

        }
    }

    // Triggered by scheduleSurgery button;
    class ScheduleSurgery extends OneShotBehaviour{

        @Override
        public void action() {

        }
    }



}
