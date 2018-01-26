package com.alexeyosadchy.android.notes.presenter;

import com.alexeyosadchy.android.notes.view.ListNotesActivityMvp;

import javax.inject.Inject;

public class ListNotesPresenterImpl<V extends ListNotesActivityMvp>
        extends BasePresenterImpl<V> implements ListNotesPresenter<V> {

    @Inject
    public ListNotesPresenterImpl() {
        super();
    }
}
