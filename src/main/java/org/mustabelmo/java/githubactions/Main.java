package org.mustabelmo.java.githubactions;

import org.json.JSONObject;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("FFFFFFf");
        String inputs = Properties.get("__inputs");
        JSONObject jsonInputs = new JSONObject(inputs);
        String javaClass = jsonInputs.getString("java-class");
        Class<?> aClass = Class.forName(javaClass);
        aClass.getMethod("main").invoke(args);
    }
}
