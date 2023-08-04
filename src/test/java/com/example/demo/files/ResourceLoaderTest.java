package com.example.demo.files;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.nio.file.Files;

@SpringBootTest(classes = {ResourceLoaderTest.class})
public class ResourceLoaderTest {
    @Value("classpath:/static/index.html")
    File fileToLoad;

    @Test
    void shouldLoadFileFromResources() throws Exception {
        var content = Files.readAllBytes(fileToLoad.toPath());
        System.out.println(new String(content));
    }
}
