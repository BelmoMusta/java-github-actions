package io.github.belmomusta.java.actions.sdk;

import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

public class UtilsTest {

    @Test
    public void toCommandValue() {
        String commandValue = Utils.toCommandValue("foo-bar");
        Assertions.assertEquals("foo-bar", commandValue);
    }

    @Test
    public void TestToCommandValueWithNull() {
        String commandValue = Utils.toCommandValue(null);
        Assertions.assertEquals("", commandValue);
    }

    @Test
    public void TestToCommandValueWithObject() {
        Map<String, String> object = Map.of("name", "foo", " class", "bar");
        String commandValue = Utils.toCommandValue(object);
        Assertions.assertEquals(JSONObject.valueToString(object), commandValue);
    }
    @Test
    void testEmptyCommandProperties() {
        Map<String, Object> commandProperties = Utils.toCommandProperties(Map.of());
        Assertions.assertTrue(commandProperties.isEmpty());
    }

    @Test
    void testToCommandProperties() {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("title", "A title");
        map.put("file", "file-Title");
        map.put("startLine", "1");
        map.put("endLine", "20");
        map.put("startColumn", "0");
        map.put("endColumn", "30");
        Map<String, Object> result = Utils.toCommandProperties(map);
        Assertions.assertEquals(result.get("title"), "A title");
        Assertions.assertEquals(result.get("file"), "file-Title");
        Assertions.assertEquals(result.get("line"), "1");
        Assertions.assertEquals(result.get("endLine"), "20");
        Assertions.assertEquals(result.get("col"), "0");
        Assertions.assertEquals(result.get("endColumn"), "30");
       // org.junit.platform.engine.discovery.MethodSelector

        Assertions.assertFalse(result.isEmpty());
    }
}