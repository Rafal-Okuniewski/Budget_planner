package com.finanteq.recruitment.di;

import com.finanteq.recruitment.ui.activity.MainActivity;
import com.finanteq.recruitment.di.main.MainFragmentBuildersModule;
import com.finanteq.recruitment.di.main.MainModule;
import com.finanteq.recruitment.di.main.MainViewModelsModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(
            modules = {MainFragmentBuildersModule.class, MainViewModelsModule.class, MainModule.class})
    abstract MainActivity contributeMainActivity();

}
