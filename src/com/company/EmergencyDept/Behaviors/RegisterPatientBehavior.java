package com.company.EmergencyDept.Behaviors;

import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import org.json.JSONObject;

public class RegisterPatientBehavior extends OneShotBehaviour {

    private String name;
    private String cin;

    public RegisterPatientBehavior(String name, String cin) {
        this.name = name;
        this.cin = cin;
    }

    @Override
    public void action() {
        AID admin = new AID("server", AID.ISLOCALNAME);
        ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
        msg.addReceiver(admin);
        msg.setOntology("new");
        JSONObject object = new JSONObject();
        object.put("service", "emergency");
        object.put("cin", cin);
        object.put("name", name);
        msg.setContent(object.toString());
        myAgent.send(msg);

        System.out.println(name + " has been registred successfuly");
    }
}
