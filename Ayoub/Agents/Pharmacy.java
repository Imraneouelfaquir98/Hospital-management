import jade.core.Agent;

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
    HashMap<String,Integer> inventory=new HashMap<String, Integer>();

    @Override
    protected void setup() {
        super.setup();
        // Load inventory from database;
        // Register in DF.
    }

    @Override
    protected void takeDown() {
        super.takeDown();
        // append Logs and save Inventory: filename must contain AID
        // delist from DF
    }
}
