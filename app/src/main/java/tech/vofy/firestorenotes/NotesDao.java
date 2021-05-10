package tech.vofy.firestorenotes;

import androidx.lifecycle.LiveData;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tech.vofy.firestorenotes.objects.Note;

public interface NotesDao{
    void addNote(Note note);
    void deleteNote(int id);
    void updateNote(int id, Note note);
    HashMap<String, Note> getNote(int id);
    LiveData<Map<String, Note>> getAllNotes();
    LiveData<String> getUserDisplayName();
    LiveData<String> getUserEmail();
    void userSignIn(String email, String password);
    void userSignOut();
    void userRecoverPassword(String email);
}