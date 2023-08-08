package com.example.demo.webclient;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class LocationDeserializer extends JsonDeserializer<Location> {
    @Override
    public Location deserialize(JsonParser parser, DeserializationContext ctxt) throws IOException {
        TreeNode node = parser.readValueAsTree();
        assertIsObject(node);

        TreeNode localNameNode = node.get("local_names");
        assertIsObject(localNameNode);

        return new Location(
                node.get("name").toString(),
                node.get("lon").toString(),
                node.get("lat").toString(),
                localNameNode.get("uk").toString(),
                node.get("country").toString()
        );
    }

    private void assertIsObject(TreeNode node) {
        if (node == null || !node.isObject()) {
            throw new IllegalArgumentException("Invalid JSON format");
        }
    }
}
