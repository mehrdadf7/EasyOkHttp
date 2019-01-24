# EasyOkHttp
Fast and easy RESTful api calls for android and it using: 
[OkHttp](https://square.github.io/okhttp)
and
[RxJava-RxAndroid](https://github.com/ReactiveX/RxAndroid)

![screen_shot](https://github.com/mehrdadf7/EasyOkHttp/blob/master/screen_shot.png)

# Quick Setup
add EasyOkHttp to dependencies :
```java
implementation 'github.mehrdadf7:okhttp:1.0.3'
```
add RxJava-RxAndroid to dependencies :
```kotlin
implementation 'io.reactivex.rxjava2:rxandroid:2.1.0'
implementation 'io.reactivex.rxjava2:rxjava:2.2.0'
```

# Options
      . send request
      . cancel request
      . is sending request
      . GET, POST request

# How to use

#### AndroidManifest.xml :
```java
<uses-permission android:name="android.permission.INTERNET" />
```

#### activity or fragment :
```java
Observable<T> observable = OkHttpInjector.getHttpClient().makeRequest(
      HttpRequest.Method method, //GET, POST
      String url, 
      String requestBody, 
      Class<T> responseType //ClassModel.class
  ).send();
  
  observable
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Observer<T>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        // pre-request
                    }

                    @Override
                    public void onNext(T t) {
                      //response(t)
                    }

                    @Override
                    public void onError(Throwable e) {
                      //handle error
                    }

                    @Override
                    public void onComplete() {
                      //complete-request
                    }
                });
```

### JAVA
```java
// Observer<T> and Observable<T> in 'io.reactivex' package name
public void requestName(Observer<ClassModel> observer) {
        Observable<ClassModel> observable =
                OkHttpInjector.getHttpClient().makeRequest(
                        HttpRequest.Method.GET, //HttpRequest.Method.POST
                        url,
                        "", //jsonObject.toString()
                        ClassModel.class
                ).send();
        observable
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(observer);
    }
```
### KOTLIN
```kotlin
fun requestName(observer: Observer<ClassModel>) {
        val observable = OkHttpInjector.getHttpClient()?.makeRequest(
            HttpRequest.Method.GET, //HttpRequest.Method.POST
            url,
            "", //jsonObject.toString()
            ClassModel::class.java
        )?.send()
        observable
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribeOn(Schedulers.newThread())
            ?.subscribe(observer)
    }
```
