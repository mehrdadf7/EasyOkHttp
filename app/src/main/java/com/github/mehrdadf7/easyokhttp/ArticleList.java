package com.github.mehrdadf7.easyokhttp;

import java.util.List;

public class ArticleList {

    private String status;
    private int totalResults;
    private List<Article> articles;

    public String getStatus() {
        return status;
    }

    public int getTotalResults() {
        return totalResults;
    }

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