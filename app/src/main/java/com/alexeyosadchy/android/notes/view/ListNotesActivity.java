package com.alexeyosadchy.android.notes.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.alexeyosadchy.android.notes.App;
import com.alexeyosadchy.android.notes.R;
import com.alexeyosadchy.android.notes.di.component.ActivityComponent;
import com.alexeyosadchy.android.notes.di.component.DaggerActivityComponent;
import com.alexeyosadchy.android.notes.di.module.ActivityModule;
import com.alexeyosadchy.android.notes.presenter.ListNotesPresenter;

import javax.inject.Inject;

public class ListNotesActivity extends AppCompatActivity implements ListNotesActivityMvp {

    protected ActivityComponent mActivityComponent;

    @Inject
    ListNotesPresenter<ListNotesActivityMvp> mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_notes);

        mActivityComponent = DaggerActivityComponent.builder()
                .applicationComponent(((App) getApplication()).getApplicationComponent())
                .activityModule(new ActivityModule(this))
                .build();
        mActivityComponent.inject(this);
    }
}
