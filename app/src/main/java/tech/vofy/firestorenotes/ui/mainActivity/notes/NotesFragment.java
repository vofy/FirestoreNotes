package tech.vofy.firestorenotes.ui.mainActivity.notes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.firestorenotes.R;
import com.example.firestorenotes.databinding.NotesFragmentBinding;

public class NotesFragment extends Fragment {

    private NotesFragmentBinding binding;
    private NotesViewModel mViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = NotesFragmentBinding.inflate(getLayoutInflater());

        return inflater.inflate(R.layout.notes_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(NotesViewModel.class);
        // TODO: Use the ViewModel
    }

}