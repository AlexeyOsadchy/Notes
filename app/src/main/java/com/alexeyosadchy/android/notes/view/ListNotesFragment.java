package com.alexeyosadchy.android.notes.view;

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

public class ListNotesFragment extends Fragment {

    private FloatingActionButton mFloatingActionButton;
    private RecyclerView mNotesRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;
    private NoteAdapter mNoteAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_notes_list, container, false);



        return v;
    }
}
