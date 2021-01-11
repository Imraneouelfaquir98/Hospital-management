package Agents;

import Agents.Surgery;
import jade.core.Agent;

import java.util.LinkedList;
import java.util.Queue;

public class SurgeryRoom extends Agent {
    private Queue<Surgery> surgeries=new LinkedList<>();

    @Override
    protected void setup() {
        super.setup();
        // Treat next patient
        // Simultaneously, wait for the next one to schedule his surgery
    }

    @Override
    protected void takeDown() {
        super.takeDown();
        // append the queue of surgeries to the database;
    }

    private void scheduleSurgery(int patient_id,String surgery){
        // used when requested by emergency;
    }

    private void cleanRoom(){
        // after each surgery is done;
    }

}
