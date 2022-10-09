package com.qazima.habari.plugin.directory;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@JsonPropertyOrder(alphabetic = true)
public class Configuration extends com.qazima.habari.plugin.core.Configuration {
    @Getter
    @JsonProperty("defaultPages")
    private final List<String> defaultPages = new ArrayList<>();
    @Getter
    @JsonProperty("errorPages")
    private final List<ErrorPage> errorPages = new ArrayList<>();
    @Getter
    @Setter
    @JsonProperty("path")
    private String path = "./";
}
