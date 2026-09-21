package test.main;

import org.mustabelmo.java.githubactions.Core;
import org.mustabelmo.java.githubactions.Properties;

public class MainTest {
    public static void main(String[] args) {
        String test = Core.getInput("test");
        String inputs = Properties.get("inputs");
        System.out.println("Inputs == " + inputs);
    }
}
