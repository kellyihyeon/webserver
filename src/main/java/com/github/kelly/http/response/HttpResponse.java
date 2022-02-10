package com.github.kelly.http.response;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class HttpResponse {

    private String responseBody;


    public HttpResponse(OutputStream outputStream) {
        DataOutputStream dos = new DataOutputStream(outputStream);

        responseBody = "hi! I am a html!!! trust me :)";

        try {
            // status line -> protocol version, status code, status text
            dos.writeBytes("HTTP/1.1 200 OK\r\n");
            // response header (: )
            dos.writeBytes("Content-Type: text/html; charset=utf-8\r\n");
            dos.writeBytes("Content-Length: " + responseBody.length() + "\r\n");
            dos.writeBytes("\r\n");

            dos.write(responseBody.getBytes());
            dos.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getResponseBody() {
        return responseBody;
    }
}
