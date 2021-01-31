package com.company.WardDept.UI;

import com.company.WardDept.WardAgent;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;

public class ChoiceForm {
    private WardAgent agent;

    private JPanel panel;
    private JButton hostButton;
    private JButton checkoutButton;
    private JTable beds;
    private JTable visitors;

    public ChoiceForm(WardAgent agent) {
        this.agent = agent;

        hostButton.addActionListener(e -> {
            System.out.println("Hosting a patient ...");
            agent.switchToHostForm();
        });

        checkoutButton.addActionListener(e -> {
            System.out.println("Checkout of a patient ...");
            agent.switchToCheckoutForm();
        });
    }

    public JPanel getPanel(String[][] places, String[][] visitorss) {
        TableModel placesModel = new DefaultTableModel(places, new String[]{"Bed", "Patient"});
        beds.setModel(placesModel);

        TableModel visitsModel = new DefaultTableModel(visitorss, new String[]{"Patient", "Visitor", "Time"});
        visitors.setModel(visitsModel);

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
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        panel.add(panel1, gbc);
        hostButton = new JButton();
        hostButton.setText("Host");
        panel1.add(hostButton);
        checkoutButton = new JButton();
        checkoutButton.setText("Checkout");
        panel1.add(checkoutButton);
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        panel.add(panel2, gbc);
        final JScrollPane scrollPane1 = new JScrollPane();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        panel2.add(scrollPane1, gbc);
        beds = new JTable();
        beds.setEnabled(true);
        scrollPane1.setViewportView(beds);
        final JScrollPane scrollPane2 = new JScrollPane();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        panel2.add(scrollPane2, gbc);
        visitors = new JTable();
        scrollPane2.setViewportView(visitors);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel;
    }

}