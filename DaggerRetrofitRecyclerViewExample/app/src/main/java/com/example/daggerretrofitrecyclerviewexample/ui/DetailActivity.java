package com.example.daggerretrofitrecyclerviewexample.ui;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import com.example.daggerretrofitrecyclerviewexample.MyApplication;
import com.example.daggerretrofitrecyclerviewexample.R;
import com.example.daggerretrofitrecyclerviewexample.components.ApplicationComponent;
import com.example.daggerretrofitrecyclerviewexample.components.DaggerDetailActivityComponent;
import com.example.daggerretrofitrecyclerviewexample.components.DetailActivityComponent;
import com.example.daggerretrofitrecyclerviewexample.pojo.Film;
import com.example.daggerretrofitrecyclerviewexample.qualifiers.ApplicationContext;
import com.example.daggerretrofitrecyclerviewexample.retrofit.APIInterface;

import javax.inject.Inject;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {

    DetailActivityComponent detailActivityComponent;

    @Inject
    APIInterface apiInterface;

    @Inject
    @ApplicationContext
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        final TextView textView = findViewById(R.id.textView);

        String url = getIntent().getStringExtra("url");

        ApplicationComponent component = MyApplication.get(this).getApplicationComponent();

        detailActivityComponent = DaggerDetailActivityComponent.builder()
                .applicationComponent(component)
                .build();

        detailActivityComponent.injectDetailActivity(this);

        apiInterface.getFilmData(url, "json").enqueue(new Callback<Film>() {
            @Override
            public void onResponse(Call<Film> call, Response<Film> response) {
                Film film = response.body();
                String data = "Title : " + film.title + " Director : " + film.director;
                textView.setText(data);
            }

            @Override
            public void onFailure(Call<Film> call, Throwable t) {

            }
        });
    }
}