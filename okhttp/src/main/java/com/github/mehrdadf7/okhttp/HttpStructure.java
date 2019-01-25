package com.github.mehrdadf7.okhttp;

import org.json.JSONObject;

import java.util.HashMap;

public interface HttpStructure {

    <T> HttpRequest<T> makeRequest(String url, Class<T> responseType);

    <T> HttpRequest<T> makeRequest(HttpRequest.Method method, String url, Class<T> responseType);

    <T> HttpRequest<T> makeRequest(HttpRequest.Method method, String url, HashMap<String, String> requestBody, Class<T> responseType);

    <T> HttpRequest<T> makeRequest(HttpRequest.Method method, String url, JSONObject requestBody, Class<T> responseType);

    <T> HttpRequest<T> makeRequest(HttpRequest.Method method, String url, String requestBody, Class<T> responseType);

}
