package tech.vofy.firestorenotes.ui.mainActivity;

import androidx.lifecycle.LiveData;

import java.util.List;

import tech.vofy.firestorenotes.objects.Note;

public interface NotesDao{
    void AddNote(Note note);
    void DeleteNote(int id);
    void UpdateNote(int id, Note note);
    Note GetNote(int id);
    LiveData<List<Note>> GetAllNotes();
    LiveData<String> getUserDisplayName();
    void UserSignIn(String email, String password);
    void UserSignOut();
}