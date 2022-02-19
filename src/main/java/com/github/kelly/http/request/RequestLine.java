package com.github.kelly.http.request;

public class RequestLine {

    private final HttpMethod httpMethod;
    private final String url;
    private QueryString queryString = null;
    private final String protocol;

    private final int METHOD_INDEX = 0;
    private final int URL_INDEX = 1;
    private final int PROTOCOL_INDEX = 2;


    public RequestLine(String line) {
        String[] components = line.split(" ");

        this.httpMethod = HttpMethod.valueOf(components[METHOD_INDEX]);
        this.protocol = components[PROTOCOL_INDEX];

        String[] urlAndParameters = divideUrlAndParameters(components[URL_INDEX]);
        final int URL = 0;
        final int PARAMETERS = 1;

        this.url = urlAndParameters[URL];
        if (urlAndParameters.length > 1) {
            this.queryString = new QueryString(urlAndParameters[PARAMETERS]);
        }
    }

    private String[] divideUrlAndParameters(String rawUrl) {
        return rawUrl.split("\\?");
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
