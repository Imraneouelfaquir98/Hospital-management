package com.company.LaboratoryDept;

import com.company.LaboratoryDept.UI.ChoiceForm;
import com.company.LaboratoryDept.UI.EnterResultsForm;
import jade.core.Agent;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class LaboratoryAgent extends Agent {

    private final LaboratoryAgent me = this;

    @Override
    protected void setup() {
        super.setup();

        showFrame();

    }

    @Override
    protected void takeDown() {
        super.takeDown();

        System.out.println("turning down Lab... ");
    }

    private void showFrame() {
        //        // show UI
        frame = new JFrame("Laboratory");

        choiceForm = new ChoiceForm(this);
        enterResultsForm = new EnterResultsForm(this);

        switchToChoiceForm();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                e.getWindow().dispose();
                me.takeDown();
            }
        });
    }

    /////////////////////////////////////////////////////////  UI transition methods

    private ChoiceForm choiceForm;
    private EnterResultsForm enterResultsForm;
    private JFrame frame;

    public void switchToChoiceForm() {
        switchPanel(choiceForm.getPanel());
    }

    public void switchToAddMedsForm() {
        switchPanel(enterResultsForm.getPanel());
    }

    private void switchPanel(JPanel panel) {
        frame.setContentPane(panel);
        frame.revalidate();
        frame.pack();
    }
}
