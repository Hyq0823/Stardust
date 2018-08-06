package com.file.readFile;

import com.sun.jndi.toolkit.url.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class NewWayRead {

    public static void main(String[] args) throws IOException {
        String path = NewWayRead.class.getClassLoader().getResource("com/file/readFile/1.txt").getPath();
        System.out.println(path);
        Path path1 = Paths.get(path);

        byte[] bytes = Files.readAllBytes(path1);
        String content = new String(bytes,StandardCharsets.UTF_8);
        System.out.println(content);
    }
}
