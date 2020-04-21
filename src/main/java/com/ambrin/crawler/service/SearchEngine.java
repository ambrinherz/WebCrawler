package com.ambrin.crawler.service;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SearchEngine {

    private static final String SEARCH_PREFIX = "https://www.google.com/search?q=";

    private SearchService searchService;

    public SearchEngine() {
        this(new SearchServiceImpl());
    }

    SearchEngine(SearchService searchService) {
        this.searchService = searchService;
    }

    public List<String> search(String keyword) throws IOException {
        String url = SEARCH_PREFIX + keyword;
        String html = searchService.getHTML(url);
        Document doc = Jsoup.parse(html);

        return doc.getElementsByClass("r").stream()
                .flatMap(SearchEngine::getLinks)
                .map(SearchEngine::getPing)
                .filter(Objects::nonNull)
                .map(SearchEngine::getUrl)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    private static String getUrl(String ping) {
        String[] split = splitByUrlTag(ping);

        if (hasFullUrl(split)) {
            String fullUrl = split[1];
            return splitByVedTag(fullUrl)[0];
        }
        return null;
    }

    private static String[] splitByVedTag(String s) {
        return s.split("&ved=");
    }

    private static String[] splitByUrlTag(String s) {
        return s.split("url=");
    }

    private static boolean hasFullUrl(String[] split) {
        return split.length >= 2;
    }

    private static String getPing(Elements elements) {
        return elements.attr("ping");
    }

    private static Stream<Elements> getLinks(Element element) {
        return Stream.of(element.select("a"));
    }
}

