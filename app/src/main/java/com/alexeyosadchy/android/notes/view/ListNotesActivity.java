package com.alexeyosadchy.android.notes.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;

import com.alexeyosadchy.android.notes.App;
import com.alexeyosadchy.android.notes.R;
import com.alexeyosadchy.android.notes.di.component.ActivityComponent;
import com.alexeyosadchy.android.notes.di.component.DaggerActivityComponent;
import com.alexeyosadchy.android.notes.di.module.ActivityModule;
import com.alexeyosadchy.android.notes.presenter.ListNotesPresenter;
import com.alexeyosadchy.android.notes.view.navigation.Navigator;

import java.util.List;

import javax.inject.Inject;

public class ListNotesActivity extends FragmentActivity implements ListNotesActivityMvp, ListNotesFragment.OnItemSelectedListener, NoteDetailFragment.Callback {

    private static final int REQUEST_CODE_EDIT_NOTE = 1001;
    private static final String EXTRA_KEY_TRANSFER_NOTE = "com.alexeyosadchy.android.TRANSFER_NOTE";

    @Inject
    public Navigator navigator;
    protected ActivityComponent mActivityComponent;

    private boolean isTwoPane = false;

    ListNotesFragment listNotesFragment;
    NoteDetailFragment noteDetailFragment;
    @Inject
    ListNotesPresenter<ListNotesActivityMvp> mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);

        mActivityComponent = DaggerActivityComponent.builder()
                .applicationComponent(((App) getApplication()).getApplicationComponent())
                .activityModule(new ActivityModule(this))
                .build();
        mActivityComponent.inject(this);
        listNotesFragment =
                (ListNotesFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentItemsList);
        determinePaneLayout();
        init();
    }

    @Override
    public void noteUpdate(Note note) {

    }

    @Override
    public void prepareView(List<Note> notes) {
        listNotesFragment.prepareView(notes);
    }

    @Override
    public void onLongClick(int position) {
        mPresenter.onLongClickNote(position);
    }

    @Override
    public void onClick(int position) {
        mPresenter.onClickNote(position);
    }

    @Override
    public void onFabClick() {
        mPresenter.onClickFloatingActionBtn();
    }

    private void determinePaneLayout() {
        FrameLayout fragmentItemDetail = (FrameLayout) findViewById(R.id.flDetailContainer);
        if (fragmentItemDetail != null) {
            isTwoPane = true;
        }
    }

    private void init() {
        mPresenter.onAttach(this);
        mPresenter.onInitialisation();
    }

    @Override
    public void updateList(List<Note> notes, int position) {
        listNotesFragment.updateList(notes, position);
    }

    @Override
    public void updateList(int position) {
        listNotesFragment.updateList(position);
    }

    @Override
    public void updateList() {
        listNotesFragment.updateList();
    }

    @Override
    public void openNote(Note note) {
        if (isTwoPane) {
            if(noteDetailFragment != null){
                mPresenter.onActivityResult(noteDetailFragment.saveNote());
            }
            noteDetailFragment = NoteDetailFragment.newInstance(note);
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.flDetailContainer, noteDetailFragment);
            ft.commit();
        } else {
            navigator.navigateToNoteScreen(this, REQUEST_CODE_EDIT_NOTE, note);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_EDIT_NOTE && resultCode == RESULT_OK) {
            mPresenter.onActivityResult(data.getParcelableExtra(EXTRA_KEY_TRANSFER_NOTE));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDetach();
    }
}
