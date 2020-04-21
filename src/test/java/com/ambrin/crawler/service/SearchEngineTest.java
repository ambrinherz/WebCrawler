package com.ambrin.crawler.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


public class SearchEngineTest {

    private SearchEngine searchEngine;

    @Mock
    private SearchService searchService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        searchEngine = new SearchEngine(searchService);
    }

    @Test
    public void should_returnCorrectResults_when_searchingForJewelHands() throws IOException {
        when(searchService.getHTML(anyString()))
                .thenReturn(getHTML("JewelHands.html"));

        List<String> results = searchEngine.search("jewel+hands");

        List<String> expected = Arrays.asList(
                "https://www.youtube.com/watch%3Fv%3DAfsS3pIDBfw",
                "https://en.wikipedia.org/wiki/Hands_(Jewel_song)",
                "https://genius.com/Jewel-hands-lyrics",
                "https://www.songfacts.com/lyrics/jewel/hands",
                "https://www.songfacts.com/facts/jewel/hands",
                "https://songmeanings.com/songs/view/52661/",
                "https://www.chickensoup.com/book-story/30719/hands",
                "https://www.dailymotion.com/video/x27mn6"
        );
        assertEquals(expected, results);
    }

    private String getHTML(String fileName) throws IOException {
        return String.join("", Files.readAllLines(Paths.get(fileName)));
    }

}