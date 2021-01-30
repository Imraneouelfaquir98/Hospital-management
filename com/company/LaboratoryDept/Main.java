package com.company.LaboratoryDept;

import com.company.Login.Callable;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.StaleProxyException;

import static com.company.Login.LoginMain.Run;

public class Main implements Callable {

    private static final String DEPT = "Laboratory";

    public static void main(String[] args) {
        Run(DEPT, new Main());
    }

    @Override
    public void setResult(boolean loggedIn) {
        if (loggedIn) {
            //Get the JADE runtime interface (singleton)
            jade.core.Runtime runtime = jade.core.Runtime.instance();
//Create a Profile, where the launch arguments are stored
            Profile profile = new ProfileImpl();
            profile.setParameter(Profile.CONTAINER_NAME, DEPT);
            profile.setParameter(Profile.MAIN_HOST, "localhost");
//create a non-main agent container
            ContainerController container = runtime.createAgentContainer(profile);
            try {
                AgentController ag = container.createNewAgent("RoomAt" + DEPT,
                        "com.company.LaboratoryDept.LaboratoryAgent",
                        new Object[]{});//arguments
                ag.start();
            } catch (StaleProxyException e) {
                e.printStackTrace();
            }

        } else {
            // User Closed the window.
            System.out.println("no pass");
        }
    }
}
