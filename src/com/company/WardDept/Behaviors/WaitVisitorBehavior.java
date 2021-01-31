package com.company.WardDept.Behaviors;

import com.company.WardDept.WardAgent.RoomType;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static com.company.WardDept.WardAgent.VISITS_TABLE;

public class WaitVisitorBehavior extends CyclicBehaviour {

    private final MessageTemplate template = MessageTemplate.or(
            MessageTemplate.MatchOntology("reception"),
            MessageTemplate.MatchOntology("surgery"));

    private List<String> places;
    private Connection connection;
    private RoomType type;

    public WaitVisitorBehavior(Connection connection, RoomType type, List<String> places) {
        this.places = places;
        this.type = type;
        this.connection = connection;
    }

    @Override
    public void action() {
        ACLMessage msg = myAgent.receive(template);
        if (msg != null) {
            // Read message
            JSONObject json = new JSONObject(msg.getContent());
            String source = msg.getOntology();
            String patient = json.getString("idPatient");
            String visitor = json.getString("idVisitor");

            int res = -1;
            for (int i = 0; i < places.size(); i++) {
                if (places.get(i).equals(patient)) {
                    res = i;
                    break;
                }
            }
            if (type == RoomType.ICU)
                res = -1;

            // Respond to the source
            ACLMessage reply = new ACLMessage(ACLMessage.INFORM);
            reply.setOntology(source);
            reply.addReceiver(msg.getSender());
            json = new JSONObject();
            json.put("room", res == -1 ? res : myAgent.getAID().getLocalName());
            reply.setContent(json.toString());
            myAgent.send(reply);

            if (res == -1) return;

            try {
                PreparedStatement statement = connection.prepareStatement("INSERT INTO " + VISITS_TABLE+type +
                        "(patient,visitor) VALUES(?,?);");
                statement.setString(1, patient);
                statement.setString(2, visitor);
                int row = statement.executeUpdate();
                System.out.println(row + " Added.");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        } else block();
    }
}
