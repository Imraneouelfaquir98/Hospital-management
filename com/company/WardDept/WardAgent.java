package com.company.WardDept;

import com.company.WardDept.UI.CheckoutForm;
import com.company.WardDept.UI.ChoiceForm;
import com.company.WardDept.UI.HostForm;
import jade.core.Agent;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class WardAgent extends Agent {

    private final WardAgent me = this;

    @Override
    protected void setup() {
        super.setup();

        showFrame();

    }

    @Override
    protected void takeDown() {
        super.takeDown();

        System.out.println("turning down ward... ");
    }

    /////////////////////////////////////////////////////////  UI transition methods

    private ChoiceForm choiceForm;
    private CheckoutForm checkoutForm;
    private HostForm hostForm;
    private JFrame frame;


    private void showFrame() {
        //        // show UI
        frame = new JFrame("Ward");

        choiceForm = new ChoiceForm(this);
        hostForm = new HostForm(this);
        checkoutForm = new CheckoutForm(this);

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

    public void switchToChoiceForm() {
        switchPanel(choiceForm.getPanel());
    }
    public void switchToCheckoutForm() {
        switchPanel(checkoutForm.getPanel());
    }
    public void switchToHostForm() {
        switchPanel(hostForm.getPanel());
    }

    private void switchPanel(JPanel panel) {
        frame.setContentPane(panel);
        frame.revalidate();
        frame.pack();
    }
}
