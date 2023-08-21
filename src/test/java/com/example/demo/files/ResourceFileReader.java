package com.example.demo.files;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;
import java.nio.file.Files;

@SpringBootTest(classes = {ResourceFileReader.class})
public class ResourceFileReader {
    @Value("classpath:/static/index.html")
    File resourceFile;

    @Test
    void shouldDisplayFileContent() {
        try (
                InputStream inputStream = this.getClass().getClassLoader()
                        .getResourceAsStream("static/index.html");
                BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))
        ) {
            br.lines().forEach(System.out::println);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Test
    void shouldDisplayFileContentWithSpringAnnotation() throws IOException {
        byte[] content = Files.readAllBytes(resourceFile.toPath());
        System.out.println(new String(content));
    }
}
