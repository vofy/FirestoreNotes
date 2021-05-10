package tech.vofy.firestorenotes.ui.mainActivity.notes;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import tech.vofy.firestorenotes.objects.Note;
import tech.vofy.firestorenotes.NotesDao;
import tech.vofy.firestorenotes.NotesDaoFirestore;

public class NotesViewModel extends ViewModel {
    private final NotesDao mDatabase = NotesDaoFirestore.getInstance();
    private final LiveData<Map<String, Note>> notes = mDatabase.getAllNotes();
}