import jade.core.Agent;

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

    }

    private void host(int patient_id){
        //TODO
    }

    private void checkout(int patient_id){
        //TODO : + cleaning
    }

}
