package com.company.PharmacyDept.UI;

import Agents.Pharmacy;
import com.company.PharmacyDept.PharmacyAgent;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GiveMedsForm {
    private PharmacyAgent agent;

    private JPanel panel;
    private JTextField patientId;
    private JTextField med;
    private JTextField qty;
    private JButton backButton;
    private JButton confirmButton;

    public GiveMedsForm(PharmacyAgent agent) {
        this.agent = agent;

        backButton.addActionListener(e -> {
            System.out.println("Going back ...");
            clearInputs();
            agent.switchToChoiceForm();
        });

        confirmButton.addActionListener(e -> {
            // todo: check stock first;
            System.out.println("checking stock ...");
            String me = med.getText();
            int q = Integer.parseInt(qty.getText());
            clearInputs();
            int res=agent.giveMedsBehavior(patientId.getText(), me, q);
            if(res!=-1)
                qty.setText("We Only have: "+res);

        });
    }

    public JPanel getPanel() {
        return panel;
    }

    private void clearInputs() {
        med.setText("");
        qty.setText("");
    }
}
