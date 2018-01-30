package com.alexeyosadchy.android.notes.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.alexeyosadchy.android.notes.R;

public class NoteActivity extends AppCompatActivity {

    public static final String EXTRA_KEY_TEXT_OF_NOTE = "com.alexeyosadchy.android.notes.TEXT";
    private static final String EXTRA_KEY_TRANSFER_NOTE = "com.alexeyosadchy.android.TRANSFER_NOTE";

    private NoteDetailFragment noteDetailFragment;

    public static Intent getCallingIntent(Context context, Note note) {
        Intent intent = new Intent(context, NoteActivity.class);
        intent.putExtra(EXTRA_KEY_TEXT_OF_NOTE, note);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_detail);
        Note note = (Note) getIntent().getParcelableExtra(EXTRA_KEY_TEXT_OF_NOTE);

        if (savedInstanceState == null) {
            noteDetailFragment = NoteDetailFragment.newInstance(note);
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.flDetailContainer, noteDetailFragment).commit();
        }
    }

    @Override
    public void onBackPressed() {
        if (noteDetailFragment == null) {
            noteDetailFragment = (NoteDetailFragment) getSupportFragmentManager().findFragmentById(R.id.flDetailContainer);
        }
        setResult(RESULT_OK, new Intent().putExtra(EXTRA_KEY_TRANSFER_NOTE, noteDetailFragment.saveNote()));
        finish();
        super.onBackPressed();
    }
}
