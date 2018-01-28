package com.alexeyosadchy.android.notes.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alexeyosadchy.android.notes.R;
import com.alexeyosadchy.android.notes.view.adapter.NoteAdapter;

import java.util.ArrayList;
import java.util.List;

public class ListNotesFragment extends Fragment {

    private FloatingActionButton mFloatingActionButton;
    private RecyclerView mNotesRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;
    private NoteAdapter mNoteAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_notes_list, container, false);

        mFloatingActionButton = (FloatingActionButton) v.findViewById(R.id.button_add);
        mNotesRecyclerView = (RecyclerView) v.findViewById(R.id.recycler_view_notes);

        List<Note> list = new ArrayList<>();
        list.add(new Note(1, "ds sdfdsf"));
        list.add(new Note(2, "3  g32g2 3"));
        list.add(new Note(3, "ds syu r jrt  er gsf"));
        list.add(new Note(4, "ds s65 43dsf"));
        list.add(new Note(5, "ds sd232535325 2"));
        list.add(new Note(6, "ds s346 3 34sf"));
        //prepareView(list);

        return v;
    }

    private OnItemSelectedListener listener;

    public interface OnItemSelectedListener {
        void onLongClick(int position);
        void onClick(int position);
        void onFabClick();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof OnItemSelectedListener) {
            listener = (OnItemSelectedListener) activity;
        } else {
            throw new ClassCastException(activity.toString()
                    + " must implement ItemsListFragment.OnItemSelectedListener");
        }
    }

    public void updateList(List<Note> notes, int position) {
        mNoteAdapter.notifyItemChanged(position);
        mNoteAdapter.notifyDataSetChanged();
    }

    public void updateList(int position) {
        mNoteAdapter.notifyItemRemoved(position);
    }

    public void updateList() {
        mNoteAdapter.notifyDataSetChanged();
    }

    public void openNote(Note note) {
        //navigator.navigateToNoteScreen(this, REQUEST_CODE_EDIT_NOTE, note);
    }

    public void prepareView(List<Note> notes) {

        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //mPresenter.onClickFloatingActionBtn();
                listener.onFabClick();
            }
        });

        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mNoteAdapter = new NoteAdapter(notes, listener);
        mNotesRecyclerView.setLayoutManager(mLinearLayoutManager);
        mNotesRecyclerView.setAdapter(mNoteAdapter);
    }
}
