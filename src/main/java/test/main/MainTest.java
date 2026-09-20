package test.main;

import org.mustabelmo.java.githubactions.Core;
import org.mustabelmo.java.githubactions.Properties;

import java.util.Map;

public class MainTest {
    public static void main(String[] args) {
        String test = Core.getInput("test");
        for (Map.Entry<String, String> stringStringEntry : Properties.CORE_PROPERTIES.entrySet()) {
            System.out.println("Key : "+stringStringEntry.getKey() + ", value: " + stringStringEntry.getValue());
        }
        System.out.println("this a test for " + test);
    }
}
