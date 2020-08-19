package com.example.daggerretrofitrecyclerviewexample.components;

import android.content.Context;

import com.example.daggerretrofitrecyclerviewexample.ui.MainActivity;
import com.example.daggerretrofitrecyclerviewexample.module.AdapterModule;
import com.example.daggerretrofitrecyclerviewexample.qualifiers.ActivityContext;
import com.example.daggerretrofitrecyclerviewexample.scopes.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(modules = {AdapterModule.class}, dependencies = {ApplicationComponent.class})
public interface MainActivityComponent {

    @ActivityContext
    Context getContext();

    void injectMainActivity(MainActivity mainActivity);
}
