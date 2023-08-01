package com.example.demo.parser;

import org.springframework.stereotype.Service;

@Service
public class ParserJson implements Parser {
    @Override
    public FileType supports() {
        return FileType.JSON;
    }

    @Override
    public String parse() {
        return "JSON";
    }
}
