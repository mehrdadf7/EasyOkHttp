package com.github.mehrdadf7.okhttp.okhttp;

import com.github.mehrdadf7.okhttp.HttpStructure;
import com.github.mehrdadf7.okhttp.HttpRequest;

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
    public <T> HttpRequest<T> makeRequest(HttpRequest.Method method, String url,
                                          String requestBody, Class<T> responseType) {
        return new OkHttpRequest<>(method, url, requestBody, responseType);
    }

}
