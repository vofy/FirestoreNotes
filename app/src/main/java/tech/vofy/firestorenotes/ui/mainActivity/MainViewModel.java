package tech.vofy.firestorenotes.ui.mainActivity;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import tech.vofy.firestorenotes.NotesDao;
import tech.vofy.firestorenotes.NotesDaoFirestore;

public class MainViewModel extends ViewModel {
    public LiveData<String> email;
    public MutableLiveData<String> displayName = new MutableLiveData<>();

    public MainViewModel() {
        NotesDao mDatabase = NotesDaoFirestore.getInstance();
        email = mDatabase.getUserEmail();
        //mDatabase.getUserEmail().observeForever(email -> this.email.setValue(email));
        /*mDatabase.getUserDisplayName().observeForever(displayName -> this.displayName.setValue(displayName));*/
        displayName.setValue(mDatabase.getUserEmail().getValue());
        Log.d("user-frbs", displayName.getValue());
    }
}
