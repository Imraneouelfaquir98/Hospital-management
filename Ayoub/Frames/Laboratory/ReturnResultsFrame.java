package Frames.Laboratory;

import javax.swing.*;
import java.awt.*;

public class ReturnResultsFrame extends JFrame {
    // Sample was take from that patient; send next one in;
    private JLabel patientId,testInfos;
    private JTextField patientIdf,testInfosf;

    private JButton dispatch;

    public ReturnResultsFrame() throws HeadlessException {
        // Window Configuration
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(400, 400);
        setLayout(new GridLayout(4, 2));

        // labels & textfields
        patientId=new JLabel("Patient Id: ");
        testInfos=new JLabel("Infos on test: ");
        patientIdf=new JTextField();
        testInfosf=new JTextField();

        dispatch=new JButton("Dispatch Results");

        // adding to layout
        add(patientId);
        add(patientIdf);
        add(testInfos);
        add(testInfosf);
        add(dispatch);

        //click listener
        dispatch.addActionListener(e -> System.out.println("Dispatching the results"));

        setVisible(true);
    }
}
