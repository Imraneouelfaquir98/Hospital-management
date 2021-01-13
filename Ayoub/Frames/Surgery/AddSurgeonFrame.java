package Frames.Surgery;

import javax.swing.*;
import java.awt.*;

public class AddSurgeonFrame extends JFrame {
    private JLabel id, cin, name, address, specialty;
    private JTextField idf, cinf, namef, addressf, specialtyf;
    private JButton  add;

    public AddSurgeonFrame() throws HeadlessException {
        // Window Configuration;
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(400, 400);
        setLayout(new GridLayout(6, 2));

        // Labels & Text Fields;
        id = new JLabel("Id: ");
        cin = new JLabel("CIN: ");
        name = new JLabel("Name: ");
        address = new JLabel("Address: ");
        specialty = new JLabel("Specialty: ");    // todo: provide specific fields
        idf = new JTextField();
        cinf = new JTextField();
        namef = new JTextField();
        addressf = new JTextField();
        specialtyf = new JTextField();

        // Buttons
        add = new JButton("Add Surgeon");

        // Add in the layout
        add(id);
        add(idf);
        add(cin);
        add(cinf);
        add(name);
        add(namef);
        add(address);
        add(addressf);
        add(specialty);
        add(specialtyf);
        add(add);

        // ClickListeners
        add.addActionListener(e -> System.out.println("Adding Surgeon to DB"));

        setVisible(true);
    }
}
