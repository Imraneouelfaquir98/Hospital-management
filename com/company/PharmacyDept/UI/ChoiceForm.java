package com.company.PharmacyDept.UI;

import Agents.Pharmacy;
import com.company.PharmacyDept.PharmacyAgent;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChoiceForm {
    private PharmacyAgent agent;

    private JPanel panel;
    private JButton giveMedecineButton;
    private JButton requestMedecineButton;
    private JButton addMedsToStockButton;

    public ChoiceForm(PharmacyAgent agent) {
        this.agent = agent;

        giveMedecineButton.addActionListener(e -> {
            System.out.println("Switch to giving mode;");
            agent.switchToGiveMedsForm();
        });
        requestMedecineButton.addActionListener(e -> {
            System.out.println("Switch to requesting meds;");
            agent.switchToRequestForm();
        });
        addMedsToStockButton.addActionListener(e -> {
            System.out.println("Switch to receiving meds;");
            agent.switchToAddMedsForm();
        });
    }

    public JPanel getPanel() {
        return panel;
    }
}
