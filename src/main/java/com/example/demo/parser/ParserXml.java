package com.example.demo.parser;

import org.springframework.stereotype.Service;

@Service
public class ParserXml implements Parser {
    @Override
    public FileType supports() {
        return FileType.XML;
    }

    @Override
    public String parse() {
        return "XML";
    }
}
