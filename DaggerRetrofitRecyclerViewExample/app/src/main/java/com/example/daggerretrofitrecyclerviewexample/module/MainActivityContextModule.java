package com.example.daggerretrofitrecyclerviewexample.module;

import android.content.Context;

import com.example.daggerretrofitrecyclerviewexample.ui.MainActivity;
import com.example.daggerretrofitrecyclerviewexample.qualifiers.ActivityContext;
import com.example.daggerretrofitrecyclerviewexample.scopes.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityContextModule {

    MainActivity mainActivity;
    Context context;

    public MainActivityContextModule(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        context = mainActivity;
    }

    @Provides
    @ActivityContext
    @ActivityScope
    public Context provideContext() {
        return context;
    }

    @Provides
    @ActivityScope
    public MainActivity provideMainActivity() {
        return mainActivity;
    }
}
