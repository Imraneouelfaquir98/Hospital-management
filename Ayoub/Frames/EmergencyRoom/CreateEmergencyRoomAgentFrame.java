package Frames.EmergencyRoom;

import javax.swing.*;
import java.awt.*;

public class CreateEmergencyRoomAgentFrame extends JFrame {
    private JButton addEmergency;

    public CreateEmergencyRoomAgentFrame() throws HeadlessException {
        // Window Configuration
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 400);
        setLayout(new FlowLayout());

        // button;
        addEmergency=new JButton("Add Emergency Agent");

        // aadding to layout
        add(addEmergency);

        //  click listener
        addEmergency.addActionListener(e -> System.out.println("Adding new Emergency room agent... "));

        setVisible(true);
    }

}
