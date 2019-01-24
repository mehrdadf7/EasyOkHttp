package com.github.mehrdadf7.okhttp;

public interface HttpStructure {
    <T> HttpRequest<T> makeRequest(
            HttpRequest.Method method,
            String url,
            String requestBody,
            Class<T> responseType
    );
}
