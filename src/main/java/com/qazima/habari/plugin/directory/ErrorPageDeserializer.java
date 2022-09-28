package com.qazima.habari.plugin.directory;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class ErrorPageDeserializer extends JsonDeserializer<ErrorPage> {
    @Override
    public ErrorPage deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        // String oBrace = jsonParser.getText();
        jsonParser.nextToken();
        String code = jsonParser.getText();
        jsonParser.nextToken();
        String page = jsonParser.getText();
        jsonParser.nextToken();
        // String cBrace = jsonParser.getText();
        ErrorPage errorPage = new ErrorPage();
        errorPage.setCode(Integer.parseInt(code));
        errorPage.setPage(page);
        return errorPage;
    }
}
