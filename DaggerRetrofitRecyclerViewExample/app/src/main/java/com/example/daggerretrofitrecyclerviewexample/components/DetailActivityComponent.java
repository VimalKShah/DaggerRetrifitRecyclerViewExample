package com.example.daggerretrofitrecyclerviewexample.components;

import com.example.daggerretrofitrecyclerviewexample.scopes.ActivityScope;
import com.example.daggerretrofitrecyclerviewexample.ui.DetailActivity;

import dagger.Component;

@ActivityScope
@Component(dependencies = {ApplicationComponent.class})
public interface DetailActivityComponent {

    void injectDetailActivity(DetailActivity detailActivity);

}
