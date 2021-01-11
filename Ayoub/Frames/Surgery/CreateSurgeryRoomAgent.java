package Frames.Surgery;

import javax.swing.*;
import java.awt.*;

public class CreateSurgeryRoomAgent extends JFrame {
    private JButton addSurgeryRoom;
    private JButton addSurgeon;
    private JButton addNurse;

    public CreateSurgeryRoomAgent() throws HeadlessException {
        // Window Configuration
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 400);
        setLayout(new FlowLayout());

        // button;
        addSurgeryRoom=new JButton("Add Surgery room Agent");
        addSurgeon=new JButton("Add human surgeon");
        addNurse=new JButton("Add human nurse");

        // aadding to layout
        add(addSurgeryRoom);
        add(addSurgeon);
        add(addNurse);

        //  click listener
        addSurgeryRoom.addActionListener(e -> System.out.println("Adding new Surgery room agent... "));
        addSurgeon.addActionListener(e -> new AddSurgeonFrame());
        addNurse.addActionListener(e -> new AddNurseFrame());

        setVisible(true);
    }
}
