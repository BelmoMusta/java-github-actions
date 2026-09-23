package org.mustabelmo.java.githubactions.sdk;

import org.json.JSONObject;

import java.util.LinkedHashMap;
import java.util.Map;

public class Utils {
    public static String toCommandValue(Object input) {
        if (input == null) {
            return "";
        }
        if (input instanceof String s){
            return s;
        }
         return JSONObject.valueToString(input);
    }

    public static Map<String, Object> toCommandProperties(
            Map<String, Object> annotationProperties) {
        if (annotationProperties.isEmpty()) {
            return Map.of();
        }

        Map<String, Object> result = new LinkedHashMap<>();
        result.put("title", annotationProperties.get("title"));
        result.put("file", annotationProperties.get("file"));
        result.put("line", annotationProperties.get("startLine"));
        result.put("endLine", annotationProperties.get("endLine"));
        result.put("col", annotationProperties.get("startColumn"));
        result.put("endColumn", annotationProperties.get("endColumn"));

        return result;
    }

}
