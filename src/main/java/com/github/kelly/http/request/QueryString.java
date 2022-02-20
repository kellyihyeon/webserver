package com.github.kelly.http.request;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class QueryString {

    private final String queryStringOrNull;
    private final Map<String, String> queryStringMap = new HashMap<>();



    public QueryString(String rawUrl) {
        this.queryStringOrNull = parseQueryString(rawUrl);
        createQueryStringMap();
    }

    private void createQueryStringMap() {
        if (queryStringOrNull != null) {    // board = 3 & username=pipi
            // void accept(T t);
            Arrays.stream(queryStringOrNull.split("&"))
                    .forEach(keyAndValueString -> {
                        String[] keyAndValueComponents = keyAndValueString.split("=");
                        queryStringMap.put(keyAndValueComponents[0], keyAndValueComponents[1]);
                    });
        }
    }

    private String parseQueryString(String rawUrl) {
        // /welcome?board=3&username=pipi  or /welcome
        if (rawUrl.contains("?")) {
            return rawUrl.split("\\?")[1];
        }
        return null;
    }

    // refactoring 대상
    public Map<String, String> getQueryStringMap() {
        return queryStringMap;
    }

    public String getRawQueryString() {
        return queryStringOrNull;
    }

    public String getParameterValue(String key) {
        return queryStringMap.get(key);
    }
}
