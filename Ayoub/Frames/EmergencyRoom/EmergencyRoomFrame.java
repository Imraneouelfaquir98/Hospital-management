package Frames.EmergencyRoom;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmergencyRoomFrame extends JFrame {

    private JButton addPatient, testPatient, scheduleSurgery;

    public EmergencyRoomFrame() throws HeadlessException {
        // Window Configuration
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());
        // Buttons
        addPatient = new JButton("Add Patient");
        testPatient = new JButton("Test Patient");
        scheduleSurgery = new JButton("Schedule Surgery");
        add(addPatient);
        add(testPatient);
        add(scheduleSurgery);

        // Click Listeners: a new jframe must popup;
        addPatient.addActionListener(addListener);
        testPatient.addActionListener(testListener);
        scheduleSurgery.addActionListener(scheduleListener);

        setVisible(true);
    }



    private ActionListener addListener= e -> new AddPatientFrame();
    private ActionListener testListener= e ->new TestPatientFrame();
    private ActionListener scheduleListener= e -> new ScheduleSurgeryFrame();
}
