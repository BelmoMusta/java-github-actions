package io.github.belmomusta.java.actions.sdk;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

 class Properties {
    /**
     * Copy all the env variables
     */
    public static final Map<String, String> CORE_PROPERTIES;
    public static final String EMPTY = "";

    static {
        Map<String, String> envProperties = System.getenv();
        CORE_PROPERTIES = new ConcurrentHashMap<>(envProperties);
    }

    public static String get(String name) {
        return Optional.ofNullable(CORE_PROPERTIES.get(name)).orElse(EMPTY);
    }

    public static String put(String name, String value) {
        System.setProperty(name, value);
        return CORE_PROPERTIES.put(name, value);
    }
}
