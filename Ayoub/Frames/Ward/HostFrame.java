package Frames.Ward;

import javax.swing.*;
import java.awt.*;

public class HostFrame extends JFrame {
    private JLabel patientId,bedId;
    private JTextField patientIdf,bedIdf;

    private JButton host;

    public HostFrame() throws HeadlessException {
        // Window Configuration
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(400, 400);
        setLayout(new GridLayout(3, 2));

        // labels & textfields
        patientId=new JLabel("Patient Id: ");
        bedId=new JLabel("Bed Id: ");
        patientIdf=new JTextField();
        bedIdf=new JTextField();

        host=new JButton("Host Patient");

        // adding to layout
        add(patientId);
        add(patientIdf);
        add(bedId);
        add(bedIdf);
        add(host);

        //click listener
        host.addActionListener(e -> System.out.println("Hosting Patient: "+patientIdf.getText()));

        setVisible(true);
    }

}
