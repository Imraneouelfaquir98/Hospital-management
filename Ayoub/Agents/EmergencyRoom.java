import jade.core.Agent;

import java.util.LinkedList;
import java.util.Queue;

public class EmergencyRoom extends Agent {
    private Queue<Integer> patients=new LinkedList<>();

    @Override
    protected void setup() {
        super.setup();
        //Treat next patient
        // Simultaneously, wait for the next one and register it the database if his not there yet;
    }

    @Override
    protected void takeDown() {
        super.takeDown();
        // append the queue of patients to the database;
    }

    private void register_patient(String infos){

    }

    private void plan_patient_tests(int patient_id, String tests){

    }

    private void scheduleSurgery(int patient_id,String surgery){

    }

    private void hostPatient(int id){

    }
}
