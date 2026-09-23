package org.mustabelmo.java.githubactions;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import org.json.JSONObject;

public class Core {

    public static void exportVariable(String name, String val) {
        String convertedVal = Utils.toCommandValue(val);
        Properties.put(name, convertedVal);
        String filePath = Properties.get("GITHUB_ENV");
        if (!filePath.isBlank()) {
            FileCommand.issueFileCommand("ENV", FileCommand.prepareKeyValueMessage(name, val));
            return;
        }
        Command.issueCommand("set-env", Map.of("name", name), convertedVal);
    }

    public static void setSecret(String secret) {
        Command.issueCommand("add-mask", Map.of(), secret);
    }

    public static void addPath(String inputPath) {
        String filePath = Properties.get("GITHUB_PATH");
        if (!filePath.isBlank()) {
            FileCommand.issueFileCommand("PATH", inputPath);
        } else {
            Command.issueCommand("add-path", Map.of(), inputPath);
        }
        Properties.put("PATH", inputPath + ":" + Properties.get("PATH"));
    }

    public static String getInput(String name) {
        return getInput(name, null);
    }
    public static String getInput(String name, InputOptions options) {
        String inputs = Properties.get("__INPUTS");
        JSONObject jsonInputs = new JSONObject(inputs);
        String val = jsonInputs.getString(name);
        if (options != null && options.isRequired() && val.isBlank()) {
            throw new RuntimeException("Input required and not supplied:" + name);
        }
        if (options != null && !options.trimWhitespace()) {
            return val;
        }

        return val.trim();
    }

    public static List<String> getMultilineInput(String name, InputOptions options) {
        List<String> inputs = Arrays.stream(getInput(name, options)
                        .split("\n"))
                .filter(x -> !x.isEmpty())
                .toList();

        if (options != null && !options.trimWhitespace()) {
            return inputs;
        }
        return inputs.stream().map(String::trim).toList();
    }

    public static boolean getBooleanInput(String name, InputOptions options) {
        final List<String> trueValue = List.of("true", "True", "TRUE");
        final List<String> falseValue = List.of("false", "False", "FALSE");
        final String val = getInput(name, options);
        if (trueValue.contains(val)) return true;
        if (falseValue.contains(val)) return false;

        throw new RuntimeException("Input does not meet YAML 1.2 'Core Schema' specification: "
                + name + "\n" + "Support boolean input list: `true | True | TRUE | false | False | FALSE`");
    }

    public static void setOutput(String name, String value) {
        final String filePath = Properties.get("GITHUB_OUTPUT");
        if (!filePath.isEmpty()) {
            FileCommand.issueFileCommand("OUTPUT", FileCommand.prepareKeyValueMessage(name, value));
            return;
        }
        System.out.println();
        Command.issueCommand("set-output", Map.of("name", name), Utils.toCommandValue(value));
    }

    public static void setCommandEcho(boolean enabled) {
        Command.issue("echo", enabled ? "on" : "off");
    }

    public static void setFailed(Object message) {
        error(message);
        System.exit(ExitCode.Failure);
    }
    public static void error(Object message, Map<String, Object> properties) {
        Command.issueCommand(
                "error", Utils.toCommandProperties(properties), message.toString());
    }

    public static void error(Object message) {
        error(message, Map.of());
    }

    public static boolean isDebug() {
        return Properties.get("RUNNER_DEBUG").equals("1");
    }

    public static void debug(String message) {
        Command.issueCommand("debug", Map.of(), message);
    }

    public static void warning(
            Object message,
            Map<String, Object> properties) {
        Command.issueCommand(
                "warning",
                Utils.toCommandProperties(properties),
                message.toString());
    }

    public static void notice(
            Object message,
            Map<String, Object> properties) {
        Command.issueCommand(
                "notice",
                Utils.toCommandProperties(properties),
                message.toString());
    }

    public static void info(String message) {
        System.out.println(message);
    }

    public static void startGroup(String name) {
        Command.issue("group", name);
    }
    public static void endGroup() {
        Command.issue("endgroup");
    }

    public static <T> T group(String name, Supplier<T> fn) {
        startGroup(name);
        T result;
        try {
            result = fn.get();
        } finally {
            endGroup();
        }
        return result;
    }

    public static void saveState(String name, String value) {
        String filePath = Properties.get("GITHUB_STATE");
        if (!filePath.isEmpty()) {
            FileCommand.issueFileCommand("STATE", FileCommand.prepareKeyValueMessage(name, value));
            return;
        }
        Command.issueCommand("save-state", Map.of("name", name), Utils.toCommandValue(value));
    }

    public static String getState(String name) {
        return Properties.get("STATE_" + name);
    }

}
