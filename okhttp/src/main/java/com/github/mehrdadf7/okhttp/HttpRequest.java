package com.github.mehrdadf7.okhttp;

import android.app.Activity;

import com.github.mehrdadf7.okhttp.okhttp.OkHttpRequest;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;

public abstract class HttpRequest<T> {

    protected Method method;
    protected String url;
    protected String requestBody;
    protected Class<T> responseType;
    protected State state;

    public HttpRequest(Method method, String url, String requestBody, Class<T> responseType) {
        this.method = method;
        this.url = url;
        this.requestBody = requestBody;
        this.responseType = responseType;
    }

    public abstract void send(Activity activity, OnResultCallback<T> onResultCallback);
    public abstract Observable<T> send();

    public abstract void cancel();

    protected void updateState(State state) {
        this.state = state;
    }

    public boolean isSent() {
        return state == State.SENT;
    }

    public enum Method {
        GET, POST
    }

    public enum State {
        SENT, PENDING, ERROR, SUCCESS, CANCELED
    }

}
