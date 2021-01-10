package Frames.Pharmacy;

import javax.swing.*;
import java.awt.*;

public class GiveMedicineFrame extends JFrame {
    private JLabel medicine, quantity, patient;
    private JTextField medicinef, quantityf, patientf;
    private JButton give;

    public GiveMedicineFrame() throws HeadlessException {
        // Window Configuration
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(400, 400);
        setLayout(new GridLayout(4, 2));

        // labels & fields;
        medicine = new JLabel("Medicine Name: ");
        quantity = new JLabel("Quantity: ");
        patient = new JLabel("Patient Id: ");
        medicinef = new JTextField();
        quantityf = new JTextField();
        patientf = new JTextField();

        // button;
        give = new JButton("Give");

        // adding to the layout;
        add(medicine);
        add(medicinef);
        add(quantity);
        add(quantityf);
        add(patient);
        add(patientf);

        add(give);

        // action listener
        give.addActionListener(e -> System.out.println("Giving:" + medicinef.getText()));

        setVisible(true);
    }
}
