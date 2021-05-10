package tech.vofy.firestorenotes.ui.mainActivity.login;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseAuth;
import com.google.protobuf.Any;

import tech.vofy.firestorenotes.NotesDao;
import tech.vofy.firestorenotes.NotesDaoFirestore;
import tech.vofy.firestorenotes.SingleLiveEvent;

public class LoginViewModel extends ViewModel {

    private final NotesDao mDatabase = NotesDaoFirestore.getInstance();

    public MutableLiveData<String> email = new MutableLiveData<>();
    public MutableLiveData<String> password = new MutableLiveData<>();
    public SingleLiveEvent<Any> recoverPassword = new SingleLiveEvent<>();
    public SingleLiveEvent<Any> successfulLogin = new SingleLiveEvent<>();

    public void login() {
        mDatabase.userSignIn(email.getValue(), password.getValue());
        successfulLogin.call();

        Log.d("email", email.getValue());
    }

    public void onRecoverPasswordClick() {
        recoverPassword.call();
    }
}