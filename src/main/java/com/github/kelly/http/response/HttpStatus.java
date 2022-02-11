package com.github.kelly.http.response;

public enum HttpStatus {
    OK(200, "OK"),
    NOT_FOUND(404, "NOT FOUND");

    private int statusCode;
    private String statusText;


    HttpStatus(int statusCode, String statusText) {
        this.statusCode = statusCode;
        this.statusText = statusText;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getStatusText() {
        return statusText;
    }
}
