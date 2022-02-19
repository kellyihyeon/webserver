package com.github.kelly.http.request;

import java.util.HashMap;
import java.util.Map;

public class QueryString {

    private final String rawQueryString;

    private final Map<String, String> queryStringMap = new HashMap<>();



    public QueryString(String parameters) {
        this.rawQueryString = parameters;

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

    public String getRawQueryString() {
        return this.rawQueryString;
    }

    public String getParameterValue(String key) {
        return queryStringMap.get(key);
    }
}
