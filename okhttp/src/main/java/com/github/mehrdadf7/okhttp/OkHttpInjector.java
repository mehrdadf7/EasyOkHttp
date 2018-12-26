package com.github.mehrdadf7.okhttp;

import com.github.mehrdadf7.okhttp.okhttp.OkHttp3HttpClient;

public class OkHttpInjector {

    private static HttpClient httpClient;

    public static HttpClient getHttpClient() {
        if (httpClient == null) {
            httpClient = new OkHttp3HttpClient();
        }
        return httpClient;
    }
}
