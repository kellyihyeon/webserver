package com.github.kelly.http.response;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class HttpResponse {


    public HttpResponse(OutputStream outputStream) {
        DataOutputStream dos = new DataOutputStream(outputStream);

        String html = "hi! I am a html!!! ";

        try {
            dos.writeBytes("HTTP/1.1 200 OK\r\n");
            dos.writeBytes("Content-Type: text/html; charset=utf-8\r\n");
            dos.writeBytes("Content-Length: " + html.length() + "\r\n");
            dos.writeBytes("\r\n");

            dos.write(html.getBytes());
            dos.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
