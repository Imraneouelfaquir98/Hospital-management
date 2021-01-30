package com.company.LaboratoryDept.UI;

import com.company.LaboratoryDept.LaboratoryAgent;

import javax.swing.*;

public class ChoiceForm {
    private LaboratoryAgent agent;

    private JPanel panel;
    private JButton nextPatientButton;
    private JButton enterResultsButton;

    public ChoiceForm(LaboratoryAgent agent) {
        this.agent = agent;

        nextPatientButton.addActionListener(e -> {
            // todo
            System.out.println("Calling next Patient!");
        });
        enterResultsButton.addActionListener(e -> {
            // todo
            System.out.println("Entering Results");
            agent.switchToAddMedsForm();
        });
    }

    public JPanel getPanel() {
        return panel;
    }

}
