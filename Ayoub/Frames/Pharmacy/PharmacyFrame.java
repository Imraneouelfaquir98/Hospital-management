package Frames.Pharmacy;

import javax.swing.*;
import java.awt.*;

public class PharmacyFrame extends JFrame {
    private JButton requestMedicine,giveMedicine,addToStock;

    public PharmacyFrame() throws HeadlessException {
        // Window Configuration
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 400);
        setLayout(new BoxLayout( getContentPane(),BoxLayout.Y_AXIS));

        // Buttons
        requestMedicine=new JButton("Request Medicine");
        giveMedicine=new JButton("Give Medicine");
        addToStock=new JButton("Add Medicine to Stock");

        // adding to the layout
        add(requestMedicine);
        add(giveMedicine);
        add(addToStock);

        // click listening
        requestMedicine.addActionListener(e -> new RequestMedicineFrame());
        giveMedicine.addActionListener(e -> new GiveMedicineFrame());
        addToStock.addActionListener(e -> new AddToPharmacyFrame());

        setVisible(true);
    }
}
