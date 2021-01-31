package com.company.Login;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class LoginAgent extends Agent {

    private final LoginAgent me = this;
    private LoginForm panel;
    private Callable call;

    protected void checkCredentials(String username, String pass) {
        addBehaviour(new CheckCredentialsBehavior(username, pass));
    }

    @Override
    protected void setup() {
        super.setup();

        call = (Callable) getArguments()[0];

        showFrame();

    }

    @Override
    protected void takeDown() {
        super.takeDown();
        System.out.println("turning down ... ");
    }

    private class CheckCredentialsBehavior extends OneShotBehaviour {

        private String username, password;

        public CheckCredentialsBehavior(String username, String password) {
            this.username = username;
            this.password = password;
        }

        @Override
        public void action() {
            // contact Admin
            System.out.println(username + " Checking... " + password);
            boolean result;

            // Send message to administration;
            ACLMessage msg = new ACLMessage(ACLMessage.INFORM_IF);
            msg.setOntology("connexion");
            JSONObject json = new JSONObject();
            json.put("username", username);
            json.put("password", password);
            msg.setContent(json.toString());
            msg.addReceiver(new AID("server", AID.ISLOCALNAME));
            send(msg);

            // Waiting for response;
            MessageTemplate template = MessageTemplate.MatchOntology("connexion");
            msg = receive(template);
            json = new JSONObject(msg.getContent());
            result=json.getString("connexion").equals("ok");

            System.out.println("Connected? " + result);
            // respond to UI
            panel.password.setText("");
            result = true;

            if (result) {
                call.setResult(true, username);
                doDelete();
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(panel.panel);
                frame.dispose();
                System.out.println("Correct pass");
            } else {
                System.out.println("Incorrect pass");
            }

        }
    }

    private void showFrame() {
        //        // show UI
        JFrame frame = new JFrame("Login Form");
        panel = new LoginForm(this);
        frame.setContentPane(panel.panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                me.takeDown();
                e.getWindow().dispose();
                call.setResult(false, "");
            }
        });
    }

}
