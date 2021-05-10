package tech.vofy.firestorenotes.ui.mainActivity.notes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firestorenotes.R;
import com.example.firestorenotes.databinding.NotesFragmentBinding;

public class NotesFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        com.example.firestorenotes.databinding.NotesFragmentBinding binding = NotesFragmentBinding.inflate(inflater);

        NotesRecyclerViewAdapter notesRecyclerViewAdapter = new NotesRecyclerViewAdapter(getContext());

        RecyclerView recyclerView = binding.notesRecyclerView;
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(notesRecyclerViewAdapter);

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        NotesViewModel mViewModel = new ViewModelProvider(this).get(NotesViewModel.class);
    }

}