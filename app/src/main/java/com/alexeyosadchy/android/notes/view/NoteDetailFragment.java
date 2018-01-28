package com.alexeyosadchy.android.notes.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.alexeyosadchy.android.notes.R;

public class NoteDetailFragment extends Fragment {

    private Note note;
    EditText editText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        note = (Note) getArguments().getParcelable("item");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_note_detail, container, false);

        editText = (EditText) v.findViewById(R.id.editText_note);
        editText.setText(note.getDescription());
        editText.setSelection(note.getDescription().length());

        return v;
    }

    public static NoteDetailFragment newInstance(Note note) {
        NoteDetailFragment fragmentDemo = new NoteDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("item", note);
        fragmentDemo.setArguments(args);
        return fragmentDemo;
    }
}