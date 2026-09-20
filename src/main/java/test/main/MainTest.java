package test.main;

import org.mustabelmo.java.githubactions.Core;

public class MainTest {
    public static void main(String[] args) {
        String test = Core.getInput("test");
        System.out.println("this a test for " + test);
    }
}
