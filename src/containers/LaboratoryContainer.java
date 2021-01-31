package containers;

import com.company.Login.Callable;

import static com.company.Login.LoginMain.Run;
import static com.company.Utils.createAgent;

public class LaboratoryContainer implements Callable {

    private static final String DEPT = "Laboratory";

    public static void main(String[] args) {
        Run(DEPT, new LaboratoryContainer());
    }

    @Override
    public void setResult(boolean loggedIn, String username) {
        if (loggedIn) {
            createAgent("com.company.LaboratoryDept.LaboratoryAgent",DEPT,username);
        } else {
            // User Closed the window.
            System.out.println("no pass");
        }
    }
}
