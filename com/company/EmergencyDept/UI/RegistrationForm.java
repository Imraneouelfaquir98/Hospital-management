package com.company.EmergencyDept.UI;

import com.company.EmergencyDept.EmergencyAgent;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistrationForm {
    private EmergencyAgent agent;

    private JPanel panel;
    private JTextField textField1;
    private JTextField textField2;
    private JButton registerButton;
    private JButton backButton;

    public RegistrationForm(EmergencyAgent agent) {
        this.agent = agent;

        backButton.addActionListener(e -> {
            System.out.println("switch to choice ui ...");
            agent.switchToChoiceForm();
        });
        registerButton.addActionListener(e -> {
            System.out.println("registration completed + switch to choice ui ...");
            // TODO: make sure It's registred
            agent.switchToChoiceForm();
        });
    }

    public JPanel getPanel() {
        return panel;
    }
}
