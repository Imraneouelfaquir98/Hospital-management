package com.company.EmergencyDept.Behaviors;

import com.company.LaboratoryDept.LaboratoryAgent.Specialty;
import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import org.json.JSONObject;

public class TestPatientBehavior extends OneShotBehaviour {

    private String patient;
    private Specialty specialty;

    public TestPatientBehavior(String patient, Specialty specialty) {
        this.patient = patient;
        this.specialty = specialty;
    }

    @Override
    public void action() {
        DFAgentDescription template = new DFAgentDescription();
        ServiceDescription sd = new ServiceDescription();
        sd.setName("Laboratory");
        sd.setType(String.valueOf(specialty));
        template.addServices(sd);


        try {
            System.out.println("We found... ");
            DFAgentDescription[] result = DFService.search(myAgent, template);
            for (DFAgentDescription dfAgentDescription : result) {
                AID agent = dfAgentDescription.getName();
                int res = messageAgent(agent);
                if (res != -1) break;
            }
            System.out.println("We found " + result.length);

        } catch (FIPAException fe) {
            fe.printStackTrace();
        }
    }

    private int messageAgent(AID agent) {
        System.out.println("senddddddddddddddddddddd");

        ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
        msg.setOntology("emergency");
        msg.addReceiver(agent);
        JSONObject json = new JSONObject();
        json.put("idPatient", patient);
        msg.setContent(json.toString());
        myAgent.send(msg);

        MessageTemplate template = MessageTemplate.and(MessageTemplate.MatchSender(agent),
                MessageTemplate.MatchOntology("emergency"));

        do {
            msg = myAgent.receive(template);
        }
        while (msg == null);

        System.out.println("- receiiiiiiiiiiiive" + msg.getContent());
        json = new JSONObject(msg.getContent());
        int order = Integer.parseInt(json.getString("order"));
        System.out.println("The order: " + order);
        return order;
    }
}
