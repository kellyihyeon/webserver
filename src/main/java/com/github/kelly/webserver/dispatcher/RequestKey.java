package com.github.kelly.webserver.dispatcher;

import com.github.kelly.http.request.HttpMethod;

import java.util.Objects;

public class RequestKey {

    private String url;

    private HttpMethod method;


    public RequestKey(String url, HttpMethod method) {
        this.url = url;
        this.method = method;
    }

    /**
     * requestKey 는 주소값이 아닌 url, method 종류가 맞으면 같은 객체라고 규정하고 컨트롤러에 연결
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RequestKey that = (RequestKey) o;
        return Objects.equals(url, that.url) && method == that.method;
    }

    @Override
    public int hashCode() {
        return Objects.hash(url, method);
    }
}
