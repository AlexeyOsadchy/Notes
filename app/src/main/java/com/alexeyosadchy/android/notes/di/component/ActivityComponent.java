package com.alexeyosadchy.android.notes.di.component;

import com.alexeyosadchy.android.notes.di.PerActivity;
import com.alexeyosadchy.android.notes.di.module.ActivityModule;
import com.alexeyosadchy.android.notes.view.ListNotesActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(ListNotesActivity activity);
}
