package com.github.kelly.start.fileio;

import java.io.*;

/**
 * file io
 */
public class Application {

    public static void main(String[] args) throws IOException {
        File file = new File("web_server.txt");

        FileInputStream fileInputStream = new FileInputStream(file);
        FileOutputStream fileOutputStream = new FileOutputStream("web_server_output_result.txt");

        byte[] buffer = new byte[85];

        // 0 ~ buffer.length() 까지 읽는다.
        while (fileInputStream.read(buffer) != -1) {
            fileOutputStream.write(buffer);
        }


    }
}
