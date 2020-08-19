package com.example.daggerretrofitrecyclerviewexample.module;

import com.example.daggerretrofitrecyclerviewexample.ui.MainActivity;
import com.example.daggerretrofitrecyclerviewexample.adapter.RecyclerViewAdapter;
import com.example.daggerretrofitrecyclerviewexample.scopes.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module(includes = {MainActivityContextModule.class})
public class AdapterModule {

    @Provides
    @ActivityScope
    public RecyclerViewAdapter getStarWarPeopleList(RecyclerViewAdapter.ClickListener clickListener) {
        return new RecyclerViewAdapter(clickListener);
    }

    @Provides
    @ActivityScope
    public RecyclerViewAdapter.ClickListener getClickListener(MainActivity mainActivity) {
        return mainActivity;
    }

}
