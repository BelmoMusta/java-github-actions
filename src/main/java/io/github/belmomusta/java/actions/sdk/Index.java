package io.github.belmomusta.java.actions.sdk;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Index {
    public Index() {
    }
    public static void main(String[] args) {
        final String mainClass = Properties.get("__MAIN_CLASS");
        Class<Action> aClass = null;
        Action action = null;
        try {
            Class temp = Class.forName(mainClass);
            if (Action.class.isAssignableFrom(temp)) {
                aClass = temp;
            } else {
                throw new RuntimeException(mainClass + " has to implement the '" + Action.class.getCanonicalName() +"' interface");
            }
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
