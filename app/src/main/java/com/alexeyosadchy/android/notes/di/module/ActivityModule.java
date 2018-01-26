package com.alexeyosadchy.android.notes.di.module;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.alexeyosadchy.android.notes.di.ActivityContext;
import com.alexeyosadchy.android.notes.di.PerActivity;
import com.alexeyosadchy.android.notes.presenter.ListNotesPresenter;
import com.alexeyosadchy.android.notes.presenter.ListNotesPresenterImpl;
import com.alexeyosadchy.android.notes.view.ListNotesActivityMvp;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {

    private AppCompatActivity mActivity;

    public ActivityModule(AppCompatActivity mActivity) {
        this.mActivity = mActivity;
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return mActivity;
    }

    @Provides
    @PerActivity
    ListNotesPresenter<ListNotesActivityMvp> provideListNotesPresenter(
            ListNotesPresenterImpl<ListNotesActivityMvp> presenter){
        return presenter;
    }
}
