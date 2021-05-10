package tech.vofy.firestorenotes.ui.mainActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.firestorenotes.R;
import com.example.firestorenotes.databinding.DrawerHeaderBinding;
import com.example.firestorenotes.databinding.MainActivityBinding;
import com.google.android.material.navigation.NavigationView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private MainActivityBinding binding;
    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new ViewModelProvider(this).get(MainViewModel.class);

        binding = DataBindingUtil.setContentView(this, R.layout.main_activity);
        binding.setLifecycleOwner(this);

        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, binding.drawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer);
        binding.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.drawer_nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.

        NavController navController = ((NavHostFragment) Objects.requireNonNull(getSupportFragmentManager()
                .findFragmentById(R.id.drawer_nav_host_fragment)))
                .getNavController();
        mAppBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        DrawerHeaderBinding drawerHeaderBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.drawer_header, binding.drawerNavView, false);
        binding.drawerNavView.addHeaderView(drawerHeaderBinding.getRoot());

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.nav_home) {
            binding.drawerLayout.openDrawer(GravityCompat.START);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        super.onSupportNavigateUp();
        NavController navController = Navigation.findNavController(this, R.id.drawer_nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration);
    }
}