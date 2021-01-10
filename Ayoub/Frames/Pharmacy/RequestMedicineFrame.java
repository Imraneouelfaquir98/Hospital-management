package Frames.Pharmacy;

import javax.swing.*;
import java.awt.*;

public class RequestMedicineFrame extends JFrame {
    private JLabel medicine, quantity, supplier;
    private JTextField medicinef, quantityf, supplierf;
    private JButton request;

    public RequestMedicineFrame() throws HeadlessException {
        // Window Configuration
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(400, 400);
        setLayout(new GridLayout(4, 2));

        // labels & fields;
        medicine = new JLabel("Medicine Name: ");
        quantity = new JLabel("Quantity: ");
        supplier = new JLabel("Supplier to contact: ");
        medicinef = new JTextField();
        quantityf = new JTextField();
        supplierf = new JTextField();

        // button;
        request = new JButton("Request");

        // adding to the layout;
        add(medicine);
        add(medicinef);
        add(quantity);
        add(quantityf);
        add(supplier);
        add(supplierf);

        add(request);

        // action listener
        request.addActionListener(e->System.out.println("Requesting:"+medicinef.getText()));

        setVisible(true);
    }
}
