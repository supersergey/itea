package com.example.demo.parser;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ParserTest {

    @Autowired
    FileParser parser;

    @Test
    void shouldSupportAllParsers() {
        var actual = Arrays.stream(FileType.values())
                .map(parser::parse)
                .toList();

        assertThat(actual).containsExactlyInAnyOrder("CSV", "JSON", "XML");
    }
}