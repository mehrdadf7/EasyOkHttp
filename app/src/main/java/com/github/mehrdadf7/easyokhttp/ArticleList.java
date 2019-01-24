package com.github.mehrdadf7.easyokhttp;

import java.util.List;

public class ArticleList {

    private List<Article> articles;

    public List<Article> getArticleList() {
        return articles;
    }

    class Article {
        private String title;
        private String description;

        public String getTitle() {
            return title;
        }

        public String getDescription() {
            return description;
        }
    }
}