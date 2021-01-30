package com.company.WardDept.UI;

import com.company.WardDept.WardAgent;

import javax.swing.*;

public class CheckoutForm {
    private WardAgent agent;

    private JPanel panel;
    private JButton backButton;
    private JButton checkOutButton;
    private JTextField bed;
    private JTextField patientId;

    public CheckoutForm(WardAgent agent) {
        this.agent = agent;

        checkOutButton.addActionListener(e -> {
            System.out.println("checking xxx out :  " + patientId);
            clearInputs();

        });
        backButton.addActionListener(e -> {
            System.out.println("Going back");
            clearInputs();
            agent.switchToChoiceForm();

        });
    }

    public JPanel getPanel() {
        return panel;
    }

    private void clearInputs() {
        patientId.setText("");
        bed.setText("");
    }
}
