package com.example.demo.locator;

import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
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

    public String parse(File file) {
        FileType extension = new HashSet<>(Arrays.asList(FileType.values())).stream().findFirst().get();
        return parsers.get(extension).parse();
    }
}
