package com.github.kelly.http.request;

public class RequestLine {

    private final HttpMethod httpMethod;
    private final String url;
    private final QueryString queryString;
    private final String protocol;

    private final int METHOD_INDEX = 0;
    private final int URL_INDEX = 1;
    private final int PROTOCOL_INDEX = 2;


    public RequestLine(String line) {
        String[] components = line.split(" ");

        this.httpMethod = HttpMethod.valueOf(components[METHOD_INDEX]);
        this.url = parseUrl(components[URL_INDEX]);
        this.protocol = components[PROTOCOL_INDEX];

        this.queryString = new QueryString(components[URL_INDEX]);
    }

    private String parseUrl(String url) {
        if (url.contains("?")) {
            return url.split("\\?")[0];
        }
        return url;
    }

    public HttpMethod getHttpMethod() {
        return httpMethod;
    }

    public String getUrl() {
        return url;
    }

    public String getProtocol() {
        return protocol;
    }

    public QueryString getQueryString() {
        return queryString;
    }

    public String getRawQueryString() {
        return queryString.getRawQueryString();
    }

    public String getParameterValue(String key) {
        return queryString.getParameterValue(key);
    }

}
