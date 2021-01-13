package Frames.Ward;

import javax.swing.*;
import java.awt.*;

public class CreateWardAgentFrame extends JFrame {
    private JLabel type,maxPlaces;
    private JComboBox<String> types;
    private JTextField maxPlacesf;
    private JButton addAgent;

    public CreateWardAgentFrame() throws HeadlessException {
        // Window Configuration
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 400);
        setLayout(new GridLayout(3,2));

        // labels and fields ...
        type=new JLabel("Type of room:");
        maxPlaces=new JLabel("How many rooms does it contain? ");
        maxPlacesf=new JTextField();
        types=new JComboBox<>(new String[]{"Pregnants","Babies","ICU"});

        addAgent=new JButton("Add Agent");

        // adding to layout;
        add(type);
        add(types);
        add(maxPlaces);
        add(maxPlacesf);
        add(addAgent);

        //clickListener
        addAgent.addActionListener(e -> System.out.println("Adding Agent..."));

        setVisible(true);
    }
}
