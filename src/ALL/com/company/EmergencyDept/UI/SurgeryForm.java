package com.company.EmergencyDept.UI;

import com.company.EmergencyDept.EmergencyAgent;

import javax.swing.*;
import java.awt.*;

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
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(label1, gbc);
        textField1 = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(textField1, gbc);
        final JLabel label2 = new JLabel();
        label2.setText("Surgery Type");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(label2, gbc);
        comboBox1 = new JComboBox();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(comboBox1, gbc);
        backButton = new JButton();
        backButton.setText("Back");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        panel.add(backButton, gbc);
        scheduleSurgeryButton = new JButton();
        scheduleSurgeryButton.setText("Schedule Surgery");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        panel.add(scheduleSurgeryButton, gbc);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel;
    }

}
