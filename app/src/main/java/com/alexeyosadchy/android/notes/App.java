package com.alexeyosadchy.android.notes;

import android.app.Application;

import com.alexeyosadchy.android.notes.di.component.ApplicationComponent;
import com.alexeyosadchy.android.notes.di.component.DaggerApplicationComponent;
import com.alexeyosadchy.android.notes.di.module.ApplicationModule;

public class App extends Application {

    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }

    public void setApplicationComponent(ApplicationComponent applicationComponent) {
        mApplicationComponent = applicationComponent;
    }
}
