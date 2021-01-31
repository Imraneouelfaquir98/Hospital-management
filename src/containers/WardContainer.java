package containers;

import com.company.Login.Callable;

import static com.company.Login.LoginMain.Run;
import static com.company.Utils.createAgent;

public class WardContainer implements Callable {

    private static final String DEPT = "Ward";

    public static void main(String[] args) {
        Run(DEPT, new WardContainer());
    }

    @Override
    public void setResult(boolean loggedIn, String username) {
        if (loggedIn) {
            createAgent("com.company.WardDept.WardAgent",DEPT,username);
        } else {
            // User Closed the window.
            System.out.println("no pass");
        }
    }
}
