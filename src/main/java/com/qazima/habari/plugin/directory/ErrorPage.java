package com.qazima.habari.plugin.directory;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;

@JsonDeserialize(using = ErrorPageDeserializer.class)
public class ErrorPage {
    @Getter
    @Setter
    private int code;
    @Getter
    @Setter
    private String page;
}
