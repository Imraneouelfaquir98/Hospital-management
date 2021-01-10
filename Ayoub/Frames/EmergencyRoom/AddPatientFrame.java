package Frames.EmergencyRoom;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class AddPatientFrame extends JFrame {

    private JLabel id, cin, name, address, health;
    private JTextField idf, cinf, namef, addressf, healthf;
    private JButton generateId, add;
    // Note Either id or CIN must be specified;

    public AddPatientFrame() throws HeadlessException {
        // Window Configuration;
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(400, 400);
        setLayout(new GridLayout(6, 2));

        // Labels & Text Fields;
        id = new JLabel("Id: ");
        cin = new JLabel("CIN: ");
        name = new JLabel("Name: ");
        address = new JLabel("Address: ");
        health = new JLabel("Health State: ");    // todo: provide specific fields
        idf = new JTextField();
        cinf = new JTextField();
        namef = new JTextField();
        addressf = new JTextField();
        healthf = new JTextField();

        // Buttons
        add = new JButton("Add patient");
        generateId = new JButton("generateId");

        // Add in the layout
        add(id);
        add(idf);
        add(cin);
        add(cinf);
        add(name);
        add(namef);
        add(address);
        add(addressf);
        add(health);
        add(healthf);
        add(add);
        add(generateId);

        // ClickListeners
        add.addActionListener(addListener);
        generateId.addActionListener(generateListener);

        setVisible(true);
    }


    private ActionListener addListener = e -> System.out.println("Adding successfully...");
    private ActionListener generateListener = e -> System.out.println("Generating the id...");
}
