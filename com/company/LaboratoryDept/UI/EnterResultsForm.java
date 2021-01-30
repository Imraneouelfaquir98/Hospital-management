package com.company.LaboratoryDept.UI;

import Agents.Laboratory;
import com.company.LaboratoryDept.LaboratoryAgent;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EnterResultsForm {
    private LaboratoryAgent agent;

    private JPanel panel;
    private JTextField patientId;
    private JTextField results;
    private JButton browseButton;
    private JButton saveButton;
    private JComboBox<String> comboBox;
    private JButton backButton;

    private String imagePath;
    private final String[] tests = {"aaaa", "dsdsfds", "ssasaa", "sss"};

    public EnterResultsForm(LaboratoryAgent agent) {
        this.agent=agent;

        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        for (String s : tests)
            model.addElement(s);
        comboBox.setModel(model);

        browseButton.addActionListener(e -> {
            // todo : choose image then send it

        });
        saveButton.addActionListener(e -> {
            // save all inputs and inform concerned agents
            clearInputs();
        });
        backButton.addActionListener(e -> {
            clearInputs();
            agent.switchToChoiceForm();
        });
    }

    public JPanel getPanel() {
        return panel;
    }

    public void clearInputs() {
        imagePath = "";
        results.setText("");
        patientId.setText("");
    }
}
