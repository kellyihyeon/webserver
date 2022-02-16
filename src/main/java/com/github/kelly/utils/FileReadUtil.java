package com.github.kelly.utils;

import java.io.IOException;
import java.io.InputStream;

/**
 * todo
 * 1. byte 크기를 어떻게 고정은 안되겠지? 어떤 파일을 읽을지 모르니까
 * 2. while 몸체가 없는데 이렇게 둬도 괜찮나?
 */
public class FileReadUtil {

    public static byte[] readFromStaticFile(String filePath) {
        // filePath = static/path.extension
        InputStream in = null;
        in = FileReadUtil.class.getClassLoader().getResourceAsStream(filePath);
        byte[] buffer = new byte[6000];
        int size;

        try {
            while ((size = in.read(buffer)) != -1) {

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
        return buffer;
    }
}
