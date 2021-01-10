package Frames.Laboratory;

import javax.swing.*;
import java.awt.*;

public class LaboratoryFrame extends JFrame {
    private JButton nextPatient,returnResutls;

    public LaboratoryFrame() throws HeadlessException {
        // Window Configuration
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 400);
        setLayout(new BoxLayout( getContentPane(),BoxLayout.Y_AXIS));

        // Buttons
        nextPatient=new JButton("Next Patient");
        returnResutls=new JButton("Dispatch test results");

        // adding to layout
        add(nextPatient);
        add(returnResutls);

        // click Listeners
        nextPatient.addActionListener(e -> System.out.println("Send in the next patient!"));
        returnResutls.addActionListener(e -> new ReturnResultsFrame() );
        setVisible(true);
    }
}
