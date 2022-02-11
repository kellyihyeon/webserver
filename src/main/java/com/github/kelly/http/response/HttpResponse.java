package com.github.kelly.http.response;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * 응답의 역할 구현하기
 */
public class HttpResponse {

    private HttpStatus httpStatus = HttpStatus.OK;

    private Map<String, String> responseHeaderMap = new HashMap<>();

    private DataOutputStream dos;

    // 간단해진 생성자
    public HttpResponse(OutputStream outputStream) {
        dos = new DataOutputStream(outputStream);
    }

    public String makeStatusLine() {
        // dos.writeBytes("HTTP/1.1 200 OK\r\n");
        return String.format("HTTP/1.1 %d %s\r\n", httpStatus.getStatusCode(), httpStatus.getStatusText());
    }

    public void addHeader(String key, String value) {
        // controller 에서 Content-Type, Content-Length 담을 때
        responseHeaderMap.put(key, value);
    }

    public void writeBody(byte[] body) {
        try {
            // status line
            dos.writeBytes(makeStatusLine());
            // status header
            for (Map.Entry<String, String> entry : responseHeaderMap.entrySet()) {
                dos.writeBytes(String.format("%s: %s\r\n", entry.getKey(), entry.getValue()));
            }
            //    - content length
            dos.writeBytes(String.format("Content-Length: %d\r\n", body.length));
            // empty
            dos.writeBytes("\r\n");
            // write
            dos.write(body);
            dos.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
