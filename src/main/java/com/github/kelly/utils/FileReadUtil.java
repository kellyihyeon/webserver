package com.github.kelly.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;


public class FileReadUtil {

    public static byte[] readFromStaticFile(String filePath) {
        // filePath = static/path.extension
        InputStream in = null;
        in = FileReadUtil.class.getClassLoader().getResourceAsStream(filePath);
        byte[] buffer = new byte[6000];
        int size;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        try {
            while ((size = in.read(buffer)) != -1) {
                // Content-Length 조정
                byteArrayOutputStream.write(buffer, 0, size);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return byteArrayOutputStream.toByteArray();
    }
}
