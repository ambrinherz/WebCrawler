package com.ambrin.crawler.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;


public class SearchServiceImpl implements SearchService {


    @Override
    public String getHTML(String url) throws IOException {
        Document doc = Jsoup.connect(url).get();
        return doc.html();
    }
}
