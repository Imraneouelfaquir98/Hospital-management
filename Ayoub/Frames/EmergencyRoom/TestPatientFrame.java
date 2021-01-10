package Frames.EmergencyRoom;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TestPatientFrame extends JFrame {
    private JLabel id, test;
    private JTextField idf;
    private JComboBox<String> tests;
    private JButton requestTests;

    public TestPatientFrame() throws HeadlessException {
        // Window Configuration;
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(400, 400);
        setLayout(new GridLayout(3, 2));

        // labels  text fields  button;
        id = new JLabel("Id: ");
        test = new JLabel("Tests: ");
        idf = new JTextField();
        tests = new JComboBox<>(new String[]{"salam 3Alaykom", "blood", "Radio", "Heart"});
        requestTests = new JButton("Request Tests");

        // adding
        add(id);
        add(idf);
        add(test);
        add(tests);
        add(requestTests);

        // click listener
        requestTests.addActionListener(e -> System.out.println("Requesting Tests..." +
                idf.getText() + " : " + tests.getSelectedItem()));

        setVisible(true);
    }
}
