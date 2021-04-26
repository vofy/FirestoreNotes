package tech.vofy.firestorenotes.ui.mainActivity.login;

import android.util.Log;

import androidx.databinding.BindingAdapter;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseAuth;

import java.util.concurrent.Executor;

import static android.service.controls.ControlsProviderService.TAG;

public class LoginViewModel extends ViewModel {

    private FirebaseAuth mAuth;

    @BindingAdapter("viewModel")
    private void login() {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener((Executor) this, task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "createUserWithEmail:success");
                        // TODO: updateUI(user);
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "createUserWithEmail:failure", task.getException());
                        // TODO: updateUI()
                    }
                });
    }

}