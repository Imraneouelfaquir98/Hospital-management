package Frames.Ward;

import javax.swing.*;
import java.awt.*;

public class WardFrame extends JFrame {
    private JButton host, checkout;

    public WardFrame() throws HeadlessException {
        // Window Configuration
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 400);
        setLayout(new BoxLayout( getContentPane(),BoxLayout.Y_AXIS));

        // buttons
        host=new JButton("Host");
        checkout=new JButton("Checkout");

        // adding to layout
        add( host);
        add(checkout);

        // click listeners
        host.addActionListener(e -> new HostFrame());
        checkout.addActionListener(e -> new CheckoutFrame());

        setVisible(true);
    }
}
