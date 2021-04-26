package tech.vofy.firestorenotes.ui.noteEditorActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.firestorenotes.R;

import tech.vofy.firestorenotes.ui.noteEditorActivity.noteEditor.NoteEditorFragment;

public class NoteEditor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_editor_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, NoteEditorFragment.newInstance())
                    .commitNow();
        }
    }
}