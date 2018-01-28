package com.alexeyosadchy.android.notes.di.module;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;

import com.alexeyosadchy.android.notes.di.ActivityContext;
import com.alexeyosadchy.android.notes.di.ApplicationContext;
import com.alexeyosadchy.android.notes.di.PerActivity;
import com.alexeyosadchy.android.notes.model.DaoMaster;
import com.alexeyosadchy.android.notes.model.DaoSession;
import com.alexeyosadchy.android.notes.model.RepositoryNotes;
import com.alexeyosadchy.android.notes.model.RepositoryNotesImpl;
import com.alexeyosadchy.android.notes.presenter.ListNotesPresenter;
import com.alexeyosadchy.android.notes.presenter.ListNotesPresenterImpl;
import com.alexeyosadchy.android.notes.view.ListNotesActivityMvp;

import org.greenrobot.greendao.database.Database;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module
public class ActivityModule {

    private FragmentActivity mActivity;

    public ActivityModule(FragmentActivity mActivity) {
        this.mActivity = mActivity;
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return mActivity;
    }

    @Provides
    DaoSession provideGreenDao(@ApplicationContext Context context) {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, "notes-db");
        Database db = helper.getWritableDb();
        return new DaoMaster(db).newSession();
    }

    @Provides
    RepositoryNotes provideRepositoryNotes(DaoSession daoSession) {
        return new RepositoryNotesImpl(daoSession);
    }

    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    @PerActivity
    ListNotesPresenter<ListNotesActivityMvp> provideListNotesPresenter(
            ListNotesPresenterImpl<ListNotesActivityMvp> presenter) {
        return presenter;
    }
}
