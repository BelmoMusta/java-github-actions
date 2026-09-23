package org.mustabelmo.java.githubactions;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Index {
    public Index() {
    }
    public static void main(String[] args) {
        String mainClass = Properties.get("__MAIN_CLASS");
        Class<Action> aClass = null;
        Action action = null;
        try {
            aClass = (Class<Action>) Class.forName(mainClass);
            Constructor<Action> constructor = aClass.getConstructor();
            action = constructor.newInstance();
        } catch (ClassNotFoundException
                 | InvocationTargetException
                 | NoSuchMethodException
                 | InstantiationException
                 | IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        try {
            action.run();
        } catch (Exception e) {
            Core.setFailed(e);
            throw new RuntimeException(e);
        }
    }
}
