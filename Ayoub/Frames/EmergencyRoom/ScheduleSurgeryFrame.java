package Frames.EmergencyRoom;

import javax.swing.*;
import java.awt.*;

public class ScheduleSurgeryFrame extends JFrame {
    private JLabel id, surgery;
    private JTextField idf;
    private JComboBox<String> surgeries;
    private JButton scheduleSurgery;

    public ScheduleSurgeryFrame() throws HeadlessException {
        // Window Configuration;
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(400, 400);
        setLayout(new GridLayout(3, 2));

        // labels  text fields  button;
        id = new JLabel("Id: ");
        surgery = new JLabel("Surgery: ");
        idf = new JTextField();
        surgeries = new JComboBox<>(new String[]{"Wa 3Alaykom salam", "bones", "heart", "teeth", "imrane ", "ait hammadi"});
        scheduleSurgery = new JButton("Request Tests");

        // adding
        add(id);
        add(idf);
        add(surgery);
        add(surgeries);
        add(scheduleSurgery);

        // click listener
        scheduleSurgery.addActionListener(e -> System.out.println("Requesting Tests..." +
                idf.getText() + " : " + surgeries.getSelectedItem()));

        setVisible(true);
    }
}
