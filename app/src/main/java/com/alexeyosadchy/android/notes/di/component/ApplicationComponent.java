package com.alexeyosadchy.android.notes.di.component;

import android.content.Context;

import com.alexeyosadchy.android.notes.di.ApplicationContext;
import com.alexeyosadchy.android.notes.di.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    @ApplicationContext
    Context getContext();
}
