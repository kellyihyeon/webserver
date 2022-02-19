package com.github.kelly.http.request;

import java.util.HashMap;
import java.util.Map;

public class QueryString {

    private final Map<String, String> queryStringMap = new HashMap<>();



    public QueryString(String parameters) {
        final int KEY = 0;
        final int VALUE = 1;

        String[] queryData = parameters.split("&");
        for (String query : queryData) {
            String[] keyAndValue = query.split("=");
            queryStringMap.put(keyAndValue[KEY], keyAndValue[VALUE]);
        }

    }

    public Map<String, String> getQueryStringMap() {
        return queryStringMap;
    }
}
