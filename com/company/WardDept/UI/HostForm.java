package com.company.WardDept.UI;

import com.company.WardDept.WardAgent;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HostForm {
    private WardAgent agent;

    private JPanel panel;
    private JTextField patientId;
    private JTextField bed;
    private JButton confirmButton;
    private JButton backButton;

    public HostForm(WardAgent agent) {
        this.agent=agent;

        confirmButton.addActionListener(e -> {
            System.out.println("Hosting :  " + patientId.getText());
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
