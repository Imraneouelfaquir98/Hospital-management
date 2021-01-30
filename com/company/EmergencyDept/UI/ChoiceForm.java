package com.company.EmergencyDept.UI;

import com.company.EmergencyDept.EmergencyAgent;

import javax.crypto.ExemptionMechanism;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChoiceForm {
    private EmergencyAgent agent;

    private JPanel panel;
    private JButton registerPatientButton;
    private JButton performTestsButton;
    private JButton scheduleSurgeryButton;

    public ChoiceForm(EmergencyAgent agent) {
        this.agent = agent;

        // Click listeners
        registerPatientButton.addActionListener(e -> {
            System.out.println("Switching to Registration form...");
            agent.switchToRegistrationForm();
        });
        performTestsButton.addActionListener(e -> {
            System.out.println("Switching to Testing form...");
            agent.switchToTestForm();
        });
        scheduleSurgeryButton.addActionListener(e -> {
            System.out.println("Switching to Surgery form...");
            agent.switchToSurgeryForm();
        });
    }

    public JPanel getPanel() {
        return panel;
    }
}
