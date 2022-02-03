package com.github.kelly.start.webio;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * web io
 */
public class Application {

    public static void main(String[] args) {

        // port = 8080
        try (ServerSocket serverSocket = new ServerSocket(8080)){

            Socket socketConnection;

            while ((socketConnection = serverSocket.accept()) != null) {

                InputStream inputStream = socketConnection.getInputStream();
                OutputStream outputStream = socketConnection.getOutputStream();

                // http input stream 읽기
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line;

                while (!(line = bufferedReader.readLine()).equals("")) {
                    System.out.println(line);
                }


                DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
                String html = "<HTML>" +
                        "<HEAD><TITLE>웹 브라우저에서 io 하는 방법</TITLE></HEAD>" +
                        "<BODY>" +
                        "<h2><center><b>html 양식을 만들어봅시다.</b></center></h2>" +
                        "<p><center>안녕하세요. 저는 내용입니다.<center></p>"+
                        "</BODY>" +
                        "</HTML>";
                int htmlLength = html.getBytes().length;

                // Server response
                // status line
                dataOutputStream.writeBytes("HTTP/1.1 200 OK\r\n");
                // response headers
                dataOutputStream.writeBytes("content-type: text/html; charset=utf-8 \r\n");
                dataOutputStream.writeBytes("content-length: " + htmlLength + "\r\n");
                dataOutputStream.writeBytes("\r\n");
                // response body
                dataOutputStream.write(html.getBytes());
                dataOutputStream.flush();

            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
