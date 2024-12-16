package org.shaobig.json.builder.reader.value;

import com.fasterxml.jackson.databind.JsonNode;
import org.shaobig.json.builder.exception.WrongClassException;
import org.shaobig.json.builder.object.JsonNodeSetter;
import org.shaobig.json.builder.reader.path.JsonPathReader;
import org.shaobig.json.builder.reader.value.object.reader.ObjectReaderSupplier;

import java.io.IOException;
import java.util.List;

public class EntityListObjectReader implements ListObjectReader, JsonNodeSetter {

    private ObjectReaderSupplier objectReaderSupplier;
    private JsonPathReader jsonPathReader;

    public EntityListObjectReader(ObjectReaderSupplier objectReaderSupplier, JsonPathReader jsonPathReader) {
        this.objectReaderSupplier = objectReaderSupplier;
        this.jsonPathReader = jsonPathReader;
    }

    @Override
    public <T> List<T> readList(String path, Class<T> valueType) {
        try {
            return getObjectReaderSupplier().getObjectReader(valueType).readValue(getJsonPathReader().readPath(path).toString());
        }
        catch (IOException e) {
            throw new WrongClassException(String.format("Can't cast the '%s' path value to %s", path, valueType));
        }
    }

    @Override
    public void setJsonNode(JsonNode jsonNode) {
        getJsonPathReader().setJsonNode(jsonNode);
    }

    public ObjectReaderSupplier getObjectReaderSupplier() {
        return objectReaderSupplier;
    }

    public void setObjectReaderSupplier(ObjectReaderSupplier objectReaderSupplier) {
        this.objectReaderSupplier = objectReaderSupplier;
    }

    public JsonPathReader getJsonPathReader() {
        return jsonPathReader;
    }

    public void setJsonPathReader(JsonPathReader jsonPathReader) {
        this.jsonPathReader = jsonPathReader;
    }

}
