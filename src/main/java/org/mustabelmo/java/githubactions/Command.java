package org.mustabelmo.java.githubactions;

import java.util.Map;

import static org.mustabelmo.java.githubactions.Utils.toCommandValue;

public class Command {
    private static final String CMD_STRING = "::";
    private final String command;
    private final Map<String, Object> properties;
    private final String message;
    public Command(String command, Map<String, Object> properties, String message) {
        if (command == null || command.isBlank()) {
            command = "missing.command";
        }
        this.command = command;
        this.properties = properties;
        this.message = message;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(CMD_STRING)
                .append(this.command);
        if (properties != null && !properties.isEmpty()) {
            builder.append(" ");
            boolean first = true;
            for (String key : properties.keySet()) {
                Object val = this.properties.get(key);
                if (val != null) {
                    if (first) {
                        first = false;
                    } else {
                        builder.append(",");
                    }
                    builder.append(key).append("=")
                            .append(escapeProperty(val));
                }
            }
        }
        builder.append(CMD_STRING)
                .append(escapeData(this.message));
        return builder.toString();
    }
    private String escapeData(String s) {
        return toCommandValue(s)
                .replaceAll("%", "%25")
                .replaceAll("\r", "%0D")
                .replaceAll("\n", "%0A");
    }
    private String escapeProperty(Object s) {
        return toCommandValue(s)
                .replaceAll("%", "%25")
                .replaceAll("\r", "%0D")
                .replaceAll("\n", "%0A")
                .replaceAll(":", "%3A")
                .replaceAll(",", "%2C");
    }
    public static void issueCommand(String command, Map<String, Object> properties, String message) {
        Command cmd = new Command(command, properties, message);
        System.out.println(cmd);
    }
    public static void issue(String command, String message) {
        Command cmd = new Command(command, Map.of(), message);
        System.out.println(cmd);
    }
    public static void issue(String command) {
        Command cmd = new Command(command, Map.of(), "");
        System.out.println(cmd);
    }
}
