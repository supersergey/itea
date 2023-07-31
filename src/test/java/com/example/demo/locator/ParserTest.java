package com.example.demo.locator;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ParserTest {

    @Autowired
    List<Parser> parsers;

    @Test
    void shouldInvokeTwoInstances() {
        var actual = parsers.stream().map(Parser::parse).toList();

        assertThat(actual).containsExactlyInAnyOrder("A", "B");
    }
}