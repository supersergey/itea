package com.example.demo.parser;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FileParser {

    private final Map<FileType, Parser> parsers;

    public FileParser(List<Parser> parsers) {
        this.parsers = parsers.stream().collect(
                Collectors.toMap(
                        Parser::supports,
                        p -> p
                ));
    }

    public String parse(FileType type) {
        return parsers.get(type).parse();
    }
}
