package com.qazima.habari.plugin.directory.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.qazima.habari.plugin.directory.ErrorPage;

import java.io.IOException;
import java.io.StringWriter;
import java.util.AbstractMap;
import java.util.Map;

public class ErrorPageSerializer extends JsonSerializer<ErrorPage> {
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public void serialize(ErrorPage value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        StringWriter writer = new StringWriter();
        Map.Entry<Integer, String> mapEntry = new AbstractMap.SimpleEntry<>(value.getCode(), value.getPage());
        mapper.writeValue(writer, mapEntry);
        jsonGenerator.writeRawValue(writer.toString());
    }
}
