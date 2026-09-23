package test.main;

import org.mustabelmo.java.githubactions.Core;

public class MainTest {
    public static void main(String[] args) {
        String inputs = Core.getInput("test");
        System.out.println("Inputs == " + inputs);
    }
}
