package test.main;

import org.mustabelmo.java.githubactions.Core;
import org.mustabelmo.java.githubactions.Properties;

public class MainTest {
    public static void main(String[] args) {
        String inputs = Properties.get("__inputs");
        System.out.println("Inputs == " + inputs);
    }
}
