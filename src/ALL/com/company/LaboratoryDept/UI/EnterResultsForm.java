package com.company.LaboratoryDept.UI;

import com.company.LaboratoryDept.LaboratoryAgent;

import javax.swing.*;
import java.awt.*;

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

    public EnterResultsForm(LaboratoryAgent agent, String test) {
        this.agent = agent;

        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
//        for (String s : tests)
        model.addElement(test);
        comboBox.setModel(model);

        saveButton.addActionListener(e -> {
            // save all inputs and inform concerned agents
            agent.enterResultsBehavior(patientId.getText(), results.getText());
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

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        final JLabel label1 = new JLabel();
        label1.setText("Patient Id");
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        panel.add(label1, gbc);
        final JLabel label2 = new JLabel();
        label2.setText("Test Type");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        panel.add(label2, gbc);
        final JLabel label3 = new JLabel();
        label3.setText("Results");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        panel.add(label3, gbc);
        patientId = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(patientId, gbc);
        results = new JTextField();
        results.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.BOTH;
        panel.add(results, gbc);
        saveButton = new JButton();
        saveButton.setText("Save");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(saveButton, gbc);
        comboBox = new JComboBox();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(comboBox, gbc);
        backButton = new JButton();
        backButton.setText("Back");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(backButton, gbc);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel;
    }

}
