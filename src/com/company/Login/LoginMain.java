package com.company.Login;

import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.StaleProxyException;

public class LoginMain {


    public static void Run(String department, Callable call) {

        //Get the JADE runtime interface (singleton)
        jade.core.Runtime runtime = jade.core.Runtime.instance();
//Create a Profile, where the launch arguments are stored
        Profile profile = new ProfileImpl();
        profile.setParameter(Profile.CONTAINER_NAME, department);
        profile.setParameter(Profile.MAIN_HOST, "localhost");
//create a non-main agent container
        ContainerController container = runtime.createAgentContainer(profile);
        try {
            AgentController ag = container.createNewAgent("LoginTo" + department,
                    "com.company.Login.LoginAgent",
                    new Object[]{call});//arguments
            ag.start();
        } catch (StaleProxyException e) {
            e.printStackTrace();
        }
    }
}
