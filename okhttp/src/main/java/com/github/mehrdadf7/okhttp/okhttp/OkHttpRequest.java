package com.github.mehrdadf7.okhttp.okhttp;

import android.app.Activity;
import android.util.Log;

import com.github.mehrdadf7.okhttp.HttpRequest;
import com.github.mehrdadf7.okhttp.OnResultCallback;
import com.google.gson.Gson;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttpRequest<T> extends HttpRequest<T> {

    private Request request;
    private Call call;
    private String TAG = getClass().getSimpleName();

    OkHttpRequest(HttpRequest.Method method, String url, String requestBody, Class<T> responseType) {
        super(method, url, requestBody, responseType);
        state = State.PENDING;
    }

    @Override
    public void send(final Activity activity, final OnResultCallback<T> onResultCallback) {
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        OkHttpClient okHttpClient = OkHttp.okHttpClient;
                        MediaType contentType = MediaType.parse("application/json; charset=utf-8");
                        RequestBody json = RequestBody.create(contentType, requestBody);

                        if (method == Method.GET) {
                            request = new Request.Builder()
                                    .url(url)
                                    .build();
                        } else if (method == Method.POST) {
                            request = new Request.Builder()
                                    .url(url)
                                    .post(json)
                                    .build();
                        }
                        call = okHttpClient.newCall(request);
                        state = State.SENT;
                        Log.e(TAG,"Request is sending to: " + request.url());

                        try {
                            Response response = call.execute();
                            state = State.SUCCESS;
                            Gson gson = new Gson();
                            final T res = gson.fromJson(response.body().string(), responseType);
                            activity.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    onResultCallback.onReceived(res);
                                }
                            });
                        } catch (Exception e) {
                            state = State.ERROR;
                            Log.e(TAG,"1. onError: " + e.toString());
                            activity.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    onResultCallback.onError();
                                }
                            });
                        }
                    }
                }
        ).start();
    }

    @Override
    public Observable<T> send() {
        return Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(ObservableEmitter<T> emitter) throws Exception {
                OkHttpClient okHttpClient = OkHttp.okHttpClient;

                MediaType contentType = MediaType.parse("application/json; charset=utf-8");
                RequestBody json = RequestBody.create(contentType, requestBody);

                if (method == Method.GET) {
                    request = new Request.Builder()
                            .url(url)
                            .build();
                } else if (method == Method.POST) {
                    request = new Request.Builder()
                            .url(url)
                            .post(json)
                            .build();
                }

                call = okHttpClient.newCall(request);
                state = State.SENT;
                Log.e(TAG,"Request is sending to: " + request.url());
                Response response = call.execute();
                state = State.SUCCESS;
                try {
                    Gson gson = new Gson();
                    T res = gson.fromJson(response.body().string(), responseType);
                    emitter.onNext(res);
                    emitter.onComplete();
                } catch (Exception e) {
                    state = State.ERROR;
                    Log.e(TAG,"2.onError: " + e.toString());
                    emitter.onError(e);
                }
            }
        });
    }

    @Override
    public void cancel() {
        Log.e(TAG,"onCancel: The request has been canceled");
        call.cancel();
        state = State.CANCELED;
    }

}
