package com.qazima.habari.plugin.directory;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.qazima.habari.plugin.core.Content;
import com.sun.net.httpserver.HttpExchange;
import lombok.Getter;
import lombok.Setter;
import org.apache.http.HttpStatus;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.*;
import java.util.regex.Pattern;

@JsonTypeName("com.qazima.habari.plugin.directory.Plugin")
public class Plugin extends com.qazima.habari.plugin.core.Plugin {
    @Getter
    @Setter
    @JsonProperty("configurationUri")
    private String configurationUri;
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

    private boolean isNullOrEmpty(String string) {
        return string == null || string.isEmpty();
    }

    public int process(HttpExchange httpExchange, Content content) {
        String localPath = getPath();
        String remotePath = Pattern.compile(getUri()).matcher(httpExchange.getRequestURI().getPath().replace('/', File.separatorChar)).replaceAll("$2");
        String fileName = Path.of(localPath, remotePath).toString();
        if (remotePath.endsWith(File.separator) || isNullOrEmpty(remotePath)) {
            String defaultPage = getDefaultPages().stream().filter(dp -> new File(Path.of(localPath, remotePath, dp).toString()).exists()).findFirst().orElse("");
            fileName = Path.of(localPath, remotePath, defaultPage).toString();
        }

        File file = new File(fileName);
        if (file.exists()) {
            try {
                content.setType(URLConnection.guessContentTypeFromName(file.getName()));
                content.setStatusCode(HttpStatus.SC_OK);
                FileInputStream fileInputStream = new FileInputStream(file);
                content.setBody(fileInputStream.readAllBytes());
                fileInputStream.close();
            } catch (Exception e) {
                content.setStatusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);
                content.setType("text/plain");
                content.setBody(e.toString().getBytes(StandardCharsets.UTF_8));
            }
        } else {
            content.setStatusCode(HttpStatus.SC_NOT_FOUND);
            content.setType("text/plain");
            content.setBody("".getBytes(StandardCharsets.UTF_8));
        }

        if(content.getStatusCode() != HttpStatus.SC_OK) {
            Optional<ErrorPage> errorPage = getErrorPages().stream().filter(ep -> ep.getCode() == content.getStatusCode()).findFirst();
            if(errorPage.isPresent()) {
                String errorFilePath = errorPage.get().getPage();
                File errorFile = new File(errorFilePath);
                if (errorFile.exists()) {
                    try {
                        FileInputStream errorFileInputStream = new FileInputStream(errorFile);
                        content.setBody(errorFileInputStream.readAllBytes());
                    } catch (IOException e) {
                        content.setBody("".getBytes(StandardCharsets.UTF_8));
                    }
                }
            }
        }

        return content.getStatusCode();
    }
}
