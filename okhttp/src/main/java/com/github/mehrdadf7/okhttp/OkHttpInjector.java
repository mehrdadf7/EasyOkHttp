package com.github.mehrdadf7.okhttp;

import com.github.mehrdadf7.okhttp.okhttp.OkHttp;

public class OkHttpInjector {

    private static HttpStructure httpClient;

    public static HttpStructure getHttpClient() {
        if (httpClient == null) {
            httpClient = new OkHttp();
        }
        return httpClient;
    }
}
