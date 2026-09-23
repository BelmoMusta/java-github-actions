package test.main;

import org.mustabelmo.java.githubactions.Action;
import org.mustabelmo.java.githubactions.Core;

public class MainTest implements Action {

    @Override
    public void run() {
        String inputs = Core.getInput("test");
        System.out.println("Inputs == " + inputs);
        System.out.println("inside the run method");
    }
}
