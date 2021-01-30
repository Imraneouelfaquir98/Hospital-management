package com.company.WardDept.UI;

import com.company.WardDept.WardAgent;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChoiceForm {
    private WardAgent agent;

    private JPanel panel;
    private JButton hostButton;
    private JButton checkoutButton;

    public ChoiceForm(WardAgent agent) {
        this.agent = agent;

        hostButton.addActionListener(e -> {
            System.out.println("Hosting a patient ...");
            agent.switchToHostForm();
        });

        checkoutButton.addActionListener(e -> {
            System.out.println("Checkout of a patient ...");
            agent.switchToCheckoutForm();
        });
    }

    public JPanel getPanel() {
        return panel;
    }
}
