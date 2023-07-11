package com.codersfactory.common.exception;

public class ArticleNotFoundException extends RuntimeException {
  
    public ArticleNotFoundException() {
        super("Article not found");
    }

    public ArticleNotFoundException(Long id) {
        super("Article with id " + id + " not found");
    }

    public ArticleNotFoundException(String message) {
        super(message);
    }
}
