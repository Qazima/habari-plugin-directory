package com.qazima.habari.plugin.directory;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.qazima.habari.plugin.directory.deserializer.ErrorPageDeserializer;
import com.qazima.habari.plugin.directory.serializer.ErrorPageSerializer;
import lombok.Getter;
import lombok.Setter;

@JsonDeserialize(using = ErrorPageDeserializer.class)
@JsonSerialize(using = ErrorPageSerializer.class)
public class ErrorPage {
    @Getter
    @Setter
    private int code;
    @Getter
    @Setter
    private String page;
}
