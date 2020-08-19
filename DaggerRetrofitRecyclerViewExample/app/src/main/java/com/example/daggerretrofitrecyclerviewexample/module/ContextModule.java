package com.example.daggerretrofitrecyclerviewexample.module;

import android.content.Context;

import com.example.daggerretrofitrecyclerviewexample.qualifiers.ApplicationContext;
import com.example.daggerretrofitrecyclerviewexample.scopes.ApplicationScope;

import dagger.Module;
import dagger.Provides;

@Module
public class ContextModule {

    Context context;

    public ContextModule(Context context) {
        this.context = context;
    }

    @Provides
    @ApplicationContext
    @ApplicationScope
    public Context provideContext() {
        return context;
    }
}
