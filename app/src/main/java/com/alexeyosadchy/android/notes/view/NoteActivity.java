package com.alexeyosadchy.android.notes.view;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.alexeyosadchy.android.notes.R;

import butterknife.BindView;

public class NoteActivity extends AppCompatActivity {

    public static final String EXTRA_KEY_TEXT_OF_NOTE = "com.alexeyosadchy.android.notes.TEXT";
    private static final String EXTRA_KEY_TRANSFER_NOTE = "com.alexeyosadchy.android.TRANSFER_NOTE";
    //@BindView(R.id.editText_note)
    EditText editText;
    Note note;

    public static Intent getCallingIntent(Context context, Note note) {
        Intent intent = new Intent(context, NoteActivity.class);
        intent.putExtra(EXTRA_KEY_TEXT_OF_NOTE, note);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        note = (Note)getIntent().getParcelableExtra(EXTRA_KEY_TEXT_OF_NOTE);
        editText = (EditText) findViewById(R.id.editText_note);
        editText.setText(note.getDescription());
        editText.setSelection(note.getDescription().length());
    }

    @Override
    public void onBackPressed() {
        note.setDescription(editText.getText().toString());
        setResult(RESULT_OK, new Intent().putExtra(EXTRA_KEY_TRANSFER_NOTE, note));
        finish();
        super.onBackPressed();
    }


}
