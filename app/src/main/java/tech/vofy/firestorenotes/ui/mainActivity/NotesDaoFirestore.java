package tech.vofy.firestorenotes.ui.mainActivity;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import tech.vofy.firestorenotes.objects.Note;

class NotesDaoFirestore implements NotesDao {

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
    public void AddNote(Note note) {
        mDatabase.collection("users")
                .document(mAuth.getUid())
                .collection("notes")
                .add(note);
    }

    @Override
    public void DeleteNote(int id) {
        mDatabase.collection("users")
                .document(mAuth.getUid())
                .collection("notes")
                .document(String.valueOf(id))
                .delete();
    }

    @Override
    public void UpdateNote(int id, Note note) {
        mDatabase.collection("users")
                .document(mAuth.getUid())
                .collection("notes")
                .document(String.valueOf(id))
                .update(note.getHashMap());
    }

    @Override
    public Note GetNote(int id) {
        AtomicReference<Note> note = null;

        mDatabase.collection("users")
                .document(mAuth.getUid())
                .collection("notes")
                .document(String.valueOf(id)).get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        DocumentSnapshot documentSnapshot = task.getResult();
                        if(documentSnapshot != null) {
                            note.set(documentSnapshot.toObject(Note.class));
                        }
                    }
                });

        return note.get();
    }

    @Override
    public LiveData<List<Note>> GetAllNotes() {
        List<Note> notes = new ArrayList<>();

        mDatabase.collection("users")
                .document(mAuth.getUid())
                .collection("notes")
                .addSnapshotListener((value, error) -> {
                    for (int i = 0; i < value.getDocuments().size(); i++) {
                        notes.add(value.getDocuments().get(i).toObject(Note.class));
                    }
        });

        return new MutableLiveData<>(notes);
    }

    @Override
    public LiveData<String> getUserDisplayName() {
        return new MutableLiveData<>(mAuth.getCurrentUser().getDisplayName());
    }

    @Override
    public void UserSignIn(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password);
    }

    @Override
    public void UserSignOut() {
        mAuth.signOut();
    }
}
