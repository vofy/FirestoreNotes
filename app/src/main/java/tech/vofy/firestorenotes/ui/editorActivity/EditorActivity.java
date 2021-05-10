package tech.vofy.firestorenotes.ui.editorActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.firestorenotes.R;

import tech.vofy.firestorenotes.ui.editorActivity.noteEditor.NoteEditorFragment;

public class EditorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editor_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, NoteEditorFragment.newInstance())
                    .commitNow();
        }
    }
}