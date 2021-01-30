package com.company.EmergencyDept.UI;

import com.company.EmergencyDept.EmergencyAgent;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TestForm {
    private EmergencyAgent agent;

    private JPanel panel;
    private JTextField patientId;
    private JComboBox<String> comboBox1;
    private JButton backButton;
    private JButton informLabButton;

    private final String[] tests = {"aaaa", "dsdsfds", "ssasaa", "sss"};

    public TestForm(EmergencyAgent agent) {
        this.agent = agent;
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        for (String s : tests)
            model.addElement(s);
        comboBox1.setModel(model);


        backButton.addActionListener(e -> {
            System.out.println("switch to choice ui ...");
            agent.switchToChoiceForm();
        });
        informLabButton.addActionListener(e -> {
            System.out.println("request to lab completed + switch to choice ui ..." + patientId.getText());
            // TODO: inform lab and get their answer back;
            agent.switchToChoiceForm();
        });
    }

    public JPanel getPanel() {
        return panel;
    }

}
