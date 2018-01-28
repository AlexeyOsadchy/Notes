package com.alexeyosadchy.android.notes.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;

import com.alexeyosadchy.android.notes.App;
import com.alexeyosadchy.android.notes.R;
import com.alexeyosadchy.android.notes.di.component.ActivityComponent;
import com.alexeyosadchy.android.notes.di.component.DaggerActivityComponent;
import com.alexeyosadchy.android.notes.di.module.ActivityModule;
import com.alexeyosadchy.android.notes.presenter.ListNotesPresenter;
import com.alexeyosadchy.android.notes.view.adapter.NoteAdapter;
import com.alexeyosadchy.android.notes.view.navigation.Navigator;

import java.util.List;

import javax.inject.Inject;

public class ListNotesActivity extends FragmentActivity implements ListNotesActivityMvp {

    private static final int REQUEST_CODE_EDIT_NOTE = 1001;
    private static final String EXTRA_KEY_TRANSFER_NOTE = "com.alexeyosadchy.android.TRANSFER_NOTE";

    @Inject
    public Navigator navigator;
    protected ActivityComponent mActivityComponent;

    private FloatingActionButton mFloatingActionButton;
    private RecyclerView mNotesRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;
    private NoteAdapter mNoteAdapter;

    private boolean isTwoPane = false;

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
        determinePaneLayout();
        //init();
    }

    private void determinePaneLayout() {
        FrameLayout fragmentItemDetail = (FrameLayout) findViewById(R.id.flDetailContainer);
        if (fragmentItemDetail != null) {
            isTwoPane = true;
            ListNotesFragment listNotesFragment =
                    (ListNotesFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentItemsList);
            //listNotesFragment.setActivateOnItemClick(true);
        }
    }

    private void init() {
        mPresenter.onAttach(this);
        mPresenter.onInitialisation();
    }

    @Override
    public void updateList(List<Note> notes, int position) {
        mNoteAdapter.notifyItemChanged(position);
        mNoteAdapter.notifyDataSetChanged();
    }

    @Override
    public void updateList(int position) {
        mNoteAdapter.notifyItemRemoved(position);
    }

    @Override
    public void updateList() {
        mNoteAdapter.notifyDataSetChanged();
    }

    @Override
    public void prepareView(List<Note> notes) {
        mFloatingActionButton = (FloatingActionButton) findViewById(R.id.button_add);
        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.onClickFloatingActionBtn();
            }
        });
        mNotesRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_notes);
        mLinearLayoutManager = new LinearLayoutManager(this);
        mNoteAdapter = new NoteAdapter(notes, mPresenter);
        mNotesRecyclerView.setLayoutManager(mLinearLayoutManager);
        mNotesRecyclerView.setAdapter(mNoteAdapter);
    }

    @Override
    public void openNote(Note note) {
        navigator.navigateToNoteScreen(this, REQUEST_CODE_EDIT_NOTE, note);
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

//    FragmentManager fm = getSupportFragmentManager();
//    Fragment fragment = fm.findFragmentById(R.id.fragment_container);
//        if (fragment == null) {
//        fragment = createFragment();
//        fm.beginTransaction()
//                .add(R.id.fragment_container, fragment)
//                .commit();
//    }
}
