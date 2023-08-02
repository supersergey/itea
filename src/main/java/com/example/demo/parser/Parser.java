package com.example.demo.parser;

public interface Parser {
    FileType supports();
    String parse();
}
