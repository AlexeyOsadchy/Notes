package com.alexeyosadchy.android.notes.di.module;

import android.content.Context;

import com.alexeyosadchy.android.notes.App;
import com.alexeyosadchy.android.notes.di.ApplicationContext;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    private Context mContext;

    public ApplicationModule(App context) {
        this.mContext = context;
    }

    @Singleton
    @Provides
    @ApplicationContext
    Context provideAppContext() {
        return mContext;
    }
}
