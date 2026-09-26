package io.github.belmomusta.demo;


import io.github.belmomusta.java.actions.sdk.Action;
import io.github.belmomusta.java.actions.sdk.Core;

import java.util.Map;

public class MonAction implements Action {
    @Override
    public void run() throws Exception {
        Core.info("Hello World");
        boolean flag = Core.getBooleanInput("flag", null);
        Core.info("boolean flag : " + flag);
        Core.setOutput("results", "1");
        Core.setSecret("SECRET_007");
        Core.info("my secret is SECRET_007");
        Core.startGroup("Group 1");
        Core.info("this line should be wrapped into a group");
        Core.endGroup();

        Core.warning("this is a warning", Map.of());
    }
}
