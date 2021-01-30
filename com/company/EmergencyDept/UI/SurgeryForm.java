package com.company.EmergencyDept.UI;

import com.company.EmergencyDept.EmergencyAgent;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SurgeryForm {
    private EmergencyAgent agent;

    private JPanel panel;
    private JTextField textField1;
    private JComboBox<String> comboBox1;
    private JButton backButton;
    private JButton scheduleSurgeryButton;

    private final String[] surgeries = {"aaaa", "dsdsfds", "ssasaa", "sss"};

    public SurgeryForm(EmergencyAgent agent) {
        this.agent = agent;

        // Adding in combobox
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        for (String s : surgeries)
            model.addElement(s);
        comboBox1.setModel(model);

        // Clicklisteners
        backButton.addActionListener(e -> {
            System.out.println("switch to choice ui ...");
            agent.switchToChoiceForm();
        });
        scheduleSurgeryButton.addActionListener(e -> {
            System.out.println("scheduling + switch to choice ui ...");
            //TODO: schedule surgery and get the answer on the date.
            agent.switchToChoiceForm();
        });
    }

    public JPanel getPanel() {
        return panel;
    }
}
