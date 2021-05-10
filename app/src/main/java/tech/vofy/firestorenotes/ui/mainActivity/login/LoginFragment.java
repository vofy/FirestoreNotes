package tech.vofy.firestorenotes.ui.mainActivity.login;

import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.firestorenotes.R;
import com.example.firestorenotes.databinding.LoginFragmentBinding;
import com.example.firestorenotes.databinding.RecoverPasswordDialogBinding;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputLayout;
import com.google.protobuf.Any;

import tech.vofy.firestorenotes.NotesDaoFirestore;

public class LoginFragment extends Fragment {

    private ViewGroup container;
    private LoginFragmentBinding binding;
    private LoginViewModel mViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.login_fragment, container, false);
        mViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        mViewModel.recoverPassword.observe(this.getViewLifecycleOwner(), any -> buildRecoverPasswordDialog());
        mViewModel.successfulLogin.observe(this.getViewLifecycleOwner(), any -> onSuccessfulLogin());

        binding.setViewmodel(mViewModel);
        this.container = container;

        return binding.getRoot();
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public void buildRecoverPasswordDialog() {
        MaterialAlertDialogBuilder recoverPasswordDialog = new MaterialAlertDialogBuilder(this.getContext());
        RecoverPasswordDialogBinding recoverPasswordDialogBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.recover_password_dialog, null, false);

        recoverPasswordDialog
                .setView(recoverPasswordDialogBinding.getRoot())
                .setPositiveButton("Recover", (dialog, which) -> NotesDaoFirestore.getInstance().userRecoverPassword(mViewModel.email.getValue()))
                .show();
    }

    public void onSuccessfulLogin() {

    }
}