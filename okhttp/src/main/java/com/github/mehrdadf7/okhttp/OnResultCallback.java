package com.github.mehrdadf7.okhttp;

public interface OnResultCallback<T> {
    void onReceived(T t);
    void onError();
}
