package com.sandsbeach.surfreport.service;

import com.sandsbeach.surfreport.adapter.gpt.GptAdapter;
import com.sandsbeach.surfreport.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

import static com.sandsbeach.surfreport.config.CacheConfig.CACHE_NAME_ARTICLES;

@Service
public class ArticleService {

    @Autowired
    private GptAdapter gptAdapter;

    @Cacheable(cacheNames = CACHE_NAME_ARTICLES)
    public List<Article> getArticles() {
        return Arrays.asList(
                gptAdapter.generateArticle("Surfer that can't make it past the whitewater")
                        .withRank(0),
                gptAdapter.generateArticle("Prank gone too far")
                        .withRank(1),
                gptAdapter.generateArticle("Bad break up")
                        .withRank(2),
                gptAdapter.generateArticle("One student's dietary change for the better")
                        .withRank(3)
        );
    }
}
