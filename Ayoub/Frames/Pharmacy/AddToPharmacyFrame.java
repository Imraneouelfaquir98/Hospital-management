package Frames.Pharmacy;

import javax.swing.*;
import java.awt.*;

public class AddToPharmacyFrame extends JFrame {
    private JLabel medicine, quantity;
    private JTextField medicinef, quantityf;
    private JButton stock;

    public AddToPharmacyFrame() throws HeadlessException {
        // Window Configuration
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(400, 400);
        setLayout(new GridLayout(3, 2));

        // labels & fields;
        medicine = new JLabel("Medicine Name: ");
        quantity = new JLabel("Quantity: ");
        medicinef = new JTextField();
        quantityf = new JTextField();

        // button;
        stock = new JButton("Stock");

        // adding to the layout;
        add(medicine);
        add(medicinef);
        add(quantity);
        add(quantityf);

        add(stock);

        // action listener
        stock.addActionListener(e -> System.out.println("Stocking ... :" + medicinef.getText()));

        setVisible(true);
    }
}
