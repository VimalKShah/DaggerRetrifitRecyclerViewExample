package com.example.daggerretrofitrecyclerviewexample;

import android.app.Activity;
import android.app.Application;

import com.example.daggerretrofitrecyclerviewexample.components.ApplicationComponent;
import com.example.daggerretrofitrecyclerviewexample.components.DaggerApplicationComponent;
import com.example.daggerretrofitrecyclerviewexample.module.ContextModule;

public class MyApplication extends Application {

    ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        applicationComponent = DaggerApplicationComponent.builder()
                .contextModule(new ContextModule(this))
                .build();
        applicationComponent.inject(this);
    }

    public static MyApplication get(Activity activity) {
        return (MyApplication) activity.getApplication();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
