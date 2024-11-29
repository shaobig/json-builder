package org.shaobig.json.builder.creator;

import com.fasterxml.jackson.databind.JsonNode;

public interface NodeCreator<T> {

    JsonNode createNode(String path, T object);

}
