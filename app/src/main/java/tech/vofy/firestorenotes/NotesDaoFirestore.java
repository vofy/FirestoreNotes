package tech.vofy.firestorenotes;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;

import tech.vofy.firestorenotes.objects.Note;

public class NotesDaoFirestore implements NotesDao {

    private static NotesDaoFirestore instance;

    private final FirebaseAuth mAuth;
    private final FirebaseFirestore mDatabase;

    public static NotesDaoFirestore getInstance() {
        if(instance == null)
            instance = new NotesDaoFirestore();

        return instance;
    }

    private NotesDaoFirestore() {
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseFirestore.getInstance();
    }

    @Override
    public void addNote(Note note) {
        if(mAuth.getCurrentUser() != null)
            mDatabase.collection("users")
                    .document(mAuth.getCurrentUser().getUid())
                    .collection("notes")
                    .add(note);
    }

    @Override
    public void deleteNote(int id) {
        if(mAuth.getCurrentUser() != null)
            mDatabase.collection("users")
                    .document(mAuth.getCurrentUser().getUid())
                    .collection("notes")
                    .document(String.valueOf(id))
                    .delete();
    }

    @Override
    public void updateNote(int id, Note note) {
        if(mAuth.getCurrentUser() != null)
            mDatabase.collection("users")
                    .document(mAuth.getCurrentUser().getUid())
                    .collection("notes")
                    .document(String.valueOf(id))
                    .update(note.getHashMap());
    }

    @Override
    public HashMap<String, Note> getNote(int id) {
        HashMap<String, Note> note = new HashMap<>();

        if(mAuth.getCurrentUser() != null) {
            mDatabase.collection("users")
                    .document(mAuth.getCurrentUser().getUid())
                    .collection("notes")
                    .document(String.valueOf(id)).get()
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            DocumentSnapshot documentSnapshot = task.getResult();
                            if (documentSnapshot != null) {
                                note.put(documentSnapshot.getId(), documentSnapshot.toObject(Note.class));
                            }
                        }
                    });
        }

        return note;
    }

    @Override
    public LiveData<Map<String,Note>> getAllNotes() {
        MutableLiveData<Map<String, Note>> notes = new MutableLiveData<>();

        if(mAuth.getCurrentUser() != null) {
            mDatabase.collection("users")
                    .document(mAuth.getCurrentUser().getUid())
                    .collection("notes")
                    .addSnapshotListener((value, error) -> {
                        if(value != null) {
                            Map<String, Note> notesMap = new HashMap<>();
                            for (QueryDocumentSnapshot document : value) {
                                notesMap.put(document.getId(), document.toObject(Note.class));
                                Log.d("frbs", document.getId() + " => " + document.getData());
                            }
                            notes.postValue(notesMap);
                        }
                    });
        }

        return notes;
    }

    @Override
    public LiveData<String> getUserDisplayName() {
        MutableLiveData<String> userDisplayName = new MutableLiveData<>();

        if(mAuth.getCurrentUser() != null)
            userDisplayName.setValue(mAuth.getCurrentUser().getDisplayName());

        return userDisplayName;
    }

    @Override
    public LiveData<String> getUserEmail() {
        MutableLiveData<String> userEmail = new MutableLiveData<>();

        if(mAuth.getCurrentUser() != null)
            userEmail.setValue(mAuth.getCurrentUser().getEmail());

        return userEmail;
    }

    @Override
    public void userSignIn(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password);
    }

    @Override
    public void userSignOut() {
        mAuth.signOut();
    }

    @Override
    public void userRecoverPassword(String email) {
        mAuth.sendPasswordResetEmail(email);
    }
}
