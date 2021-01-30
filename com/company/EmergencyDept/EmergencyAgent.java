package com.company.EmergencyDept;

import com.company.EmergencyDept.UI.ChoiceForm;
import com.company.EmergencyDept.UI.SurgeryForm;
import com.company.EmergencyDept.UI.TestForm;
import com.company.EmergencyDept.UI.RegistrationForm;
import jade.core.Agent;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class EmergencyAgent extends Agent {

    private final EmergencyAgent me = this;

    @Override
    protected void setup() {
        super.setup();

        showFrame();

    }

    @Override
    protected void takeDown() {
        super.takeDown();

        System.out.println("turning down Emergency ... ");
    }

    private void showFrame() {
        //        // show UI
        frame = new JFrame("Emergency Room");

        choiceForm = new ChoiceForm(this);
        registrationForm = new RegistrationForm(this);
        testForm = new TestForm(this);
        surgeryForm = new SurgeryForm(this);

        switchToChoiceForm();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                me.takeDown();
                e.getWindow().dispose();
            }
        });
    }

    /////////////////////////////////////////////////////////  UI transition methods
    private ChoiceForm choiceForm;
    private RegistrationForm registrationForm;
    private TestForm testForm;
    private SurgeryForm surgeryForm;
    private JFrame frame;

    public void switchToChoiceForm() {
        switchPanel(choiceForm.getPanel());
    }

    public void switchToRegistrationForm() {
        switchPanel(registrationForm.getPanel());
    }

    public void switchToTestForm() {
        switchPanel(testForm.getPanel());
    }

    public void switchToSurgeryForm(){
        switchPanel(surgeryForm.getPanel());
    }

    private void switchPanel(JPanel panel){
        frame.setContentPane(panel);
        frame.revalidate();
        frame.pack();
    }
}
