package Agents;

import java.util.LinkedList;
import java.util.Queue;

public class Nurse {
    enum Type {
        anesthesia, helper
    }

    private String personalInfos;       // includes working hours
    private Type type;
    private Queue<Surgery> surgeries = new LinkedList<>();  // those not yet performed!
}
