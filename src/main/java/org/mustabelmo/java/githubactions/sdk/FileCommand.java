package org.mustabelmo.java.githubactions.sdk;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.UUID;

import static org.mustabelmo.java.githubactions.sdk.Utils.toCommandValue;

public class FileCommand {
    public static void issueFileCommand(String command, String message) {
        String filePath = Properties.get("GITHUB_" + command);
        if (filePath.isBlank()) {
            throw new RuntimeException("Unable to find environment variable for file command " + command);
        }

        final Path pathToFile = Path.of(filePath);
        if (!Files.exists(pathToFile)) {
            throw new RuntimeException("Missing file at path:" + filePath);
        }
        final String content = toCommandValue(message) + System.lineSeparator();
        try {
            Files.write(
                    pathToFile,
                    List.of(content),
                    StandardCharsets.UTF_8,
                    StandardOpenOption.APPEND);
        } catch (IOException ioException) {
            throw new RuntimeException(ioException);
        }
    }

    public static String prepareKeyValueMessage(String key, String value) {
        String randomUUID = UUID.randomUUID().toString();
        String delimiter = "ghadelimiter_" + randomUUID;

        // These should realistically never happen, but just in case someone finds a
        // way to exploit uuid generation let's not allow keys or values that contain
        // the delimiter.
        if (key.contains(delimiter)) {
            throw new RuntimeException("Unexpected input:name should not contain the delimiter " + delimiter);
        }
        String convertedValue = toCommandValue(value);
        if (convertedValue.contains(delimiter)) {
            throw new RuntimeException("Unexpected input:value should not contain the delimiter " + delimiter);
        }
        return key + "<<" + delimiter + System.lineSeparator() + convertedValue + System.lineSeparator() + delimiter;
    }
}
