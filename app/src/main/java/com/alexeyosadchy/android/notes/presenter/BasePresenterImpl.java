package com.alexeyosadchy.android.notes.presenter;

import com.alexeyosadchy.android.notes.view.MvpView;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class BasePresenterImpl<V extends MvpView> implements BasePresenter<V> {

    protected V mMvpView;
    private CompositeDisposable mCompositeDisposable;

    @Inject
    public BasePresenterImpl(CompositeDisposable compositeDisposable) {
        mCompositeDisposable = compositeDisposable;
    }

    @Override
    public void onAttach(V mvpView) {
        mMvpView = mvpView;
    }

    @Override
    public void onDetach() {
        mMvpView = null;
        mCompositeDisposable.dispose();
    }

    @Override
    public V getMvpView() {
        return mMvpView;
    }

    public CompositeDisposable getCompositeDisposable() {
        return mCompositeDisposable;
    }
}
