package com.company.SurgeryDept;

import com.company.WardDept.UI.CheckoutForm;
import com.company.WardDept.UI.ChoiceForm;
import com.company.WardDept.UI.HostForm;
import jade.core.Agent;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ORAgent extends Agent {

    private final ORAgent me = this;

    @Override
    protected void setup() {
        super.setup();
        System.out.println("turning up OR... ");


    }

    @Override
    protected void takeDown() {
        super.takeDown();

        System.out.println("turning down OR... ");
    }

}
