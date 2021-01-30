package com.company.PharmacyDept.UI;

import com.company.PharmacyDept.PharmacyAgent;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddMedsForm {
    private PharmacyAgent agent;

    private JPanel panel;
    private JTextField medicine;
    private JTextField qty;
    private JButton backButton;
    private JButton confirmAddingButton;

    public AddMedsForm(PharmacyAgent agent) {
        this.agent = agent;

        backButton.addActionListener(e -> {
            System.out.println("Going back ...");
            agent.switchToChoiceForm();
            clearInputs();
        });
        confirmAddingButton.addActionListener(e -> {
            System.out.println("Going back ...");
            // TODO: first check the situation
            agent.addMedsBehavior(medicine.getText(),Integer.parseInt(qty.getText()));
            clearInputs();
        });
    }

    private void clearInputs() {
        medicine.setText("");
        qty.setText("");
    }

    public JPanel getPanel() {
        return panel;
    }

}
