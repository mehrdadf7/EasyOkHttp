package com.github.mehrdadf7.easyokhttp;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.github.mehrdadf7.okhttp.OnResultCallback;

public class MainActivity extends AppCompatActivity implements OnResultCallback<ArticleList> {

    private ProgressBar progressBar;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        init();
    }

    private void findViews() {
        progressBar  = findViewById(R.id.progressBar);
        recyclerView = findViewById(R.id.recyclerView);
    }

    private void init() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setNestedScrollingEnabled(false);

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            Drawable wrapDrawable = DrawableCompat.wrap(progressBar.getIndeterminateDrawable());
            DrawableCompat.setTint(wrapDrawable, ContextCompat.getColor(this, android.R.color.white));
            progressBar.setIndeterminateDrawable(DrawableCompat.unwrap(wrapDrawable));
        } else {
            progressBar.getIndeterminateDrawable().setColorFilter(ContextCompat
                    .getColor(this, android.R.color.white), PorterDuff.Mode.SRC_IN);
        }

        ApiService apiService = new ApiService();
        apiService.getArticles(this, this);
    }

    @Override
    public void onReceived(ArticleList articleList) {
        progressBar.setVisibility(View.GONE);
        recyclerView.setAdapter(new ArticleAdapter(articleList.getArticleList()));
    }

    @Override
    public void onError() {
        progressBar.setVisibility(View.GONE);
        Toast.makeText(this, "check internet", Toast.LENGTH_SHORT).show();
    }

}
