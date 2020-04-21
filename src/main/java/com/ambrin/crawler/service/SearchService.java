package com.ambrin.crawler.service;

import java.io.IOException;

public interface SearchService {

    String getHTML(String url) throws IOException;
}
