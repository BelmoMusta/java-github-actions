package test.main;

import org.mustabelmo.java.githubactions.sdk.Action;
import org.mustabelmo.java.githubactions.sdk.Core;

public class MainTest implements Action {

    @Override
    public void run() {
        String inputs = Core.getInput("test");
        System.out.println("Inputs == " + inputs);
        System.out.println("inside the run method");
    }
}
