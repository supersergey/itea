package com.example.demo.locator;

import org.springframework.stereotype.Service;

@Service
public class ParserCsv implements Parser {

    @Override
    public FileType supports() {
        return FileType.CSV;
    }

    @Override
    public String parse() {
        return "CSV";
    }
}
