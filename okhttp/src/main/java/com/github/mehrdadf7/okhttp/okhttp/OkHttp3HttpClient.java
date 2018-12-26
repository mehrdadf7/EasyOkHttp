package com.github.mehrdadf7.okhttp.okhttp;

import com.github.mehrdadf7.okhttp.HttpClient;
import com.github.mehrdadf7.okhttp.HttpRequest;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

public class OkHttp3HttpClient implements HttpClient {

    protected static OkHttpClient okHttpClient;

    public OkHttp3HttpClient() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();
    }

    @Override
    public <T> HttpRequest<T> makeRequest(HttpRequest.Method method, String url,
                                          String requestBody, Class<T> responseType) {
        return new OkHttpRequest<>(method, url, requestBody, responseType);
    }

}
