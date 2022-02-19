package com.github.kelly.http.request;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RequestHeaders {

    private final Map<String, String> requestHeader = new HashMap<>();

    final int KEY = 0;
    final int VALUE = 1;


    public RequestHeaders(BufferedReader br) throws IOException {
        String line;
        String[] keyAndValue;

        while (!(line = br.readLine()).equals("")) {
            keyAndValue = line.split(":", 2);
            requestHeader.put(keyAndValue[KEY], keyAndValue[VALUE].trim());
        }
    }


    public String getHeader(String key) {
        return requestHeader.get(key);
    }
}
