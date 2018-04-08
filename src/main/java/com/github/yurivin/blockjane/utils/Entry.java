package com.github.yurivin.blockjane.utils;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.Map;

public class Entry implements Map.Entry<String, JsonNode> {
    private final String key;
    private JsonNode value;

    public Entry(String key, JsonNode value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public JsonNode getValue() {
        return value;
    }

    @Override
    public JsonNode setValue(JsonNode value) {
        JsonNode old = this.value;
        this.value = value;
        return old;
    }
}
