package com.example.daggerretrofitrecyclerviewexample.components;

import android.content.Context;

import com.example.daggerretrofitrecyclerviewexample.MyApplication;
import com.example.daggerretrofitrecyclerviewexample.module.ContextModule;
import com.example.daggerretrofitrecyclerviewexample.module.RetrofitModule;
import com.example.daggerretrofitrecyclerviewexample.qualifiers.ApplicationContext;
import com.example.daggerretrofitrecyclerviewexample.retrofit.APIInterface;
import com.example.daggerretrofitrecyclerviewexample.scopes.ApplicationScope;

import dagger.Component;

@ApplicationScope
@Component(modules = {ContextModule.class, RetrofitModule.class})
public interface ApplicationComponent {
    
    public APIInterface getApiInterface();

    @ApplicationContext
    public Context getContext();

    public void inject(MyApplication application);
}
