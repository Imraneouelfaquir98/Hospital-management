package Frames.Ward;

import javax.swing.*;
import java.awt.*;

public class CheckoutFrame extends JFrame {
    private JLabel patientId;
    private JTextField patientIdf;

    private JButton host;

    public CheckoutFrame() throws HeadlessException {
        // Window Configuration
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(400, 400);
        setLayout(new GridLayout(2, 2));

        // labels & textfields
        patientId=new JLabel("Patient Id: ");
        patientIdf=new JTextField();

        host=new JButton("Checkout Patient");

        // adding to layout
        add(patientId);
        add(patientIdf);
        add(host);

        //click listener
        host.addActionListener(e -> System.out.println("Checking out Patient: "+patientIdf.getText()));

        setVisible(true);
    }

}
