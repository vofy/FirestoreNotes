package tech.vofy.firestorenotes.ui.mainActivity.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.firestorenotes.R;
import com.example.firestorenotes.databinding.HomeFragmentBinding;

public class HomeFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        HomeFragmentBinding binding = DataBindingUtil.inflate(inflater, R.layout.home_fragment, container,false);
        new ViewModelProvider(this).get(HomeViewModel.class);

        return binding.getRoot();
    }
}