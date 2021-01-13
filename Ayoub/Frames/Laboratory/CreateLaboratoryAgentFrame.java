package Frames.Laboratory;

import javax.swing.*;
import java.awt.*;

public class CreateLaboratoryAgentFrame extends JFrame {
    private JLabel type;
    private JComboBox<String> types;
    private JButton addLab;

    public CreateLaboratoryAgentFrame() throws HeadlessException {
        // Window Configuration
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 400);
        setLayout(new GridLayout(2,2));

        // label  field...
        type=new JLabel("Type of Lab: ");
        types=new JComboBox<>(new String[]{"Blood","Heart","a9cha","Agayo"});
        addLab=new JButton("Add Lab Agent");

        // adding to layout
        add(type);
        add(types);
        add(addLab);

        //click listener
        addLab.addActionListener(e -> System.out.println("Adding new Lab agent;;;"));

        setVisible(true);
    }
}
