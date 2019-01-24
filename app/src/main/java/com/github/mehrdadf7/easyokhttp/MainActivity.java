package com.github.mehrdadf7.easyokhttp;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.github.mehrdadf7.okhttp.OnResultCallback;
import com.github.mehrdadf7.okhttp.okhttp.OkHttpRequest;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MainActivity extends AppCompatActivity implements OnResultCallback<ArticleList> {

    private ProgressBar progressBar;
    private AppCompatTextView status, totalArticles;
    private RecyclerView recyclerView;
    private final String TAG = this.getClass().getSimpleName();
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        init();
    }

    private void findViews() {
        progressBar   = findViewById(R.id.progressBar);
        status        = findViewById(R.id.status);
        totalArticles = findViewById(R.id.totalArticles);
        recyclerView  = findViewById(R.id.recyclerView);
    }

    private void init() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setNestedScrollingEnabled(false);

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {

            Drawable wrapDrawable = DrawableCompat.wrap(progressBar.getIndeterminateDrawable());
            DrawableCompat.setTint(wrapDrawable, ContextCompat.getColor(this, android.R.color.white));
            progressBar.setIndeterminateDrawable(DrawableCompat.unwrap(wrapDrawable));
        } else {
            progressBar.getIndeterminateDrawable().setColorFilter(ContextCompat.getColor(this, android.R.color.white), PorterDuff.Mode.SRC_IN);
        }

        apiService = new ApiService(this);
        apiService.getArticles(this);

    }

    @Override
    public void onReceived(final ArticleList articleList) {
        progressBar.setVisibility(View.GONE);
        Log.e(TAG, "onReceived: " + articleList.getStatus());
        Log.e(TAG, "onReceived: " + articleList.getTotalResults());
        status       .setText(articleList.getStatus());
        totalArticles.setText(articleList.getTotalResults()+"");
        recyclerView.setAdapter(new ArticleAdapter(articleList.getArticleList()));
    }

}
