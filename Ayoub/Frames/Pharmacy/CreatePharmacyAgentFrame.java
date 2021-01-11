package Frames.Pharmacy;

import javax.swing.*;
import java.awt.*;

public class CreatePharmacyAgentFrame extends JFrame {
    private JButton addPharmacy;

    public CreatePharmacyAgentFrame() throws HeadlessException {
        // Window Configuration
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 400);
        setLayout(new FlowLayout());

        // button;
        addPharmacy=new JButton("Add Pharmacy Agent");

        // aadding to layout
        add(addPharmacy);

        //  click listener
        addPharmacy.addActionListener(e -> System.out.println("Adding new Pharmacy... "));

        setVisible(true);
    }
}
