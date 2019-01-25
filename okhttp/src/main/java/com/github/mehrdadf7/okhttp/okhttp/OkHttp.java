package com.github.mehrdadf7.okhttp.okhttp;

import com.github.mehrdadf7.okhttp.HttpRequest;
import com.github.mehrdadf7.okhttp.HttpStructure;

import org.json.JSONObject;

import java.util.HashMap;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

public class OkHttp implements HttpStructure {

    protected static OkHttpClient okHttpClient;

    public OkHttp() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();
    }

    @Override
    public <T> HttpRequest<T> makeRequest(String url, Class<T> responseType) {
        return new OkHttpRequest<>(HttpRequest.Method.GET, url, "", responseType);
    }

    @Override
    public <T> HttpRequest<T> makeRequest(HttpRequest.Method method, String url, Class<T> responseType) {
        return new OkHttpRequest<>(method, url, "", responseType);
    }

    @Override
    public <T> HttpRequest<T> makeRequest(HttpRequest.Method method, String url, HashMap<String, String> requestBody, Class<T> responseType) {
        return new OkHttpRequest<>(method, url, String.valueOf(requestBody), responseType);
    }

    @Override
    public <T> HttpRequest<T> makeRequest(HttpRequest.Method method, String url, JSONObject requestBody, Class<T> responseType) {
        return new OkHttpRequest<>(method, url, requestBody.toString(), responseType);
    }

    @Override
    public <T> HttpRequest<T> makeRequest(HttpRequest.Method method, String url, String requestBody, Class<T> responseType) {
        return new OkHttpRequest<>(method, url, requestBody, responseType);
    }

}
