package com.company.PharmacyDept.UI;

import Agents.Pharmacy;
import com.company.PharmacyDept.PharmacyAgent;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RequestForm {
    private PharmacyAgent agent;

    private JPanel panel;
    private JTextField med;
    private JTextField qty;
    private JTextField supplier;
    private JButton backButton;
    private JButton confirmRequestButton;

    public RequestForm(PharmacyAgent agent) {
        this.agent = agent;
        backButton.addActionListener(e -> {
            System.out.println("Going back ...");
            clearInputs();
            agent.switchToChoiceForm();
        });
        confirmRequestButton.addActionListener(e -> {
            // TODO: send mail to supplier
            clearInputs();
            agent.switchToChoiceForm();
        });
    }

    private void clearInputs() {
        med.setText("");
        qty.setText("");
        supplier.setText("");
    }

    public JPanel getPanel() {
        return panel;
    }
}
