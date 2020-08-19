package com.example.daggerretrofitrecyclerviewexample.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.daggerretrofitrecyclerviewexample.MyApplication;
import com.example.daggerretrofitrecyclerviewexample.R;
import com.example.daggerretrofitrecyclerviewexample.adapter.RecyclerViewAdapter;
import com.example.daggerretrofitrecyclerviewexample.components.ApplicationComponent;
import com.example.daggerretrofitrecyclerviewexample.components.DaggerMainActivityComponent;
import com.example.daggerretrofitrecyclerviewexample.components.MainActivityComponent;
import com.example.daggerretrofitrecyclerviewexample.module.MainActivityContextModule;
import com.example.daggerretrofitrecyclerviewexample.pojo.StarWars;
import com.example.daggerretrofitrecyclerviewexample.qualifiers.ActivityContext;
import com.example.daggerretrofitrecyclerviewexample.qualifiers.ApplicationContext;
import com.example.daggerretrofitrecyclerviewexample.retrofit.APIInterface;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements RecyclerViewAdapter.ClickListener {

    @Inject
    RecyclerViewAdapter recyclerViewAdapter;
    @Inject
    APIInterface apiInterface;
    @Inject
    @ApplicationContext
    Context context;
    @Inject
    @ActivityContext
    Context activityContext;

    RecyclerView recyclerView;
    MainActivityComponent mainActivityComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ApplicationComponent applicationComponent = MyApplication.get(this).getApplicationComponent();

        mainActivityComponent = DaggerMainActivityComponent.builder()
                .applicationComponent(applicationComponent)
                .mainActivityContextModule(new MainActivityContextModule(this))
                .build();

        mainActivityComponent.injectMainActivity(this);
        recyclerView.setAdapter(recyclerViewAdapter);

        apiInterface.getPeople("json").enqueue(new Callback<StarWars>() {
            @Override
            public void onResponse(Call<StarWars> call, Response<StarWars> response) {
                recyclerViewAdapter.setData(response.body().peopleList);
            }

            @Override
            public void onFailure(Call<StarWars> call, Throwable t) {

            }
        });
    }

    @Override
    public void launchIntent(String filename) {
        Toast.makeText(context, "Row Selected", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra("url", filename);
        startActivity(intent);
    }
}