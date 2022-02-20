package com.github.kelly.http.request;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class RequestBody {

    private final RequestHeaders requestHeaders;
    private final String rawOptionalBodyResource;
    private final Map<String, String> requestBodyResourceMap = new HashMap<>();

    public RequestBody(BufferedReader br, RequestHeaders requestHeaders) {
        this.requestHeaders = requestHeaders;
        this.rawOptionalBodyResource = readBodyResource(br);
        createRequestBodyResourceMap();
    }

    private void createRequestBodyResourceMap() {
        if (rawOptionalBodyResource != null) {
            String[] rawBody = rawOptionalBodyResource.split("&");
            for (String resource : rawBody) {
                String[] keyAndValue = resource.split("=");
                requestBodyResourceMap.put(keyAndValue[0], keyAndValue[1]);
            }
        }
    }

    private String readBodyResource(BufferedReader br) {
        String contentLength = requestHeaders.getHeader("Content-Length");
        if (contentLength != null) {
            if (requestHeaders.getHeader("Content-Type").equals("application/x-www-form-urlencoded")) {
                try {
                    char[] optionalBodyBuffer = new char[Integer.parseInt(contentLength)];
                    br.read(optionalBodyBuffer);
                    return String.valueOf(optionalBodyBuffer);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public String getBodyResourceValue(String key) {
        return requestBodyResourceMap.get(key);
    }
}
