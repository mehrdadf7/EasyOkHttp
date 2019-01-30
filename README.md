# EasyOkHttp
Fast and easy RESTful api request for android that parse response by gson and it using: 
[OkHttp](https://square.github.io/okhttp)
and
[RxJava-RxAndroid](https://github.com/ReactiveX/RxAndroid)
and
[Gson](https://github.com/google/gson)

<p align="center">
  <img width="300" height="550" src="https://github.com/mehrdadf7/EasyOkHttp/blob/master/screen_shot0.png">
  <img width="300" height="550" src="https://github.com/mehrdadf7/EasyOkHttp/blob/master/screen_shot.png">
</p>

# Quick Setup
add EasyOkHttp to dependencies :
```java
implementation 'github.mehrdadf7:okhttp:1.1.7'
```
### min api = 15

Don't forget Connect your phone to internet :) 
and notice jcenter() is added :
```java
repositories {
        jcenter()
    }
```

# How to use

#### AndroidManifest.xml :
```xml
<uses-permission android:name="android.permission.INTERNET" />
```

#### activity or fragment :
```java
OkHttpInjector.getHttpClient().makeRequest(
      HttpRequest.Method method, //GET, POST
      String url, 
      String requestBody, //JsonObject requestBody
      Class<T> responseType //ClassModel.class
  ).send().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread())
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
                
/*************** OR ******************/    

OkHttpInjector.getHttpClient().makeRequest(
      HttpRequest.Method method, //GET, POST
      String url, 
      String requestBody, 
      Class<T> responseType //ClassModel.class
  ).send(activity, new OnResultCallback<T>() {
            @Override
            public void onReceived(T t) {
                  //response(t)
            }
            @Override
            public void onError() {
              //handle error
              Toast.makeText(this, "check internet", Toast.LENGTH_SHORT).show();
            }
        });                

```

### JAVA
```java
// Observer<T> and Observable<T> in 'io.reactivex' package name
public void requestName(Observer<ClassModel> observer) {
                OkHttpInjector.getHttpClient().makeRequest(
                        HttpRequest.Method.GET, //HttpRequest.Method.POST
                        url,
                        "", //jsonObject
                        ClassModel.class
                ).send().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread())
                .subscribe(observer);
    }
```
### KOTLIN
```kotlin
fun requestName(observer: Observer<ClassModel>) {
        OkHttpInjector.getHttpClient()?.makeRequest(
            HttpRequest.Method.GET, //HttpRequest.Method.POST
            url,
            "", //jsonObject
            ClassModel::class.java
        )?.send()?.observeOn(AndroidSchedulers.mainThread())?.subscribeOn(Schedulers.newThread())
            ?.subscribe(observer)
    }
```
