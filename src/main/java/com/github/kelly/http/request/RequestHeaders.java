package com.github.kelly.http.request;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RequestHeaders {

    private Map<String, String> requestHeader = new HashMap<>();

    final int KEY = 0;
    final int VALUE = 1;


    public RequestHeaders(BufferedReader br) throws IOException {
        String line;
        String[] keyAndValue;
        // null 이 아닐때까지가 아니라, 한 줄의 공백이 나올 때까지 (요청을 한번만 읽는다고 가정)
        while (!(line = br.readLine()).equals("")) {
            keyAndValue = line.split(":", 2);
            requestHeader.put(keyAndValue[KEY], keyAndValue[VALUE].trim());
        }
    }


    public String getHeader(String key) {
        return requestHeader.get(key);
    }
}
