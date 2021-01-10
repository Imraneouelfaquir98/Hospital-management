import jade.core.Agent;

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
        //Wait for requests

    }

    @Override
    protected void takeDown() {
        super.takeDown();
        // append the queue of requests to the database;
    }
}
