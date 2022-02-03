package com.github.kelly.start.mission;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 브라우저에서 이미지 출력 -> 다운로드
 */
public class Application {

    public static void main(String[] args) {

        try (ServerSocket serverSocket = new ServerSocket(8080)) {

            Socket socketConnection;

            File img = new File("gitHub_repository.png");

            while ((socketConnection = serverSocket.accept()) != null) {

                DataOutputStream dataOutputStream;
                try (FileInputStream in = new FileInputStream(img);
                     OutputStream outputStream = socketConnection.getOutputStream();) {

                    dataOutputStream = new DataOutputStream(outputStream);

                    byte[] buffer = new byte[5800];
                    int totalBytes = 0;

                    dataOutputStream.writeBytes("HTTP/1.1 200 OK \r\n");
                    dataOutputStream.writeBytes("content-type: img/png \r\n");
                    dataOutputStream.writeBytes("content-length: " + buffer.length + "\r\n");
                    dataOutputStream.writeBytes("\r\n");
                    // response body
                    while ((totalBytes = in.read(buffer)) != -1) {
                        dataOutputStream.write(buffer);
                    }
                }
                dataOutputStream.flush();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
