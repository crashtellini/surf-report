package com.sandsbeach.surfreport.controller;

import com.sandsbeach.surfreport.model.Article;
import com.sandsbeach.surfreport.model.SurfLocationReport;
import com.sandsbeach.surfreport.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/articles")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @CrossOrigin
    @GetMapping
    public ResponseEntity<List<Article>> getArticles() {
        return new ResponseEntity<>(articleService.getArticles(), HttpStatus.OK);
    }
}
