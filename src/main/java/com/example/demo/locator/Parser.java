package com.example.demo.locator;

public interface Parser {
    FileType supports();
    String parse();
}
