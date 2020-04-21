package com.ambrin.crawler;

import com.ambrin.crawler.service.SearchEngine;

import java.io.IOException;
import java.util.List;

public class Example {

    public static void main(String[] args) throws IOException {
        SearchEngine engine = new SearchEngine();
        List<String> results = engine.search("Animal Crossing new horizons");
        results.forEach(System.out::println);
    }
}
