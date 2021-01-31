package containers;

import com.company.Login.Callable;

import static com.company.Login.LoginMain.Run;
import static com.company.Utils.createAgent;

public class EmergencyContainer implements Callable {

    private static final String DEPT = "Emergency";

    public static void main(String[] args) {
        Run(DEPT, new EmergencyContainer());
    }

    @Override
    public void setResult(boolean loggedIn, String username) {
        if (loggedIn) {
            createAgent("com.company.EmergencyDept.EmergencyAgent", DEPT, username);
        } else {
            // User Closed the window.
            System.out.println("no pass");
        }
    }
}
