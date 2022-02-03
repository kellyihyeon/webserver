package com.github.kelly.start.fileio;

import java.io.*;

public class Application2 {

    public static void main(String[] args) throws IOException {
        File imgFile = new File("gitHub_repository.png");

        FileInputStream fileInputStream = new FileInputStream(imgFile);
        FileOutputStream fileOutputStream = new FileOutputStream("gitHub_repository_output_result.png");

        byte[] buffer = new byte[5800];
        System.out.println("buffer.length = " + buffer.length);

        int count = 0;
        while ((count = fileInputStream.read(buffer)) != -1) {
            fileOutputStream.write(buffer);
            System.out.println("읽은 바이트 수 = " + count);
        }
    }
}
